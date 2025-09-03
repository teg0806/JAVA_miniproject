package com.kh.miniproject.view.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import com.kh.miniproject.sokect.client.ClientManager;
import com.kh.miniproject.vo.Member;

// JPanel을 상속받아서 만들자. 그래야 프레임에 붙일 수 있으니까.
public class ClientChatMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextArea chatArea; // 채팅 내용이 표시될 공간
    private JTextField messageField; // 메시지 입력창
    private JButton sendButton; // 전송 버튼
    private ClientManager clientManager; // 서버와 통신을 담당할 녀석

    public ClientChatMenu(ClientMainFrame frame, Member member, ClientManager clientManager) {
        this.clientManager = clientManager;
        setLayout(new BorderLayout(5, 5));

        // --- 중앙: 채팅 내용 ---
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        // --- 하단 전체를 감쌀 '남쪽 패널' 생성 ---
        JPanel southWrapperPanel = new JPanel(new BorderLayout());

        // --- 남쪽 패널의 '가운데' 부분: 메시지 입력창과 전송 버튼 ---
        JPanel messagePanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        sendButton = new JButton("전송");
        messagePanel.add(messageField, BorderLayout.CENTER);
        messagePanel.add(sendButton, BorderLayout.EAST);
        
//        messageField.addActionListener(e -> sendMessage());
        //엔터를 누르면 전송되는 메서드
        messageField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
        
//        sendButton.addActionListener(e -> sendMessage());
        sendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

        
        // --- 남쪽 패널의 '아래' 부분: 뒤로가기 버튼 ---
        JPanel backButtonPanel = BackButtonPanel(frame);

        // ★★★ 바로 여기가 핵심! ★★★
        // 남쪽 패널에 메시지 입력창과 뒤로가기 버튼을 차례대로 추가
        southWrapperPanel.add(messagePanel, BorderLayout.CENTER);
        southWrapperPanel.add(backButtonPanel, BorderLayout.SOUTH);

        // 완성된 '남쪽 패널'을 프레임의 SOUTH에 추가
        add(southWrapperPanel, BorderLayout.SOUTH);
        
        // ClientManager에게 채팅창 등록
        clientManager.setChatArea(chatArea);

    }

    // 메시지 전송 로직
    private void sendMessage() {
        String message = messageField.getText();
        if (message != null && !message.trim().isEmpty()) {
            clientManager.sendMessage(message); // 통신 담당자에게 메시지 전송을 요청
            messageField.setText(""); // 입력창은 다시 비워주고
        }
    }
    
    private JPanel BackButtonPanel(ClientMainFrame frame) {
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton mainMenuBackBtn = new JButton("메인 메뉴");
        mainMenuBackBtn.setPreferredSize(new java.awt.Dimension(120, 30));
        backButtonPanel.add(mainMenuBackBtn);

        mainMenuBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changePanel(new ClientMainMenu(frame));
            }
        });

        return backButtonPanel;
    }

}