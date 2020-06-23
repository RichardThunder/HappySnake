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
	/*###########导入图片#########*/
	ImageIcon title = new ImageIcon("image/BGUp.jpg");
	ImageIcon body = new ImageIcon("image/body.png");
	ImageIcon up = new ImageIcon("image/up.png");
	ImageIcon down = new ImageIcon("image/down.png");
	ImageIcon left = new ImageIcon("image/left.png");
	ImageIcon right = new ImageIcon("image/right.png");
	ImageIcon food = new ImageIcon("image/food.png");
	
	
/*##############属性定义##############*/
	int score=0;//分数
	int len = 3;//蛇的长度，初始长度为3
	int[] snakex = new int[750];//蛇头坐标的数组
	int[] snakey = new int[750];
	String fx = "R";//初始化蛇头向右
	boolean isStarted=false;//是否开始
	boolean isFailed=false;//是否结束
	Timer timer=new Timer(100,this);//定时器
	int foodx;//生成食物座标
	int foody;
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
	  g.drawString("Score:"+score, 700, 50);//画出分数
	  switch(fx)//根据键值选择移动方向
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
	 food.paintIcon(this, g, foodx, foody);//根据随机生成的位置画出食物
		 //if(isStarted==false)
		 //{画出提示信息：通过空格键来控制开始和暂停
			 g.setColor(Color.white);
			 g.setFont(new Font("arial",Font.BOLD,40));
			 g.drawString("Press space to countinue/pause", 50, 50);
		// }
		 if(isFailed)//失败时画出分数和提示信息
		 {
			 g.setColor(Color.red);
			 g.setFont(new Font("arial",Font.BOLD,40));
			 g.drawString("Failed:Score:"+score, 300, 300);
			 g.drawString("Press space to resume", 300, 350);
		 }
	  
	  for(int i=1;i<len;i++)//画出身体的移动
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
		foodx=25+25*rand.nextInt(29);
		foody=75+25*rand.nextInt(19);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
    public void keyPressed(KeyEvent e) {
    	int keyCode= e.getKeyCode();
    	if(keyCode==KeyEvent.VK_SPACE)//空格来控制开始暂停重新开始
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
    	else if(keyCode==KeyEvent.VK_RIGHT &&fx!="L")//蛇不能回头吃自己
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
		if(fx=="R") //向右时坐标的变化
		{
		snakex[0]=snakex[0]+25;
		
		}
		
		if(fx=="L") {//向左时坐标的变化
			snakex[0]=snakex[0]-25;
			
		}
		if(fx=="U") {//向上时坐标的变化
			snakey[0]=snakey[0]-25;
		
		}
		if(fx=="D") {//向下时坐标的变化
			snakey[0]=snakey[0]+25;
			
		}
		if(snakex[0]==foodx&&snakey[0]==foody)//蛇吃到食物的判定
		{len++;
		score=score+10;
		foodx=25+25*rand.nextInt(30);
		foody=75+25*rand.nextInt(20);
		}
		
		for(int i=1;i<len;i++)//蛇吃到自己的判定
		{
			if(snakex[i]==snakex[0] && snakey[i]==snakey[0])
				isFailed=true;
		}
		if(snakex[0]>850||snakex[0]<0||snakey[0]<75||snakey[0]>650)//蛇碰到边界的判定
			isFailed=true;
		
		repaint();
		}
		
		timer.start();//重新调用定时器
		
	}

}
