package com.kh.miniproject.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JPanel {
    private static final long serialVersionUID = 1L;

    public StartMenu(MainFrame frame) {
        // 이 패널이 투명해야 배경 이미지가 보인다
        setOpaque(false);
        // 전체 구조는 BorderLayout 사용
        setLayout(new BorderLayout());

        // 버튼들을 담을 패널을 만들어서 SOUTH 구역에 추가
        add(createButtonPanel(frame), BorderLayout.SOUTH);
    }

    /**
     * 시작/종료 버튼을 세로로 배치한 패널을 생성하는 메소드
     */
    private JPanel createButtonPanel(MainFrame frame) {
        // 1. 버튼들을 세로로 쌓을 GridLayout 패널 (이전과 동일)
        JPanel verticalButtonPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        verticalButtonPanel.setOpaque(false);

        JButton startBtn = new JButton("시작");
        JButton endBtn = new JButton("종료");

        // --- 여기가 핵심! ---
        // 2. 버튼의 '선호하는' 가로/세로 크기를 정해준다.
        startBtn.setPreferredSize(new java.awt.Dimension(120, 30)); // (가로, 세로)
        endBtn.setPreferredSize(new java.awt.Dimension(120, 30));

        verticalButtonPanel.add(startBtn);
        verticalButtonPanel.add(endBtn);

        // 3. --- 이것도 핵심! ---
        //    세로로 쌓인 버튼 패널을 가운데에 예쁘게 배치하기 위한 '포장지' 패널.
        //    FlowLayout은 자기가 감싸는 내용물의 크기를 존중해 줘.
        JPanel wrapperPanel = new JPanel(new java.awt.FlowLayout());
        wrapperPanel.setOpaque(false); // 이 패널도 투명하게!
        wrapperPanel.add(verticalButtonPanel); // 포장지에 버튼 묶음을 넣는다.
        

        // 이벤트 리스너는 그대로...
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changePanel(new MainMenu(frame));
            }
        });

        endBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // 4. 최종적으로 '포장지' 패널을 반환한다.
        return wrapperPanel;
    }
}
