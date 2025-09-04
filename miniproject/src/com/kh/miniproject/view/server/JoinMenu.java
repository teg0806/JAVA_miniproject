package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.kh.miniproject.common.BaseFormPanel;
import com.kh.miniproject.common.ViewUtils;
import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;

public class JoinMenu extends JPanel {
    private static final long serialVersionUID = 1L;
    private MemberController mc;

    public JoinMenu(MainFrame frame) {
        this.mc = new MemberController();
        setLayout(new BorderLayout());

        JPanel joinFormPanel = createJoinFormPanel(frame);
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(joinFormPanel);
        add(wrapperPanel, BorderLayout.CENTER);

        // ViewUtils를 사용하여 뒤로가기 버튼 생성
        ActionListener backListener = e -> frame.changePanel(new MainMenu(frame));
        add(ViewUtils.createButtonPanel("이전으로", backListener), BorderLayout.SOUTH);
    }

    private JPanel createJoinFormPanel(MainFrame frame) {
        // BaseFormPanel을 상속받는 내부 클래스로 폼을 만듦
        class JoinForm extends BaseFormPanel {
            private static final long serialVersionUID = 1L;

            public JoinForm() {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("회원가입"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));

                JComponent[] fields = {
                    new JTextField(15), new JPasswordField(15), new JTextField(15),
                    new JTextField(15), new JTextField(15), new JTextField(15)
                };
                String[] labels = {"아이디: ", "비밀번호: ", "이름: ", "성별(F|M): ", "닉네임: ", "이메일: "};

                for (int i = 0; i < labels.length; i++) {
                    addFormRow(labels[i], fields[i], i);
                }

                JButton registerButton = new JButton("가입하기");
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
                    frame.changePanel(new MainMenu(frame));
                });
            }
        }
        return new JoinForm();
    }
}