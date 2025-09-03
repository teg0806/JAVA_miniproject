package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.kh.miniproject.sokect.server.ServerManager;
import com.kh.miniproject.vo.Member;

public class ChatMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    
    public ChatMenu(MainFrame frame, Member m) {
        setLayout(new BorderLayout());
        
        // 화면 분할(JSplitPane)을 제거하고 서버 패널만 중앙에 배치
        add(createServerPanel(frame, m), BorderLayout.CENTER);

        frame.setTitle("채팅 서버 관리");
        setVisible(true);
    }

    /**
     * 서버 관리 및 내역 표시를 위한 패널 생성
     */
    private JPanel createServerPanel(MainFrame frame, Member m) { // Member 객체를 인자로 받도록 수정
    	// 전체를 감싸는 메인 패널 (BorderLayout 사용)
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5)); //간격 5px
        
        // 가장 커져야 할 JTextArea를 CENTER에 바로 넣는 게 핵심이
        JTextArea logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        ServerManager.getInstance().setLogArea(logArea);
        
        mainPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);
        
        // 하단에 들어갈 모든 것들을 담을 '남쪽 패널'을 하나 만들어.
        JPanel southPanel = new JPanel(new BorderLayout());

        // 남쪽 패널의 '가운데'에는 텍스트 필드와 전송 버튼을 넣어.
        JPanel sendPanel = new JPanel(new BorderLayout());
        JTextField messageField = new JTextField(); // 컬럼 수나 Dimension으로 크기 조절 안 해도 돼. CENTER가 알아서 늘려주니까.
        JButton sendButton = new JButton("전송");
        sendPanel.add(messageField, BorderLayout.CENTER);
        sendPanel.add(sendButton, BorderLayout.EAST);
        
        // 남쪽 패널의 '아래쪽'에는 뒤로가기 버튼을 넣어.
        JPanel backPanel = new JPanel(); // FlowLayout (기본값)
        JButton backMenuButton = new JButton("뒤로가기");
        backMenuButton.setPreferredSize(new Dimension(120, 30));
        backPanel.add(backMenuButton);
        
        // 남쪽 패널 조립!
        southPanel.add(sendPanel, BorderLayout.CENTER);
        southPanel.add(backPanel, BorderLayout.SOUTH);

        // 완성된 남쪽 패널을 메인 패널의 SOUTH에 추가.
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        
        //전송 버튼
        sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = messageField.getText();
				if(msg != null & !msg.trim().isEmpty()) {
					// 서버 관리자가 보내는 메시지라는 것을 알리기 위해 "[서버]" 같은 접두사를 붙여주면 좋아.
					//static 메서드로 미리 생성하여 new를 사용하지 않으며, 매개변수로 받을 필요가 없음
					ServerManager.getInstance().broadcast("[서버 공지]: " + msg); 

		            logArea.append("[나 -> 모두]: " + msg + "\n"); // 내 로그 창에도 표시
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

		            logArea.append("[나 -> 모두]: " + msg + "\n"); // 내 로그 창에도 표시
		            messageField.setText(""); // 입력창 비우기
				} else {
					JOptionPane.showMessageDialog(frame, "전체 클라이언트에 보낼 메시지를 입력해주세요!");
				}
			}
		});
        
        //뒤로 가기
        backMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changePanel(new ServerMenu(frame, m));
			}
		});

        return mainPanel;
    }
    
}
