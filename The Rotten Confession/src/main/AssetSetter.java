package main;

import object.OBJ_door;
import object.OBJ_key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        gp.superObject[0] = new OBJ_key();
        gp.superObject[0].worldX = 18 * gp.tileSize;
        gp.superObject[0].worldY = 5 * gp.tileSize;

        gp.superObject[1] = new OBJ_door();
        gp.superObject[1].worldX = 35 * gp.tileSize;
        gp.superObject[1].worldY = 2 * gp.tileSize;

    }

}
