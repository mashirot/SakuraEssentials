package ski.mashiro.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

/**
 * @author MashiroT
 */
public class Utils {
    public static final ObjectMapper OBJECT_MAPPER = new YAMLMapper();
    private static final String PLAYER_PLACE_HOLDER = "%player%";
    public static String transferPlaceHolder(String str, String playerName) {
        String result = str.replaceAll("&", "§");
        if (result.contains(PLAYER_PLACE_HOLDER)) {
            result = result.replaceAll(PLAYER_PLACE_HOLDER, playerName);
        }
        return result;
    }

}
