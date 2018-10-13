package com.csy.mq.first;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@RabbitListener(id="queue01")
public class Consumer {
	@Autowired
	AmqpTemplate amqpTemplate;
	
	@Bean
	public void receive() {
		Object receiveAndConvert = amqpTemplate.receiveAndConvert("queue01");
		System.out.println(receiveAndConvert);
	}
}
