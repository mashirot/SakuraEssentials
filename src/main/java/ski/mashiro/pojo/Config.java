package ski.mashiro.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author MashiroT
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Config {
    private double version;
    private boolean checkUpdate;
    private boolean enableCleanDropItem;
}
