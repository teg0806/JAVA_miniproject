package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.kh.miniproject.common.ButtonPanelTamplate;
import com.kh.miniproject.vo.Member;

public class ManagementMenu extends JPanel {

    private static final long serialVersionUID = 1L;

    public ManagementMenu(MainFrame frame, Member member) {
    	//전체 레이아웃 생성
        setLayout(new BorderLayout());
        
        //중단 패널 생성
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(createMemberMangementPanel(frame, member));
        
        //중단 패널에 추가
        add(wrapperPanel, BorderLayout.CENTER);
        
        //하단 패널에 추가
        add(craeteBackPanel(frame, member), BorderLayout.SOUTH);
    }
    
    private JPanel createMemberMangementPanel(MainFrame frame, Member member) {
    	//버튼 4개를 담을 grid레이아웃 패널 생성
    	JPanel menuPanel = new JPanel(new GridLayout(4, 1, 10, 10));
    	
    	//grid에 버튼들 추가
    	//같은 기능이 3개 이상일 경우 세번의 원칙으로 중복을 낮춰야 함.
    	//이부분은 컬렉션을 사용하여 처리할 예정.
        menuPanel.add(ButtonPanelTamplate.createButton("회원 추가", e -> frame.changePanel(new InsertMenu(frame, member))));
        menuPanel.add(ButtonPanelTamplate.createButton("회원 정보 수정", e -> frame.changePanel(new UpdateMenu(frame, member))));
        menuPanel.add(ButtonPanelTamplate.createButton("회원 탈퇴", e -> frame.changePanel(new DeleteMenu(frame, member))));
        menuPanel.add(ButtonPanelTamplate.createButton("회원 검색", e -> frame.changePanel(new SearchMenu(frame, member))));
        
        return menuPanel;
    }
    
    //하단 패널
    private JPanel craeteBackPanel(MainFrame frame, Member member) {
        return ButtonPanelTamplate.createButtonPanel("뒤로가기", e -> frame.changePanel(new ServerMenu(frame, member)));
    }
}
