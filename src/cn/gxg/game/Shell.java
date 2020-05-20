package cn.gxg.game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 炮弹类
 * @author KeanethGuo
 *
 */
public class Shell extends GameObject {
	double degree;
	/**
	 * 构造器
	 */
	public Shell() {
		x=200;
		y=200;
		width=10;
		height=10;
		speed=3;
		
		degree = Math.random()*Math.PI*2;//生成一个0-2Π之间的随机数
		
	}
	
	public void draw(Graphics g) {
		Color c= g.getColor();
		g.setColor(Color.YELLOW);
		
		g.fillOval((int)x, (int)y, width, height);
		
		//炮弹沿着任意角度去飞
		x += speed*Math.cos(degree);
		y += speed*Math.sin(degree);
		
		if(x<0||x>Constant.GAME_WIDTH-width) {
			degree =Math.PI-degree;//左右方向翻转，关于y轴对称
		}
		if(y<30||y>Constant.GAME_HEIGHT-height/*30为标题栏的宽度*/) {
			degree=-degree;
		}
		
		
		g.setColor(c);
	}
}
