package happySnake;
import javax.swing.JFrame;
    

@SuppressWarnings("serial")
public class HSnake extends JFrame {


	public  HSnake() {//���캯��
		JFrame frame=new JFrame("HappySnake");//���ڱ���
		frame.setBounds(100,100,900,720);//���ô��ڴ�С
		frame.setResizable(false);//���ڲ��ܵ�����С
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�����˳�
		frame.add(new HPanel());//��ʼ��ͼ
		frame.setVisible(true);
		

	}

}
