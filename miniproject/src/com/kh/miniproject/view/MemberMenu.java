package com.kh.miniproject.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.kh.miniproject.vo.Member;

public class MemberMenu extends JPanel {
    private static final long serialVersionUID = 1L;

    public MemberMenu(MainFrame frame, Member member) {
        setLayout(new BorderLayout(20, 20));

        // 상단 환영 메시지
        JLabel welcomeLabel = new JLabel(member.getUserNickName() + "님, 환영합니다!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        add(welcomeLabel, BorderLayout.NORTH);

        // 중앙 버튼 메뉴
        JPanel menuPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // 버튼이 3개니까 GridLayout 행 수정
        
        JButton memberButton = new JButton("회원 관리");
        JButton chatButton = new JButton("채팅 서버 관리");
        JButton logoutButton = new JButton("로그아웃");

        menuPanel.add(memberButton);
        menuPanel.add(chatButton);
        menuPanel.add(logoutButton);

        // 버튼 패널을 가운데 정렬하기 위한 래퍼 패널
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(menuPanel);
        add(centerPanel, BorderLayout.CENTER);

        // --- 이벤트 리스너 ---
        
        // 회원 관리 버튼 (UpdateMenu로 바로 연결)
        memberButton.setPreferredSize(new java.awt.Dimension(120, 30));
        memberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changePanel(new ManagementMenu(frame, member));
            }
        });

        // 채팅 서버 관리 버튼
        chatButton.setPreferredSize(new java.awt.Dimension(120, 30));
        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changePanel(new ChatMenu(frame, member));
            }
        });
        
        // 로그아웃 버튼
        logoutButton.setPreferredSize(new java.awt.Dimension(120, 30));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 로그아웃 시에는 StartMenu로 돌아가야 해
                frame.changePanel(new MainMenu(frame)); 
            }
        });
    }
}


