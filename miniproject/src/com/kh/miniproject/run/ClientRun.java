package com.kh.miniproject.run;

import com.kh.miniproject.view.client.ClientMainFrame;

public class ClientRun {
    
    public static void main(String[] args) {
        // 클라이언트용 GUI를 띄우고
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClientMainFrame();
            }
        });
    }
}
