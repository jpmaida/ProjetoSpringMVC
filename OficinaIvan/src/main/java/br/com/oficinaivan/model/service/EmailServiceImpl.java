package br.com.oficinaivan.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import br.com.oficinaivan.model.exception.BusinessException;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	@Qualifier("emaiilSource")
	private MessageSource emailSource;
	
	@Autowired
	private MailSender mailSender;
	
	private void enviar(SimpleMailMessage message){
		message.setFrom(emailSource.getMessage("email.nick.from", null, null));
		message.setFrom(emailSource.getMessage("email.from", null, null));
		mailSender.send(message);
	}

	public void enviarEmailAniversario(String to, String aniversariante) throws BusinessException{
		try{
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(emailSource.getMessage("email.subject", null, null));
			message.setText(emailSource.getMessage("email.msg", new String[]{aniversariante}, null));
			this.enviar(message);
		} catch(Exception e) {
			e.printStackTrace();
			throw new BusinessException("email.nao.enviado");
		}
	}
	
	public MessageSource getEmailSource() {
		return emailSource;
	}

	public void setEmailSource(MessageSource emailSource) {
		this.emailSource = emailSource;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
}
