package main;

import entity.Player;
import object.OBJ_key;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen settings
    final int originalTileSize = 16; //the size of character and others
    final int scale = 3;//scaling 3(scale)*16(originaltilesize) = 48

    public final int tileSize = originalTileSize * scale;
   public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

   public final int screenWidth = tileSize * maxScreenCol;//768 pixels
     public final int screenHeight = tileSize * maxScreenRow;//576 pixels

    //WORLD MAP SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
//    public final int worldWidth = tileSize * maxWorldCol;
//    public final int worldHeight = tileSize * maxWorldRow;




    //FPS
    int FPS = 60;

    TileManager tileManager = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    Sound sound = new Sound();




    public CollisionCheck collisionCheck  =  new CollisionCheck(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;


   public Player player = new Player(this,keyH);

   public SuperObject superObject[] = new SuperObject[10];


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame(){
        aSetter.setObject();
        playMusic(0);
    }

    public void startGameThread (){
        gameThread = new Thread(this);
        gameThread.start();
    }

//    @Override
//    public void run() {
//
//        double drawInterval = 1000000000/FPS; // 0.016666 seconds
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//       while (gameThread != null){
//
//
//           //  1 UPDATE: character position as example
//           update();
//
//           // 2 DRAW : draw the screen with updated information
//           repaint();
//
//
//           try {
//               double remainingTime = nextDrawTime - System.nanoTime();
//               remainingTime = remainingTime/1000000;
//
//               if (remainingTime < 0){
//                   remainingTime= 0;
//               }
//               Thread.sleep((long) remainingTime);
//
//               nextDrawTime += drawInterval;
//
//           } catch (InterruptedException e) {
//               throw new RuntimeException(e);
//           }
//
//       }
//    }


    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;

        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread!=null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta>1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){

      //move in player update()
        player.update();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g); //recall for parent method
        Graphics2D g2 = (Graphics2D)g;
        tileManager.draw(g2);
        for (int i = 0; i < superObject.length; i++) {
            if (superObject[i] != null){
                superObject[i].draw(g2,this);
            }

        }
         player.draw(g2);

      //move in player draw()
        g2.dispose();
    }

    public  void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();

    }
    public void stopMusic(){
        sound.stop();
    }
    public void playerSE(int i ){
        sound.setFile(i);
        sound.play();

    }

}
