package com.kh.miniproject.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.kh.miniproject.vo.Member;

public class ChatMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea messageArea;
    private JTextField textField;
    private PrintWriter writer;
    
    public ChatMenu(MainFrame frame, Member m) {
    	initUI();
    	frame.setSize(600, 450);
        frame.setTitle("채팅 클라이언트 - " + m.getUserNickName());
        setVisible(true);
    }
    
    private void initUI() {
        messageArea = new JTextArea(20, 50);
        messageArea.setEditable(false);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        textField = new JTextField(40);
        JButton sendButton = new JButton("전송");
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(textField);
        bottomPanel.add(sendButton);
        add(bottomPanel, BorderLayout.SOUTH);
        
        sendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
        
    }
}