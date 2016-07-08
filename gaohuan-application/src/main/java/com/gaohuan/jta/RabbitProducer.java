package com.gaohuan.jta;

import org.springframework.amqp.core.AmqpTemplate;

/**
 * Created by acer on 2016/7/8.
 */
public class RabbitProducer {

    private AmqpTemplate rabbitTemplate;

    public void send(String msg) {
        rabbitTemplate.convertAndSend("rabbit_queue_key", msg);
    }

    public AmqpTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }

    public void setRabbitTemplate(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
