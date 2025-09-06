package com.kh.miniproject.view.server;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.kh.miniproject.common.GridFormTemplate;
import com.kh.miniproject.common.ButtonPanelTemplate;
import com.kh.miniproject.controller.MemberController;
import com.kh.miniproject.vo.Member;

public class JoinMenu extends JPanel {
    private static final long serialVersionUID = 1L;
    private MemberController mc;
    private static MainFrame frame;

    public JoinMenu(MainFrame frame) {
    	JoinMenu.frame = frame;
        this.mc = new MemberController();
        //전체 패널 생성
        setLayout(new BorderLayout());

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(createJoinFormPanel());
        add(wrapperPanel, BorderLayout.CENTER);
        add(createBackPanel(frame), BorderLayout.SOUTH);
    }
    //로그인 폼과 마찬가지로 내부클래스로 폼을 생성.
    private JPanel createJoinFormPanel() {
        class JoinForm extends GridFormTemplate {
            private static final long serialVersionUID = 1L;

            public JoinForm() {
            	//테두리
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("회원가입"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                //텍스트 필드 생성
                JComponent[] fields = {
                    new JTextField(15), new JPasswordField(15), new JTextField(15),
                    new JTextField(15), new JTextField(15), new JTextField(15)
                };
                
                //라벨 생성
                String[] labels = {"아이디: ", "비밀번호: ", "이름: ", "성별(F|M): ", "닉네임: ", "이메일: "};
                
                //라벨 길이만큼 반복
                for (int i = 0; i < labels.length; i++) {
                    addFormRow(labels[i], fields[i], i);
                }
                
                //가입하기 버튼 생성 후 배치
                JButton registerButton = new JButton("가입하기");
                GridBagConstraints gbc = getGbc();
                gbc.gridx = 1;
                gbc.gridy = labels.length;
                gbc.anchor = GridBagConstraints.EAST;
                add(registerButton, gbc);
                
                //가입하기 버튼 상호작용
                registerButton.addActionListener(e -> {
                	//MemberController에 입력한 값을 모두 전달
                    mc.insertMember(new Member(
                            ((JTextField) fields[0]).getText(),
                            new String(((JPasswordField) fields[1]).getPassword()),
                            ((JTextField) fields[2]).getText(),
                            ((JTextField) fields[3]).getText(),
                            ((JTextField) fields[4]).getText(),
                            ((JTextField) fields[5]).getText()));
                   
                });
            }
        }
        return new JoinForm();
    }
    
    private JPanel createBackPanel(MainFrame frame) {
    	//버튼 기능과 이름을 전달 후 버튼 패널을 반환.
        return ButtonPanelTemplate.createButtonPanel("이전으로", e -> frame.changePanel(new MainMenu(frame)));
    }
    
    public static void joinSuccess() {
    	JOptionPane.showMessageDialog(frame, "가입 완료");
        //그후 그전 화면으로 전환
    	 frame.changePanel(new MainMenu(frame));
    }
    
    public static void joinFail(int result) {
    	if (result == -1) { // 아이디 중복 (-1)
            JOptionPane.showMessageDialog(frame, "이미 사용 중인 아이디입니다.", "가입 실패", JOptionPane.ERROR_MESSAGE);
        } else { // 그 외 실패 (0)
            JOptionPane.showMessageDialog(frame, "가입에 실패했습니다. 다시 시도해주세요.", "가입 실패", JOptionPane.ERROR_MESSAGE);
        }
    }
}