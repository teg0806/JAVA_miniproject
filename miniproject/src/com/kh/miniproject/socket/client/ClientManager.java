package com.kh.miniproject.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.kh.miniproject.view.client.ClientChatMenu;
import com.kh.miniproject.view.client.ClientMainFrame;
import com.kh.miniproject.view.client.ClientMainMenu;
import com.kh.miniproject.vo.Member;

public class ClientManager {
    private static final String SERVER_IP = "127.0.0.1"; //로컬 호스팅
    private static final int PORT = 9999; //포트 번호
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private ClientMainFrame frame; // 화면 전환을 위한 메인 프레임
    private JTextArea chatArea;   // 채팅 메시지를 표시할 JTextArea
    private Member loginUser;     // 로그인 성공한 유저 정보

    // 생성자에서 메인 프레임을 받아오자.
    public ClientManager(ClientMainFrame frame) {
        this.frame = frame;
    }

    public void connectToServer() {
        new Thread(() -> {
            try {
                socket = new Socket(SERVER_IP, PORT);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("클라이언트: 서버 연결 성공!");

                //연결 성공시 실행
                listenToServer();

            } catch (IOException e) {
                System.err.println("클라이언트: 서버 문제로 연결이 끊어졌습니다.");
                showErrorDialog("서버 연결에 실패했습니다. 프로그램을 재시작해주세요.");
            }
        }).start(); //스레드 실행
    }
    
    //controller, service, dao 등은 sql과 연결되어 있는 부분이기에 보안을 생각 
    //클라이언트는 서버의 리소스에 직접적으로 연결할 수 없다고 생각
    //클라이언트가 로그인와 회원가입할때 서버에게 요청을 보내고 인증이 되면 승인이 되고 클라이언트에게 서버 접속할 권한을 부여하는 과정을 생각
    
    // 서버로부터 오는 모든 메시지를 처리하는 메소드
    private void listenToServer() {
        try {
            String serverMessage;
            //받은 메세지가 null이 아닐경우에만 실행
            while ((serverMessage = in.readLine()) != null) {
            	//콘솔에 채팅 이력 남기기
//                System.out.println("클라이언트 <- 서버: " + serverMessage);
                
            	//서버에서 받은 메세지를 : 중심으로 나눔
                String[] parts = serverMessage.split(":", 2);
                
                //첫번째 메시지에 따라 분할
                String command = parts[0];

                switch (command) {
                    case "LOGIN_SUCCESS":
                        // 로그인 성공시 서버가 보내준 닉네임으로 유저 정보 저장하고 채팅방으로 이동
                        String nickName = parts[1];
                        this.loginUser = new Member(); // 임시로 객체 생성, 원래는 ID도 받아와야 함
                        this.loginUser.setUserNickName(nickName);
                        
                        //외부에 존재하는 스레드들(스윙 스레드가 아닌 스레드들)이 내부 스윙 스레드(EDT)에게 기능들을 전달
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(frame, nickName + "님, 환영합니다!");
                            frame.changePanel(new ClientChatMenu(frame, loginUser, this));
                        });
                        break;
                    
                    case "LOGIN_FAIL":
                        showErrorDialog("아이디 또는 비밀번호가 일치하지 않습니다.");
                        break;
                    case "JOIN_SUCCESS":
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(frame, "회원가입 성공!");
                            frame.changePanel(new ClientMainMenu(frame)); // 메인 메뉴로 이동
                        });
                        break;

                    case "JOIN_FAIL":
                        showErrorDialog("회원가입에 실패. 아이디나 정보를 다시 확인해주세요.");
                        break;
                    default:
                        // 위에서 걸러지지 않은 메시지는 모두 채팅 메시지로 취급
                        if (chatArea != null) {
                            final String msg = serverMessage;
                            SwingUtilities.invokeLater(() -> chatArea.append(msg + "\n"));
                        }
                        break;
                }
            }
        } catch (IOException e) {
            showErrorDialog("서버와 연결이 끊어졌습니다.");
        }
    }

    // 서버로 메시지를 보내는 메소드
    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    // 채팅방 화면이 만들어지면, 메시지를 표시할 JTextArea를 여기에 등록
    public void setChatArea(JTextArea chatArea) {
        this.chatArea = chatArea;
    }
    
    // 에러 메시지 다이얼로그를 띄우는 메소드
    private void showErrorDialog(String message) {
        SwingUtilities.invokeLater(() -> {
             JOptionPane.showMessageDialog(frame, message, "오류", JOptionPane.ERROR_MESSAGE);
        });
    }
    
    public Socket getSocket() {
        return socket;
    }
}