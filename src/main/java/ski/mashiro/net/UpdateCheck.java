package ski.mashiro.net;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import ski.mashiro.file.ConfigFiles;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author MashiroT
 */
public class UpdateCheck {

    public static void checkUpdate(@NotNull Plugin plugin) {
        if (!ConfigFiles.config.isCheckUpdate()) {
            return;
        }

        try {
            URL url = new URL("https://update.check.mashiro.ski/SakuraEssentials.txt");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            try (
                    InputStream inputStream = urlConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
            ) {
                if (ConfigFiles.config.getVersion().equals(bufferedReader.readLine())) {
                    plugin.getLogger().info("当前为最新版本，感谢您的使用");
                    return;
                }
                plugin.getLogger().info("当前有更新可用，请前往MCBBS发布贴下载");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
