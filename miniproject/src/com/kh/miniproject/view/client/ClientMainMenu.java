package com.kh.miniproject.view.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kh.miniproject.common.ButtonPanelTamplate;
import com.kh.miniproject.sokect.client.ClientManager;


public class ClientMainMenu extends JPanel{
    private static final long serialVersionUID = 1L;
    
    private ClientManager clientManager; // ClientManager 멤버 변수 추가
    
    public ClientMainMenu(ClientMainFrame frame) {

        // 네트워크 관리자(NetworkManager)가 서버에 접속을 시도
        this.clientManager = new ClientManager(frame); // frame을 넘겨주도록 수정!
        clientManager.connectToServer();
        //전체 레이아웃 설정
        setLayout(new BorderLayout());
        
        //상단 패널 추가
        add(createTitlePanel(), BorderLayout.NORTH);
        
        //중단 패널 추가
        add(createCenterPanel(frame), BorderLayout.CENTER);
        
        //하단 패널 추가
        add(craeteExitPanel(frame), BorderLayout.SOUTH);
    }
    
    //상단 패널
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel mainLabel = new JLabel("채팅");
        mainLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        titlePanel.add(mainLabel);
        return titlePanel;
    }
    
    //중단 패널
    private JPanel createCenterPanel(ClientMainFrame frame) {
        //엑셀처럼 생긴 Grid는 row, col 값에 맞게 생성
        JPanel verticalButtonPanel = new JPanel(new GridLayout(2, 1, 0, 15)); //2개의 행, 1개의 열
        
        //자바 8버전 이전에는 익명클래스로 사용했지만, 람다식으로 인해 e 라는 객체를 받아 -> 를 이용해 다음에 올 코드를 실행하는 구조 
        //createButton는 버튼 텍스트, 기능을 담는 메서드로 다음 화면을 넘기는 기능을 전달하여 버튼을 생성하고 다시 불러와 패널에 추가되는 구조
        verticalButtonPanel.add(ButtonPanelTamplate.createButton("로그인", e -> frame.changePanel(new ClientLoginMenu(frame, clientManager))));
        verticalButtonPanel.add(ButtonPanelTamplate.createButton("회원가입", e -> frame.changePanel(new ClientJoinMenu(frame, clientManager))));

        JPanel wrapperPanel = new JPanel(new GridBagLayout()); //grid를 담는 gridbag
        wrapperPanel.add(verticalButtonPanel); // 만든 버튼들을 다시 담음

        return wrapperPanel;
    }

    //하단 패널
    private JPanel craeteExitPanel(ClientMainFrame frame) {
    	//마찬가지로 changePanel이 아닌 시스템 종료 기능을 보내고 버튼을 만들어 다시 반환
        return ButtonPanelTamplate.createButtonPanel("종료", e -> System.exit(0));
    }
}


