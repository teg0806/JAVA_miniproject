package com.kh.miniproject.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.kh.miniproject.controller.ClientMemberController;

//서버쪽에서, 접속한 클라이언트 한 명과 통신을 담당할 클래스 (직원 클래스)
//클라이언트가 접속할 때마다 이 클래스의 객체가 하나씩 생성될 거야. (스레드로)
public class ClientHandler extends Thread {

	private Socket clientSocket; // 통신을 위한 소켓
	private ClientMemberController cmc;
	private BufferedReader in;   // 클라이언트로부터 메시지를 받을 빨대
	private PrintWriter out;     // 클라이언트에게 메시지를 보낼 빨대

	public ClientHandler(Socket socket, ClientMemberController controller) {
		this.clientSocket = socket;
		this.cmc = controller;
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

	// 클라이언트의 요청을 분석하고 처리하는 메소드
	private void processRequest(String request) {
	    // "LOGIN:user01:pass01" 같은 형식의 요청을 ':' 기준으로 쪼개.
		String[] parts = request.split(":");
	    String command = parts[0]; // 첫 번째 부분은 항상 명령어(LOGIN, JOIN 등)
	
	    switch (command) {
	        case "LOGIN":
	            // 여기에 로그인 처리 로직을 넣을 거야.
	        	// String userId = parts[1];
	            // String password = parts[2];
	            // Member result = mc.loginMember(new Member(userId, password));
	            // out.println("LOGIN_RESULT:" + (result != null ? "SUCCESS" : "FAIL"));
	            System.out.println("로그인 요청 받음 (아직 처리 로직 없음)");
	            break;
	        case "JOIN":
	            // 여기에 회원가입 처리 로직을 넣을 거야.
	            System.out.println("회원가입 요청 받음 (아직 처리 로직 없음)");
	            break;
	        default:
	            System.out.println("알 수 없는 요청입니다: " + command);
	            break;
	    }
	}
}
