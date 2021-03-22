package com.milkpointapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.milkpointapi.model.SmsRequest;
import com.milkpointapi.service.SmsService;

@RestController
@RequestMapping("api/sms")
public class SmsResource {

	private final SmsService smsService;

	@Autowired
	public SmsResource(SmsService smsService) {
		this.smsService = smsService;
	}

	@PostMapping
	public void sendSMS(@Validated @RequestBody SmsRequest smsRequest) {
		System.out.println("Mensagem enviada!");
		System.out.println(smsRequest);	
		smsService.sendSms(smsRequest);
	}

}
