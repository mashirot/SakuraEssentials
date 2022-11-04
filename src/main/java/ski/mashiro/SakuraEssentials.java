package ski.mashiro;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ski.mashiro.command.Command;
import ski.mashiro.data.DropItemData;
import ski.mashiro.file.ConfigFiles;
import ski.mashiro.file.HomeFiles;
import ski.mashiro.file.MessageFiles;
import ski.mashiro.listener.Listener;
import ski.mashiro.net.UpdateCheck;

import java.util.Objects;

/**
 * @author MashiroT
 */
@SuppressWarnings("unused")
public final class SakuraEssentials extends JavaPlugin {

    @Override
    public void onLoad() {
        this.getLogger().info("加载中");
        ConfigFiles.plugin = this;
        ConfigFiles.createConfig();
        HomeFiles.plugin = this;
        HomeFiles.createHomeFolder();
        MessageFiles.createMessageFile(this);
    }

    @Override
    public void onEnable() {
        ConfigFiles.loadConfig();
        HomeFiles.loadHomeData();
        MessageFiles.loadMessage(this);
        Objects.requireNonNull(Bukkit.getPluginCommand("sakura")).setExecutor(new Command());
        Objects.requireNonNull(Bukkit.getPluginCommand("sakura")).setTabCompleter(new Command());
        Bukkit.getPluginManager().registerEvents(new Listener(), this);
        DropItemData.clearDropItem(this);
        this.getLogger().info("启动成功");
        UpdateCheck.checkUpdate(this);
        new Metrics(this, 16747);
    }

    @Override
    public void onDisable() {
        ConfigFiles.saveConfig();
        HomeFiles.saveHomeData();
        this.getLogger().info("卸载成功");
    }
}
