package com.xiaozang.test;

import com.aliyun.openservices.ons.api.*;
import com.aliyun.openservices.ons.api.impl.rocketmq.ONSChannel;

import java.util.Properties;

/**
 * Created by Zangyy on 2016/7/25.
 */
public class ProducerTest {

    public static void producerTest() {

        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ProducerId, "PID_VENUS");
        properties.put(PropertyKeyConst.AccessKey, "23090346");
        properties.put(PropertyKeyConst.SecretKey, "86cbf0523750dcc9e4cd223f02e8e4d9");
        properties.put(PropertyKeyConst.OnsChannel, ONSChannel.CLOUD);
        properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.CLUSTERING);//集群消费，默认为集群消费模式

        Producer producer = ONSFactory.createProducer(properties);

        //在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
        producer.start();

        Message msg = new Message(

                "TopicTestONS", // Message Topic

                "TagA", // Message Tag

                "11111111111111111111111".getBytes()); // Message Body

// 发送消息，只要不抛异常就是成功

        SendResult sendResult = producer.send(msg);

        System.out.println(sendResult);

        producer.shutdown();// 在应用退出前，销毁Producer对象

    }

}
