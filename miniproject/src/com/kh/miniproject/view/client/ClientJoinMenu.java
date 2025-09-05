package com.kh.miniproject.view.client;

import java.awt.BorderLayout;
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

import com.kh.miniproject.common.ViewUtils;
import com.kh.miniproject.sokect.client.ClientManager;

public class ClientJoinMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	private ClientManager clientManager;

    public ClientJoinMenu(ClientMainFrame frame, ClientManager clientManager) {
        this.clientManager = clientManager;
    	// 전체적인 레이아웃은 BorderLayout으로 설정
        setLayout(new BorderLayout());

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(createJoinFormPanel(frame));

        //중단 패널 추가
        add(wrapperPanel, BorderLayout.CENTER);
        
        //하단 패널 생성
        add(createBackPanel(frame), BorderLayout.SOUTH);
    }

    private JPanel createJoinFormPanel(ClientMainFrame frame) {
        JPanel joinPanel = new JPanel(new GridBagLayout());
        
        //회원가입이라는 제목이 있는 테두리를 생성
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
        String[] labels = {"아이디: ", "비밀번호: ", "이름: ", "성별(F|M): ", "닉네임: ", "이메일: "};

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
            	String userId = ((JTextField) fields[0]).getText();
                String userPwd = new String(((JPasswordField) fields[1]).getPassword());
                String userName = ((JTextField) fields[2]).getText();
                String gender = ((JTextField) fields[3]).getText();
                String nickName = ((JTextField) fields[4]).getText();
                String email = ((JTextField) fields[5]).getText();

                // 서버에 "JOIN:아이디:비번:이름:성별:닉네임:이메일" 형식으로 전송!
                String joinMessage = String.join(":", "JOIN", userId, userPwd, userName, gender, nickName, email);
                clientManager.sendMessage(joinMessage);
                
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

    private JPanel createBackPanel(ClientMainFrame frame) {
       	//버튼 기능과 이름을 전달 후 버튼 패널을 반환.
           return ViewUtils.createButtonPanel("이전으로", e -> frame.changePanel(new ClientMainMenu(frame)));
       }
}
