package com.kh.miniproject.view;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kh.miniproject.tamplate.Tamplate;
import com.kh.miniproject.vo.Member;

public class JoinMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	private int width = Tamplate.FRAMEW; // 화면 크기
	private int height = Tamplate.FRAMEH; // 화면 크기
	
	private Member m;
	
	public JoinMenu(MainFrame frame) {
		setLayout(null);
		
		joinMemberPanel(frame);
		
		
		mainMenuBackBtn(frame);

	}
	
	public void joinMemberPanel(MainFrame frame) {
//		JPanel joinpanel = new JPanel(new GridLayout(6, 2));
		
		JLabel idLabel = new JLabel("아이디: "); //이름 문자를 화면 출력
		JTextField idField = new JTextField(); //text상자를 필드에 생성
		
        JLabel passwordLabel = new JLabel("비밀번호: ");
        JPasswordField passwordField = new JPasswordField(); 
        //비밀번호 전용 text상자를 필드에 생성(*로 가려짐)
		
		JLabel nameLabel = new JLabel("이름: "); //이름 문자를 화면 출력
		JTextField nameField = new JTextField(); //text상자를 필드에 생성
		
		JLabel genderLabel = new JLabel("성별: "); //성별 문자를 화면 출력
		JTextField genderField = new JTextField(); //text상자를 필드에 생성
        
        JLabel nickNameLabel = new JLabel("닉네임: "); //닉네임 문자를 화면 출력
		JTextField nickNameField = new JTextField(); //text상자를 필드에 생성
        
		JLabel emailLabel = new JLabel("이메일: "); //이메일 문자를 화면 출력
		JTextField emailField = new JTextField(); //text상자를 필드에 생성
		
		JButton registerButton = new JButton("가입하기");
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//따로 메서드로 구현.
				Member m = new Member(idField.getText(), 
						new String(passwordField.getPassword()), 
						nameField.getText(), 
						genderField.getText(), 
						nickNameField.getText(), 
						emailField.getText());
//				mc.insertMember(m);
				JOptionPane.showMessageDialog(frame, "가입 완료!");
				frame.changePanel(new MainMenu(frame));
			}
		});
		/*
		//id를 패널에 추가
		joinpanel.add(idLabel); 
		joinpanel.add(idField);
		
		//password를 패널에 추가
		joinpanel.add(passwordLabel);
		joinpanel.add(passwordField);
		
		//name을 패널에 추가
		joinpanel.add(nameLabel);
		joinpanel.add(nameField);
		
		//gender를 패널에 추가
		joinpanel.add(genderLabel);
		joinpanel.add(genderField);
		
		//nickname을 패널에 추가
		joinpanel.add(nickNameLabel);
		joinpanel.add(nickNameField);
		
		//email을 패널에 추가
		joinpanel.add(emailLabel);
		joinpanel.add(emailField);
		
		joinpanel.add(new JLabel());
		joinpanel.add(registerButton);
		
		frame.add(joinpanel, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.changePanel(joinpanel);
		*/
	}

	
	public void mainMenuBackBtn(MainFrame frame) {
		JButton startMenuBackBtn = new JButton("이전으로");
		startMenuBackBtn.setBounds(width/2-60, height/2+200, 120, 30); //버튼 panel에 추가
		add(startMenuBackBtn);
		
		startMenuBackBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changePanel(new MainMenu(frame));
			}
		});
	}
}
