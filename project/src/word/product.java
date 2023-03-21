package word;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class product {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	
	Connection Con= null;
	Statement St = null;
	ResultSet Rs = null;
	public void SelectSeller()
	{
		try {
		Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BB","root","1234");
		St = Con.createStatement();
		Rs = St.executeQuery("Select * from bb.pd1");
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
					product window = new product();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public product() {
		initialize();
		SelectSeller();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 204, 204));
		frame.setBounds(100, 100, 983, 496);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(54, 52, 125, 40);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PRICE");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(54, 103, 125, 40);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNumberOfProducts = new JLabel("NUMBER OF PRODUCTS");
		lblNumberOfProducts.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumberOfProducts.setBounds(486, 52, 215, 40);
		frame.getContentPane().add(lblNumberOfProducts);
		
		textField = new JTextField();
		textField.setBounds(140, 63, 125, 27);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(140, 114, 125, 27);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(747, 63, 125, 27);
		frame.getContentPane().add(textField_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 187, 858, 248);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			int Myindex = table.getSelectedRow();
			textField.setText(model.getValueAt(Myindex, 0).toString());
			textField_1.setText(model.getValueAt(Myindex, 1).toString());
			textField_2.setText(model.getValueAt(Myindex, 2).toString());
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"ID", "PRICE", "NUMBER "
			}
		));
		
		JLabel lblNewLabel_2 = new JLabel("PRODUCT");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(249, 0, 293, 59);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("ADD");
		
			
		btnNewButton.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				if(textField.getText().isEmpty()||textField_1.getText().isEmpty()||textField_2.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Missing Information");
				}
				else {
					try {
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BB","root","1234");
				PreparedStatement add = Con.prepareStatement("insert into pd1 values(?,?,?)");
				add.setString(1, textField.getText());
				add.setString(2, textField_1.getText());
				add.setString(3, textField_2.getText());
				int row = add.executeUpdate();
				JOptionPane.showMessageDialog(null, "Seller Added Successfully");
				Con.close();
				SelectSeller();
					} catch (Exception a) {
					a.printStackTrace();
				
				
				
			}
				}
		}});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(311, 116, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textField.getText().isEmpty()||textField_1.getText().isEmpty()||textField_2.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Missing Information");
				}
				else {
					try {
						Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BB","root","1234");
						String Query ="Update BB.pd1 set PRICE='"+textField_1.getText()+"'"+",NUMBER='"+textField_2.getText()+"'"+"where ID="+textField.getText();
						Statement Add = Con.createStatement();
						Add.executeUpdate(Query);
						JOptionPane.showMessageDialog(null, "Seller Updated");
						SelectSeller();
					}catch(SQLException a)
					{
						a.printStackTrace();
					}
				}
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEdit.setBounds(427, 116, 89, 23);
		frame.getContentPane().add(btnEdit);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(559, 116, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Enter The Seller Tobe Deleted");
				}
				else {
				try {
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BB","root","1234");
				String SId = textField.getText();
				String Query = "Delete from BB.pd1 where ID="+SId;
				Statement Add = Con.createStatement();
				Add.executeUpdate(Query);
				SelectSeller();
				JOptionPane.showMessageDialog(null, "Seller Added Successfully");
				}catch(Exception a)
				{
					a.printStackTrace();
				}
				
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(682, 116, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton btnNewButton_1 = new JButton("Log out\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.main(null);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(870, 11, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}
