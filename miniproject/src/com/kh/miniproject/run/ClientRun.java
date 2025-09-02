package com.kh.miniproject.run;

import com.kh.miniproject.view.client.ClientMainFrame;

public class ClientRun {
	
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClientMainFrame();  // 프로그램 시작
            }
        });
    }
}
