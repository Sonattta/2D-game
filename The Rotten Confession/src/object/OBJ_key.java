package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_key extends SuperObject{
    public OBJ_key() {
        name = "key01";
        try {
            bufferedImage = ImageIO.read(getClass().getResourceAsStream("/objects/key01.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
