package ski.mashiro.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

/**
 * @author MashiroT
 */
public class Utils {
    public static final ObjectMapper OBJECT_MAPPER = new YAMLMapper();

}
