package com.uwtgpc.shadowgames.gui;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class ShadowGamesGui extends Game{
  private AnimatedSprite plane;
  private BufferedImage bullet_image;
  private List<Sprite> bullets;
  private AnimatedSprite explosion;
  private Font my_font = new Font("Helvetica", Font.PLAIN, 18);
  private long last_fire_time;


  @Override
  public void initResources(){
    last_fire_time = 0;
    bullet_image = getImage("resources" + File.separatorChar + "projectile.png");
    BufferedImage[] plane_img = getImages("resources" + File.separatorChar +"plane2.png", 3 , 1);
    //explosion = new AnimatedSprite(getImages("resources" + File.separatorChar +"explosion.png", 7, 1), 0, 0);
    plane = new AnimatedSprite(plane_img, 65, 65);
    bullets = new LinkedList<Sprite>();

    plane.setAnimate(true);
    plane.setLoopAnim(true);
    //explosion.setAnimate(true);
    //explosion.setLoopAnim(true);
  }

  @Override
  public void update(long l){
    //explosion.update(l);
    plane.update(l);
    for(Sprite bullet : bullets)
    {
      bullet.update(l);
    }

    double distance = 0.1 * l;
    if(keyDown(KeyEvent.VK_DOWN)){
      plane.move(0, distance);
    }
    if(keyDown(KeyEvent.VK_UP)){
      plane.move(0, -1 * distance);
    }
    if(keyDown(KeyEvent.VK_RIGHT)){
      plane.move(distance, 0);
    }
    if(keyDown(KeyEvent.VK_LEFT)){
      plane.move(-1 * distance, 0);
    }
    if(keyDown(KeyEvent.VK_SPACE)){
      if(last_fire_time+300 < System.currentTimeMillis())
      {
        last_fire_time = System.currentTimeMillis();
        Sprite sing_bullet = new Sprite(bullet_image);
        double bullet_start_x = plane.getX() + plane.getWidth() - 5;
        double bullet_start_y = plane.getY() + plane.getHeight()/2 - sing_bullet.getHeight()/2;
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
    plane.render(graphics2D);
    //explosion.render(graphics2D);
    for(Sprite bullet : bullets)
    {
      bullet.render(graphics2D);
    }
  }
}
