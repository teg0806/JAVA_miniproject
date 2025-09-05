package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.kh.miniproject.common.ButtonTamplate;
import com.kh.miniproject.sokect.server.ServerManager;
import com.kh.miniproject.vo.Member;

public class ChatMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    
    public ChatMenu(MainFrame frame, Member member) {
        setLayout(new BorderLayout());
        
        // 화면 분할(JSplitPane)을 제거하고 서버 패널만 중앙에 배치
//        add(createServerPanel(frame, m), BorderLayout.CENTER);
        
        //TextArea 패널 추가
        add(createTextAreaPanel(), BorderLayout.CENTER);
        
        //하단 패널 추가
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(createSendPanel(frame), BorderLayout.CENTER);
        bottomPanel.add(createBackPanel(frame, member), BorderLayout.SOUTH);
        
        add(bottomPanel, BorderLayout.SOUTH);
        
        frame.setTitle("채팅 서버 관리");
        setVisible(true);
    }
    
    //TextArea 패널 
    private JPanel createTextAreaPanel() {
    	// 전체를 감싸는 메인 패널 (BorderLayout 사용
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5)); //간격 5px
        
        // 가장 커져야 할 JTextArea를 CENTER에 바로 넣는 게 핵심이
        JTextArea logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        ServerManager.getmanager().setLogArea(logArea);
        
        mainPanel.add(new JScrollPane(logArea));
        return mainPanel;
    }
    
    //전송 패널
    private JPanel createSendPanel(MainFrame frame) {
    	JPanel sendPanel = new JPanel(new BorderLayout());
        JButton sendButton = new JButton("전송");

        JTextField messageField = new JTextField(); 
        
        sendPanel.add(messageField, BorderLayout.CENTER);
        sendPanel.add(sendButton, BorderLayout.EAST);
        
        addSendAction(frame, sendButton, messageField);
        
        return sendPanel;
    }
    
    //채팅방 나가는 버튼 패널
    private JPanel createBackPanel(MainFrame frame, Member member) {
    	return ButtonTamplate.createButtonPanel("채팅 나가기", e -> frame.changePanel(new ServerMenu(frame, member)));
    }
    
    private void addSendAction(MainFrame frame, JButton sendButton, JTextField messageField) {
    	//Runnalbe로 중복 처리
    	Runnable send = () -> { //파라미터를 받지 않음
    		//실행 내용 부분
    		String msg = messageField.getText();
			if(msg != null & !msg.trim().isEmpty()) {
				// 서버 관리자가 보내는 메시지라는 것을 알리기 위해 "[서버]" 같은 접두사를 붙임
				//static 메서드로 미리 생성하여 new를 사용하지 않으며, 매개변수로 받을 필요가 없음
				ServerManager.getmanager().broadcast("[서버 공지]: " + msg); 
				
	            messageField.setText(""); // 입력창 비우기
			} else {
				JOptionPane.showMessageDialog(frame, "전체 클라이언트에 보낼 메시지를 입력해주세요!");
			}
		};
		
		//상호작용 마다 Runnable로 생성한 기능을 적용
		sendButton.addActionListener(e -> send.run());
		messageField.addActionListener(e -> send.run());
    	
		//람다식와 Runnable 없이 사용했던 부분 
    	/*
        //전송 버튼
        sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = messageField.getText();
				if(msg != null & !msg.trim().isEmpty()) {
					// 서버 관리자가 보내는 메시지라는 것을 알리기 위해 "[서버]" 같은 접두사를 붙여주면 좋아.
					//static 메서드로 미리 생성하여 new를 사용하지 않으며, 매개변수로 받을 필요가 없음
					ServerManager.getInstance().broadcast("[서버 공지]: " + msg); 
					
		            messageField.setText(""); // 입력창 비우기
				} else {
					JOptionPane.showMessageDialog(frame, "전체 클라이언트에 보낼 메시지를 입력해주세요!");
				}
			}
		});
        
        messageField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = messageField.getText();
				if(msg != null & !msg.trim().isEmpty()) {
					// 서버 관리자가 보내는 메시지라는 것을 알리기 위해 "[서버]" 같은 접두사를 붙여주면 좋아.
					//static 메서드로 미리 생성하여 new를 사용하지 않으며, 매개변수로 받을 필요가 없음
					ServerManager.getInstance().broadcast("[서버 공지]: " + msg); 

		            messageField.setText(""); // 입력창 비우기
				} else {
					JOptionPane.showMessageDialog(frame, "전체 클라이언트에 보낼 메시지를 입력해주세요!");
				}
			}
		});*/
    }
    
}
