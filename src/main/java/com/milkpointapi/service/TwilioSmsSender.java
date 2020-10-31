package com.milkpointapi.service;

import com.milkpointapi.model.SmsRequest;
import com.milkpointapi.repository.SmsSender;
import com.milkpointapi.utils.TwilioConfiguration;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("twilio")
public class TwilioSmsSender implements SmsSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);

	private final TwilioConfiguration twilioConfiguration;

	@Autowired
	public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
		this.twilioConfiguration = twilioConfiguration;
	}

	@Override
	public void sendSms(SmsRequest smsRequest) {

		if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
			PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
			PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
			String message = smsRequest.getMessage();
			MessageCreator creator = Message.creator(to, from, message);
			creator.create();
			LOGGER.info("Mensagem enviada {}" + smsRequest);
		} else {
			throw new IllegalArgumentException(
					"O número de telefone [" + smsRequest.getPhoneNumber() + "] não existe!");
		}

	}

	private boolean isPhoneNumberValid(String phoneNumber) {
		// Metodo para verificar numero do telefone
		return true;
	}

}
