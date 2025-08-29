package com.kh.miniproject.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.kh.miniproject.tamplate.Tamplate;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public MainMenu(MainFrame frame) {
		setLayout(null);
		
		int width = Tamplate.FRAMEW;
		int height = Tamplate.FRAMEH;
		
		JLabel mainLabel = new JLabel("메인 메뉴");
		mainLabel.setBounds(width/2-60, height/2-200 , 120, 30); //라벨 위치
		mainLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
		add(mainLabel);
		
		JButton startMenuBackBtn = new JButton("시작 메뉴");
		startMenuBackBtn.setBounds(width/2+120, height/2+200, 120, 30); //버튼 panel에 추가
		add(startMenuBackBtn);
		
		startMenuBackBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changePanel(new StartMenu(frame));
			}
		});
	}
	
}
