package com.kh.miniproject.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private static final long serialVersionUID = 1L;

    // 생성자에서 userRole을 받아야 관리자/사용자 구분이 가능해
    public MainMenu(MainFrame frame, String userRole) {
        setOpaque(false); // 배경이 보이도록 투명 처리
        setLayout(new BorderLayout());

        // 1. 상단에 "메인 메뉴" 타이틀 추가
        add(createTitlePanel(), BorderLayout.NORTH);

        // 2. 중앙에 로그인, 회원가입 버튼들 추가
        add(createCenterButtonPanel(frame), BorderLayout.CENTER);
        
        // 3. 하단에 "이전으로" 버튼 추가
        add(createBackButtonPanel(frame), BorderLayout.SOUTH);

        // TODO: userRole이 "ADMIN"일 경우에만 보이는 관리자 버튼 추가 로직
    }
    
    /**
     * "메인 메뉴" 타이틀 라벨을 담은 패널을 생성
     */
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setOpaque(false);

        JLabel mainLabel = new JLabel("메인 메뉴");
        mainLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        // 라벨에 직접 색상을 주려면 HTML 태그를 활용할 수 있어.
        mainLabel.setText("<html><font color='white'>메인 메뉴</font></html>");

        titlePanel.add(mainLabel);
        return titlePanel;
    }

    /**
     * 로그인, 회원가입 버튼을 세로로 묶어서 중앙에 배치하는 패널을 생성
     */
    private JPanel createCenterButtonPanel(MainFrame frame) {
        // 버튼들을 세로로 쌓을 패널
        JPanel verticalButtonPanel = new JPanel(new GridLayout(2, 1, 0, 15));
        verticalButtonPanel.setOpaque(false);

        JButton loginBtn = new JButton("로그인");
        JButton joinBtn = new JButton("회원가입");
        
        loginBtn.setPreferredSize(new java.awt.Dimension(120, 30));
        joinBtn.setPreferredSize(new java.awt.Dimension(120, 30));

        verticalButtonPanel.add(loginBtn);
        verticalButtonPanel.add(joinBtn);

        // verticalButtonPanel을 다시 감싸서 중앙에 배치할 포장지 패널
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setOpaque(false);
        wrapperPanel.add(verticalButtonPanel);
        
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changePanel(new LoginMenu(frame));
            }
        });

        joinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changePanel(new JoinMenu(frame));
            }
        });

        return wrapperPanel;
    }

    /**
     * "이전으로" 버튼을 담은 패널을 생성
     */
    private JPanel createBackButtonPanel(MainFrame frame) {
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backButtonPanel.setOpaque(false);
        
        JButton backBtn = new JButton("이전으로");
        backBtn.setPreferredSize(new java.awt.Dimension(120, 30));
        backButtonPanel.add(backBtn);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changePanel(new StartMenu(frame));
            }
        });

        return backButtonPanel;
    }
}
