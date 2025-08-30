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

    // --- 수정된 부분 ---
    // 생성자에서 userRole 인자를 받지 않습니다.
    public MainMenu(MainFrame frame) {
        setLayout(new BorderLayout());

        add(createTitlePanel(), BorderLayout.NORTH);
        add(createCenterButtonPanel(frame), BorderLayout.CENTER);
        add(createBackButtonPanel(frame), BorderLayout.SOUTH);
    }
    
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel mainLabel = new JLabel("메인 메뉴");
        mainLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        titlePanel.add(mainLabel);
        return titlePanel;
    }

    private JPanel createCenterButtonPanel(MainFrame frame) {
        JPanel verticalButtonPanel = new JPanel(new GridLayout(2, 1, 0, 15));
        JButton loginBtn = new JButton("로그인");
        JButton joinBtn = new JButton("회원가입");
        
        loginBtn.setPreferredSize(new java.awt.Dimension(120, 30));
        joinBtn.setPreferredSize(new java.awt.Dimension(120, 30));

        verticalButtonPanel.add(loginBtn);
        verticalButtonPanel.add(joinBtn);

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(verticalButtonPanel);
        
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // LoginMenu로 이동
                frame.changePanel(new LoginMenu(frame));
            }
        });

        joinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // JoinMenu로 이동
                frame.changePanel(new JoinMenu(frame));
            }
        });

        return wrapperPanel;
    }

    private JPanel createBackButtonPanel(MainFrame frame) {
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backBtn = new JButton("이전으로");
        backBtn.setPreferredSize(new java.awt.Dimension(120, 30));
        backButtonPanel.add(backBtn);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // StartMenu로 이동
                frame.changePanel(new StartMenu(frame));
            }
        });

        return backButtonPanel;
    }
}
