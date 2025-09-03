package com.kh.miniproject.controller;

import com.kh.miniproject.vo.Member;
import com.kh.miniproject.sokect.client.ClientManager;
import com.kh.miniproject.view.client.ClientMainFrame; // 이건 예시야. 실제 프레임 클래스에 맞게 바꿔.

// 클라이언트의 "뇌" 역할을 하는 컨트롤러야.
// View(GUI)와 NetworkManager(통신) 사이를 중재하지.
public class ClientMemberController {

    private ClientManager clientManager; // 서버와 통신할 객체
    private ClientMainFrame frame;
    
    // 이제 ClientManager만 받으면 돼.
    public ClientMemberController(ClientMainFrame frame, ClientManager manager) {
    	this.frame = frame;
        this.clientManager = manager;
    }

    // ClientLoginMenu가 이 메소드를 호출
    public void clientLogin(Member m) {
        clientManager.sendLoginRequest(m);
    }

    // ClientJoinMenu가 이 메소드를 호출
    public void clientJoin(Member m) {
        clientManager.sendJoinRequest(m);
    }
}
