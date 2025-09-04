package com.kh.miniproject.view.server;

import com.kh.miniproject.common.BaseFormPanel;
import com.kh.miniproject.common.ViewUtils;
import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;
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

public class LoginMenu extends JPanel {
    private static final long serialVersionUID = 1L;
    private MemberController mc;

    public LoginMenu(MainFrame frame) {
        this.mc = new MemberController();
        setLayout(new BorderLayout());

        JPanel loginFormPanel = createLoginFormPanel(frame);
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(loginFormPanel);
        add(wrapperPanel, BorderLayout.CENTER);

        // ViewUtils를 사용하여 뒤로가기 버튼 생성
        ActionListener backListener = e -> frame.changePanel(new MainMenu(frame));
        add(ViewUtils.createButtonPanel("메인 메뉴", backListener), BorderLayout.SOUTH);
    }

    private JPanel createLoginFormPanel(MainFrame frame) {
        class LoginForm extends BaseFormPanel {
            private static final long serialVersionUID = 1L;

            public LoginForm() {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("로그인"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));

                JComponent[] fields = { new JTextField(15), new JPasswordField(15) };
                String[] labels = {"아이디: ", "비밀번호: "};

                for (int i = 0; i < labels.length; i++) {
                    addFormRow(labels[i], fields[i], i);
                }

                JButton loginButton = new JButton("로그인");
                GridBagConstraints gbc = getGbc();
                gbc.gridx = 1;
                gbc.gridy = labels.length;
                gbc.anchor = GridBagConstraints.EAST;
                add(loginButton, gbc);

                loginButton.addActionListener(e -> {
                    Member m = new Member(
                            ((JTextField) fields[0]).getText(),
                            new String(((JPasswordField) fields[1]).getPassword()));
                    mc.loginMember(frame, m);
                });
            }
        }
        return new LoginForm();
    }
}