package com.kh.miniproject.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public MainMenu(MainFrame frame) {
		setLayout(null);
		
		JLabel mainLabel = new JLabel("메인 메뉴");
		mainLabel.setBounds(540, 70, 120, 30);
		add(mainLabel);
		
		JButton startMenuBackBtn = new JButton("시작 메뉴");
		startMenuBackBtn.setBounds(540, 120, 120, 30); //버튼 panel에 추가
		add(startMenuBackBtn);
		
		startMenuBackBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changePanel(new StartMenu(frame));
			}
		});
	}
	
}
