package ski.mashiro.file;

import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.Plugin;
import ski.mashiro.pojo.Home;
import ski.mashiro.data.HomeData;
import ski.mashiro.util.Utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author MashiroT
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class HomeFiles {
    public static Plugin plugin;

    public static void createHomeFolder() {
        File homeFolder = new File(plugin.getDataFolder() + "/Homes");
        if (!homeFolder.exists()) {
            homeFolder.mkdir();
            return;
        }
        if (!homeFolder.isDirectory()) {
            if (homeFolder.delete()) {
                homeFolder.mkdir();
            }
        }
    }

    public static void saveHomeData() {
        File homeFolder = new File(plugin.getDataFolder() + "/Homes");
        if (!homeFolder.exists()) {
            createHomeFolder();
        }
        if (HomeData.PLAYER_HOME.isEmpty()) {
            return;
        }
        try {
            for (Map.Entry<String, List<Home>> homeListEntry : HomeData.PLAYER_HOME.entrySet()) {
                File homeFile = new File(plugin.getDataFolder() + "/Homes" + homeListEntry.getKey() + ".yml");
                if (homeFile.createNewFile()) {
                    FileUtils.write(homeFile, Utils.OBJECT_MAPPER.writeValueAsString(homeListEntry.getValue()), "utf-8");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadHomeData() {
        File homeFolder = new File(plugin.getDataFolder() + "/Homes");
        if (!homeFolder.exists()) {
            createHomeFolder();
        }
        File[] homeFiles = homeFolder.listFiles();
        if (homeFiles == null || homeFiles.length == 0) {
            return;
        }
        CollectionType collectionType = Utils.OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, Home.class);
        try {
            for (File homeFile : homeFiles) {
                HomeData.PLAYER_HOME.put(homeFile.getName().substring(0, homeFile.getName().indexOf(".")), Utils.OBJECT_MAPPER.readValue(FileUtils.readFileToString(homeFile, "utf-8"), collectionType));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
