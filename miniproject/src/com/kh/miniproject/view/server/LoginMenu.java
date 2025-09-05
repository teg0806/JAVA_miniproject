package com.kh.miniproject.view.server;

import com.kh.miniproject.common.GridFormTamplate;
import com.kh.miniproject.common.ButtonPanelTamplate;
import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginMenu extends JPanel {
    private static final long serialVersionUID = 1L;
    private MemberController mc;

    public LoginMenu(MainFrame frame) {
        this.mc = new MemberController();
        //전체 레이아웃 생성
        setLayout(new BorderLayout());
        
        //로그인 패널을 담을 패널 생성
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        //패널 추가
        wrapperPanel.add(createLoginFormPanel(frame));
        
        //패널을 전체 레이아웃에 추가
        add(wrapperPanel, BorderLayout.CENTER);
        add(createBackPanel(frame), BorderLayout.SOUTH);
        
    }

    private JPanel createLoginFormPanel(MainFrame frame) {
        class LoginForm extends GridFormTamplate {
            private static final long serialVersionUID = 1L;
            
            public LoginForm() {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("로그인"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                
                //객체 배열 생성하여 아이디, 패스워드를 담을 필드 추가
                JComponent[] fields = { new JTextField(15), new JPasswordField(15) };
                //문자열 배열 생성
                String[] labels = {"아이디: ", "비밀번호: "};
                
                //addFormRow로 문자열 배열의 길이만큼 반복
                for (int i = 0; i < labels.length; i++) {
                	
                    addFormRow(labels[i], fields[i], i);
                }
                //get으로 gridBag 레이아웃을 전달받음
                GridBagConstraints gbc = getGbc();
                //버튼 추가
                JButton loginButton = new JButton("로그인");
                gbc.gridx = 1;
                gbc.gridy = labels.length;
                gbc.anchor = GridBagConstraints.EAST;
                add(loginButton, gbc); //전체 패널에 추가

                loginButton.addActionListener(e -> {
                	//Member에 사용자가 입력한 값을 넣어 객체 생성
                    Member m = new Member(
                            ((JTextField) fields[0]).getText(),
                            new String(((JPasswordField) fields[1]).getPassword()));
                    //MemberController에 전달하여 Dao와 간접적인 상호작용
                    mc.loginMember(frame, m);
                });
            }
        }
        return new LoginForm();
    }
    
    private JPanel createBackPanel(MainFrame frame) {
    	//버튼 기능과 이름을 전달 후 버튼 패널을 반환.
        return ButtonPanelTamplate.createButtonPanel("이전으로", e -> frame.changePanel(new MainMenu(frame)));
    }
}