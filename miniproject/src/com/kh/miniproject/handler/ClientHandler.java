package com.kh.miniproject.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.kh.miniproject.service.MemberService;
import com.kh.miniproject.socket.server.ServerManager;
import com.kh.miniproject.vo.Member;


//서버쪽에서, 접속한 클라이언트 한 명과 통신을 담당할 클래스 (직원 클래스)
//클라이언트가 접속할 때마다 이 클래스의 객체가 하나씩 생성(스레드)
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
			// 통신을 위한 스트림 생성
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new PrintWriter(clientSocket.getOutputStream(), true);

			String request;
			// 클라이언트가 종료될 때까지 반복
			while ((request = in.readLine()) != null) {
				//콘솔에 채팅 출력
//				serverManager.broadcast("서버 <- " + (userNickName != null ? userNickName : "클라이언트") + ": " + request);
             
				// 클라이언트의 요청을 분석해서 처리
				processRequest(request);
			}

		} catch (IOException e) {
			//닉네임이 출력
			if (userNickName != null) {
				serverManager.broadcast(userNickName + "님과의 연결이 끊어졌습니다.");
	        } else {
	            // 로그인 전에 접속이 끊기면 IP로 표시
	        	serverManager.broadcast("클라이언트(" + clientSocket.getInetAddress() + ")와의 연결이 끊어졌습니다.");
	        }
		} finally {
			try {
				clientSocket.close(); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 클라이언트의 요청을 분석하고 처리하는 메소드
    private void processRequest(String request) {
        // String을 : 로 나눈 것을 다시 분리
    	String[] parts = request.split(":");
        String command = parts[0]; //첫번째 배열이 Login인지 Join인지 판별
        
     // 로그인 요청 처리
        if ("LOGIN".equals(command) && parts.length == 3) { // LOGIN:아이디:비번
            Member m = new Member(parts[1], parts[2]); //로그인할 아이디와 패스워드를 Member에 저장
            Member loginUser = memberService.memberIdSearch(m); //memberService에 member 객체를 전달 후 반환
            
            if (loginUser != null) { //sql문을 실행했고, 그값이 null이 아닌 경우 
                this.userNickName = loginUser.getUserNickName(); //닉네임을 추출
                sendMessage("LOGIN_SUCCESS:" + this.userNickName); // 클라이언트에 성공 응답 전송
                serverManager.broadcast(this.userNickName + "님이 입장하셨습니다."); //TextArea에 출력
            } else { //sql문으로 검색했지만 null인경우
                sendMessage("LOGIN_FAIL"); // 실패 응답 전송
            }
        } 
        // 회원가입 요청을 처리하는 로직을 추가
        else if ("JOIN".equals(command) && parts.length == 7) {
            Member m = new Member(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
            int result = memberService.insertMember(m);
            
            if (result == 1) {
                sendMessage("JOIN_SUCCESS");
            } else if (result == -1) {
                sendMessage("JOIN_FAIL_DUPLICATE"); // 중복 실패 신호!
            } else {
                sendMessage("JOIN_FAIL");
            }
        }
        else { 
            if (this.userNickName != null) { // 로그인이 된 사용자만 채팅 가능
                serverManager.broadcast(this.userNickName + ": " + request);
            }
        }
    }
	
    // ServerManager가 이 메소드를 호출해서 클라이언트에게 메시지를 보냄
    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }
    
    public String getUserNickName() {
        return userNickName;
    }
}
