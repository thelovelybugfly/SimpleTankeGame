package tank.game;

import java.applet.AudioClip; 
import java.io.*; 
import java.applet.Applet;
import java.net.URI;
import java.net.URL;
import javax.swing.JFrame;

public class Music  extends JFrame{ 
	
	private static final long serialVersionUID = 1L;

	public Music() {
    try {      
          File   f = new File("imgs/music.wav"); 
          URI uri = f.toURI();
          URL  url = uri.toURL();  //������ַ
            AudioClip aau; 
            aau = Applet.newAudioClip(url);
          aau.play();  //����һ��  loop();ѭ������
        } catch (Exception e) { 
        	e.printStackTrace();
	}
  }
}

