package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.kh.miniproject.common.GridFormTemplate;
import com.kh.miniproject.common.ButtonPanelTemplate;
import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;

public class UpdateMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private MemberController mc;
    private static MainFrame frame;

    public UpdateMenu(MainFrame frame, Member member) {
        super();
        UpdateMenu.frame = frame;
        this.mc = new MemberController();
        frame.setTitle("회원 수정 메뉴");
        
        //전체 패널 추가
        setLayout(new BorderLayout());
        
        //중단 패널을 감싸줄 패널 생성
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        
        //중단 패널 추가
        wrapperPanel.add(createUpdatePanel(member));
        add(wrapperPanel, BorderLayout.CENTER);
        
        //하단 패널 추가
        add(createBackPanel(member), BorderLayout.SOUTH);
    }
    
    //내부 클래스로 파일을 새로 생성하지 않고 안에서 처리
    public JPanel createUpdatePanel(Member member) {
        class UpdateForm extends GridFormTemplate {
            private static final long serialVersionUID = 1L;

            public UpdateForm() {
            	//테두리
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("수정할 회원"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                
                //텍스트 필드 배열
                JComponent[] fields = { new JTextField(15), new JTextField(15), new JTextField(15) };
                
                //라벨 배열
                String[] labels = {"아이디: ", "닉네임: ", "이메일: "};

                //반복분으로 라벨 길이만큼 반복, 라벨과 텍스트 배치
                for (int i = 0; i < labels.length; i++) {
                    addFormRow(labels[i], fields[i], i);
                }
                
                //버튼 생성
                GridBagConstraints gbc = getGbc();
                JButton registerButton = new JButton("수정하기");
                gbc.gridx = 1;
                gbc.gridy = labels.length;
                gbc.anchor = GridBagConstraints.EAST;
                
                //버튼 배치
                add(registerButton, gbc);
                
                //수정 버튼 누른 후의 상호작용
                registerButton.addActionListener(e -> {
                	//MemberController에 전달 후 처리
                    mc.updateMember(new Member(
                            ((JTextField) fields[0]).getText(),
                            ((JTextField) fields[1]).getText(),
                            ((JTextField) fields[2]).getText()));
                });
            }
        }
        return new UpdateForm();
    }
    
    private JPanel createBackPanel(Member member) {
        // ViewUtils를 사용하여 뒤로가기 버튼 생성
        return ButtonPanelTemplate.createButtonPanel("뒤로가기", e -> frame.changePanel(new ManagementMenu(frame, member)));
    }
    
    public static void updateSuccess(Member member) {
		JOptionPane.showMessageDialog(frame, "회원 정보가 수정하는데 성공하였습니다.");
		//화면 전환
        frame.changePanel(new ServerMenu(frame, member));
    }
    
    public static void updateFail() {
		JOptionPane.showMessageDialog(frame, "회정 정보를 수정하는데 실패하였습니다.");
    }

}