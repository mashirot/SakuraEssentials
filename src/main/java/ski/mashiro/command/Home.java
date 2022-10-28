package ski.mashiro.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * @author MashiroT
 */
public class Home implements CommandExecutor {
    private static final int LENGTH_1 = 1;
    private static final int LENGTH_2 = 2;
    private static final String SETHOME = "sethome";
    private static final String DELHOME = "delhome";
    private static final String LISTHOME = "listhome";
    private static final String HOME = "home";
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("");
        }
        String cmd = args[0];
        if (args.length == LENGTH_2) {
            switch (cmd.toLowerCase()) {
                case SETHOME:
                    break;
                case DELHOME:
                    break;
                case HOME:
                    break;
                default:
                    sender.sendMessage("");
            }
        }
        if (args.length == LENGTH_1) {
            if (LISTHOME.equalsIgnoreCase(cmd)) {

            }
        }
        return true;
    }
}
