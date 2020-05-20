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
	
	Explode bao;//��ײ��ʱ����newһ��
	Date startTime =new Date();
	Date endTime;
	
	int period;//��Ϸ������ʱ��
	
	@Override
	public void paint(Graphics g) {//�Զ������ã�g�൱��һ������
		
		Color c = g.getColor();
		Font f=g.getFont();
		
		
	g.drawImage(bg, 0, 0, null);
	plane.drawSelf(g);//���ɻ�
	//��50���ڵ�,��ÿ����һ���ڵ���ʱ��ͱȽ�һ���Ƿ���ɻ���ײ
	for (int i = 0; i < shells.length; i++) {
		shells[i].draw(g);//���ڵ�
		
		//�ɻ����ڵ�����ײ���
		boolean peng= shells[i].getRect().intersects(plane.getRect());
		if(peng) {
			System.out.println("��ײ�ˣ�����");
			//�ɻ���ײ������
			plane.live=false;
			
			//�����if������ǣ�һ��������ը��ֻ��Ҫ����һ������һ�α�ը
			if(bao==null) {
				bao=new Explode(plane.x,plane.y);
				
				endTime=new Date();
				period=(int)((endTime.getTime()-startTime.getTime())/1000);
				
			}
			
			bao.draw(g);	
		}
		//��ʱ���ܣ�������ʾ
		if(!plane.live) {//��ʱplane.liveΪfalse���ɻ��Ѿ�������
			
			
			g.setColor(Color.red);//�ı仭����ɫ��ʾ���ֵ���ɫ
			
			Font newf= new Font("����",Font.BOLD,50);
			g.setFont(newf);//���������壬��С
			g.drawString("��Ϸ������ʱ��:"+period+"��",
					(int)plane.x, (int)plane.y);
			
		}
		
	}
	//��ԭ��������
	g.setColor(c);
	g.setFont(f);
	
	}
	//�ڲ������ֱ��ʹ���ⲿ������Ժͷ����ܷ���
	class PaintThread extends Thread{
		@Override
		public void run() {
			while(true) {
//				System.out.println("���ڱ��ػ���һ�Σ�����");
				repaint();//��������������ⲿ��MyGameFrame��,�ػ���
				
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//������̼������ڲ���
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
	 * ��ʼ������
	 */
	public void launchFrame() {
		this.setTitle("�ɻ���Ϸ");
		
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		this.setLocation(300, 300);
		
		this.setVisible(true);
		//�����ڲ��࣬ʹ�ÿ���ͨ�����Ͻǹرմ���
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);//0��ʾ�����ر�
			}
		});
		
		
		//�����ػ����ڵ��߳�
		new PaintThread().start();
		addKeyListener(new KeyMonitor());//���������Ӽ��̼���
		
		
		//��ʼ��50���ڵ�
		for (int i = 0; i < shells.length; i++) {
			shells[i]=new Shell();
		}
	}
	
	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();
		f.launchFrame();
		
	}
	
	
	//���˫��������Ĵ���
	private Image offScreenImage = null;
	public void update(Graphics g) {
		if(offScreenImage == null)
			offScreenImage=this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
}

		

