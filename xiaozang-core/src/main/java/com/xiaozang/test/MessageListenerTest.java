package com.xiaozang.test;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by Zangyy on 2016/7/25.
 */

public class MessageListenerTest implements MessageListener{

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListenerTest.class);

    private static final Logger ONS_LOGGER = LoggerFactory.getLogger("com.dustpan.taobao.ons");


    public Action consume(Message message, ConsumeContext context) {
        String msgBody;
        try {
            msgBody = new String(message.getBody(), "UTF-8");
        } catch (Exception e) {
            LOGGER.error("Parse ons message error|msg_id={}|tag={}", message.getMsgID(), message.getTag());
            return Action.CommitMessage;
        }
        ONS_LOGGER.warn("receive ons topic=|" + message.getTopic() + "|tag=" + message.getTag() + "|key=" + message.getKey() + "|id=" + message.getMsgID() + "|msgBody=" + msgBody);
        try {

            boolean res = true;

            if (res) {
                return Action.CommitMessage;
            } else {
                return Action.ReconsumeLater;
            }
        } catch (Exception e) {
            LOGGER.error("topic=|" + message.getTopic() + "|tag=" + message.getTag() + "|key=" + message.getKey() + "|msgBody=" + msgBody, e);
            return Action.ReconsumeLater;
        }
    }

    private String getString(JsonObject object, String key) {
        JsonPrimitive ele = object.getAsJsonPrimitive(key);
        if (ele == null) {
            return "";
        }
        return ele.getAsString();
    }


}
