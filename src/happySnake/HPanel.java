package happySnake;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;



@SuppressWarnings("serial")
public class HPanel extends JPanel implements KeyListener,ActionListener {
	ImageIcon title = new ImageIcon("src/BGUp.jpg");
	ImageIcon body = new ImageIcon("src/body.png");
	ImageIcon up = new ImageIcon("src/up.png");
	ImageIcon down = new ImageIcon("src/down.png");
	ImageIcon left = new ImageIcon("src/left.png");
	ImageIcon right = new ImageIcon("src/right.png");
	ImageIcon food = new ImageIcon("src/food.png");
	int score=0;
	int len = 3;
	int[] snakex = new int[750];
	int[] snakey = new int[750];
	String fx = "R";
	boolean isStarted=false;
	boolean isFailed=false;
	Timer timer=new Timer(100,this);//定时器
	int foodx;
	int foody;//生成食物座标
	Random rand=new Random();//生成食物座标的随机数

	public HPanel() {//构造函数
		initSanke();
		this.setFocusable(true);
		this.addKeyListener(this);
		timer.start();
	}

	public void paintComponent(Graphics g)
  {
		
	  super.paintComponent(g);
	  this.setBackground(Color.white);
	  title.paintIcon(this, g, 15, 11);
	  
	  g.fillRect(0, 75, 900, 600);//黑色背景
	  g.drawString("Len"+len, 750, 535);
	  g.setColor(Color.green);
	  g.setFont(new Font("arial",Font.ROMAN_BASELINE,20));
	  g.drawString("Score:"+score, 700, 50);
	  switch(fx)
	  {
	  case "R":
		  right.paintIcon(this, g, snakex[0], snakey[0]); break;
	  case "L":
		  left.paintIcon(this, g, snakex[0], snakey[0]); break;
	  case "D":
		down.paintIcon(this, g, snakex[0], snakey[0]); break;
	  case "U":
		up.paintIcon(this, g, snakex[0], snakey[0]); break;
	  }
	 food.paintIcon(this, g, foodx, foody);
		 //if(isStarted==false)
		 //{
			 g.setColor(Color.white);
			 g.setFont(new Font("arial",Font.BOLD,40));
			 g.drawString("Press space to countinue/pause", 50, 50);
		// }
		 if(isFailed)
		 {
			 g.setColor(Color.red);
			 g.setFont(new Font("arial",Font.BOLD,40));
			 g.drawString("Failed:Score:"+score, 300, 300);
			 g.drawString("Press space to resume", 300, 350);
		 }
	  
	  for(int i=1;i<len;i++)
	  {
		  body.paintIcon(this, g, snakex[i], snakey[i]);
	  }
	  
  }

	// 初始化蛇的身体
	public void initSanke() {
		len = 3;
		score=0;
		snakex[0] = 100;
		snakey[0] = 100;
		snakex[1] = 75;
		snakey[1] = 100;
		snakex[2] = 50;
		snakey[2] = 100;
		fx="R";
		foodx=25+25*rand.nextInt(30);
		foody=75+25*rand.nextInt(20);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
    public void keyPressed(KeyEvent e) {
    	int keyCode= e.getKeyCode();
    	if(keyCode==KeyEvent.VK_SPACE)
    	{
    		if(isFailed)
    		{
    			isFailed=false;
    			initSanke();
    		}else {
				isStarted=!isStarted;
			}
    		
    		repaint();
    	}
    	else if(keyCode==KeyEvent.VK_RIGHT &&fx!="L")
    		fx="R";
    	else if(keyCode==KeyEvent.VK_UP&&fx!="D")
    		fx="U";
    	else if (keyCode==KeyEvent.VK_DOWN&&fx!="U")
    	fx="D";
    	else if(keyCode==KeyEvent.VK_LEFT&&fx!="R")
    		fx="L";
    	 
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(isStarted&& !isFailed) {
		for(int i=len-1;i>0;i--)
		{
			snakex[i]=snakex[i-1];
			snakey[i]=snakey[i-1];
		}
		if(fx=="R") 
		{
		snakex[0]=snakex[0]+25;
		
		}
		
		if(fx=="L") {
			snakex[0]=snakex[0]-25;
			
		}
		if(fx=="U") {
			snakey[0]=snakey[0]-25;
		
		}
		if(fx=="D") {
			snakey[0]=snakey[0]+25;
			
		}
		if(snakex[0]==foodx&&snakey[0]==foody)
		{len++;
		score=score+10;
		foodx=25+25*rand.nextInt(30);
		foody=75+25*rand.nextInt(20);
		}
		
		for(int i=1;i<len;i++)
		{
			if(snakex[i]==snakex[0] && snakey[i]==snakey[0])
				isFailed=true;
		}
		if(snakex[0]>850||snakex[0]<0||snakey[0]<75||snakey[0]>650)
			isFailed=true;
		
		repaint();
		}
		
		timer.start();
		
	}

}
