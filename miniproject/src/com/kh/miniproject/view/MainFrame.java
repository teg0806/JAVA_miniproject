package com.kh.miniproject.view;

import javax.swing.*;

import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	//프레임 세팅 메서드
	public MainFrame() {
		setTitle("패널 전환 예제"); // 프레임 제목
		setSize(1080, 720); //창 크기 (가로, 세로)
		setResizable(false); //창 크기 고정
		setLocationRelativeTo(null); //창이 화면 가운데에서 시작 
		setDefaultCloseOperation(EXIT_ON_CLOSE); //창을 끄면 프로그램 종료
		
		setContentPane(new StartMenu(this));
		setVisible(true); //창 보이도록 설정
	}
	
    // 패널 전환 메서드
    public void changePanel(JPanel newPanel) {
        setContentPane(newPanel);
        revalidate();
        repaint();
    }
    
}
