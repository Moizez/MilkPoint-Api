package com.milkpointapi.repository;

import com.milkpointapi.model.SmsRequest;

public interface SmsSender {

	void sendSms(SmsRequest smsRequest);
}
