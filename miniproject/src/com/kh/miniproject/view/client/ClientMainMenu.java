package com.kh.miniproject.view.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kh.miniproject.sokect.client.ClientManager;


public class ClientMainMenu extends JPanel{
    private static final long serialVersionUID = 1L;
    
    private JButton loginBtn;
    private JButton joinBtn;
    private JButton endBtn;
    private ClientManager clientManager; // ClientManager 멤버 변수 추가
    
    public ClientMainMenu(ClientMainFrame frame) {

        // 네트워크 관리자(NetworkManager)가 서버에 접속을 시도
        this.clientManager = new ClientManager(frame); // frame을 넘겨주도록 수정!
        clientManager.connectToServer();
    	
        setLayout(new BorderLayout());

        add(createTitlePanel(), BorderLayout.NORTH);
        add(CenterButtonPanel(frame), BorderLayout.CENTER);
        add(BackButtonPanel(frame), BorderLayout.SOUTH);
    }
    
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel mainLabel = new JLabel("채팅");
        mainLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        titlePanel.add(mainLabel);
        return titlePanel;
    }

    private JPanel CenterButtonPanel(ClientMainFrame frame) {
    	
        JPanel verticalButtonPanel = new JPanel(new GridLayout(2, 1, 0, 15));
        loginBtn = new JButton("로그인");
        joinBtn = new JButton("회원가입");
        
        loginBtn.setPreferredSize(new java.awt.Dimension(120, 30));
        joinBtn.setPreferredSize(new java.awt.Dimension(120, 30));

        verticalButtonPanel.add(loginBtn);
        verticalButtonPanel.add(joinBtn);

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(verticalButtonPanel);
        
        loginBtn.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent e) {
        	joinBtn.setVisible(false);
            endBtn.setVisible(false);
            loginBtn.setEnabled(false); // 로그인 버튼도 중복 클릭 방지
        	
            // LoginMenu로 이동
            frame.changePanel(new ClientLoginMenu(frame, clientManager));
	        }
	    });
	
		joinBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	joinBtn.setVisible(false);
		        endBtn.setVisible(false);
		        loginBtn.setEnabled(false);
		    	
		        // JoinMenu로 이동
		        frame.changePanel(new ClientJoinMenu(frame, clientManager));
		    }
		});
	    return wrapperPanel;
	}

	private JPanel BackButtonPanel(ClientMainFrame frame) {
	    JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    endBtn = new JButton("종료");
	    endBtn.setPreferredSize(new java.awt.Dimension(120, 30));
	    backButtonPanel.add(endBtn);
	
	    endBtn.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.exit(0);
	        }
	    });
	
	    return backButtonPanel;
	}
}


