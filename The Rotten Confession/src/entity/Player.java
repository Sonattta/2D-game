package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

   public final int screenX;
   public   final int screenY;
   int hasKey = 0;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 3;
        worldY = gp.tileSize * 3;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up01.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up02.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down001.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down002.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left01.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left02.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right01.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right02.png"));

        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    public void update(){
        if (keyH.downPressed ==true || keyH.leftPressed == true || keyH.upPressed ==true || keyH.rightPressed ==true){
            if (keyH.upPressed == true){
                direction = "up";

            } else if (keyH.downPressed == true) {
                direction = "down";

            } else if (keyH.leftPressed == true) {
                direction = "left";

            } else if (keyH.rightPressed == true) {
                direction = "right";


            }

            collisionOn = false;
            gp.collisionCheck.checkTile(this);
           int objIndex =  gp.collisionCheck.checkObject(this, true);
           pickUpObject(objIndex);

            if (collisionOn == false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down" :
                        worldY += speed;
                        break;
                    case  "left":
                        worldX -= speed;
                        break;
                    case "right" :
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if (spriteCounter> 9){
                if (spriteNumb ==1){
                    spriteNumb = 2;
                } else if (spriteNumb==2) {
                    spriteNumb =1;

                }
                spriteCounter = 0;
            }
        }

    }
    public void  pickUpObject(int i){
        if (i != 999){
           String objectName = gp.superObject[i].name;
            switch (objectName){
                case "key01":
                    hasKey++;
                    gp.superObject[i] = null;
                    System.out.println("Key : " + hasKey);
                    break;
                case "door" :
                    if (hasKey>0){
                        gp.superObject[i] = null;
                        hasKey --;
                    }
                    System.out.println("Key : " + hasKey);
                    break;
            }
        }


    }
    public void draw(Graphics2D g2 ){
//        g2.setColor(Color.blue);
//
//        g2.drawRect(x,y,gp.tileSize,gp.tileSize);


        BufferedImage image = null;
        switch (direction){
            case "up":
                if (spriteNumb == 1){
                    image = up1;
                }
                if (spriteNumb == 2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumb == 1){
                    image = down1;
                }
                if (spriteNumb == 2){
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumb == 1){
                    image = left1;
                }
                if (spriteNumb == 2){
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumb == 1){
                    image = right1;
                }
                if (spriteNumb == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize,null);

    }


}
