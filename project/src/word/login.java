package word;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class login {

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 153, 204));
		frame.setBounds(100, 100, 966, 493);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(72, 99, 118, 39);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(72, 214, 118, 39);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(200, 108, 199, 28);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("LOGIN TO SHOP SHOES");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(149, 29, 293, 59);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("ACCESS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con=null;
                Statement st=null;
                ResultSet rs=null;
                String url = "jdbc:mysql://localhost:3306/BB";
                String username = "root";
                String password = "1234";

                String Query = "select * from BB.user where ID='"+textField_1.getText()+"'and PASSWORD='"+textField_2.getText()+"'";
                try {
                    con = DriverManager.getConnection(url, username, password);
                    st = con.createStatement();
                    rs = st.executeQuery(Query);
                    if(rs.next()) {
                        product.main(null);
                        frame.dispose();
                    }
                    
                    else {
                        JOptionPane.showMessageDialog(null, "Wrong ID or Password");
                    }
                }
                catch(Exception a) {
                    a.printStackTrace();
                }
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(64, 307, 126, 39);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnRemove = new JButton("REMOVE");
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRemove.setBounds(251, 307, 126, 39);
		frame.getContentPane().add(btnRemove);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(200, 223, 199, 28);
		frame.getContentPane().add(textField_2);
		
		JButton btnNewButton_1 = new JButton("ADD USER");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adduser.main(null);
				frame.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(72, 379, 305, 28);
		frame.getContentPane().add(btnNewButton_1);
	}
}
