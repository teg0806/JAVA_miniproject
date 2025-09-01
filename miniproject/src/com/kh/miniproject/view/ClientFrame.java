package com.kh.miniproject.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.kh.miniproject.vo.Member;

public class ClientFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextArea messageArea;
    private JTextField textField;

    public ClientFrame(Member m) {
        setTitle("채팅 클라이언트 - " + m.getUserNickName());
        setSize(500, 400);
        // 이 창을 닫아도 전체 프로그램이 종료되지 않도록 설정
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationByPlatform(true); // OS가 창 위치를 적절히 결정하도록 함

        // 클라이언트 UI 구성
        JPanel clientPanel = new JPanel(new BorderLayout());
        
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        clientPanel.add(new JScrollPane(messageArea), BorderLayout.CENTER);

        // 메시지 입력 필드와 전송 버튼을 담을 하단 패널
        JPanel bottomPanel = new JPanel(new BorderLayout());
        textField = new JTextField();
        JButton sendButton = new JButton("전송");

        bottomPanel.add(textField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        
        clientPanel.add(bottomPanel, BorderLayout.SOUTH);

        // 전송 버튼 이벤트 처리
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 여기에 메시지 전송 로직을 구현하면 돼
            }
        });

        setContentPane(clientPanel);
        setVisible(true);
    }
}

