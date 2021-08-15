package iam.xiaoan;

import iam.xiaoan.Listener.Get;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;

public final class RanImage extends JavaPlugin {
    public static boolean r18 = false;
    public static final RanImage INSTANCE = new RanImage();

    private RanImage() {
        super(new JvmPluginDescriptionBuilder("iam.xiaoan.RanImage", "1.0-SNAPSHOT")
                .author("xiaoan")
                .build());
    }

    @Override
    public void onEnable() {
        Get.onNeed();
        getLogger().info("Plugin loaded!");
        getLogger().info("你好！我是小安！感谢你使用这款随机二次元图片插件！");
    }
}