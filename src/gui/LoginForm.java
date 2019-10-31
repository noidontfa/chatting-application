package gui;

import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import listener.MyLoginActionListener;

public class LoginForm extends JFrame {

	/**
	 * 
	 */
	private static LoginForm instance;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPassword;
	private JTextField txtUserName;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private Button btnLogin;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm login = LoginForm.getInstance();
					login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnLogin = new Button("Login");
		btnLogin.addActionListener(new MyLoginActionListener());
		btnLogin.setBounds(193, 172, 66, 21);
		contentPane.add(btnLogin);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(176, 132, 96, 19);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(176, 101, 96, 19);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(88, 104, 66, 13);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(88, 135, 66, 13);
		contentPane.add(lblNewLabel_1);
	}
	
	public static LoginForm getInstance() {
		if(instance == null) {
			instance = new LoginForm();
		} 
		return instance;			
	}

	public JTextField getTxtPassword() {
		return txtPassword;
	}

	public JTextField getTxtUserName() {
		return txtUserName;
	}
	
	
	
}
