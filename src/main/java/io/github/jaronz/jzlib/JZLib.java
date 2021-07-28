package io.github.jaronz.jzlib;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginLogger;
import cn.nukkit.utils.TextFormat;

public class JZLib extends PluginBase {
    private PluginLogger logger;

    @Override
    public void onLoad() {
        logger = this.getLogger();
        logger.info(TextFormat.WHITE + this.getName() + " has been loaded!");
    }

    @Override
    public void onEnable() {
        logger.info(TextFormat.DARK_GREEN + this.getName() + " has been enabled!");
    }

    @Override
    public void onDisable() {
        logger.info(TextFormat.DARK_RED + this.getName() + " has been disabled!");
    }
}
