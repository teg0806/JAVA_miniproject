package com.kh.miniproject.sokect.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class ClientManager {
	// 서버 주소랑 포트는 여기서 관리해.
    private static final String SERVER_IP = "127.0.0.1"; // "localhost"랑 똑같아. 네 컴퓨터 자신을 가리키지.
    private static final int PORT = 9999;
    private Socket socket; // 서버랑 연결된 소켓
    private PrintWriter out;
    
    public void connectToServer() {
        // 클라이언트도 GUI가 멈추면 안 되니까 스레드를 따로 만들어.
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("클라이언트: 서버에 접속 중... (" + SERVER_IP + ":" + PORT + ")");
                    socket = new Socket(SERVER_IP, PORT);
                    System.out.println("클라이언트: 연결 성공!");
                    
                    // 연결 성공했다는 메시지를 사용자에게 보여주는 것도 좋겠지.
                    // Swing GUI 관련 코드는 Event Dispatch Thread에서 돌리는 게 안전해.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(null, "서버에 연결되었습니다.");
                        }
                    });
                    
                } catch (IOException e) {
                    System.err.println("클라이언트: 서버 문제로 연결이 끊어졌습니다.");
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                             JOptionPane.showMessageDialog(null, "서버 연결에 실패했습니다.", "연결 오류", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                }
            }
        }).start();
    }
    
    // 나중에 이 소켓으로 메시지를 보내고 받을 거야.
    public Socket getSocket() {
        return socket;
    }

}
