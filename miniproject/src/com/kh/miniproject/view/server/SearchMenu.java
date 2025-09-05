package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.kh.miniproject.common.ButtonPanelTemplate;
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
		return ButtonPanelTemplate.createButtonPanel("전체 회원 조회", e -> searchAction(frame));
	}
	
	//경고가 되어있는 이유는 작업하는 일이 많기 때문.
	private void searchAction(MainFrame frame) { 
        // 컨트롤러에게 '데이터'만 요청
        List<Member> list = mc.getMemberList();

        // View가 직접 데이터를 가공하고 UI를 업데이트
        updateTextArea(list);

        // View가 직접 사용자에게 피드백(JOptionPane)
        if (mc.saveMemberListToFile(list)) {
            JOptionPane.showMessageDialog(frame, "MemberList.txt 파일에 저장이 완료되었습니다.");
        } else {
            JOptionPane.showMessageDialog(frame, "파일 저장 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	//리스트를 받아 화면에 출력
    private void updateTextArea(List<Member> list) {
        if (list == null || list.isEmpty()) {
            resultArea.setText("조회된 회원이 없습니다.");
        } else {
            // 데이터 가공(문자열 만들기) 로직은 View의 책임이다.
            StringBuilder sb = new StringBuilder();
            sb.append("===== 전체 회원 목록 =====\n\n");
            for (Member m : list) {
                sb.append("회원번호: ").append(m.getUserNo()).append("\n");
                sb.append("아이디: ").append(m.getUserId()).append("\n");
                sb.append("비밀번호: ").append(m.getUserPwd()).append("\n");
                sb.append("이름: ").append(m.getUserName()).append("\n");
                sb.append("성별: ").append(m.getGender()).append("\n");
                sb.append("닉네임: ").append(m.getUserNickName()).append("\n");
                sb.append("이메일: ").append(m.getEmail()).append("\n");
                sb.append("---------------------------------\n");
            }
            resultArea.setText(sb.toString());
            resultArea.setCaretPosition(0); // 스크롤을 맨 위로
        }
    }
	
	//중단 패널
	private JScrollPane createTextAreaPanel() {
        resultArea = new JTextArea(20, 40);
        resultArea.setEditable(false); //수정 금지
        return new JScrollPane(resultArea); // 스크롤 기능
	}
	
	//하단 패널
	private JPanel craeteBackPanel(MainFrame frame, Member member) {
		return ButtonPanelTemplate.createButtonPanel("뒤로가기", e -> frame.changePanel(new ManagementMenu(frame, member)));
	}
	
}
