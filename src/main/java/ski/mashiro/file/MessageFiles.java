package ski.mashiro.file;

import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.Plugin;
import ski.mashiro.pojo.Message;
import ski.mashiro.util.Utils;

import java.io.File;

/**
 * @author MashiroT
 */
public class MessageFiles {
    public static Message message;

    public static void createMessageFile(Plugin plugin) {
        File messageFile = new File(plugin.getDataFolder() + "/Message.yml");
        try {
            if (!messageFile.exists()) {
                if (messageFile.createNewFile()) {
                    FileUtils.write(messageFile, Utils.OBJECT_MAPPER.writeValueAsString(new Message()), "utf-8");
                }
            }
            if (!messageFile.isFile()) {
                if (messageFile.delete()) {
                    if (messageFile.createNewFile()) {
                        FileUtils.write(messageFile, Utils.OBJECT_MAPPER.writeValueAsString(new Message()), "utf-8");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadMessage(Plugin plugin) {
        createMessageFile(plugin);
        File messageFile = new File(plugin.getDataFolder() + "/Message.yml");
        try {
            message = Utils.OBJECT_MAPPER.readValue(messageFile, Message.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
