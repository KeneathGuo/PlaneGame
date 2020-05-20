package cn.gxg.game;

import java.awt.Graphics;
import java.awt.Image;

/*
 * ��ը��
 */
public class Explode {
	double x, y;//��ը��λ��
	
	static Image[] imgs = new Image[16];//��̬���飬��ΪͼƬ��Ҫ��������
	
	/**
	 * ��̬��ʼ���飬ͼƬ�Ǵ�1-16.ѭ����0-15
	 */
	static {
		for (int i = 0; i < 16; i++) {
			imgs[i] = GameUtil.getImage("images/explode/e" + (i + 1) + ".gif");
			imgs[i].getWidth(null);
		}
	}

	int count;//���������λ��ڼ���ͼƬ

	public void draw(Graphics g) {
		if (count <= 15) {
			g.drawImage(imgs[count], (int) x, (int) y, null);
			count++;
		}
	}

	public Explode(double x, double y) {
		this.x = x;
		this.y = y;
	}
}