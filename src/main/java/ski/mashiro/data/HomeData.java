package ski.mashiro.data;

import org.bukkit.entity.Player;
import ski.mashiro.file.HomeFiles;
import ski.mashiro.pojo.Home;

import java.util.*;

/**
 * @author MashiroT
 */
public class HomeData {
    private HomeData() {}
    public static final Map<String, List<Home>> PLAYER_HOME = new Hashtable<>();

    public static boolean addHome(Player player, String homeName) {
        if (PLAYER_HOME.isEmpty() || !PLAYER_HOME.containsKey(player.getName())) {
            PLAYER_HOME.put(player.getName(), List.of(new Home(homeName, player.getLocation())));
            return true;
        }
        for (Home home : PLAYER_HOME.get(player.getName())) {
            if (homeName.equals(home.getHomeName())) {
                return false;
            }
        }
        PLAYER_HOME.get(player.getName()).add(new Home(homeName, player.getLocation()));
        HomeFiles.saveHomeData();
        return true;
    }

    public static boolean delHome(String playerName, String homeName) {
        if (PLAYER_HOME.isEmpty()) {
            return false;
        }
        if (!PLAYER_HOME.containsKey(playerName)) {
            return false;
        }
        Iterator<Home> it = PLAYER_HOME.get(playerName).listIterator();
        while (it.hasNext()) {
            if (homeName.equals(it.next().getHomeName())) {
                it.remove();
                HomeFiles.saveHomeData();
                return true;
            }
        }
        return false;
    }

    public static boolean transportPlayerToHome(Player player, String homeName) {
        if (PLAYER_HOME.isEmpty()) {
            return false;
        }
        if (!PLAYER_HOME.containsKey(player.getName())) {
            return false;
        }
        for (Home home : PLAYER_HOME.get(player.getName())) {
            if (homeName.equals(home.getHomeName())) {
                player.teleport(home.getHomeLocation());
                return true;
            }
        }
        return false;
    }

    public static List<Home> listPlayerHome(String playerName) {
        if (PLAYER_HOME.isEmpty()) {
            return null;
        }
        for (Map.Entry<String, List<Home>> homeListEntry : PLAYER_HOME.entrySet()) {
            if (playerName.equals(homeListEntry.getKey())) {
                return homeListEntry.getValue();
            }
        }
        return null;
    }
}
