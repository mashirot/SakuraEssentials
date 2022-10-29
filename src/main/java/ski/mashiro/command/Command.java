package ski.mashiro.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ski.mashiro.file.MessageFiles;
import ski.mashiro.util.Utils;

import java.util.Arrays;
import java.util.List;

import static ski.mashiro.command.Home.*;
import static ski.mashiro.command.Tp.*;

/**
 * @author MashiroT
 */
public class Command implements TabExecutor {
    public static final int LENGTH_1 = 1;
    public static final int LENGTH_2 = 2;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.transferPlaceHolder(MessageFiles.message.getSenderTypeErr(), sender.getName()));
            return true;
        }
        if (args.length == LENGTH_2) {
            if (!(Tp.tpLength2(sender, args) || Home.homeLength2(sender, args))) {
                sender.sendMessage(Utils.transferPlaceHolder(MessageFiles.message.getErrCommandMsg(), sender.getName()));
            }
        }
        if (args.length == LENGTH_1) {
            if (!(Tp.tpLength1(sender, args) || Home.homeLength1(sender, args))) {
                sender.sendMessage(Utils.transferPlaceHolder(MessageFiles.message.getErrCommandMsg(), sender.getName()));
            }
        }
        if (args.length != LENGTH_1 && args.length != LENGTH_2) {
            sender.sendMessage(Utils.transferPlaceHolder(MessageFiles.message.getErrCommandMsg(), sender.getName()));
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            return null;
        }

        if (args.length == LENGTH_1) {
            String[] cmd = {TPA, TPAHERE, TPACCEPT, TPADENY, SETHOME, DELHOME, HOME, LISTHOME};
            return Arrays.asList(cmd);
        }
        return null;
    }

}
