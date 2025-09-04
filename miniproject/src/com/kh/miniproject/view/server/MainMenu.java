package com.kh.miniproject.view.server;

import com.kh.miniproject.common.ViewUtils;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;


public class MainMenu extends JPanel{
    private static final long serialVersionUID = 1L;

    public MainMenu(MainFrame frame) {
    	
        setLayout(new BorderLayout());

        add(createTitlePanel(), BorderLayout.NORTH);
        add(CenterButtonPanel(frame), BorderLayout.CENTER);
        add(BackButtonPanel(frame), BorderLayout.SOUTH);
    }
    
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel mainLabel = new JLabel("채팅");
        mainLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        titlePanel.add(mainLabel);
        return titlePanel;
    }

    private JPanel CenterButtonPanel(MainFrame frame) {
        JPanel verticalButtonPanel = new JPanel(new GridLayout(2, 1, 0, 15));

        verticalButtonPanel.add(ViewUtils.createButton("로그인", e -> frame.changePanel(new LoginMenu(frame))));
        verticalButtonPanel.add(ViewUtils.createButton("회원가입", e -> frame.changePanel(new JoinMenu(frame))));

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(verticalButtonPanel);

        return wrapperPanel;
    }

    private JPanel BackButtonPanel(MainFrame frame) {
    	JPanel backButtonPanel = ViewUtils.createButtonPanel("종료", e -> System.exit(0));

        return backButtonPanel;
    }
}
