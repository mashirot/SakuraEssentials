package ski.mashiro;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ski.mashiro.command.Home;
import ski.mashiro.command.Tp;

import java.util.Objects;

/**
 * @author MashiroT
 */
public final class SakuraEssentials extends JavaPlugin {

    @Override
    public void onLoad() {
        this.getLogger().info("SakuraEssentials 加载中");
    }

    @Override
    public void onEnable() {
        Objects.requireNonNull(Bukkit.getPluginCommand("sakura")).setExecutor(new Tp());
        Objects.requireNonNull(Bukkit.getPluginCommand("sakura")).setExecutor(new Home());

        this.getLogger().info("SakuraEssentials 启动成功");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
