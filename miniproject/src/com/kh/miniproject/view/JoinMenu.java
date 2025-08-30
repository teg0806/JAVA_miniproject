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

public class JoinMenu extends JPanel {
    private static final long serialVersionUID = 1L;
	private MemberController mc;


    public JoinMenu(MainFrame frame) {
    	this.mc = new MemberController();
    	
        // 전체적인 레이아웃은 BorderLayout으로 설정
        setLayout(new BorderLayout());

        // 1. 우리가 만든 회원가입 폼 패널을 일단 생성해.
        JPanel joinFormPanel = createJoinFormPanel(frame);

        // 2. --- 여기가 핵심! ---
        //    폼 패널을 중앙에 예쁘게 배치하기 위한 '포장지' 패널을 하나 더 만들어.
        //    GridBagLayout은 컴포넌트를 중앙에 배치하는 데 아주 좋아.
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        
        // 3. 포장지 패널 안에 폼 패널을 넣어.
        //    이렇게 하면 폼 패널은 자기가 필요한 만큼만 공간을 차지하게 돼.
        wrapperPanel.add(joinFormPanel);

        // 4. 최종적으로 '포장지' 패널을 프레임의 중앙에 추가하는 거야.
        //    이제 포장지가 남는 공간을 다 차지하고, 그 안의 폼은 가운데에 머물게 돼.
        add(wrapperPanel, BorderLayout.CENTER);
        
        // 하단에는 '이전으로' 버튼 패널을 추가
        add(createBackButtonPanel(frame), BorderLayout.SOUTH);
    }

    /**
     * GridBagLayout을 사용한 메인 회원가입 폼 패널을 생성하는 메소드
     */
    private JPanel createJoinFormPanel(MainFrame frame) {
        JPanel joinPanel = new JPanel(new GridBagLayout());
        
        // 1. "회원가입"이라는 제목이 있는 테두리를 생성
        // 2. 안쪽에 여백(padding)을 주기 위해 빈 테두리와 조합 (CompoundBorder)
        joinPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("회원가입"), //회원가입 글자 추가
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
        String[] labels = {"아이디: ", "비밀번호: ", "이름: ", "성별: ", "닉네임: ", "이메일: "};

        // 반복문을 사용해서 라벨과 필드를 한 줄씩 추가
        for (int i = 0; i < labels.length; i++) {
            addFormRow(joinPanel, gbc, labels[i], fields[i], i);
        }

        // 가입하기 버튼 추가
        JButton registerButton = new JButton("가입하기");
        gbc.gridx = 1;
        gbc.gridy = labels.length; // 마지막 줄 다음에 추가
        gbc.anchor = GridBagConstraints.EAST; // 오른쪽 끝에 붙이기
        joinPanel.add(registerButton, gbc); //

        // 가입하기 버튼 이벤트 처리
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Member m = new Member(
                        ((JTextField) fields[0]).getText(), //아이디
                        new String(((JPasswordField) fields[1]).getPassword()), //비밀번호
                        ((JTextField) fields[2]).getText(), //이름
                        ((JTextField) fields[3]).getText(), //성별
                        ((JTextField) fields[4]).getText(), //닉네임
                        ((JTextField) fields[5]).getText() //이메일
                );
                //mc.insertMember(m); // DB 컨트롤러 연결 부분
                JOptionPane.showMessageDialog(frame, "가입 완료!");
                frame.changePanel(new MainMenu(frame)); // 역할(role) 전달 필요
            }
        });

        return joinPanel;
    }

    /**
     * 반복되는 라벨과 필드 추가 작업을 처리하는 헬퍼(helper) 메소드
     * @param panel 추가될 부모 패널
     * @param gbc GridBagConstraints 객체
     * @param labelText 라벨에 표시될 텍스트
     * @param component 라벨 옆에 추가될 컴포넌트 (JTextField, JPasswordField 등)
     * @param gridy 컴포넌트가 위치할 y 좌표 (몇 번째 줄인지)
     */
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
    private JPanel createBackButtonPanel(MainFrame frame) {
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); //panel을 생성하고 panel 안에 FlowLayout을 중앙에 생성
        JButton startMenuBackBtn = new JButton("이전으로"); //이전 버튼을 생성
        startMenuBackBtn.setPreferredSize(new java.awt.Dimension(120, 30));
        backButtonPanel.add(startMenuBackBtn); //panel에 추가

        startMenuBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changePanel(new MainMenu(frame)); //main으로 화면 전환
            }
        });

        return backButtonPanel; //설정한 panel 객체 반환
    }
}
