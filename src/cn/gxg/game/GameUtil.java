package cn.gxg.game;
import java.awt.Image;
import java.awt.image.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;;

public class GameUtil {
	//��������ý�������˽�л�,����static������û��Ҫȥnewһ������
	private GameUtil() {
		
	}
	
	//����ָ��·���ļ���ͼƬ����
	public static Image getImage(String path) {
		BufferedImage bi = null;
		try {
			URL u = GameUtil.class.getClassLoader().getResource(path);
			bi=ImageIO.read(u);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return bi;
	}
}
