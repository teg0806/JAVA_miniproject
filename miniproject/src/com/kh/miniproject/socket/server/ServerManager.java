package com.kh.miniproject.socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.kh.miniproject.handler.ClientHandler;

public class ServerManager {
	
	private static ServerManager manager = new ServerManager();
    // 서버 포트는 여기서 관리해. 바꾸고 싶으면 이 숫자만 바꾸면 돼.
    private static final int PORT = 9999;
    private List<ClientHandler> clients = new ArrayList<>();
    private JTextArea logArea;
    
    public ServerManager() {
		super();
	}

    public static ServerManager getManager() {
        return manager;
    }
    
	public void startServer() {
        // 서버는 GUI랑 별개로 돌아가야 하니까 새로운 스레드를 만들어서 실행
		//실시간으로 처리하기 위해 스윙 스레드와 별도의 스레드를 추가 생성
        new Thread(new Runnable() { //Runnable 익명클래스 
            @Override
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                    System.out.println("서버: 실행 중... (포트: " + PORT + ")");

                    // 서버는 죽지 않기 위해 무한 루프로 계속 클라이언트의 접속 대기
                    while (true) {
                        Socket clientSocket = serverSocket.accept(); // 클라이언트가 접속하면 통신용 소켓을 만들기
                        System.out.println("서버: 정상 실행 완료! (" + clientSocket.getInetAddress() + ")");
                        ClientHandler handler = new ClientHandler(clientSocket, ServerManager.this); // ClientHandler 생성
                        clients.add(handler); // 리스트에 추가
                        handler.start();
                        
                    }

                } catch (IOException e) {
                	System.err.println("서버: 오류가 발생했습니다.");
                    e.printStackTrace();
				}
            }
        }).start();
    }
    
    // ChatMenu가 자신의 채팅창(JTextArea)을 여기에 등록할 수 있도록 하는 메소드
    public void setLogArea(JTextArea logArea) {
        this.logArea = logArea;
    }

    // 서버 GUI의 채팅창에 메시지를 추가하는 메소드 (스레드 충돌 방지를 위해 SwingUtilities 사용)
    private void appendLogToGui(String message) {
        if (logArea != null) {
            SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
        }
    }

    public void broadcast(String message) {
    	//콘솔에 채팅 출력
        appendLogToGui(message); // 모든 클라이언트에게 보내는 메시지는 서버 GUI에 표시
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }
}
