package com.csy.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfigure {
	/**
	 * 计算彩种属性的队列
	 * @return
	 */
	@Bean
    public Queue propertyGenerateQueue() {
        return new Queue("PropertyGenerate");
    }


}
