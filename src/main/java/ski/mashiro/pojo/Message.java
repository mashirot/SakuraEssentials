package ski.mashiro.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MashiroT
 */
@Data
@NoArgsConstructor
public class Message {
    private String senderTypeErr = "&2[SakuraEssentials] 输入者只能是玩家";
    private String errCommandMsg = "&2[SakuraEssentials] 用法错误，/sakura help 查看帮助";
    private String tpaSenderMsg = "&2[SakuraEssentials] 传送请求已发送到 %player%";
    private String tpaReceiverMsg = "&2[SakuraEssentials] 玩家 %player% 请求传送到你这里，输入 /tpaccept 或 /tpadeny , 接受或拒绝";
    private String tpahereSenderMsg = "&2[SakuraEssentials] 传送请求已发送到 %player%";
    private String tpahereReceiverMsg = "&2[SakuraEssentials] 玩家 %player% 请求你传送到他那里，输入 /tpaccept 或 /tpadeny , 接受或拒绝";
    private String tpacceptSenderMsg = "&2[SakuraEssentials] 接受请求";
    private String tpacceptReceiverMsg = "&2[SakuraEssentials] %player% 已接受传送请求";
    private String tpadenySenderMsg = "&2[SakuraEssentials] 拒绝请求";
    private String tpadenyReceiverMsg = "&2[SakuraEssentials] %player% 已拒绝传送请求";
    private String noTpRequestMsg = "&2[SakuraEssentials] 暂无传送请求";
    private String tpSelfMsg = "&2[SakuraEssentials] 不能给自己发送请求";
    private String backSuccessMsg = "&2[SakuraEssentials] 传送成功";
    private String noDeathPoint = "&2[SakuraEssentials] 无死亡点";

    private String noHomeMsg = "&2[SakuraEssentials] 还没有设置家";
    private String setHomeSuccessMsg = "&2[SakuraEssentials] 添加家成功";
    private String delHomeSuccessMsg = "&2[SakuraEssentials] 删除家成功";
    private String transportToHomeSuccessMsg = "&2[SakuraEssentials] 传送成功";
    private String homeExistMsg = "&2[SakuraEssentials] 家已存在";
    private String homeDontExistMsg = "&2[SakuraEssentials] 不存在此家";

    private String clearDropMsg = "&2[SakuraEssentials] &a清理了 %sum% 个掉落物";
}
