package ski.mashiro.file;

import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.Plugin;
import ski.mashiro.pojo.Config;
import ski.mashiro.util.Utils;

import java.io.File;

/**
 * @author MashiroT
 */
public class ConfigFiles {
    public static Config config;
    public static Plugin plugin;
    public static void createConfig() {
        File pluginFolder = plugin.getDataFolder();
        File configFile = new File(plugin.getDataFolder() + "/Config.yml");
        try {
            if (!pluginFolder.exists()) {
                if (pluginFolder.mkdir()) {
                    if (!configFile.exists()) {
                        if (configFile.createNewFile()) {
                            FileUtils.write(configFile, Utils.OBJECT_MAPPER.writeValueAsString(new Config(plugin.getDescription().getVersion(), true)), "utf-8");
                            return;
                        }
                    }
                }
            }
            if (!configFile.exists()) {
                if (configFile.createNewFile()) {
                    FileUtils.write(configFile, Utils.OBJECT_MAPPER.writeValueAsString(new Config(plugin.getDescription().getVersion(), true)), "utf-8");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadConfig() {
        File configFile = new File(plugin.getDataFolder() + "/Config.yml");
        createConfig();
        try {
            config = Utils.OBJECT_MAPPER.readValue(FileUtils.readFileToString(configFile, "utf-8"), Config.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig() {
        File configFile = new File(plugin.getDataFolder() + "/Config.yml");
        createConfig();
        try {
            FileUtils.write(configFile, Utils.OBJECT_MAPPER.writeValueAsString(config), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
