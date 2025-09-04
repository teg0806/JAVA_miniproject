package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.kh.miniproject.common.ViewUtils;
import com.kh.miniproject.vo.Member;

public class ManagementMenu extends JPanel {

    private static final long serialVersionUID = 1L;

    public ManagementMenu(MainFrame frame, Member m) {
        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JPanel backBtnPanel = new JPanel(new BorderLayout());
        
        // ViewUtils.createButton(text, 버튼 기능)
        menuPanel.add(ViewUtils.createButton("회원 추가", e -> frame.changePanel(new InsertMenu(frame, m))));
        menuPanel.add(ViewUtils.createButton("회원 정보 수정", e -> frame.changePanel(new UpdateMenu(frame, m))));
        menuPanel.add(ViewUtils.createButton("회원 탈퇴", e -> frame.changePanel(new DeleteMenu(frame, m))));
        menuPanel.add(ViewUtils.createButton("회원 검색", e -> frame.changePanel(new SearchMenu(frame, m))));
        
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(menuPanel);
        
        backBtnPanel.add(ViewUtils.createButtonPanel("뒤로가기", e -> frame.changePanel(new ServerMenu(frame, m))));
        //패널에 추가
        add(wrapperPanel, BorderLayout.CENTER);
        add(backBtnPanel, BorderLayout.SOUTH);
    }
	
}
