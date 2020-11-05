package com.milkpointapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.milkpointapi.model.SmsRequest;
import com.milkpointapi.repository.SmsSender;

@Service
public class SmsService {

	private final SmsSender smsSender;

	@Autowired
	public SmsService(@Qualifier("twilio") TwilioSmsSender twilioSmsSender) {
		this.smsSender = twilioSmsSender;
	}

	public void sendSms(SmsRequest smsRequest) {
		smsSender.sendSms(smsRequest);
	}

}
