package word;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class adduser {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JButton btnEdit;
	private JLabel lblUserAdmin;
	private JLabel lblPassword;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;

	Connection Con= null;
	Statement St = null;
	ResultSet Rs = null;
	public void SelectSeller()
	{
		try {
		Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BB","root","1234");
		St = Con.createStatement();
		Rs = St.executeQuery("Select * from bb.user");
		table.setModel(DbUtils.resultSetToTableModel(Rs));
		}catch(Exception a)
		{
			a.printStackTrace();
		}
		}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adduser window = new adduser();
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
	public adduser() {
		
		initialize();
		SelectSeller();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 988, 492);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textField.getText().isEmpty()||textField_1.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Missing Information");
				}
				else {
					try {
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BB","root","1234");
				PreparedStatement add = Con.prepareStatement("insert into user values(?,?)");
				add.setString(1, textField.getText());
				add.setString(2, textField_1.getText());
				int row = add.executeUpdate();
				JOptionPane.showMessageDialog(null, "user Added Successfully");
				Con.close();
				SelectSeller();
					} catch (Exception a) {
					a.printStackTrace();
				
				
				
			}
			}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(42, 122, 125, 40);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnReduce = new JButton("REDUCE");
		btnReduce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Enter The Seller Tobe Deleted");
				}
				else {
				try {
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BB","root","1234");
				String SId = textField.getText();
				String Query = "Delete from BB.user where ID="+SId;
				Statement Add = Con.createStatement();
				Add.executeUpdate(Query);
				SelectSeller();
				JOptionPane.showMessageDialog(null, "user Added Successfully");
				}catch(Exception a)
				{
					a.printStackTrace();
				}
				
				}
			}
		});
		btnReduce.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReduce.setBounds(215, 122, 125, 40);
		frame.getContentPane().add(btnReduce);
		
		textField = new JTextField();
		textField.setBounds(189, 40, 198, 40);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(568, 40, 198, 40);
		frame.getContentPane().add(textField_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 189, 840, 236);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int Myindex = table.getSelectedRow();
				textField.setText(model.getValueAt(Myindex, 0).toString());
				textField_1.setText(model.getValueAt(Myindex, 1).toString());
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"ID", "PASS WORD"
			}
		));
		
		btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty()||textField_1.getText().isEmpty()||textField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Missing Information");
				}
				else {
					try {
						Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BB","root","1234");
						String Query ="Update BB.user set PASSWORD='"+textField_1.getText()+"'"+"where ID="+textField.getText();
						Statement Add = Con.createStatement();
						Add.executeUpdate(Query);
						JOptionPane.showMessageDialog(null, "user Updated");
						SelectSeller();
					}catch(SQLException a)
					{
						a.printStackTrace();
					}
				}
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEdit.setBounds(406, 122, 125, 40);
		frame.getContentPane().add(btnEdit);
		
		lblUserAdmin = new JLabel("User Admin");
		lblUserAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUserAdmin.setBounds(61, 37, 118, 39);
		frame.getContentPane().add(lblUserAdmin);
		
		lblPassword = new JLabel("password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(440, 41, 118, 39);
		frame.getContentPane().add(lblPassword);
		
		lblNewLabel = new JLabel("ADD USER");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(833, 0, 141, 59);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("GO TO LOGIN");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.main(null);
				frame.dispose();

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(767, 122, 178, 40);
		frame.getContentPane().add(btnNewButton_1);
	}
}
