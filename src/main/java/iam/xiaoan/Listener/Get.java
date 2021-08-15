package iam.xiaoan.Listener;

import iam.xiaoan.Image;
import iam.xiaoan.RanImage;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import net.mamoe.mirai.message.data.PlainText;

import java.io.IOException;


public class Get {
    @EventHandler
    public static void onNeed() {
        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, event -> {
            MessageChain chain = event.getMessage();
            if (chain.contentToString().startsWith("来点壁纸")) {
                try {
                    event.getGroup().sendMessage(MessageUtils.newChain(
                            new At(event.getSender().getId()),
                            Image.getImage(event.getGroup(),"http://www.dmoe.cc/random.php?return=json", "imgurl")
                    ));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (chain.contentToString().startsWith("来点涩图")) {
                if (RanImage.r18) {
                    try {
                        event.getGroup().sendMessage(MessageUtils.newChain(
                                new At(event.getSender().getId()),
                                new PlainText(" 吸溜~"),
                                Image.getSex(event.getGroup())
                        ));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else {
                    try {
                        event.getGroup().sendMessage(MessageUtils.newChain(
                                new At(event.getSender().getId()),
                                new PlainText("暂未开启R18功能，老实看壁纸！"),
                                Image.getImage(event.getGroup(),"http://www.dmoe.cc/random.php?return=json", "imgurl")
                        ));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else if (chain.contentToString().startsWith("开启R18")) {
                if (event.getSender().getId() == 286526681) {
                    RanImage.r18 = true;
                    event.getGroup().sendMessage(MessageUtils.newChain(
                            new At(event.getSender().getId()),
                            new PlainText(" 主人，开启成功~")
                    ));
                }else {
                    event.getGroup().sendMessage(MessageUtils.newChain(
                            new At(event.getSender().getId()),
                            new PlainText(" 滚！！！")
                    ));
                }
            }else if (chain.contentToString().startsWith("关闭R18")) {
                RanImage.r18 = false;
                event.getGroup().sendMessage(MessageUtils.newChain(
                        new At(event.getSender().getId()),
                        new PlainText(" 关闭成功！")
                ));
            }
        });
    }
}
