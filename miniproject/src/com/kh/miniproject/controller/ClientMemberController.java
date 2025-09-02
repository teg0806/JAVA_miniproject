package com.kh.miniproject.controller;

import com.kh.miniproject.vo.Member;
import com.kh.miniproject.sokect.client.ClientManager;
import com.kh.miniproject.view.client.ClientMainFrame; // 이건 예시야. 실제 프레임 클래스에 맞게 바꿔.

// 클라이언트의 "뇌" 역할을 하는 컨트롤러야.
// View(GUI)와 NetworkManager(통신) 사이를 중재하지.
public class ClientMemberController {

    private ClientManager networkManager; // 서버와 통신할 전화기
    private ClientMainFrame mainFrame;     // 화면을 바꿀 메인 프레임

    public ClientMemberController(ClientMainFrame frame, ClientManager manager) {
        this.mainFrame = frame;
        this.networkManager = manager;
    }

    // ClientLoginMenu가 이 메소드를 호출할 거야.
    public void ClientLogin(Member m) {
        // 자기가 직접 Service나 DAO를 부르는 게 아니야!
        // NetworkManager에게 "서버에 로그인 요청 보내!" 하고 시키기만 하는 거야.
        networkManager.sendLoginRequest(m);
        
        // 여기서 서버의 응답을 기다렸다가 화면을 전환하는 로직이 추가되어야 해.
        // (이건 나중에 구현할 부분)
    }

    // ClientJoinMenu가 이 메소드를 호출할 거야.
    public void clientJoin(Member m) {
        // 이것도 마찬가지! NetworkManager에게 일을 시킬 뿐이야.
        networkManager.sendJoinRequest(m);
        
        // (나중에 서버 응답에 따라 "회원가입 성공/실패" 메시지를 보여주는 로직 추가)
    }
}
