package ski.mashiro.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ski.mashiro.data.PlayerData;
import ski.mashiro.file.MessageFiles;
import ski.mashiro.pojo.OwnPlayer;
import ski.mashiro.util.Utils;

/**
 * @author MashiroT
 */
public class Tp {
    public static final String TPA = "tpa";
    public static final String TPAHERE = "tpahere";
    public static final String TPACCEPT = "tpaccept";
    public static final String TPADENY = "tpadeny";
    public static final String BACK = "back";

    public static boolean tpLength1(@NotNull CommandSender sender, @NotNull String[] args) {
        String cmd = args[0];
        OwnPlayer player = PlayerData.getPlayer(sender.getName());
        if (player == null) {
            return true;
        }
        if (TPACCEPT.equalsIgnoreCase(cmd)) {
            if (PlayerData.transportPlayerToPlayer(player)) {
                sender.sendMessage(Utils.transferPlayerPlaceHolder(MessageFiles.message.getTpacceptSenderMsg(), sender.getName()));
                player.getRelatedPlayer().sendMessage(Utils.transferPlayerPlaceHolder(MessageFiles.message.getTpacceptReceiverMsg(), sender.getName()));
                return true;
            }
            sender.sendMessage(Utils.transferPlayerPlaceHolder(MessageFiles.message.getNoTpRequestMsg(), sender.getName()));
            return true;
        }
        if (TPADENY.equalsIgnoreCase(cmd)) {
            player.setType(null);
            player.setRelatedPlayer(null);
            sender.sendMessage(Utils.transferPlayerPlaceHolder(MessageFiles.message.getTpadenySenderMsg(), sender.getName()));
            player.getRelatedPlayer().sendMessage(Utils.transferPlayerPlaceHolder(MessageFiles.message.getTpadenyReceiverMsg(), sender.getName()));
            return true;
        }
        if (BACK.equalsIgnoreCase(cmd)) {
            if (PlayerData.transportPlayerToDeathLocation(player.getPlayer())) {
                sender.sendMessage(Utils.transferPlayerPlaceHolder(MessageFiles.message.getBackSuccessMsg(), sender.getName()));
                return true;
            }
            sender.sendMessage(Utils.transferPlayerPlaceHolder(MessageFiles.message.getNoDeathPoint(), sender.getName()));
            return true;
        }
        return false;
    }

    public static boolean tpLength2(@NotNull CommandSender sender, @NotNull String[] args) {
        String cmd = args[0];
        String senderName = sender.getName();
        String goalName = args[1];
        OwnPlayer goalPlayer = PlayerData.getPlayer(goalName);
        if (goalPlayer != null) {
            if (senderName.equals(goalName)) {
                sender.sendMessage(Utils.transferPlayerPlaceHolder(MessageFiles.message.getTpSelfMsg(), sender.getName()));
                return true;
            }
        }
        if (TPA.equalsIgnoreCase(cmd)) {
            if (goalPlayer == null) {
                sender.sendMessage(Utils.transferPlayerPlaceHolder(MessageFiles.message.getTpReceiverDontExist(), sender.getName()));
                return true;
            }
            sender.sendMessage(Utils.transferPlayerPlaceHolder(MessageFiles.message.getTpaSenderMsg(), senderName));
            goalPlayer.setType(TPA);
            goalPlayer.setRelatedPlayer((Player) sender);
            goalPlayer.getPlayer().sendMessage(Utils.transferPlayerPlaceHolder(MessageFiles.message.getTpaReceiverMsg(), senderName));
            return true;
        }
        if (TPAHERE.equalsIgnoreCase(cmd)) {
            if (goalPlayer == null) {
                sender.sendMessage(Utils.transferPlayerPlaceHolder(MessageFiles.message.getTpReceiverDontExist(), sender.getName()));
                return true;
            }
            sender.sendMessage(Utils.transferPlayerPlaceHolder(MessageFiles.message.getTpahereSenderMsg(), senderName));
            goalPlayer.setType(TPAHERE);
            goalPlayer.setRelatedPlayer((Player) sender);
            goalPlayer.getPlayer().sendMessage(Utils.transferPlayerPlaceHolder(MessageFiles.message.getTpahereReceiverMsg(), senderName));
            return true;
        }
        return false;
    }

}
