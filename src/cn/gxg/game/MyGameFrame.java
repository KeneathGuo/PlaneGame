package cn.gxg.game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;

public class MyGameFrame  extends Frame{
	
	Image bg=GameUtil.getImage("images/bg.jpg");
	Image planeImg=GameUtil.getImage("images/plane.png");
	
	Plane plane =new Plane(planeImg,250,250);
	Shell[] shells = new Shell[50];
	
	Explode bao;//碰撞的时候再new一个
	Date startTime =new Date();
	Date endTime;
	
	int period;//游戏持续的时间
	
	@Override
	public void paint(Graphics g) {//自动被调用，g相当于一根画笔
		
		Color c = g.getColor();
		Font f=g.getFont();
		
		
	g.drawImage(bg, 0, 0, null);
	plane.drawSelf(g);//画飞机
	//画50个炮弹,，每画出一个炮弹的时候就比较一下是否与飞机碰撞
	for (int i = 0; i < shells.length; i++) {
		shells[i].draw(g);//画炮弹
		
		//飞机和炮弹的碰撞检测
		boolean peng= shells[i].getRect().intersects(plane.getRect());
		if(peng) {
			System.out.println("碰撞了！！！");
			//飞机碰撞后死掉
			plane.live=false;
			
			//这里的if解决的是，一旦发生爆炸，只需要生成一个对象，一次爆炸
			if(bao==null) {
				bao=new Explode(plane.x,plane.y);
				
				endTime=new Date();
				period=(int)((endTime.getTime()-startTime.getTime())/1000);
				
			}
			
			bao.draw(g);	
		}
		//计时功能，给出提示
		if(!plane.live) {//此时plane.live为false，飞机已经死亡了
			
			
			g.setColor(Color.red);//改变画笔颜色显示的字的颜色
			
			Font newf= new Font("宋体",Font.BOLD,50);
			g.setFont(newf);//设置新字体，大小
			g.drawString("游戏结束，时间:"+period+"秒",
					(int)plane.x, (int)plane.y);
			
		}
		
	}
	//还原画笔属性
	g.setColor(c);
	g.setFont(f);
	
	}
	//内部类可以直接使用外部类的属性和方法很方便
	class PaintThread extends Thread{
		@Override
		public void run() {
			while(true) {
//				System.out.println("窗口被重画了一次！！！");
				repaint();//这个方法定义在外部类MyGameFrame里,重画。
				
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//定义键盘监听的内部类
	class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
			
		}
	}
	/**
	 * 初始化窗口
	 */
	public void launchFrame() {
		this.setTitle("飞机游戏");
		
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		this.setLocation(300, 300);
		
		this.setVisible(true);
		//匿名内部类，使得可以通过右上角关闭窗口
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);//0表示正常关闭
			}
		});
		
		
		//启动重画窗口的线程
		new PaintThread().start();
		addKeyListener(new KeyMonitor());//给窗口增加键盘监听
		
		
		//初始化50个炮弹
		for (int i = 0; i < shells.length; i++) {
			shells[i]=new Shell();
		}
	}
	
	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();
		f.launchFrame();
		
	}
	
	
	//解决双缓冲问题的代码
	private Image offScreenImage = null;
	public void update(Graphics g) {
		if(offScreenImage == null)
			offScreenImage=this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
}

		

