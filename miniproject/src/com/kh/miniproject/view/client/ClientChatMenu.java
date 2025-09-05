package com.kh.miniproject.view.client;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.kh.miniproject.common.ButtonTamplate;
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
        
        //전체 레이아웃 생성
        setLayout(new BorderLayout());
        
        //TextArea 패널
        add(createTextAreaPanel(), BorderLayout.CENTER);

        // 하단 패널 생성
        JPanel WrapperPanel = new JPanel(new BorderLayout());

        // 남쪽 패널에 메시지 입력창과 뒤로가기 버튼을 차례대로 추가
        WrapperPanel.add(createMessagePanel(), BorderLayout.CENTER);
        WrapperPanel.add(createBackPanel(frame), BorderLayout.SOUTH);

        // 완성된 '남쪽 패널'을 프레임의 SOUTH에 추가
        add(WrapperPanel, BorderLayout.SOUTH);
        
        // ClientManager에게 채팅창 등록
        clientManager.setChatArea(chatArea);

    }
    
    //TextArea 패널 생성
    private JPanel createTextAreaPanel() {
    	JPanel mainPanel = new JPanel(new BorderLayout(5, 5)); //간격 5px
    	
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        mainPanel.add(new JScrollPane(chatArea));
        return mainPanel;
    }
    
    //하단 메시지 박스, 버튼 생성 및 패널 생성
    private JPanel createMessagePanel() {
    	JPanel messagePanel = new JPanel(new BorderLayout());
    	messageField = new JTextField();
        sendButton = new JButton("전송");
        messagePanel.add(messageField, BorderLayout.CENTER);
        messagePanel.add(sendButton, BorderLayout.EAST);
        
        Runnable send = () -> sendMessage();
        messageField.addActionListener(e -> send.run());
        sendButton.addActionListener(e -> send.run());
        
        return messagePanel;
    }

    // 메시지 전송 로직
    private void sendMessage() {
        String message = messageField.getText();
        if (message != null && !message.trim().isEmpty()) {
            clientManager.sendMessage(message); // 통신 담당자에게 메시지 전송을 요청
            messageField.setText(""); // 입력창은 다시 비워주고
        }
    }
    
    //채팅방 나가는 버튼 패널
    private JPanel createBackPanel(ClientMainFrame frame) {
    	return ButtonTamplate.createButtonPanel("채팅 나가기", e -> frame.changePanel(new ClientMainMenu(frame)));
    }

}