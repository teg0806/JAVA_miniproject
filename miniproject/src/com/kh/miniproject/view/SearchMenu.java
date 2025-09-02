package com.kh.miniproject.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

public class SearchMenu extends JPanel{
	
	private MemberController mc;

	public SearchMenu(MainFrame frame, Member m) {
		super();
		
		setLayout(new BorderLayout());
		add(searchFormPanel(frame, m));
		
	}

	private JPanel searchFormPanel(MainFrame frame, Member m) {
		JPanel joinPanel = new JPanel(new GridBagLayout());
		add(BackButtonPanel(frame, m), BorderLayout.SOUTH);
		
		
        joinPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("검색"), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10) 
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        JComponent[] fields = {
            new JTextField(15), //id 필드
            new JPasswordField(15) //passoword 필드
        };
        
        String[] labels = {"아이디: ", "비밀번호: "};
        
        for (int i = 0; i < labels.length; i++) {
            addFormRow(joinPanel, gbc, labels[i], fields[i], i); //필드 구조 생성
        }
        
        JButton loginButton = new JButton("검색"); //로그인 버튼
        gbc.gridx = 1;
        gbc.gridy = labels.length; // 마지막 줄 다음에 추가
        gbc.anchor = GridBagConstraints.EAST; // 오른쪽 끝에 붙이기
        joinPanel.add(loginButton, gbc); //필드와 버튼 생성
        
        loginButton.addActionListener(new ActionListener() { //버튼 이벤트 리스너
            @Override
            public void actionPerformed(ActionEvent e) {
            	Member m = new Member(
                        ((JTextField) fields[0]).getText(), //아이디
                        new String(((JPasswordField) fields[1]).getPassword())); //아이디 받아아 m에 저장
            	mc.selectMember(frame, m); //검색 정보 넘기기
            }
        });

        return joinPanel;
	}
	
   private void addFormRow(JPanel panel, GridBagConstraints gbc, String labelText, JComponent component, int gridy) {
        // 라벨 생성 및 설정
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        Font currentFont = label.getFont();
        label.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 15));
        
        // 라벨 위치 설정 및 추가
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(label, gbc);

        // 텍스트 필드 위치 설정 및 추가
        gbc.gridx = 1;
        gbc.gridy = gridy;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(component, gbc);
    }
	
   private JPanel BackButtonPanel(MainFrame frame, Member m) {
       JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
       JButton mainMenuBackBtn = new JButton("뒤로가기");
       mainMenuBackBtn.setPreferredSize(new java.awt.Dimension(120, 30));
       backButtonPanel.add(mainMenuBackBtn);

       mainMenuBackBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               frame.changePanel(new ManagementMenu(frame, m));
           }
       });

       return backButtonPanel;
   }
}
