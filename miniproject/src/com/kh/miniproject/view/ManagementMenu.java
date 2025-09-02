package com.kh.miniproject.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;

public class ManagementMenu extends JPanel{

	private static final long serialVersionUID = 1L;
	private MemberController mc;

	public ManagementMenu(MainFrame frame, Member m) {
		this.mc = new MemberController();
		 // [수정] 패널의 레이아웃을 GridBagLayout으로 변경
        setLayout(new GridBagLayout());

        // 버튼들을 담을 패널 (이 패널 자체는 GridLayout을 사용)
        JPanel menuPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        
        JButton updateButton = new JButton("회원 정보 수정");
        JButton deleteButton = new JButton("회원 탈퇴");
        JButton searchButton = new JButton("회원 검색");
        JButton logoutButton = new JButton("뒤로가기");

        menuPanel.add(updateButton);
        menuPanel.add(deleteButton);
        menuPanel.add(searchButton);
        menuPanel.add(logoutButton);
        
        add(menuPanel);

		// 정보 수정 버튼
	    updateButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            frame.changePanel(new UpdateMenu(frame, m));
	        }
	    });
	
	    // 회원 탈퇴 버튼
	    deleteButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            frame.changePanel(new DeleteMenu(frame, m));
	        }
	    });
	
	    // 회원 검색 버튼
	    searchButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            frame.changePanel(new SearchMenu(frame, m));
	        }
	    });
    
	    // 처음 메뉴로 돌아가기
		logoutButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        frame.changePanel(new MemberMenu(frame, m)); 
		    }
		});
	}

}
