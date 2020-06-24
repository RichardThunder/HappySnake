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
	/*###########����ͼƬ#########*/
	ImageIcon title = new ImageIcon("image/BGUp.jpg");
	ImageIcon body = new ImageIcon("image/body.png");
	ImageIcon up = new ImageIcon("image/up.png");
	ImageIcon down = new ImageIcon("image/down.png");
	ImageIcon left = new ImageIcon("image/left.png");
	ImageIcon right = new ImageIcon("image/right.png");
	ImageIcon food = new ImageIcon("image/food.png");
	
	
/*##############���Զ���##############*/
	int score=0;//����
	int len = 3;//�ߵĳ��ȣ���ʼ����Ϊ3
	int[] snakex = new int[750];//��ͷ���������
	int[] snakey = new int[750];
	String fx = "R";//��ʼ����ͷ����
	boolean isStarted=false;//�Ƿ�ʼ
	boolean isFailed=false;//�Ƿ����
	Timer timer=new Timer(100,this);//��ʱ��
	int foodx;//����ʳ������
	int foody;
	Random rand=new Random();//����ʳ������������

	public HPanel() {//���캯��
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
	  
	  g.fillRect(0, 75, 900, 600);//��ɫ����
	  g.drawString("Len"+len, 750, 535);
	  g.setColor(Color.green);
	  g.setFont(new Font("arial",Font.ROMAN_BASELINE,20));
	  g.drawString("Score:"+score, 700, 50);//��������
	  switch(fx)//���ݼ�ֵѡ���ƶ�����
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
	 food.paintIcon(this, g, foodx, foody);//����������ɵ�λ�û���ʳ��
		 //if(isStarted==false)
		 //{������ʾ��Ϣ��ͨ���ո�������ƿ�ʼ����ͣ
			 g.setColor(Color.white);
			 g.setFont(new Font("arial",Font.BOLD,40));
			 g.drawString("Press space to countinue/pause", 50, 50);
		// }
		 if(isFailed)//ʧ��ʱ������������ʾ��Ϣ
		 {
			 g.setColor(Color.red);
			 g.setFont(new Font("arial",Font.BOLD,40));
			 g.drawString("Failed:Score:"+score, 300, 300);
			 g.drawString("Press space to resume", 300, 350);
		 }
	  
	  for(int i=1;i<len;i++)//����������ƶ�
	  {
		  body.paintIcon(this, g, snakex[i], snakey[i]);
	  }
	  
  }

	// ��ʼ���ߵ�����
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
    	if(keyCode==KeyEvent.VK_SPACE)//�ո������ƿ�ʼ��ͣ���¿�ʼ
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
    	else if(keyCode==KeyEvent.VK_RIGHT &&fx!="L")//�߲��ܻ�ͷ���Լ�
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
		if(fx=="R") //����ʱ����ı仯
		{
		snakex[0]=snakex[0]+25;
		
		}
		
		if(fx=="L") {//����ʱ����ı仯
			snakex[0]=snakex[0]-25;
			
		}
		if(fx=="U") {//����ʱ����ı仯
			snakey[0]=snakey[0]-25;
		
		}
		if(fx=="D") {//����ʱ����ı仯
			snakey[0]=snakey[0]+25;
			
		}
		if(snakex[0]==foodx&&snakey[0]==foody)//�߳Ե�ʳ����ж�
		{len++;
		score=score+10;
		foodx=25+25*rand.nextInt(30);
		foody=75+25*rand.nextInt(20);
		}
		
		for(int i=1;i<len;i++)//�߳Ե��Լ����ж�
		{
			if(snakex[i]==snakex[0] && snakey[i]==snakey[0])
				isFailed=true;
		}
		if(snakex[0]>850||snakex[0]<0||snakey[0]<75||snakey[0]>650)//�������߽���ж�
			isFailed=true;
		
		repaint();
		}
		
		timer.start();//���µ��ö�ʱ��
		
	}

}
