package com.kh.miniproject.common;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanelTemplate {

    // 버튼을 생성해서 반환하는 메서드
    public static JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 30));
        button.addActionListener(listener);
        return button;
    }

    // createButton을 담은 패널을 생성하는 메서드
    //사용한 부분 : 뒤로가기 버튼, 다음 화면으로 넘어가는 버튼
    public static JPanel createButtonPanel(String buttonText, ActionListener listener) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // 이제 버튼 생성 로직이 중복되지 않음
        JButton button = createButton(buttonText, listener);
        buttonPanel.add(button);
        return buttonPanel;
    }
}