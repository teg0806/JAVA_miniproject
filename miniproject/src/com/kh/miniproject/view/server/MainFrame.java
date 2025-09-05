package com.kh.miniproject.view.server;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public MainFrame() {
        setTitle("채팅 - 서버"); //스윙으로 만든 윈도우의 제목
        setSize(360, 540); //윈도우 크기
        setResizable(false); 
        setLocationRelativeTo(null); //중앙에 윈도우를 띄움
        setDefaultCloseOperation(EXIT_ON_CLOSE); //x로 창을 닫으면 프로그램에 종료.

        // 프로그램 시작 시 보여줄 첫 화면 설정
        setContentPane(new MainMenu(this));
        setVisible(true);
    }

    public void changePanel(JPanel newPanel) {
        setContentPane(newPanel);
        revalidate(); // 레이아웃을 다시 계산하도록 지시
        repaint();    // 화면을 다시 그리도록 지시
    }
}