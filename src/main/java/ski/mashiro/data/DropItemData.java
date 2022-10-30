package ski.mashiro.data;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.concurrent.*;

/**
 * @author MashiroT
 */
public class DropItemData {

    private static final ThreadPoolExecutor POOL = new ThreadPoolExecutor(2, 3, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3), new ThreadPoolExecutor.AbortPolicy());

    public static Integer clearDropItem(World world) throws ExecutionException, InterruptedException {
        Callable<Integer> clearDrop = () -> {
            int index = 0;
            for (Entity entity : world.getEntities()) {
                if (entity.getType().equals(EntityType.DROPPED_ITEM)) {
                    entity.remove();
                    index++;
                }
            }
            return index;
        };
        return POOL.submit(clearDrop).get();
    }

}
