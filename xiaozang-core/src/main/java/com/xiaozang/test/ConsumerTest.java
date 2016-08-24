package com.xiaozang.test;

import com.aliyun.openservices.ons.api.*;
import com.aliyun.openservices.ons.api.impl.rocketmq.ONSChannel;
import com.google.gson.*;

import java.util.Properties;

/**
 * Created by Zangyy on 2016/7/25.
 */

public class ConsumerTest {
    private static final String ONS_CONSUMER_ID = "CID_VENUS_MYGOD";
    private static final String ONS_TOPIC = "TopicTestONS";
    public static void consumerTest() {
        String consumerId = ONS_CONSUMER_ID;
        Properties properties = new Properties();

        properties.put(PropertyKeyConst.ConsumerId, consumerId);
        properties.put(PropertyKeyConst.AccessKey, "23090346");// 应用appkey，根据用户实际参数修改
        properties.put(PropertyKeyConst.SecretKey, "86cbf0523750dcc9e4cd223f02e8e4d9");// 应用密钥，根据用户实际参数修改
        properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.CLUSTERING);//集群消费，默认为集群消费模式
        properties.put(PropertyKeyConst.OnsChannel, ONSChannel.CLOUD);


        Consumer consumer = ONSFactory.createConsumer(properties);

        consumer.subscribe(ONS_TOPIC, "*", new MessageListenerTest());
        consumer.start();

        System.out.println("Consumer Started");

    }
    private static String getString(JsonObject object, String key) {
        JsonPrimitive ele = object.getAsJsonPrimitive(key);
        if (ele == null) {
            return "";
        }
        return ele.getAsString();
    }
}