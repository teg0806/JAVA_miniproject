package com.kh.miniproject.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    // 배경 이미지를 그릴 전용 패널
    private JPanel backgroundPanel;
    // 현재 화면에 표시될 배경 이미지를 기억하는 변수
    private Image currentBackgroundImage;

    public MainFrame() {
        setTitle("마피아 게임");
        setSize(1080, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 1. 배경을 설정하고, 첫 화면을 그 위에 올린다.
        setupBackgroundAndInitialPanel();

        setVisible(true);
    }

    /**
     * 배경 패널을 설정하고, 그 위에 첫 화면(StartMenu)을 올리는 메소드
     */
    private void setupBackgroundAndInitialPanel() {
        // 첫 배경 이미지 로드
        currentBackgroundImage = new ImageIcon("images/start_background.jpg").getImage(); // 시작 화면 배경

        backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // currentBackgroundImage를 현재 패널 크기에 맞춰 그린다.
                g.drawImage(currentBackgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // 프레임의 기본 도화지를 backgroundPanel로 설정
        setContentPane(backgroundPanel);

        // 첫 화면인 StartMenu를 만들어서 올린다.
        StartMenu startMenu = new StartMenu(this);
        startMenu.setOpaque(false); // StartMenu를 투명하게 만들어야 배경이 보인다!
        backgroundPanel.add(startMenu);
    }

    /**
     * 패널과 배경 이미지를 함께 교체하는 '업그레이드된' 메소드
     */
    public void changePanel(JPanel newPanel) {
        // 1. 새로운 배경 이미지로 교체
//        currentBackgroundImage = new ImageIcon(imagePath).getImage();

        // 2. 기존에 있던 내용물(이전 패널)을 모두 제거
        backgroundPanel.removeAll();

        // 3. 새로 들어올 패널을 투명하게 설정
        newPanel.setOpaque(false);

        // 4. 배경 패널 위에 새로운 패널을 추가
        backgroundPanel.add(newPanel);

        // 5. 화면을 갱신해서 변경사항을 즉시 적용
        revalidate();
        repaint();
    }
}
