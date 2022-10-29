package ski.mashiro;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ski.mashiro.command.Home;
import ski.mashiro.command.TabCompleter;
import ski.mashiro.command.Tp;
import ski.mashiro.file.ConfigFiles;
import ski.mashiro.file.HomeFiles;
import ski.mashiro.listener.Listener;
import ski.mashiro.net.UpdateCheck;

import java.util.Objects;

/**
 * @author MashiroT
 */
public final class SakuraEssentials extends JavaPlugin {

    @Override
    public void onLoad() {
        this.getLogger().info("加载中");
        ConfigFiles.plugin = this;
        ConfigFiles.createConfig();
        HomeFiles.plugin = this;
        HomeFiles.createHomeFolder();
    }

    @Override
    public void onEnable() {
        ConfigFiles.loadConfig();
        HomeFiles.loadHomeData();
        Objects.requireNonNull(Bukkit.getPluginCommand("sakura")).setExecutor(new Tp());
        Objects.requireNonNull(Bukkit.getPluginCommand("sakura")).setExecutor(new Home());
        Objects.requireNonNull(Bukkit.getPluginCommand("sakura")).setTabCompleter(new TabCompleter());
        Bukkit.getPluginManager().registerEvents(new Listener(), this);
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
