package com.kh.miniproject.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.kh.miniproject.tamplate.Tamplate;
import javax.swing.*;

public class StartMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	int width = Tamplate.FRAMEW;
	int height = Tamplate.FRAMEH;
	
	public StartMenu(MainFrame frame) {
		setLayout(null);
		
		startBtn(frame);
		background(frame);
		endBtn(frame);
	}
	
	public void startBtn(MainFrame frame) {
		JButton startBtn = new JButton("시작");
		startBtn.setBounds(width/2-60, height/2+160, 120, 30); //버튼 panel에 추가
		add(startBtn);
		
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changePanel(new MainMenu(frame));
			}
		});
	}
	
	public void endBtn(MainFrame frame) {
		JButton startBtn = new JButton("종료");
		startBtn.setBounds(width/2-60, height/2+200, 120, 30); //버튼 panel에 추가
		add(startBtn);
		
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void background(MainFrame frame) {
		//이미지 불러오기
		ImageIcon background = new ImageIcon("이미지 경로"); //이미지 정하고 사용
		
		//이미지 전용 패널 생성
		JPanel backgroundPanel = new JPanel() {

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		
		backgroundPanel.setLayout(null);
		frame.setContentPane(backgroundPanel);
	}
}
