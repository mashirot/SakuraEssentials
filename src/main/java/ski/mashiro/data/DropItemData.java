package ski.mashiro.data;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;
import ski.mashiro.file.ConfigFiles;
import ski.mashiro.file.MessageFiles;
import ski.mashiro.util.Utils;

import java.util.concurrent.*;

/**
 * @author MashiroT
 */
@SuppressWarnings("AlibabaThreadShouldSetName")
public class DropItemData {
    private static final ScheduledExecutorService SCHEDULED_POOL = new ScheduledThreadPoolExecutor(1);
    public static void clearDropItem(Plugin plugin) {
        if (!ConfigFiles.config.isEnableCleanDropItem()) {
            return;
        }
        SCHEDULED_POOL.schedule(() -> {
            Bukkit.getScheduler().runTask(plugin, () -> {
                try {
                    int sum = 0;
                    for (World world : Bukkit.getWorlds()) {
                        int index = 0;
                        for (Entity entity : world.getEntities()) {
                            if (entity.getType().equals(EntityType.DROPPED_ITEM)) {
                                entity.remove();
                                index++;
                            }
                        }
                        sum += index;
                    }
                    Bukkit.broadcastMessage(Utils.transferSumPlaceHolder(MessageFiles.message.getClearDropMsg(), String.valueOf(sum)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }, 5, TimeUnit.MINUTES);
    }
}
