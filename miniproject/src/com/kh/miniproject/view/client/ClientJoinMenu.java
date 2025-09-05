package com.kh.miniproject.view.client;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kh.miniproject.common.GridFormTamplate;
import com.kh.miniproject.common.ButtonTamplate;
import com.kh.miniproject.sokect.client.ClientManager;

public class ClientJoinMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	private ClientManager clientManager;

    public ClientJoinMenu(ClientMainFrame frame, ClientManager clientManager) {
        this.clientManager = clientManager;
    	// 전체적인 레이아웃은 BorderLayout으로 설정
        setLayout(new BorderLayout());

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(createJoinFormPanel(frame));

        //중단 패널 추가
        add(wrapperPanel, BorderLayout.CENTER);
        
        //하단 패널 생성
        add(createBackPanel(frame), BorderLayout.SOUTH);
    }

    private JPanel createJoinFormPanel(ClientMainFrame frame) {
    	class JoinForm extends GridFormTamplate {
			private static final long serialVersionUID = 1L;
			public JoinForm() {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("회원가입"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		        // 필드(JTextField, JPasswordField)들을 담을 객체 배열
		        JComponent[] fields = {
		            new JTextField(15),
		            new JPasswordField(15),
		            new JTextField(15),
		            new JTextField(15),
		            new JTextField(15),
		            new JTextField(15)
		        };
		        
		        // 라벨 텍스트들을 담을 배열
		        String[] labels = {"아이디: ", "비밀번호: ", "이름: ", "성별(F|M): ", "닉네임: ", "이메일: "};

		        // 반복문을 사용해서 라벨과 필드를 한 줄씩 추가
		        for (int i = 0; i < labels.length; i++) {
		            addFormRow(labels[i], fields[i], i);
		        }

		        // 가입하기 버튼 추가
		        JButton registerButton = new JButton("가입하기");
		        GridBagConstraints gbc = getGbc();  
		        gbc.gridx = 1;
		        gbc.gridy = labels.length; // 마지막 줄 다음에 추가
		        gbc.anchor = GridBagConstraints.EAST; // 오른쪽 끝에 붙이기
		        add(registerButton, gbc); //

		        // 가입하기 버튼 이벤트 처리
		        registerButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	String userId = ((JTextField) fields[0]).getText();
		                String userPwd = new String(((JPasswordField) fields[1]).getPassword());
		                String userName = ((JTextField) fields[2]).getText();
		                String gender = ((JTextField) fields[3]).getText();
		                String nickName = ((JTextField) fields[4]).getText();
		                String email = ((JTextField) fields[5]).getText();

		                // 서버에 "JOIN:아이디:비번:이름:성별:닉네임:이메일" 형식으로 전송!
		                //String 사이에 : 넣기
		                String joinMessage = String.join(":", "JOIN", userId, userPwd, userName, gender, nickName, email);
		                clientManager.sendMessage(joinMessage);
		                
		            }
		        });
			} 
    		
    	}
        return new JoinForm();
    }

    private JPanel createBackPanel(ClientMainFrame frame) {
       	//버튼 기능과 이름을 전달 후 버튼 패널을 반환.
           return ButtonTamplate.createButtonPanel("이전으로", e -> frame.changePanel(new ClientMainMenu(frame)));
       }
}
