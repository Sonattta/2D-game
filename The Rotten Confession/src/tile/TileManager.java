package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
   public Tile[] tile;
   public int[][] mapTileNum;

    public TileManager(GamePanel gp){
       this.gp = gp;

       tile = new  Tile[20];
       mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
       getTileImage();
       loadMap();
    }

    public void getTileImage(){
        try{
            tile[0]= new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1]= new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2]= new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bush.png"));
            tile[2].collision = true;

            tile[3]= new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earthroad.png"));


            tile[4]= new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;

            tile[5]= new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stoneroad.png"));
//
//            tile[6]= new Tile();
//            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road1.png"));
//
//            tile[7]= new Tile();
//            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall01.png"));
//            tile[7].collision = true;

//
//            tile[8]= new Tile();
//            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterdown.png"));
//
//            tile[9]= new Tile();
//            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/watercornerrightdown.png"));
//
//            tile[10]= new Tile();
//            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/watercornerrightup.png"));
//
//            tile[11]= new Tile();
//            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/watercornerleftup.png"));
//
//            tile[12]= new Tile();
//            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/watercornerleftdown.png"));
//
//            tile[13]= new Tile();
//            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterleft.png"));
//
//            tile[14]= new Tile();
//            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterright.png"));



        }catch (IOException e){
            e.printStackTrace();

        }
    }
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/maps/worldmap01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;


            while ( col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while (col <gp.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;

                    col++;
                }
                if (col== gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){

        }
    }

    public void draw(Graphics2D g2){
       int worldCol = 0;
       int worldRow = 0;
//       int x = 0;
//       int y = 0;

       while (worldCol< gp.maxWorldCol && worldRow < gp.maxWorldRow){
           int tileNum = mapTileNum[worldCol][worldRow];

           int worldX = worldCol * gp.tileSize;
           int worldY = worldRow * gp.tileSize;
           int screenX = worldX - gp.player.worldX + gp.player.screenX;
           int screenY = worldY - gp.player.worldY + gp.player.screenY;

           if (    worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY&&
                   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
               g2.drawImage(tile[tileNum].image,screenX, screenY, gp.tileSize,gp.tileSize,null);
           }


           worldCol++;
//           x+= gp.tileSize;

           if (worldCol ==gp.maxWorldCol){
               worldCol =0;
//               x = 0;
               worldRow++;
//               y += gp.tileSize;
           }

       }
    }

}
