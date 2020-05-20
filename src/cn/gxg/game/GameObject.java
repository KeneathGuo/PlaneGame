package cn.gxg.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * 游戏物体的父类
 * @author KeneathGuo
 *
 */
public class GameObject {
	
	 Image img;
	 double x,y;//左上角坐标
	 int speed;//速度
	 int width,height;
	 
	/**
	 * 画出自己的方法
	 * @param g
	 */
	public void drawSelf(Graphics g) {
		g.drawImage(img, (int)x, (int)y, null);
	}

	public GameObject(Image img, double x, double y, int speed, int width, int height) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}

	public GameObject(Image img, double x, double y) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
	}
	
	public GameObject() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 返回物体所在的矩形，便于后续的碰撞检测，很重要的后期的一个方法
	 * @return
	 */
	public Rectangle getRect() {
		return new Rectangle((int)x,(int)y, width, height);//为以后的碰撞检测做准备
	}
	
}
