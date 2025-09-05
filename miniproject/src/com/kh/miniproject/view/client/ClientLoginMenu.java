package com.kh.miniproject.view.client;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kh.miniproject.common.BaseFormPanel;
import com.kh.miniproject.common.ViewUtils;
import com.kh.miniproject.sokect.client.ClientManager;
import com.kh.miniproject.vo.Member;

public class ClientLoginMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	private ClientManager clientManager;
	
	public ClientLoginMenu(ClientMainFrame frame, ClientManager clientManager) {
		this.clientManager = clientManager;
		//전체 레이아웃 설정
		setLayout(new BorderLayout());
		//gridLayout을 감싸는 GridBagLayout 생성
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(createLoginPanel(frame));
        
        //중단 패널 추가
        add(wrapperPanel, BorderLayout.CENTER);
        
        //하단 패널 추가
        add(createBackPanel(frame), BorderLayout.SOUTH);
	}
	
    private JPanel createLoginPanel(ClientMainFrame frame) {
        // BaseFormPanel을 상속받는 내부 클래스를 정의
        class LoginForm extends BaseFormPanel {
            private static final long serialVersionUID = 1L;
            
            public LoginForm() {
            	//테두리
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("로그인"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                
                //TextField 객체 배열
                JComponent[] fields = { new JTextField(15), new JPasswordField(15) };
                
                //라벨 배열
                String[] labels = { "아이디: ", "비밀번호: " };
                
                //라벨 길이만큼 생성
                for (int i = 0; i < labels.length; i++) {
                    addFormRow(labels[i], fields[i], i);
                }
                
                //로그인 버튼 생성
                JButton loginButton = new JButton("로그인");
                GridBagConstraints gbc = getGbc();
                gbc.gridx = 1;
                gbc.gridy = labels.length;
                gbc.anchor = GridBagConstraints.EAST;
                add(loginButton, gbc);
                
                //로그인 입력 후 sendMessage로 서버에 전달
                loginButton.addActionListener(e -> {
                    String userId = ((JTextField) fields[0]).getText();
                    String userPwd = new String(((JPasswordField) fields[1]).getPassword());
                    
                    if (userId.trim().isEmpty() || userPwd.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "아이디와 비밀번호를 모두 입력해주세요.");
                        return;
                    }
                    
                    Member m = new Member(userId, userPwd);
                    clientManager.sendMessage("LOGIN:" + m.getUserId() + ":" + m.getUserPwd());
                });
            }
        }
        
        //내부 LoginForm 클래스의 인스턴스를 생성(메모리 할당)해서 반환한다.
        return new LoginForm();
    }
	
   private JPanel createBackPanel(ClientMainFrame frame) {
   	//버튼 기능과 이름을 전달 후 버튼 패널을 반환.
       return ViewUtils.createButtonPanel("이전으로", e -> frame.changePanel(new ClientMainMenu(frame)));
   }
}
