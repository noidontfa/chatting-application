package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import keeptoo.KGradientPanel;
import listener.MyLoginActionListener;

public class LoginForm extends JFrame {

	/**
	 * 
	 */
	private static LoginForm instance;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
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
		setBounds(100, 100, 616, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		KGradientPanel gradientPanel = new KGradientPanel();
		gradientPanel.kStartColor = new Color(78, 25, 190);
		gradientPanel.kEndColor = new Color(119, 185, 185);
		contentPane.add(gradientPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(2, 2, 1, 2));
		GroupLayout gl_gradientPanel = new GroupLayout(gradientPanel);
		gl_gradientPanel.setHorizontalGroup(
			gl_gradientPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_gradientPanel.createSequentialGroup()
					.addGap(161)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
					.addGap(160))
		);
		gl_gradientPanel.setVerticalGroup(
			gl_gradientPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_gradientPanel.createSequentialGroup()
					.addGap(72)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(74, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("Sign In");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtUserName = new JTextField();
		txtUserName.setBackground(Color.WHITE);
		txtUserName.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
	
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtUserName.setColumns(10);
		txtUserName.addActionListener(new MyLoginActionListener());
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtPassword = new JPasswordField();
		txtPassword.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.desktop));
		txtPassword.addActionListener(new MyLoginActionListener());
		JButton btnNewButton = new JButton();
		btnNewButton.setText("Sign In");
		btnNewButton.addActionListener(new MyLoginActionListener());
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBackground(new Color(78, 25, 190));
		btnNewButton.setForeground(new Color(255,255,255));
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(52)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton)
							.addContainerGap())
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addContainerGap())
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtUserName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
										.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
										.addComponent(lblUsername, Alignment.LEADING))
									.addGap(64))))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(35)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(lblUsername)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		gradientPanel.setLayout(gl_gradientPanel);
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
