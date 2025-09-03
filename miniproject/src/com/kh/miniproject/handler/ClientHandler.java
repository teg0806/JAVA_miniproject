package com.kh.miniproject.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Map;

import com.kh.miniproject.service.MemberService;
import com.kh.miniproject.sokect.server.ServerManager;
import com.kh.miniproject.vo.Member;


//서버쪽에서, 접속한 클라이언트 한 명과 통신을 담당할 클래스 (직원 클래스)
//클라이언트가 접속할 때마다 이 클래스의 객체가 하나씩 생성될 거야. (스레드로)
public class ClientHandler extends Thread {

	private Socket clientSocket;
	private ServerManager serverManager;
	private BufferedReader in;
	private PrintWriter out;
	private MemberService memberService = new MemberService();

	public ClientHandler(Socket clientSocket, ServerManager serverManager) {
		super();
		this.clientSocket = clientSocket;
		this.serverManager = serverManager;
	}

	@Override
	public void run() {
		try {
			// 통신을 위한 스트림(빨대)들을 여기서 만들어.
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new PrintWriter(clientSocket.getOutputStream(), true);

			String request;
			// 클라이언트가 접속을 끊을 때까지 계속 메시지를 읽어들여.
			while ((request = in.readLine()) != null) {
				System.out.println("서버 <- 클라이언트: " + request);
             
				// 여기서 클라이언트의 요청을 분석해서 처리해야 해.
				processRequest(request);
			}

		} catch (IOException e) {
			System.out.println("클라이언트(" + clientSocket.getInetAddress() + ")와의 연결이 끊어졌습니다.");
		} finally {
			try {
				clientSocket.close(); // 연결이 끊어지면 소켓을 꼭 닫아줘.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
    // ServerManager가 이 메소드를 호출해서 클라이언트에게 메시지를 보낸다.
    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }
 
}
