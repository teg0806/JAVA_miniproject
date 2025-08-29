package com.kh.miniproject.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.kh.miniproject.tamplate.Tamplate;
import javax.swing.*;

public class StartMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public StartMenu(MainFrame frame) {
		setLayout(null);
		int width = Tamplate.FRAMEW;
		int height = Tamplate.FRAMEH;
		JButton startBtn = new JButton("시작");
		startBtn.setBounds(width/2-60, height/2+200, 120, 30); //버튼 panel에 추가
		add(startBtn);
		
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changePanel(new MainMenu(frame));
			}
		});
	} 
}
