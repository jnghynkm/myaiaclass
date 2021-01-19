package com.aia.firstspring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aia.firstspring.domain.GuestMesageList;
import com.aia.firstspring.domain.GuestMesageList2;
import com.aia.firstspring.domain.GuestMessage;
import com.aia.firstspring.domain.GuestMessage2;

@Controller
public class GuestMessageController {

	@RequestMapping("/message/listXml")
	@ResponseBody
	public GuestMesageList listXml() {
		return getMessageList();
	}
	
	@RequestMapping("/message/listJson")
	@ResponseBody
	public GuestMesageList2 listJson() {
		return getMessageList2();
	}

	private GuestMesageList2 getMessageList2() {

		List<GuestMessage2> list = new ArrayList<GuestMessage2>();
		list.add(new GuestMessage2(1, "HI", new Date()));
		list.add(new GuestMessage2(2, "Hello", new Date()));
		list.add(new GuestMessage2(3, "bye", new Date()));

		return new GuestMesageList2(list);
	}

	private GuestMesageList getMessageList() {

		List<GuestMessage> list = new ArrayList<GuestMessage>();
		list.add(new GuestMessage(1, "HI", new Date()));
		list.add(new GuestMessage(2, "Hello", new Date()));
		list.add(new GuestMessage(3, "bye", new Date()));

		return new GuestMesageList(list);
	}

}
