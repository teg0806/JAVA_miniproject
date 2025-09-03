package com.kh.miniproject.view.client;

import java.awt.BorderLayout;
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

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        sendButton = new JButton("전송");
        
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

        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        // ★★★ 여기가 핵심! ★★★
        // 생성되자마자 ClientManager에게 채팅 내용을 표시할 JTextArea가 바로 '이것'이라고 알려준다.
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

}