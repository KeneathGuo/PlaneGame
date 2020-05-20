package cn.gxg.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * ��Ϸ����ĸ���
 * @author KeneathGuo
 *
 */
public class GameObject {
	
	 Image img;
	 double x,y;//���Ͻ�����
	 int speed;//�ٶ�
	 int width,height;
	 
	/**
	 * �����Լ��ķ���
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
	 * �����������ڵľ��Σ����ں�������ײ��⣬����Ҫ�ĺ��ڵ�һ������
	 * @return
	 */
	public Rectangle getRect() {
		return new Rectangle((int)x,(int)y, width, height);//Ϊ�Ժ����ײ�����׼��
	}
	
}
