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

import com.kh.miniproject.common.GridFormTemplate;
import com.kh.miniproject.common.ButtonPanelTemplate;
import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;

public class InsertMenu extends JPanel  {
    private static final long serialVersionUID = 1L;
	private MemberController mc;

    public InsertMenu(MainFrame frame, Member member) {
    	this.mc = new MemberController();
    	
    	//전체 레이아웃 생성
        setLayout(new BorderLayout());
        
        //중단 패널을 감싸주는 패널 생성 후 추가
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(insertFormPanel(frame, member));
        
        //중단 패널 추가
        add(wrapperPanel, BorderLayout.CENTER); 

        //하단 패널 추가
        add(createBackPanel(frame, member), BorderLayout.SOUTH);
    }
    
    //내부 클래스
    //따로 클래스를 구현하고 참조해서 사용하면, 디렉토리가 지저분해지며, insert부분에서만 사용하기에 굳이 다로 파일을 생성할 필요가 없음
    private JPanel insertFormPanel(MainFrame frame, Member m) {
        class InsertForm extends GridFormTemplate {
            private static final long serialVersionUID = 1L;

            public InsertForm() {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("회원 추가"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));

                JComponent[] fields = {
                    new JTextField(15), new JPasswordField(15), new JTextField(15),
                    new JTextField(15), new JTextField(15), new JTextField(15)
                };
                String[] labels = {"아이디: ", "비밀번호: ", "이름: ", "성별(F|M): ", "닉네임: ", "이메일: "};

                for (int i = 0; i < labels.length; i++) {
                    addFormRow(labels[i], fields[i], i);
                }

                JButton registerButton = new JButton("추가하기");
                GridBagConstraints gbc = getGbc();
                gbc.gridx = 1;
                gbc.gridy = labels.length;
                gbc.anchor = GridBagConstraints.EAST;
                add(registerButton, gbc);

                registerButton.addActionListener(e -> {
                    mc.insertMember(frame, new Member(
                            ((JTextField) fields[0]).getText(),
                            new String(((JPasswordField) fields[1]).getPassword()),
                            ((JTextField) fields[2]).getText(),
                            ((JTextField) fields[3]).getText(),
                            ((JTextField) fields[4]).getText(),
                            ((JTextField) fields[5]).getText()));
                    frame.changePanel(new ServerMenu(frame, m));
                });
            }
        }
        return new InsertForm();
    }
    
    private JPanel createBackPanel(MainFrame frame, Member member) {
        // ViewUtils를 사용하여 뒤로가기 버튼 생성
        return ButtonPanelTemplate.createButtonPanel("뒤로가기", e -> frame.changePanel(new ManagementMenu(frame, member)));
    }

}
