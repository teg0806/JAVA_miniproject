package com.kh.miniproject.run;

import com.kh.miniproject.view.MainFrame;

public class Run {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();  // 프로그램 시작
            }
        });
    }
}
