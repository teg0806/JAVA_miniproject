package com.kh.miniproject.view.server;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public MainFrame() {
        setTitle("채팅 - 서버");
        setSize(540, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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