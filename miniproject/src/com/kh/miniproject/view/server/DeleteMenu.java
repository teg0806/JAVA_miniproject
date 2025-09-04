package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.kh.miniproject.common.BaseFormPanel;
import com.kh.miniproject.common.ViewUtils;
import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;

public class DeleteMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private MemberController mc;

    public DeleteMenu(MainFrame frame, Member m) {
        this.mc = new MemberController();
        setLayout(new BorderLayout());

        JPanel deleteFormPanel = createDeleteFormPanel(frame, m);
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(deleteFormPanel);
        add(wrapperPanel, BorderLayout.CENTER);

        // ViewUtils를 사용하여 뒤로가기 버튼 생성
        ActionListener backListener = e -> frame.changePanel(new ManagementMenu(frame, m));
        add(ViewUtils.createButtonPanel("뒤로가기", backListener), BorderLayout.SOUTH);
    }

    private JPanel createDeleteFormPanel(MainFrame frame, Member m) {
        class DeleteForm extends BaseFormPanel {
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
}