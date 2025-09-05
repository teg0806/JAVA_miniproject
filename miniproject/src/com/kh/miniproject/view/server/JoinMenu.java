package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.kh.miniproject.common.GridFormTamplate;
import com.kh.miniproject.common.ButtonPanelTamplate;
import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;

public class JoinMenu extends JPanel {
    private static final long serialVersionUID = 1L;
    private MemberController mc;

    public JoinMenu(MainFrame frame) {
        this.mc = new MemberController();
        //전체 패널 생성
        setLayout(new BorderLayout());

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(createJoinFormPanel(frame));
        add(wrapperPanel, BorderLayout.CENTER);
        add(createBackPanel(frame), BorderLayout.SOUTH);
    }
    //로그인 폼과 마찬가지로 내부클래스로 폼을 생성.
    private JPanel createJoinFormPanel(MainFrame frame) {
        class JoinForm extends GridFormTamplate {
            private static final long serialVersionUID = 1L;

            public JoinForm() {
            	//테두리
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("회원가입"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                //텍스트 필드 생성
                JComponent[] fields = {
                    new JTextField(15), new JPasswordField(15), new JTextField(15),
                    new JTextField(15), new JTextField(15), new JTextField(15)
                };
                
                //라벨 생성
                String[] labels = {"아이디: ", "비밀번호: ", "이름: ", "성별(F|M): ", "닉네임: ", "이메일: "};
                
                //라벨 길이만큼 반복
                for (int i = 0; i < labels.length; i++) {
                    addFormRow(labels[i], fields[i], i);
                }
                
                //가입하기 버튼 생성 후 배치
                JButton registerButton = new JButton("가입하기");
                GridBagConstraints gbc = getGbc();
                gbc.gridx = 1;
                gbc.gridy = labels.length;
                gbc.anchor = GridBagConstraints.EAST;
                add(registerButton, gbc);
                
                //가입하기 버튼 상호작용
                registerButton.addActionListener(e -> {
                	//MemberController에 입력한 값을 모두 전달
                    mc.insertMember(frame, new Member(
                            ((JTextField) fields[0]).getText(),
                            new String(((JPasswordField) fields[1]).getPassword()),
                            ((JTextField) fields[2]).getText(),
                            ((JTextField) fields[3]).getText(),
                            ((JTextField) fields[4]).getText(),
                            ((JTextField) fields[5]).getText()));
                    //그후 그전 화면으로 전환
                    frame.changePanel(new MainMenu(frame)); //-> 회원가입과 회원 관리의 회원 추가 메서드가 동일하기에 화면 전환을 여기에 배치.
                });
            }
        }
        return new JoinForm();
    }
    
    private JPanel createBackPanel(MainFrame frame) {
    	//버튼 기능과 이름을 전달 후 버튼 패널을 반환.
        return ButtonPanelTamplate.createButtonPanel("이전으로", e -> frame.changePanel(new MainMenu(frame)));
    }
}