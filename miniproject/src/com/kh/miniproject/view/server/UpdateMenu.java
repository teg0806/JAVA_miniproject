package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.kh.miniproject.common.GridFormTamplate;
import com.kh.miniproject.common.ButtonTamplate;
import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;

public class UpdateMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private MemberController mc;

    public UpdateMenu(MainFrame frame, Member member) {
        super();
        this.mc = new MemberController();
        //전체 패널 추가
        setLayout(new BorderLayout());
        
        //중단 패널을 감싸줄 패널 생성
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        
        //중단 패널 추가
        wrapperPanel.add(createUpdatePanel(frame, member));
        add(wrapperPanel, BorderLayout.CENTER);
        
        //하단 패널 추가
        add(createBackPanel(frame, member), BorderLayout.SOUTH);
    }
    
    //내부 클래스로 파일을 새로 생성하지 않고 안에서 처리
    public JPanel createUpdatePanel(MainFrame frame, Member member) {
        class UpdateForm extends GridFormTamplate {
            private static final long serialVersionUID = 1L;

            public UpdateForm() {
            	//테두리
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("수정할 회원"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                
                //텍스트 필드 배열
                JComponent[] fields = { new JTextField(15), new JTextField(15), new JTextField(15) };
                
                //라벨 배열
                String[] labels = {"아이디: ", "닉네임: ", "이메일: "};

                //반복분으로 라벨 길이만큼 반복, 라벨과 텍스트 배치
                for (int i = 0; i < labels.length; i++) {
                    addFormRow(labels[i], fields[i], i);
                }
                
                //버튼 생성
                GridBagConstraints gbc = getGbc();
                JButton registerButton = new JButton("수정하기");
                gbc.gridx = 1;
                gbc.gridy = labels.length;
                gbc.anchor = GridBagConstraints.EAST;
                
                //버튼 배치
                add(registerButton, gbc);
                
                //수정 버튼 누른 후의 상호작용
                registerButton.addActionListener(e -> {
                	//MemberController에 전달 후 처리
                    mc.updateMember(frame, new Member(
                            ((JTextField) fields[0]).getText(),
                            ((JTextField) fields[1]).getText(),
                            ((JTextField) fields[2]).getText()));
                });
            }
        }
        return new UpdateForm();
    }
    
    private JPanel createBackPanel(MainFrame frame, Member member) {
        // ViewUtils를 사용하여 뒤로가기 버튼 생성
        return ButtonTamplate.createButtonPanel("뒤로가기", e -> frame.changePanel(new ManagementMenu(frame, member)));
    }
}