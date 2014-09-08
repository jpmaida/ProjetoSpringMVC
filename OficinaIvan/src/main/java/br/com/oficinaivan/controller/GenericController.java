package br.com.oficinaivan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;

public abstract class GenericController {
	protected final String MSGSUCCESS = "MSGSUCCESS";
	
	@Autowired
	@Qualifier("messageSource")
	private MessageSource messageSource;

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}