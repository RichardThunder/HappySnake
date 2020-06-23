package happySnake;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Start extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) //����
	{
		EventQueue.invokeLater(new Runnable()//������ʼ����
		{
			public void run() 
			{
				try
				{
					Start frame = new Start();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 */
	public Start() {
		setTitle("HappySnake");//���ڱ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);//���ڴ�С
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnNewButton = new JButton("START");//������ʼ��ť
		btnNewButton.addActionListener
		(new ActionListener() {//�����ʼ��ť������Ϸ
			public void actionPerformed(ActionEvent e) {
			HSnake HS=new HSnake();
			setVisible(false);
			}
		});
		btnNewButton.setBounds(142, 87, 131, 56);//���ð�ť�Ĵ�С
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 436, 263);//���ô��ڵĴ�С
		contentPane.add(panel);
	}
}
