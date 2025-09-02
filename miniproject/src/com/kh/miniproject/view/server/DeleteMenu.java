package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;

public class DeleteMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private MemberController mc;

    public DeleteMenu(final MainFrame frame, final Member m) {
        this.mc = new MemberController();
        setLayout(new BorderLayout());

        // 중앙의 삭제 폼 패널을 생성해서 가운데에 배치
        JPanel deleteFormPanel = createDeleteFormPanel(frame, m);
        
        // 폼을 화면 정중앙에 예쁘게 배치하기 위한 래퍼 패널
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(deleteFormPanel);
        
        add(wrapperPanel, BorderLayout.CENTER);
        
        // 하단의 뒤로가기 버튼 패널을 남쪽에 배치
        add(createBackButtonPanel(frame, m), BorderLayout.SOUTH);
    }

    /**
     * 회원 탈퇴 폼을 생성하는 메소드
     */
    private JPanel createDeleteFormPanel(final MainFrame frame, final Member m) {
        JPanel deletePanel = new JPanel(new GridBagLayout());

        deletePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("회원 탈퇴"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // --- 아이디 입력 필드 ---
        JLabel idLabel = new JLabel("아이디: ");
        final JPasswordField idField = new JPasswordField(15);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        deletePanel.add(idLabel, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        deletePanel.add(idField, gbc);

        // --- 삭제하기 버튼 ---
        JButton deleteButton = new JButton("탈퇴하기");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        deletePanel.add(deleteButton, gbc);

        // 삭제하기 버튼 이벤트 처리
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mc.deleteMember(frame, m);
            }
        });

        return deletePanel;
    }

    /**
     * '뒤로가기' 버튼 패널을 생성하는 메소드
     */
    private JPanel createBackButtonPanel(final MainFrame frame, final Member m) {
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("뒤로가기");
        backButton.setPreferredSize(new java.awt.Dimension(120, 30));
        backButtonPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // [수정] ManagementMenu가 아닌 MainMenu로 돌아가야 함
                frame.changePanel(new ManagementMenu(frame, m));
            }
        });

        return backButtonPanel;
    }
}

