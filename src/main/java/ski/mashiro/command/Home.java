package ski.mashiro.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ski.mashiro.data.HomeData;
import ski.mashiro.data.PlayerData;
import ski.mashiro.file.MessageFiles;
import ski.mashiro.pojo.OwnHome;
import ski.mashiro.pojo.OwnPlayer;
import ski.mashiro.util.Utils;

import java.util.List;

/**
 * @author MashiroT
 */
public class Home{
    public static final String SETHOME = "sethome";
    public static final String DELHOME = "delhome";
    public static final String LISTHOME = "listhome";
    public static final String HOME = "home";

    public static boolean homeLength1(@NotNull CommandSender sender, @NotNull String[] args) {
        String cmd = args[0];
        OwnPlayer player = PlayerData.getPlayer(sender.getName());
        if (player == null) {
            return true;
        }
        if (cmd.equalsIgnoreCase(LISTHOME)) {
            List<OwnHome> ownHomeList = HomeData.listPlayerHome(sender.getName());
            if (ownHomeList == null) {
                sender.sendMessage(Utils.transferPlaceHolder(MessageFiles.message.getNoHomeMsg(), sender.getName()));
                return true;
            }
            int index = 1;
            for (OwnHome ownHome : ownHomeList) {
                sender.sendMessage(index++ + ". " + ownHome.getHomeName());
            }
            return true;
        }
        if (cmd.equalsIgnoreCase(SETHOME) || cmd.equalsIgnoreCase(DELHOME) || cmd.equalsIgnoreCase(HOME)) {
            sender.sendMessage(Utils.transferPlaceHolder(MessageFiles.message.getErrCommandMsg(), sender.getName()));
            return true;
        }
        return false;
    }
    public static boolean homeLength2(@NotNull CommandSender sender, @NotNull String[] args) {
        String cmd = args[0];
        String homeName = args[1];
        if (cmd.equalsIgnoreCase(SETHOME)) {
            if (HomeData.addHome((Player) sender, homeName)) {
                sender.sendMessage(Utils.transferPlaceHolder(MessageFiles.message.getSetHomeSuccessMsg(), sender.getName()));
                return true;
            }
            sender.sendMessage(Utils.transferPlaceHolder(MessageFiles.message.getHomeExistMsg(), sender.getName()));
            return true;
        }
        if (cmd.equalsIgnoreCase(DELHOME)) {
            if (HomeData.delHome(sender.getName(), homeName)) {
                sender.sendMessage(Utils.transferPlaceHolder(MessageFiles.message.getDelHomeSuccessMsg(), sender.getName()));
                return true;
            }
            sender.sendMessage(Utils.transferPlaceHolder(MessageFiles.message.getHomeDontExistMsg(), sender.getName()));
            return true;
        }
        if (cmd.equalsIgnoreCase(HOME)) {
            if (HomeData.transportPlayerToHome((Player) sender, homeName)) {
                sender.sendMessage(Utils.transferPlaceHolder(MessageFiles.message.getTransportToHomeSuccessMsg(), sender.getName()));
                return true;
            }
            sender.sendMessage(Utils.transferPlaceHolder(MessageFiles.message.getHomeDontExistMsg(), sender.getName()));
            return true;
        }
        return false;
    }
}
