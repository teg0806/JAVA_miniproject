package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.kh.miniproject.vo.Member;

public class ChatMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private boolean isClientCreated = false; //클라이언트 제한을 위한 플래그
    
    public ChatMenu(MainFrame frame, Member m) {
        setLayout(new BorderLayout());
        
        // 화면 분할(JSplitPane)을 제거하고 서버 패널만 중앙에 배치
        add(createServerPanel(frame, m), BorderLayout.CENTER);

        frame.setSize(500, 600); // 서버 관리창에 맞는 크기로 조정
        frame.setTitle("채팅 서버 관리");
        setVisible(true);
    }

    /**
     * 서버 관리 및 내역 표시를 위한 패널 생성
     */
    private JPanel createServerPanel(MainFrame frame, Member m) { // Member 객체를 인자로 받도록 수정
        JPanel serverPanel = new JPanel(new BorderLayout());
        
        // 서버 관리용 컴포넌트(버튼 등)를 담을 패널
        JPanel serverControlPanel = new JPanel();
        JButton startServerButton = new JButton("서버 시작");
        JButton stopServerButton = new JButton("서버 종료");
        JButton createClientButton = new JButton("클라이언트 생성"); // 새 클라이언트 창을 띄울 버튼
        JButton backMenuButton = new JButton("뒤로가기"); //MemberMenu로 돌아가는 버튼

        serverControlPanel.add(startServerButton);
        serverControlPanel.add(stopServerButton);
        serverControlPanel.add(createClientButton);
        serverControlPanel.add(backMenuButton);
        


        // 서버 로그나 채팅 내역을 표시할 JTextArea
        JTextArea logArea = new JTextArea();
        logArea.setEditable(false);

        serverPanel.add(serverControlPanel, BorderLayout.NORTH);
        serverPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);
        
        // '클라이언트 생성' 버튼 이벤트 처리
        createClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 새 클라이언트 창(ClientFrame)을 생성
            	if(!isClientCreated) {	
            		new ClientFrame(m);
            		isClientCreated = true;
            	} else {
            		JOptionPane.showMessageDialog(logArea, "클라이언트가 이미 생성되었습니다.");
            	}
            }
        });
        
        startServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 서버 시작
            	 
            }
        });
        
        stopServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 서버 종료
            	
            }
        });
        //뒤로 가기
        backMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changePanel(new MemberMenu(frame, m));
			}
		});

        return serverPanel;
    }
    
}
