package ski.mashiro.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import ski.mashiro.file.HomeFiles;
import ski.mashiro.pojo.OwnHome;
import ski.mashiro.pojo.OwnLocation;

import java.util.*;

/**
 * @author MashiroT
 */
public class HomeData {
    private HomeData() {}
    public static final Map<String, List<OwnHome>> PLAYER_HOME = new Hashtable<>();

    public static boolean addHome(Player player, String homeName) {
        Location playerLocation = player.getLocation();
        if (PLAYER_HOME.isEmpty() || !PLAYER_HOME.containsKey(player.getName())) {
            List<OwnHome> ownHomeList = new ArrayList<>(5);
            ownHomeList.add(new OwnHome(homeName, new OwnLocation(Objects.requireNonNull(playerLocation.getWorld()).getName(), playerLocation.getX(), playerLocation.getY(), playerLocation.getBlockZ(), playerLocation.getYaw(), playerLocation.getPitch())));
            PLAYER_HOME.put(player.getName(), ownHomeList);
            HomeFiles.saveHomeData();
            return true;
        }
        for (OwnHome ownHome : PLAYER_HOME.get(player.getName())) {
            if (homeName.equals(ownHome.getHomeName())) {
                return false;
            }
        }
        PLAYER_HOME.get(player.getName()).add(new OwnHome(homeName, new OwnLocation(Objects.requireNonNull(playerLocation.getWorld()).getName(), playerLocation.getX(), playerLocation.getY(), playerLocation.getBlockZ(), playerLocation.getYaw(), playerLocation.getPitch())));
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
        if (PLAYER_HOME.size() == 1 && PLAYER_HOME.containsKey(homeName)) {
            PLAYER_HOME.clear();
            HomeFiles.saveHomeData();
            return true;
        }
        Iterator<OwnHome> it = PLAYER_HOME.get(playerName).listIterator();
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
        for (OwnHome ownHome : PLAYER_HOME.get(player.getName())) {
            if (homeName.equals(ownHome.getHomeName())) {
                OwnLocation ownLocation = ownHome.getHomeLocation();
                player.teleport(new Location(Bukkit.getWorld(ownLocation.getWorld()), ownLocation.getVectorX(), ownLocation.getVectorY(), ownLocation.getVectorZ(), ownLocation.getYaw(), ownLocation.getPitch()));
                return true;
            }
        }
        return false;
    }

    public static List<OwnHome> listPlayerHome(String playerName) {
        if (PLAYER_HOME.isEmpty()) {
            return null;
        }
        for (Map.Entry<String, List<OwnHome>> homeListEntry : PLAYER_HOME.entrySet()) {
            if (playerName.equals(homeListEntry.getKey())) {
                return homeListEntry.getValue();
            }
        }
        return null;
    }
}
