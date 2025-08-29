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
	private int width = Tamplate.FRAMEW;
	private int height = Tamplate.FRAMEH;
	
	public MainMenu(MainFrame frame) {
		setLayout(null);
		
		mainLabel();
		loginMemberBtn(frame);
		joinMemberBtn(frame);
		startMenuBackBtn(frame);
		
	}
	
	public void mainLabel() {
		JLabel mainLabel = new JLabel("메인 메뉴");
		mainLabel.setBounds(width/2-60, height/2-200 , 200, 40); //라벨 위치
		mainLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30)); //폰트 변경 생각
		add(mainLabel);
	}
	
	public void startMenuBackBtn(MainFrame frame) {
		JButton startMenuBackBtn = new JButton("이전으로");
		startMenuBackBtn.setBounds(width/2-60, height/2+200, 120, 30); //버튼 panel에 추가
		add(startMenuBackBtn);
		
		startMenuBackBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changePanel(new StartMenu(frame));
			}
		});
	}
	
	public void loginMemberBtn(MainFrame frame) {
		JButton startMenuBackBtn = new JButton("로그인");
		startMenuBackBtn.setBounds(width/2-60, height/2+120, 120, 30); //버튼 panel에 추가
		add(startMenuBackBtn);
		
		startMenuBackBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changePanel(new JoinMenu(frame));
			}
		});
	}
	
	public void joinMemberBtn(MainFrame frame) {
		JButton startMenuBackBtn = new JButton("회원가입");
		startMenuBackBtn.setBounds(width/2-60, height/2+160, 120, 30); //버튼 panel에 추가
		add(startMenuBackBtn);
		
		startMenuBackBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changePanel(new JoinMenu(frame));
			}
		});
	}
}
