package ski.mashiro.data;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import ski.mashiro.command.Tp;
import ski.mashiro.pojo.OwnPlayer;

import java.util.*;

/**
 * @author MashiroT
 */
public class PlayerData {
    private PlayerData() {}
    public static final List<OwnPlayer> PLAYER_LIST = new Vector<>();
    public static final Map<Player, Location> PLAYER_DEATH_LOCATION = new Hashtable<>();

    public static OwnPlayer getPlayer(String playerName) {
        if (PLAYER_LIST.size() == 0 || PLAYER_LIST.size() == 1) {
            return null;
        }
        for (OwnPlayer player : PLAYER_LIST) {
            if (playerName.equals(player.getPlayer().getName())) {
                return player;
            }
        }
        return null;
    }

    public static boolean transportPlayerToPlayer(OwnPlayer player) {
        if (Tp.TPA.equals(player.getType())) {
            player.getRelatedPlayer().teleport(player.getPlayer().getLocation());
            return true;
        }
        if (Tp.TPAHERE.equals(player.getType())) {
            player.getPlayer().teleport(player.getRelatedPlayer().getLocation());
            return true;
        }
        return false;
    }

    public static boolean transportPlayerToDeathLocation(Player player) {
        if (PLAYER_DEATH_LOCATION.containsKey(player)) {
            player.teleport(PLAYER_DEATH_LOCATION.get(player));
            return true;
        }
        return false;
    }

}
