package cn.gxg.game;

import java.awt.Graphics;
import java.awt.Image;

/*
 * 爆炸类
 */
public class Explode {
	double x, y;//爆炸的位置
	
	static Image[] imgs = new Image[16];//静态数组，因为图片不要反复加载
	
	/**
	 * 静态初始化块，图片是从1-16.循环是0-15
	 */
	static {
		for (int i = 0; i < 16; i++) {
			imgs[i] = GameUtil.getImage("images/explode/e" + (i + 1) + ".gif");
			imgs[i].getWidth(null);
		}
	}

	int count;//计数，依次画第几张图片

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