package ski.mashiro.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ski.mashiro.data.PlayerData;
import ski.mashiro.pojo.OwnPlayer;

/**
 * @author MashiroT
 */
public class Tp implements CommandExecutor {
    private static final int LENGTH_1 = 1;
    private static final int LENGTH_2 = 2;
    public static final String TPA = "tpa";
    public static final String TPAHERE = "tpahere";
    public static final String TPACCEPT = "tpaccept";
    public static final String TPADENY = "tpadeny";
    public static final String BACK = "back";
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("");
            return true;
        }
        String cmd = args[0];
        if (!(sender instanceof Player)) {
            sender.sendMessage("输入者只能是玩家");
            return true;
        }
        if (args.length == LENGTH_2) {
            String senderName = sender.getName();
            String goalName = args[1];
            OwnPlayer goalPlayer = PlayerData.getPlayer(goalName);
            if (TPA.equalsIgnoreCase(cmd)) {
                if (goalPlayer != null) {
                    sender.sendMessage("");
                    goalPlayer.setType(TPA);
                    goalPlayer.setRelatedPlayer((Player) sender);
                    goalPlayer.getPlayer().sendMessage(senderName + "请求传送到你这里，输入 /tpaccept 或 /tpadeny ，接受或拒绝");
                }
            }
            if (TPAHERE.equalsIgnoreCase(cmd)) {
                if (goalPlayer != null) {
                    sender.sendMessage("");
                    goalPlayer.setType(TPAHERE);
                    goalPlayer.setRelatedPlayer((Player) sender);
                    goalPlayer.getPlayer().sendMessage(senderName + "请求传送你到他那里，输入 /tpaccept 或 /tpadeny ，接受或拒绝");
                }
            }
        }
        if (args.length == LENGTH_1) {
            OwnPlayer player = PlayerData.getPlayer(sender.getName());
            if (player == null) {
                return true;
            }
            switch (cmd) {
                case TPACCEPT:
                    if (PlayerData.transportPlayerToPlayer(player)) {
                        sender.sendMessage("传送成功");
                        player.getRelatedPlayer().sendMessage("对方已接受传送请求");
                        break;
                    }
                    sender.sendMessage("暂无传送请求");
                    break;
                case TPADENY:
                    player.setType(null);
                    player.setRelatedPlayer(null);
                    sender.sendMessage("已拒绝请求");
                    player.getRelatedPlayer().sendMessage("对方已拒绝传送请求");
                    break;
                case BACK:
                    if (PlayerData.transportPlayerToDeathLocation(player.getPlayer())) {
                        sender.sendMessage("传送成功");
                        break;
                    }
                    sender.sendMessage("暂无死亡点");
                    break;
                default:
                    sender.sendMessage("");
                    break;
            }
        }
        sender.sendMessage("");
        return true;
    }
}
