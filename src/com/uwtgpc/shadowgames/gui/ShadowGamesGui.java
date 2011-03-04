package com.uwtgpc.shadowgames.gui;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.uwtgpc.shadowgames.util.Direction;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class ShadowGamesGui extends Game{
  private AnimatedSprite boy;
  private BufferedImage bullet_image;
  BufferedImage[] boy_img_right;
  BufferedImage[] boy_img_left;
  private List<Sprite> bullets;
  private AnimatedSprite explosion;
  private Font my_font = new Font("Helvetica", Font.PLAIN, 18);
  private long last_fire_time;
  private Direction boy_facing;

  @Override
  public void initResources(){
    last_fire_time = 0;
    bullet_image = getImage("resources" + File.separatorChar + "projectile.png");
    boy_img_right =
      getImages("resources" + File.separatorChar + "boy" + File.separatorChar + "boy_walk_right.png", 3, 1);
    boy_img_left = getImages("resources" + File.separatorChar + "boy" + File.separatorChar + "boy_walk_left.png", 3, 1);
    //explosion = new AnimatedSprite(getImages("resources" + File.separatorChar +"explosion.png", 7, 1), 0, 0);
    boy = new AnimatedSprite(boy_img_right, 400, 320);
    bullets = new LinkedList<Sprite>();

    
    //explosion.setAnimate(true);
    //explosion.setLoopAnim(true);
  }

  @Override
  public void update(long l){
    //explosion.update(l);
    boy.update(l);
    boy.setAnimate(false);
    for(Sprite bullet : bullets){
      bullet.update(l);
    }

    double distance = 0.1 * l;
    if(keyDown(KeyEvent.VK_DOWN)){
      //boy.move(0, distance);
    }
    if(keyDown(KeyEvent.VK_UP)){
      //boy.move(0, -1 * distance);
    }
    if(keyDown(KeyEvent.VK_RIGHT)){
      if(boy_facing != Direction.EAST){
        setDirection(Direction.EAST);
      }
      boy.setAnimate(true);
      boy.move(distance, 0);
    }
    if(keyDown(KeyEvent.VK_LEFT)){
      if(boy_facing != Direction.WEST){
        setDirection(Direction.WEST);
      }
      boy.setAnimate(true);
      boy.move(-1 * distance, 0);
    }
    if(keyDown(KeyEvent.VK_SPACE)){
      if(last_fire_time + 300 < System.currentTimeMillis()){
        last_fire_time = System.currentTimeMillis();
        Sprite sing_bullet = new Sprite(bullet_image);
        double bullet_start_x = boy.getX() + boy.getWidth() - 5;
        double bullet_start_y = boy.getY() + boy.getHeight() / 2 - sing_bullet.getHeight() / 2;
        sing_bullet.setLocation(bullet_start_x, bullet_start_y);
        sing_bullet.setSpeed(0.1, 0);
        bullets.add(sing_bullet);
      }
    }
  }

  @Override
  public void render(Graphics2D graphics2D){
    graphics2D.setColor(Color.WHITE);
    graphics2D.fillRect(0, 0, getWidth(), getHeight());
    graphics2D.setColor(Color.BLACK);
    graphics2D.setFont(my_font);
    graphics2D.drawString("Hello, World!", 200, 50);
    boy.render(graphics2D);
    //explosion.render(graphics2D);
    for(Sprite bullet : bullets){
      bullet.render(graphics2D);
    }
  }

  private void setDirection(Direction new_direction){
    boy_facing = new_direction;
    switch(new_direction){
      case EAST:
        boy.setImages(boy_img_right);
        break;
      case WEST:
        boy.setImages(boy_img_left);
        break;

    }
  }
}
