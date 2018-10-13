package com.csy.mq.first;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class Producer {
	@Autowired
	AmqpTemplate amqpTemplate;
	
	
	public void send() {
		amqpTemplate.convertAndSend("queue01", "");
	}
}
