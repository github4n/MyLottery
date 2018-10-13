package com.csy.mq;


import com.alibaba.fastjson.JSON;
import com.csy.domain.GamePeriod;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class JsftGamePeriodSender {


    @Autowired
    private Queue propertyGenerateQueue;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(GamePeriod  gamePeriod){
        amqpTemplate.convertAndSend(propertyGenerateQueue.getName(), JSON.toJSONString(gamePeriod));
    }



 }
