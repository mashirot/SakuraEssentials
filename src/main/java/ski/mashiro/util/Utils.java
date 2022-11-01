package ski.mashiro.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

/**
 * @author MashiroT
 */
public class Utils {
    public static final ObjectMapper OBJECT_MAPPER = new YAMLMapper();
    private static final String PLAYER_PLACE_HOLDER = "%player%";
    private static final String SUM_PLACE_HOLDER = "%sum%";
    private static final String TIME_PLACE_HOLDER = "%time%";
    public static String transferPlayerPlaceHolder(String str, String playerName) {
        String result = str.replaceAll("&", "§");
        if (result.contains(PLAYER_PLACE_HOLDER)) {
            result = result.replaceAll(PLAYER_PLACE_HOLDER, playerName);
        }
        return result;
    }
    public static String transferSumPlaceHolder(String str, String sum) {
        String result = str.replaceAll("&", "§");
        if (result.contains(SUM_PLACE_HOLDER)) {
            result = result.replaceAll(SUM_PLACE_HOLDER, sum);
        }
        return result;
    }
    public static String transferTimePlaceHolder(String str, String time) {
        String result = str.replaceAll("&", "§");
        if (result.contains(TIME_PLACE_HOLDER)) {
            result = result.replaceAll(TIME_PLACE_HOLDER, time);
        }
        return result;
    }
}
