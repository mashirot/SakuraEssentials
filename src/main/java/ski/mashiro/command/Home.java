package ski.mashiro.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ski.mashiro.data.HomeData;
import ski.mashiro.pojo.OwnHome;

import java.util.List;

/**
 * @author MashiroT
 */
public class Home implements CommandExecutor {
    private static final int LENGTH_1 = 1;
    private static final int LENGTH_2 = 2;
    public static final String SETHOME = "sethome";
    public static final String DELHOME = "delhome";
    public static final String LISTHOME = "listhome";
    public static final String HOME = "home";
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
            String homeName = args[1];
            switch (cmd.toLowerCase()) {
                case SETHOME:
                    if (HomeData.addHome((Player) sender, homeName)) {
                        sender.sendMessage("添加成功");
                        break;
                    }
                    sender.sendMessage("家重复");
                    break;
                case DELHOME:
                    if (HomeData.delHome(sender.getName(), homeName)) {
                        sender.sendMessage("删除成功");
                        break;
                    }
                    sender.sendMessage("不存在此家");
                    break;
                case HOME:
                    if (HomeData.transportPlayerToHome((Player) sender, homeName)) {
                        sender.sendMessage("已传送至家：" + homeName);
                        break;
                    }
                    sender.sendMessage("不存在此家");
                    break;
                default:
                    sender.sendMessage("用法错误，输入 /sakura help 插件");
                    break;
            }
        }
        if (args.length == LENGTH_1) {
            switch (cmd.toLowerCase()) {
                case LISTHOME:
                    List<OwnHome> ownHomeList = HomeData.listPlayerHome(sender.getName());
                    if (ownHomeList == null) {
                        sender.sendMessage("还没有设置家");
                        break;
                    }
                    for (OwnHome ownHome : ownHomeList) {
                        sender.sendMessage(ownHome.getHomeName() + "  "  + ownHome.getHomeLocation());
                    }
                    break;
                case SETHOME:
                    sender.sendMessage("用法错误，/sakura sethome [家名]");
                    break;
                case DELHOME:
                    sender.sendMessage("用法错误，/sakura delhome [家名]");
                    break;
                case HOME:
                    sender.sendMessage("用法错误，/sakura home [家名]");
                    break;
                default:
                    sender.sendMessage("用法错误，输入 /sakura help 插件");
                    break;
            }
        }
        return true;
    }
}
