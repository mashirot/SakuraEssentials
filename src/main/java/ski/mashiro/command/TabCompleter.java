package ski.mashiro.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

import static ski.mashiro.command.Tp.*;
import static ski.mashiro.command.Home.*;

/**
 * @author MashiroT
 */
public class TabCompleter implements org.bukkit.command.TabCompleter {
    private static final int LENGTH_1 = 1;

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
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
