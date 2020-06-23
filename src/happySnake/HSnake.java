package happySnake;
import javax.swing.JFrame;
    

@SuppressWarnings("serial")
public class HSnake extends JFrame {


	public  HSnake() {
		JFrame frame=new JFrame("HappySnake");
		frame.setBounds(100,100,900,720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new HPanel());
		
		

		frame.setVisible(true);
		

	}

}
