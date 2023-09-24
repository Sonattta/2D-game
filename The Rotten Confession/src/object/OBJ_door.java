package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_door extends SuperObject{
    public OBJ_door() {
        name = "door";
        try {
            bufferedImage = ImageIO.read(getClass().getResourceAsStream("/objects/door01.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
