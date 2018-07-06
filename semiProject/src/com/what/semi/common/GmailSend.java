package com.what.semi.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class GmailSend {
	// 전역변수 사용안함.

	// action
	public void GmailSet(String user, String text, String content, String filename) {// 수신자,제목,내용
		Properties p = System.getProperties();
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.host", "smtp.gmail.com"); // smtp 서버 호스트
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.port", "587"); // gmail 포트

		Authenticator auth = new MyAuthentication(); // 구글 계정 인증

		// session 생성 및 MimeMessage생성
		Session session = Session.getDefaultInstance(p, auth);
		MimeMessage msg = new MimeMessage(session);
		String fromName = "발신자 닉네임";
		String charSet = "UTF-8";

		try {
			// 편지보낸시간 설정
			msg.setSentDate(new Date());

			// 송신자 설정
			InternetAddress from = new InternetAddress();
			from = new InternetAddress(new String(fromName.getBytes(charSet), "8859_1") + "<lee032255100@gmail.com>");
			msg.setFrom(from);

			// 수신자 설정
			InternetAddress to = new InternetAddress(user);
			msg.setRecipient(Message.RecipientType.TO, to);

			// 제목 설정
			msg.setSubject(text, "UTF-8");

			msg.setText(content, "UTF-8"); // 내용 설정
			//
			MimeMultipart mp = new MimeMultipart();

			mp.setSubType("related");

			filename = "http://localhost:8181/sp/QRcode/KakaoTalk_20170331_022542716.jpg";

			MimeBodyPart mbp1 = new MimeBodyPart();

			String html =

					"<html>" +

							"<head><title></title></head>" +

							"<body>" +

							"<IMG SRC='" + filename + "' width=80% height=60%><br>" +

							"</body>" +

							"</html>";

			mbp1.setContent(html, "text/html");

			mp.addBodyPart(mbp1);

			msg.setContent(mp);
			//
			// 메일 송신
			Transport.send(msg);

			System.out.println("메일 발송을 완료하였습니다.");
		} catch (AddressException addr_e) { // 예외처리 주소를 입력하지 않을 경우
			JOptionPane.showMessageDialog(null, "메일을 입력해주세요", "메일주소입력", JOptionPane.ERROR_MESSAGE);
			addr_e.printStackTrace();
		} catch (MessagingException msg_e) { // 메시지에 이상이 있을 경우
			JOptionPane.showMessageDialog(null, "메일을 제대로 입력해주세요.", "오류발생", JOptionPane.ERROR_MESSAGE);
			msg_e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void GmailSetTxt(String user, String text, String content) {// 수신자,제목,내용
		Properties p = System.getProperties();
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.host", "smtp.gmail.com"); // smtp 서버 호스트
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.port", "587"); // gmail 포트

		Authenticator auth = new MyAuthentication(); // 구글 계정 인증

		// session 생성 및 MimeMessage생성
		Session session = Session.getDefaultInstance(p, auth);
		MimeMessage msg = new MimeMessage(session);
		String fromName = "what 관리자";
		String charSet = "UTF-8";

		try {
			// 편지보낸시간 설정
			msg.setSentDate(new Date());

			// 송신자 설정
			InternetAddress from = new InternetAddress();
			from = new InternetAddress(new String(fromName.getBytes(charSet), "8859_1") + "<whattoday2018@gmail.com>");
			msg.setFrom(from);

			// 수신자 설정
			InternetAddress to = new InternetAddress(user);
			msg.setRecipient(Message.RecipientType.TO, to);

			// 제목 설정
			msg.setSubject(text, "UTF-8");

			msg.setText(content, "UTF-8"); // 내용 설정

			// 메일 송신
			Transport.send(msg);

			System.out.println("메일 발송을 완료하였습니다.");
		} catch (AddressException addr_e) { // 예외처리 주소를 입력하지 않을 경우
			JOptionPane.showMessageDialog(null, "메일을 입력해주세요", "메일주소입력", JOptionPane.ERROR_MESSAGE);
			addr_e.printStackTrace();
		} catch (MessagingException msg_e) { // 메시지에 이상이 있을 경우
			JOptionPane.showMessageDialog(null, "메일을 제대로 입력해주세요.", "오류발생", JOptionPane.ERROR_MESSAGE);
			msg_e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendResume(String user, String text,String url){//수신자,제목,내용
        Properties p = System.getProperties();
        p.put("mail.smtp.starttls.enable", "true");    
        p.put("mail.smtp.host", "smtp.gmail.com");      // smtp 서버 호스트
        p.put("mail.smtp.auth","true");
        p.put("mail.smtp.port", "587");                 // gmail 포트
            
        Authenticator auth = new MyAuthentication();    //구글 계정 인증
          
        //session 생성 및  MimeMessage생성
        Session session = Session.getDefaultInstance(p, auth);
        MimeMessage msg = new MimeMessage(session);
        String fromName = "발신자 닉네임";
        String charSet = "UTF-8";
         
        try{
            // 편지보낸시간 설정
            msg.setSentDate(new Date());
              
            // 송신자 설정
            InternetAddress from = new InternetAddress() ;
            from = new InternetAddress(new String(fromName.getBytes(charSet), "8859_1") + "<whattoday2018@gmail.com>");
            msg.setFrom(from);
              
            // 수신자 설정
            InternetAddress to = new InternetAddress("yul2514@naver.com");
            msg.setRecipient(Message.RecipientType.TO, to);
             
            // 제목 설정
            msg.setSubject(text, "UTF-8");
            
            String content="오늘뭐해?를 이용해주셔서 감사합니다.";
            msg.setText(content, "UTF-8");  //내용 설정
            //
            MimeMultipart mp = new MimeMultipart();

            

            mp.setSubType("related");


 

            MimeBodyPart mbp1= new MimeBodyPart();

            String html = "<a href='http://localhost:8081/sp/"+url+"'>"+URLEncoder.encode("이력서 보러가기", "UTF-8")+"</a>";

 

            mbp1.setContent(html,"text/html");

 

            mp.addBodyPart(mbp1);

            msg.setContent(mp);
            //
            // 메일 송신
            Transport.send(msg);   
             
            System.out.println("메일 발송을 완료하였습니다.");
        }catch (AddressException addr_e) {  //예외처리 주소를 입력하지 않을 경우
            JOptionPane.showMessageDialog(null, "메일을 입력해주세요", "메일주소입력", JOptionPane.ERROR_MESSAGE);
            addr_e.printStackTrace();
        }catch (MessagingException msg_e) { //메시지에 이상이 있을 경우
            JOptionPane.showMessageDialog(null, "메일을 제대로 입력해주세요.", "오류발생", JOptionPane.ERROR_MESSAGE);
            msg_e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
