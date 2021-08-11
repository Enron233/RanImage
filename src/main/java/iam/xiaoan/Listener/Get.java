package iam.xiaoan.Listener;

import iam.xiaoan.Image;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;

import java.io.IOException;


public class Get {
    @EventHandler
    public static void onNeed() {
        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, event -> {
            MessageChain chain = event.getMessage();
            if (chain.contentToString().startsWith("来点壁纸")) {
                try {
                    event.getGroup().sendMessage(MessageUtils.newChain(new At(event.getSender().getId()), Image.getSeImage(event.getGroup())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
