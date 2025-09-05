package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.kh.miniproject.common.GridFormTemplate;
import com.kh.miniproject.common.ButtonPanelTemplate;
import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;

public class DeleteMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private MemberController mc;

    public DeleteMenu(MainFrame frame, Member member) {
        this.mc = new MemberController();
        setLayout(new BorderLayout());
        
        //중단 패널을 감싸줄 패널 생성과 추가
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(createDeleteFormPanel(frame, member));
        
        //중단 패널 추가
        add(wrapperPanel, BorderLayout.CENTER);
        
        //하단 패널 추가
        add(createBackPanel(frame, member), BorderLayout.SOUTH);
    }

    private JPanel createDeleteFormPanel(MainFrame frame, Member m) {
        class DeleteForm extends GridFormTemplate {
            private static final long serialVersionUID = 1L;

            public DeleteForm() {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("회원 탈퇴"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));

                JTextField idField = new JTextField(15);
                addFormRow("아이디: ", idField, 0);
                
                GridBagConstraints gbc = getGbc();
                
                JButton deleteButton = new JButton("삭제하기");
                gbc.gridx = 1;
                gbc.gridy = 1;
                gbc.anchor = GridBagConstraints.EAST;
                add(deleteButton, gbc);

                deleteButton.addActionListener(e -> mc.deleteMember(frame, idField.getText()));
                frame.changePanel(new ServerMenu(frame, m));
            }
        }
        return new DeleteForm();
    }
    
    private JPanel createBackPanel(MainFrame frame, Member member) {
        // ViewUtils를 사용하여 뒤로가기 버튼 생성
        return ButtonPanelTemplate.createButtonPanel("뒤로가기", e -> frame.changePanel(new ManagementMenu(frame, member)));
    }
    
    public static void deleteSuccess(MainFrame frame) {
		JOptionPane.showMessageDialog(frame, "회원 정보가 삭제하는데 성공하였습니다.");
    }
    
    public static void deleteFail(MainFrame frame) {
		JOptionPane.showMessageDialog(frame, "회원 정보가 삭제하는데 실패하였습니다.");
    }
}