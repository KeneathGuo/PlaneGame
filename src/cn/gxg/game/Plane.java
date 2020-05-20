package cn.gxg.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane extends GameObject {
	
	boolean left,up,right,down;
	boolean live =true;//定义飞机的生死
	
	
	@Override
	public void drawSelf(Graphics g) {
		if(live) {
			g.drawImage(img, (int)x, (int)y, null);
			
			if(left) {
				x-=speed;
			}
			if(right) {
				x+=speed;
			}
			if(up) {
				y-=speed;
			}
			if(down) {
				y+=speed;
			}
		}
		else {
			
		}
		
	}
	
	public Plane(Image img,double x,double y) {
		// TODO Auto-generated constructor stub
		this.img=img;
		this.x=x;
		this.y=y;
		this.speed=3;
		this.width=img.getWidth(null);
		this.height=img.getHeight(null);		
	}
	public Plane(Image img,int x,int y) {
		// TODO Auto-generated constructor stub
		this.img=img;
		this.x=(double)x;
		this.y=(double)y;
		this.speed=3;
		this.width=img.getWidth(null);
		this.height=img.getHeight(null);	
	}
	//按下某个键增加相应的方向
	public void addDirection(KeyEvent e) {
		//System.out.println("####"+e.getKeyCode());
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left=true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
			
			
		}

	}
	//抬起某个键取消相应的方向
		public void minusDirection(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				left=false;
				break;
			case KeyEvent.VK_UP:
				up = false;
				break;
			case KeyEvent.VK_RIGHT:
				right = false;
				break;
			case KeyEvent.VK_DOWN:
				down = false;
				break;
				
				
			}

		}
		
}
