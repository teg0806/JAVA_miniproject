package com.kh.miniproject.run;

import com.kh.miniproject.sokect.server.ServerManager;
import com.kh.miniproject.view.server.MainFrame;

public class ServerRun {
    public static void main(String[] args) {
		
		//서버 시작
		ServerManager.getmanager().startServer();
    	
        // 서버 관리자용 GUI 실행
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
