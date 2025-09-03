package com.kh.miniproject.sokect.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.kh.miniproject.handler.ClientHandler;

public class ServerManager {
	
	private static ServerManager manager = new ServerManager();
    // 서버 포트는 여기서 관리해. 바꾸고 싶으면 이 숫자만 바꾸면 돼.
    private static final int PORT = 9999;
    private List<ClientHandler> clients = new ArrayList<>();

    public ServerManager() {
		super();
	}
    
    public static ServerManager setInstance() {
        return manager;
    }
    
    public static ServerManager getInstance() {
        return manager;
    }
    
	public void startServer() {
        // 서버는 GUI랑 별개로 돌아가야 하니까 새로운 스레드를 만들어서 실행해.
        // 안 그러면 서버가 클라이언트 기다리느라 GUI가 멈춰버린다고.
        new Thread(new Runnable() { //Runnable 익명클래스 
            @Override
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                    System.out.println("서버: 실행 중... (포트: " + PORT + ")");

                    // 서버는 죽으면 안 되니까 무한 루프로 계속 클라이언트의 접속을 기다려.
                    while (true) {
                        Socket clientSocket = serverSocket.accept(); // 클라이언트가 접속하면 통신용 소켓을 만들어줘.
                        System.out.println("서버: 정상 실행 완료! (" + clientSocket.getInetAddress() + ")");
                        ClientHandler handler = new ClientHandler(clientSocket, ServerManager.this); // ClientHandler 생성
                        clients.add(handler); // 리스트에 추가
                        handler.start();
                        
                        // 여기에 나중에 클라이언트랑 1:1로 대화할 스레드를 만들어줄 거야.
                        // new ClientHandler(clientSocket, mc).start();
                    }

                } catch (IOException e) {
                    System.err.println("서버: 포트(" + PORT + ")와 IP를 확인해주세요.");
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void broadcast(String message) {
        System.out.println("서버 -> 모든 클라이언트: " + message);
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }
}
