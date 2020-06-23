package happySnake;
import javax.swing.JFrame;
    

@SuppressWarnings("serial")
public class HSnake extends JFrame {


	public  HSnake() {//构造函数
		JFrame frame=new JFrame("HappySnake");//窗口标题
		frame.setBounds(100,100,900,720);//设置窗口大小
		frame.setResizable(false);//窗口不能调整大小
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口退出
		frame.add(new HPanel());//开始绘图
		frame.setVisible(true);
		

	}

}
