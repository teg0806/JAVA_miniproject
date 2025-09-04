package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.kh.miniproject.common.BaseFormPanel;
import com.kh.miniproject.common.ViewUtils;
import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;

public class UpdateMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private MemberController mc;

    public UpdateMenu(MainFrame frame, Member m) {
        super();
        this.mc = new MemberController();

        setLayout(new BorderLayout());

        JPanel updateFormPanel = updateFormPanel(frame, m);
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(updateFormPanel);
        add(wrapperPanel, BorderLayout.CENTER);

        // ViewUtils를 사용하여 뒤로가기 버튼 생성
        ActionListener backListener = e -> frame.changePanel(new ManagementMenu(frame, m));
        add(ViewUtils.createButtonPanel("뒤로가기", backListener), BorderLayout.SOUTH);
    }

    public JPanel updateFormPanel(MainFrame frame, Member m) {
        class UpdateForm extends BaseFormPanel {
            private static final long serialVersionUID = 1L;

            public UpdateForm() {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("수정할 회원"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));

                JComponent[] fields = { new JTextField(15), new JTextField(15), new JTextField(15) };
                String[] labels = {"아이디: ", "닉네임: ", "이메일: "};

                for (int i = 0; i < labels.length; i++) {
                    addFormRow(labels[i], fields[i], i);
                }

                JButton registerButton = new JButton("수정하기");
                GridBagConstraints gbc = getGbc();
                gbc.gridx = 1;
                gbc.gridy = labels.length;
                gbc.anchor = GridBagConstraints.EAST;
                add(registerButton, gbc);

                registerButton.addActionListener(e -> {
                    mc.updateMember(frame, new Member(
                            ((JTextField) fields[0]).getText(),
                            ((JTextField) fields[1]).getText(),
                            ((JTextField) fields[2]).getText()));
                    frame.changePanel(new ServerMenu(frame, m));
                });
            }
        }
        return new UpdateForm();
    }
}