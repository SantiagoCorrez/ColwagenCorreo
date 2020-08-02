package com.demo.mail.service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import com.demo.mail.model.SendMail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class SendMailService {

	@Value("${spring.mail.username}")
	private String Remitente;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	public String enviarMail(SendMail request) {
		MimeMessage msg = javaMailSender.createMimeMessage();
		try {
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo(request.getTo());
		helper.setFrom(Remitente);
		//helper.setFrom(request.getFrom()); //en caso de parametrizar el remitente
		File file=new File("index.html");
		ClassPathResource path2=new ClassPathResource("index.html");
		byte[] encoded = Files.readAllBytes(path2.getFile().toPath());
		String body = new String(encoded, StandardCharsets.UTF_8);		
		helper.setText(body,true);
		FileSystemResource res = new FileSystemResource(new File("templatesEmails\\banderita.png"));
    	helper.addInline("banderita", res);
    	res = new FileSystemResource(new File("templatesEmails\\Car2.png"));
    	helper.addInline("Car2", res);
    	res = new FileSystemResource(new File("templatesEmails\\ducati.png"));
    	helper.addInline("ducati", res);
    	res = new FileSystemResource(new File("templatesEmails\\ElLikedin.png"));
    	helper.addInline("ElLikedin", res);
    	res = new FileSystemResource(new File("templatesEmails\\finazul.png"));
    	helper.addInline("finazul", res);
    	res = new FileSystemResource(new File("templatesEmails\\HojaAma.png"));
    	helper.addInline("HojaAma", res);
    	res = new FileSystemResource(new File("templatesEmails\\Moto.png"));
    	helper.addInline("Moto", res);
    	res = new FileSystemResource(new File("templatesEmails\\Colwagenchiquito.png"));
    	helper.addInline("Colwagenchiquito", res);
    	res = new FileSystemResource(new File("templatesEmails\\WhatsAppmen.png"));
    	helper.addInline("WhatsAppmen", res);
    	res = new FileSystemResource(new File("templatesEmails\\Yutube.png"));
    	helper.addInline("Yutube", res);
    	res = new FileSystemResource(new File("templatesEmails\\runrun.png"));
    	helper.addInline("runrun", res);
		helper.setSubject(request.getSubject());
		javaMailSender.send(msg);
		}catch (Exception e) {
			// TODO: handle exception
			return "correo fallido"+e;
		}
		
		return "correo enviado";
	}
	
}
