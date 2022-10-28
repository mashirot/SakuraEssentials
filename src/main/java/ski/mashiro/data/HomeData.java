package ski.mashiro.data;

import org.bukkit.entity.Player;
import ski.mashiro.command.Home;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author MashiroT
 */
public class HomeData {
    private HomeData() {}
    public static final Map<Player, List<Home>> PLAYER_HOME = new Hashtable<>();

    // 写入文件

    // 读取数据
}
