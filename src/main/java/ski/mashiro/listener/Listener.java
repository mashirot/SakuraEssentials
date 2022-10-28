package ski.mashiro.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ski.mashiro.data.PlayerData;
import ski.mashiro.pojo.OwnPlayer;

import java.util.Iterator;

/**
 * @author MashiroT
 */
public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        PlayerData.PLAYER_LIST.add(new OwnPlayer(e.getPlayer(), null, null));
    }

    @EventHandler
    public void onPlayerExit(PlayerQuitEvent e) {
        Iterator<OwnPlayer> iterator = PlayerData.PLAYER_LIST.listIterator();
        while (iterator.hasNext()) {
            if (e.getPlayer().equals(iterator.next().getPlayer())) {
                iterator.remove();
                break;
            }
        }
        PlayerData.PLAYER_DEATH_LOCATION.remove(e.getPlayer());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        PlayerData.PLAYER_DEATH_LOCATION.put(e.getEntity(), e.getEntity().getLocation());
    }
}
