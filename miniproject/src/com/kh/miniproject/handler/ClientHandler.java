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
	private String userNickName;

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
				//콘솔에 채팅 출력
//				System.out.println("서버 <- " + (userNickName != null ? userNickName : "클라이언트") + ": " + request);
             
				// 여기서 클라이언트의 요청을 분석해서 처리해야 해.
				processRequest(request);
			}

		} catch (IOException e) {
			//닉네임이 출력
			if (userNickName != null) {
	            System.out.println(userNickName + "님과의 연결이 끊어졌습니다.");
	        } else {
	            // 로그인 전에 접속이 끊기면 IP로 표시
	            System.out.println("클라이언트(" + clientSocket.getInetAddress() + ")와의 연결이 끊어졌습니다.");
	        }
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
        // "명령어:내용" 형식으로 오니까 ':'를 기준으로 잘라보자.
    	String[] parts = request.split(":");
        String command = parts[0];
        
     // 로그인 요청 처리
        if ("LOGIN".equals(command) && parts.length == 3) { // LOGIN:아이디:비번
            Member m = new Member(parts[1], parts[2]);
            Member loginUser = memberService.memberIdSearch(m);
            
            if (loginUser != null) {
                this.userNickName = loginUser.getUserNickName();
                sendMessage("LOGIN_SUCCESS:" + this.userNickName); // 클라이언트에 성공 응답 전송
                serverManager.broadcast(this.userNickName + "님이 입장하셨습니다.");
            } else {
                sendMessage("LOGIN_FAIL"); // 실패 응답 전송
            }
        } 
        // 회원가입 요청을 처리하는 로직을 추가
        else if ("JOIN".equals(command) && parts.length == 7) { // JOIN:아이디:비번:이름:성별:닉네임:이메일
            // 클라이언트가 보낸 정보를 순서대로 Member 객체에 담는다.
            Member m = new Member(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
            int result = memberService.insertMember(m);
            
            if (result > 0) {
                sendMessage("JOIN_SUCCESS"); // 성공했다고 클라이언트에게 알려준다.
            } else {
                sendMessage("JOIN_FAIL"); // 실패했다고 알려준다.
            }
        }
        // 일반 채팅 메시지 처리
        else { 
            if (this.userNickName != null) { // 로그인이 된 사용자만 채팅 가능
                serverManager.broadcast(this.userNickName + ": " + request);
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
