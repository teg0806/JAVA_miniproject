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
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 공통 여백
    }

    // 자식 클래스에서 이 메서드를 호출해서 폼의 행을 추가하도록 할 거야.
    protected void addFormRow(String labelText, JComponent component, int gridy) {
        // 라벨 생성 및 설정
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        Font currentFont = label.getFont();
        label.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 15));

        // 라벨 위치 설정 및 추가
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.anchor = GridBagConstraints.EAST;
        add(label, gbc);

        // 텍스트 필드 위치 설정 및 추가
        gbc.gridx = 1;
        gbc.gridy = gridy;
        gbc.anchor = GridBagConstraints.WEST;
        add(component, gbc);
    }

    // gbc 객체를 자식 클래스에서 쓸 수 있게 getter를 제공
    protected GridBagConstraints getGbc() {
        return gbc;
    }
}