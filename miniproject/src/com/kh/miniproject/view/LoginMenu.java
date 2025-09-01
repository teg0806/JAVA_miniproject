package com.kh.miniproject.view;

import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;

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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	private MemberController mc;
	
	public LoginMenu(MainFrame frame) {
		this.mc = new MemberController();
		setLayout(new BorderLayout());
		
		JPanel loginFormPanel = loginFormPanel(frame);

        // 2. --- 여기가 핵심! ---
        //    폼 패널을 중앙에 예쁘게 배치하기 위한 '포장지' 패널을 하나 더 만들어.
        //    GridBagLayout은 컴포넌트를 중앙에 배치하는 데 아주 좋아.
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        
        // 3. 포장지 패널 안에 폼 패널을 넣어.
        //    이렇게 하면 폼 패널은 자기가 필요한 만큼만 공간을 차지하게 돼.
        wrapperPanel.add(loginFormPanel);

        // 4. 최종적으로 '포장지' 패널을 프레임의 중앙에 추가하는 거야.
        //    이제 포장지가 남는 공간을 다 차지하고, 그 안의 폼은 가운데에 머물게 돼.
        add(wrapperPanel, BorderLayout.CENTER);
		
        add(BackButtonPanel(frame), BorderLayout.SOUTH);
	}
	
	private JPanel loginFormPanel(MainFrame frame) {
		JPanel joinPanel = new JPanel(new GridBagLayout());
		
        joinPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("로그인"), //회원가입 글자 추가
                BorderFactory.createEmptyBorder(10, 10, 10, 10) //안쪽 여백을 추가해하여 테두리 추가
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
        
        JButton loginButton = new JButton("가입하기"); //가입하기 버튼
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
            	mc.loginMember(frame, m); //로그인 정보 넘기기
                frame.changePanel(new ChatMenu(frame, m)); 
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
	
   private JPanel BackButtonPanel(MainFrame frame) {
       JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
       JButton mainMenuBackBtn = new JButton("메인 메뉴로 이동");
       mainMenuBackBtn.setPreferredSize(new java.awt.Dimension(120, 30));
       backButtonPanel.add(mainMenuBackBtn);

       mainMenuBackBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               frame.changePanel(new MainMenu(frame));
           }
       });

       return backButtonPanel;
   }
}
