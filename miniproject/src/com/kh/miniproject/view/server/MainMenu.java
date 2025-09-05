package com.kh.miniproject.view.server;

import com.kh.miniproject.common.ViewUtils;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class MainMenu extends JPanel{
	//내가 클래스를 수정해서 코드가 달라지면 UID가 변경사항이 있는지 판단 
    private static final long serialVersionUID = 1L;

    public MainMenu(MainFrame frame) {
    	//전체 레이아웃을 생성 : Border은 주변을 강제적으로 설정하는 레이아웃
        setLayout(new BorderLayout());
        
        //레이아웃에 패널들을 추가
        add(createTitlePanel(), BorderLayout.NORTH); //전체 레이아웃의 위쪽
        add(createCenterButtonPanel(frame), BorderLayout.CENTER); //전체 레이아웃의 가운데이며, center는 동서남북에 비해 강제적으로 넓히려는 성향이 있어 사용시 주의
        add(craeteExitPanel(frame), BorderLayout.SOUTH); //전체 레이아웃의 아래쪽
    }
    
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); //기본값은 Flow이며 Flow의 중앙에 제목 페널 추가
        JLabel mainLabel = new JLabel("채팅"); //채팅이라는 문자열을 추가
        mainLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40)); //문자열 폰트
        titlePanel.add(mainLabel); //문자열을 패널에 추가
        return titlePanel; // 패널 자체를 반환
    }

    private JPanel createCenterButtonPanel(MainFrame frame) {
        //엑셀처럼 생긴 Grid는 row, col 값에 맞게 생성
        JPanel verticalButtonPanel = new JPanel(new GridLayout(2, 1, 0, 15)); //2개의 행, 1개의 열
        
        //자바 8버전 이전에는 익명클래스로 사용했지만, 람다식으로 인해 e 라는 객체를 받아 -> 를 이용해 다음에 올 코드를 실행하는 구조 
        //createButton는 버튼 텍스트, 기능을 담는 메서드로 다음 화면을 넘기는 기능을 전달하여 버튼을 생성하고 다시 불러와 패널에 추가되는 구조
        verticalButtonPanel.add(ViewUtils.createButton("로그인", e -> frame.changePanel(new LoginMenu(frame))));
        verticalButtonPanel.add(ViewUtils.createButton("회원가입", e -> frame.changePanel(new JoinMenu(frame))));

        JPanel wrapperPanel = new JPanel(new GridBagLayout()); //grid를 담는 gridbag
        wrapperPanel.add(verticalButtonPanel); // 만든 버튼들을 다시 담음

        return wrapperPanel;
    }

    private JPanel craeteExitPanel(MainFrame frame) {
    	//마찬가지로 changePanel이 아닌 시스템 종료 기능을 보내고 버튼을 만들어 다시 반환
        return ViewUtils.createButtonPanel("종료", e -> System.exit(0));
    }
}
