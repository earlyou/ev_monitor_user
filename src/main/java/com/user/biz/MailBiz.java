package com.user.biz;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailBiz {
	
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String pwdresult, String pwdvalue) {
        
        // 수신 대상을 담을 ArrayList 생성
        ArrayList<String> toUserList = new ArrayList<>();
        
        // 수신 대상 추가
        toUserList.add(pwdresult);
        
        // 수신 대상 개수
        int toUserSize = toUserList.size();
        
        // SimpleMailMessage (단순 텍스트 구성 메일 메시지 생성할 때 이용)
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        
        // 수신자 설정
        simpleMessage.setTo((String[]) toUserList.toArray(new String[toUserSize]));
        
        // 메일 제목
        simpleMessage.setSubject("안녕하세요 EVmonitor 입니다. ");
        
        // 메일 내용
        simpleMessage.setText("고객님 비밀번호는 ["+pwdvalue+"] 입니다. "
        		+ "※ 절대 비밀번호를 타인에게 양도하지 마세요.");
        
        // 메일 발송
        javaMailSender.send(simpleMessage);
    }


	
	
}
