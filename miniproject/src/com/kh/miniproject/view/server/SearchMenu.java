package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;

public class SearchMenu extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private MemberController mc;
	private JTextArea resultArea; // 결과를 보여줄 JTextArea

	public SearchMenu(final MainFrame frame, final Member m) {
        this.mc = new MemberController();
        // 전체 구조를 BorderLayout으로 잡는다
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // --- 상단(NORTH): 검색 버튼 ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton searchButton = new JButton("전체 회원 조회");
        topPanel.add(searchButton);
        
        // --- 중앙(CENTER): 결과 표시 영역 ---
        resultArea = new JTextArea(20, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea); // 스크롤 기능
        
        // --- 하단(SOUTH): 뒤로가기 버튼 ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("뒤로가기");
        bottomPanel.add(backButton);

        // 완성된 패널들을 각 위치에 추가
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // --- 검색 버튼 이벤트 ---
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 컨트롤러에서 데이터만 받아온다
                mc.selectMember(frame, m, resultArea);
            }
        });
        
		// 뒤로가기 버튼
        backButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            frame.changePanel(new ManagementMenu(frame, m));
	        }
	    });
    }
}
