package com.m.x.picture.message.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author xiao.mou_tic
 * @date 2019/9/25
 * @remark
 */
@RestController
@RequestMapping
public class IndexController {

    @RequestMapping(value = "index")
    public Mono<String> index() {
        return Mono.just("index");
    }

    @RequestMapping(value = "producer")
    public void producer(){
        DefaultMQProducer producer = new DefaultMQProducer ("BatchProducerGroupName");
        producer.setNamesrvAddr ("127.0.0.1:9876");
        try{
            producer.start();
            String topic = "BatchTest";
            List<Message> messages= new ArrayList<>();
            messages.add(new Message(topic, "Tag", "OrderIDOOl", "Hello world1".getBytes()));
            messages.add(new Message(topic, "Tag-A", "OrderIDOO2", "Hello world2".getBytes()));
            messages.add(new Message(topic, "Tag-B", "OrderIDOO3", "Hello world3".getBytes()));
            producer.shutdown();
            try {
                System.out.println(producer.send(messages));
            } catch (RemotingException e) {
                e.printStackTrace();
            } catch (MQBrokerException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }


    public void test(){

    


    }

}
