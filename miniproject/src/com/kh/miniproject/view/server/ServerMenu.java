package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.kh.miniproject.common.ViewUtils;
import com.kh.miniproject.vo.Member;

public class ServerMenu extends JPanel {
    private static final long serialVersionUID = 1L;

    public ServerMenu(MainFrame frame, Member member) {
        setLayout(new BorderLayout());
        // 버튼 패널을 가운데 정렬하기 위한 래퍼 패널
        JPanel centerPanel = new JPanel(new GridBagLayout());
        
        // 상단 환영 메시지
        add(welcomeLabel(member), BorderLayout.NORTH);

        // 중앙 버튼 메뉴
        centerPanel.add(menuPanel(frame, member));
        add(centerPanel, BorderLayout.CENTER);
        
        //하단 뒤로가기 버튼
        add(craeteLogoutPanel(frame, member), BorderLayout.SOUTH);

    }
    
    private JLabel welcomeLabel(Member member) {
        // 상단 환영 메시지
        JLabel welcomeLabel = new JLabel(member.getUserNickName() + "님, 환영합니다!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        return welcomeLabel;
    }
    
    private JPanel menuPanel(MainFrame frame, Member member) {
        // 중앙 버튼 메뉴
        JPanel menuPanel = new JPanel(new GridLayout(2, 1, 10, 10)); // 버튼이 2개니까 GridLayout 행 수정
    	menuPanel.add(ViewUtils.createButton("회원 관리", e -> frame.changePanel(new ManagementMenu(frame, member))));
        menuPanel.add(ViewUtils.createButton("서버 관리", e -> frame.changePanel(new ChatMenu(frame, member))));
        return menuPanel;
    }
    
    private JPanel craeteLogoutPanel(MainFrame frame, Member member) {
        return ViewUtils.createButtonPanel("로그아웃", e -> frame.changePanel(new MainMenu(frame)));
    }
}


