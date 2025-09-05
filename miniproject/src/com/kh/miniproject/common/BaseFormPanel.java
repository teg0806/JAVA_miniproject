package com.kh.miniproject.common;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public abstract class BaseFormPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GridBagConstraints gbc;

    protected BaseFormPanel() {
    	//패널 생성
        setLayout(new GridBagLayout());
        //생성한 gridBag의 레이아웃은 엑셀처럼 테이블로 구성.
        //엑셀의 칸마다 객체를 넣기 위해 지정할 번호를 붙여야하기에 GridBagConstraints를 사용
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 공통 여백
    }

    // 자식 클래스에서 이 메서드를 호출해서 폼의 행을 추가하도록 할 거야.
    protected void addFormRow(String labelText, JComponent component, int gridy) {
        // 라벨 생성 및 설정
        JLabel label = new JLabel(labelText);
        //라벨의 텍스트를 오른쪽으로 정렬.
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        //폰트 객체 생성
        Font currentFont = label.getFont();//시스템 폰트
        label.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 15)); //폰트 이름과 스타일, 폰트 크기 설정

        // 라벨 위치 설정 및 추가
        gbc.gridx = 0;
        gbc.gridy = gridy; //for문의 i값을 가져와 열을 증가하며 라벨 텍스트를 배치
        gbc.anchor = GridBagConstraints.EAST; //왼쪽에 배치
        add(label, gbc); //패널에 추가

        // 텍스트 필드 위치 설정 및 추가
        gbc.gridx = 1;
        gbc.gridy = gridy;
        gbc.anchor = GridBagConstraints.WEST; //오른족에 배치
        add(component, gbc); //패널에 추가
    }

    // gbc 객체를 자식 클래스에서 쓸 수 있게 getter를 제공
    protected GridBagConstraints getGbc() {
        return gbc;
    }
}