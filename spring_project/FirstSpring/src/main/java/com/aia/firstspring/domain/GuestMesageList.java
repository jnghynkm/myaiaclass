package com.aia.firstspring.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "message-list") // <message-list></message-list>
public class GuestMesageList {
	
	@XmlElement(name = "message")
	private List<GuestMessage> message;
	
	public GuestMesageList() {
	}
	
	public GuestMesageList(List<GuestMessage> message) {
		this.message = message;
	}

	public List<GuestMessage> getMessage() {
		return message;
	}

	public void setMessage(List<GuestMessage> message) {
		this.message = message;
	}
	
	

}
