package com.kh.miniproject.view.server;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.kh.miniproject.common.ViewUtils;
import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;

public class SearchMenu extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private MemberController mc;
	private JTextArea resultArea; // 결과를 보여줄 JTextArea

	public SearchMenu(MainFrame frame, Member member) {
        this.mc = new MemberController();
        // 전체 구조를 BorderLayout으로 잡는다
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // 상단 패널 검색 버튼
        add(createAllSelectPanel(frame, member), BorderLayout.NORTH);

        // --- 중앙(CENTER): 결과 표시 영역 ---
        add(createTextAreaPanel(), BorderLayout.CENTER);
        
        // --- 하단(SOUTH): 뒤로가기 버튼 ---
        add(craeteBackPanel(frame, member), BorderLayout.SOUTH);
    }
	
	//상단 패널
	private JPanel createAllSelectPanel(MainFrame frame, Member member) {
		//MemberController에서 처리 후 반환
		return ViewUtils.createButtonPanel("전체 회원 조회", e -> mc.selectMember(frame, member, resultArea));
	}
	
	//중단 패널
	private JScrollPane createTextAreaPanel() {
        resultArea = new JTextArea(20, 40);
        resultArea.setEditable(false); //수정 금지
        JScrollPane scrollPane = new JScrollPane(resultArea); // 스크롤 기능
        return scrollPane;
	}
	
	//하단 패널
	private JPanel craeteBackPanel(MainFrame frame, Member member) {
		return ViewUtils.createButtonPanel("뒤로가기", e -> frame.changePanel(new ManagementMenu(frame, member)));
	}
}
