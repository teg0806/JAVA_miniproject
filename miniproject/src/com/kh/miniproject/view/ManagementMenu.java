package com.kh.miniproject.view;

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

import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;

public class ManagementMenu extends JPanel{

	private static final long serialVersionUID = 1L;
	private MemberController mc;

	public ManagementMenu(MainFrame frame, Member m) {
		this.mc = new MemberController();
		
		setLayout(new BorderLayout());
		add(updateMember(frame, m));
		
	}
	
	public void deleteMember() {
		
	}
	
	public void selectMember() {
		
	}
	
	public JPanel updateMember(MainFrame frame, Member m) {
		JPanel joinPanel = new JPanel(new GridBagLayout());
        
        // 1. "회원가입"이라는 제목이 있는 테두리를 생성
        // 2. 안쪽에 여백(padding)을 주기 위해 빈 테두리와 조합 (CompoundBorder)
        joinPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("수정할 회원"), //회원가입 글자 추가
                BorderFactory.createEmptyBorder(10, 10, 10, 10) //안쪽 여백을 추가해하여 테두리 추가
        ));
        
        GridBagConstraints gbc = new GridBagConstraints(); //gridLayout을 관리해주는 클래스로  
        
        // 모든 컴포넌트에 공통적으로 적용될 기본 여백
        gbc.insets = new Insets(5, 5, 5, 5);

        // 필드(JTextField, JPasswordField)들을 담을 객체 배열
        JComponent[] fields = {
            new JTextField(15),
            new JTextField(15),
            new JTextField(15)
        };
        
        // 라벨 텍스트들을 담을 배열
        String[] labels = {"아이디: ", "닉네임: ", "이메일: "};

        // 반복문을 사용해서 라벨과 필드를 한 줄씩 추가
        for (int i = 0; i < labels.length; i++) {
            addFormRow(joinPanel, gbc, labels[i], fields[i], i);
        }

        // 가입하기 버튼 추가
        JButton registerButton = new JButton("수정하기");
        gbc.gridx = 1;
        gbc.gridy = labels.length; // 마지막 줄 다음에 추가
        gbc.anchor = GridBagConstraints.EAST; // 오른쪽 끝에 붙이기
        joinPanel.add(registerButton, gbc); //

        // 가입하기 버튼 이벤트 처리
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mc.updateMember(frame, new Member(
                        ((JTextField) fields[0]).getText(), //아이디
                        ((JTextField) fields[1]).getText(), //닉네임
                        ((JTextField) fields[2]).getText() //이메일
                )); // DB 컨트롤러 연결 부분
                frame.changePanel(new ChatMenu(frame, m));
            }
        });

        return joinPanel;
    }
	
    private void addFormRow(JPanel panel, GridBagConstraints gbc, String labelText, JComponent component, int gridy) {
        // 라벨 생성 및 설정
        JLabel label = new JLabel(labelText); //labelText 제목으로 라벨 생성. 위 label 배열을 하나씩 전달 받음
        label.setHorizontalAlignment(SwingConstants.RIGHT); //라벨을 오른쪽으로 정렬
        Font currentFont = label.getFont(); //폰트 객체 생성
        label.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 15)); //폰트 설정
        
        // 라벨 위치 설정 및 추가 2차원배열 또는 테이블 느낌
        gbc.gridx = 0; //첫번째 열
        gbc.gridy = gridy; //gridy 0 ~ labels.length; -> 5 = 총 6행
        gbc.anchor = GridBagConstraints.EAST; //오른쪽으로 밀착
        panel.add(label, gbc);

        // 텍스트 필드 위치 설정 및 추가
        gbc.gridx = 1; //두번째 열
        gbc.gridy = gridy;
        gbc.anchor = GridBagConstraints.WEST; //왼쪽으로 밀착
        panel.add(component, gbc);
    }
}
