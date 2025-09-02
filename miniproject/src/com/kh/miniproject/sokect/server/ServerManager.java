package com.kh.miniproject.sokect.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerManager {

    // 서버 포트는 여기서 관리해. 바꾸고 싶으면 이 숫자만 바꾸면 돼.
    private static final int PORT = 9999;

    public void startServer() {
        // 서버는 GUI랑 별개로 돌아가야 하니까 새로운 스레드를 만들어서 실행해.
        // 안 그러면 서버가 클라이언트 기다리느라 GUI가 멈춰버린다고.
        new Thread(new Runnable() {
            @Override
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                    System.out.println("서버: 실행 중... (포트: " + PORT + ")");

                    // 서버는 죽으면 안 되니까 무한 루프로 계속 클라이언트의 접속을 기다려.
                    while (true) {
                        Socket clientSocket = serverSocket.accept(); // 클라이언트가 접속하면 통신용 소켓을 만들어줘.
                        System.out.println("서버: 정상 실행 완료! (" + clientSocket.getInetAddress() + ")");

                        // 여기에 나중에 클라이언트랑 1:1로 대화할 스레드를 만들어줄 거야.
                        // new ClientHandler(clientSocket, mc).start();
                    }

                } catch (IOException e) {
                    System.err.println("서버: 포트(" + PORT + ")와 IP를 확인해 주세요.");
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
