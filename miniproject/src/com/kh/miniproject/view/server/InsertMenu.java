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
import com.kh.miniproject.service.MemberService;
import com.kh.miniproject.tamplate.Tamplate;
import com.kh.miniproject.vo.Member;

/*
 * 전체 틀을 BorderLayout
 * 중간에는(CENTER) 그안에 GridBagLayout를 생성
 * 아래에는(SOUTH) 그안에 FlowLayout를 생성
 * */

public class InsertMenu extends JPanel {
    private static final long serialVersionUID = 1L;
	private MemberController mc;


    public InsertMenu(MainFrame frame, Member m) {
    	this.mc = new MemberController();
    	
        setLayout(new BorderLayout());

        JPanel insertFormPanel = insertFormPanel(frame);

        JPanel wrapperPanel = new JPanel(new GridBagLayout());

        wrapperPanel.add(insertFormPanel);

        add(wrapperPanel, BorderLayout.CENTER);

        add(BackButtonPanel(frame, m), BorderLayout.SOUTH);
    }

    private JPanel insertFormPanel(MainFrame frame) {
        JPanel joinPanel = new JPanel(new GridBagLayout());

        joinPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("회원 추가"), //회원가입 글자 추가
                BorderFactory.createEmptyBorder(10, 10, 10, 10) //안쪽 여백을 추가해하여 테두리 추가
        ));
        
        GridBagConstraints gbc = new GridBagConstraints(); //gridLayout을 관리해주는 클래스로  
        
        // 모든 컴포넌트에 공통적으로 적용될 기본 여백
        gbc.insets = new Insets(5, 5, 5, 5);

        // 필드(JTextField, JPasswordField)들을 담을 객체 배열
        JComponent[] fields = {
            new JTextField(15),
            new JPasswordField(15),
            new JTextField(15),
            new JTextField(15),
            new JTextField(15),
            new JTextField(15)
        };
        
        // 라벨 텍스트들을 담을 배열
        String[] labels = {"아이디: ", "비밀번호: ", "이름: ", "성별(F|M): ", "닉네임: ", "이메일: "};

        // 반복문을 사용해서 라벨과 필드를 한 줄씩 추가
        for (int i = 0; i < labels.length; i++) {
            addFormRow(joinPanel, gbc, labels[i], fields[i], i);
        }

        // 가입하기 버튼 추가
        JButton registerButton = new JButton("추가하기");
        gbc.gridx = 1;
        gbc.gridy = labels.length; // 마지막 줄 다음에 추가
        gbc.anchor = GridBagConstraints.EAST; // 오른쪽 끝에 붙이기
        joinPanel.add(registerButton, gbc); //

        // 가입하기 버튼 이벤트 처리
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mc.insertMember(frame, new Member(
                        ((JTextField) fields[0]).getText(), //아이디
                        new String(((JPasswordField) fields[1]).getPassword()), //비밀번호
                        ((JTextField) fields[2]).getText(), //이름
                        ((JTextField) fields[3]).getText(), //성별
                        ((JTextField) fields[4]).getText(), //닉네임
                        ((JTextField) fields[5]).getText() //이메일
                )); // DB 컨트롤러 연결 부분
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

    /**
     * '이전으로' 버튼이 들어가는 패널을 생성하는 메소드
     */
    private JPanel BackButtonPanel(MainFrame frame, Member m) {
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); //panel을 생성하고 panel 안에 FlowLayout을 중앙에 생성
        JButton startMenuBackBtn = new JButton("이전으로"); //이전 버튼을 생성
        startMenuBackBtn.setPreferredSize(new java.awt.Dimension(120, 30));
        backButtonPanel.add(startMenuBackBtn); //panel에 추가

        startMenuBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changePanel(new ManagementMenu(frame, m)); //main으로 화면 전환
            }
        });

        return backButtonPanel; //설정한 panel 객체 반환
    }
}
