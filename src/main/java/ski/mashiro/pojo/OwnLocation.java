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
public class OwnLocation {
    private String world;
    private double vectorX;
    private double vectorY;
    private double vectorZ;
    private float yaw;
    private float pitch;
}
