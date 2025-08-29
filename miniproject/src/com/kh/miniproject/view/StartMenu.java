package com.kh.miniproject.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StartMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public StartMenu(MainFrame frame) {
		setLayout(null);
		
		JButton startBtn = new JButton("시작");
		startBtn.setBounds(140, 120, 120, 30); //버튼 panel에 추가
		add(startBtn);
		
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changePanel(new MainMenu(frame));
			}
		});
	} 
}
