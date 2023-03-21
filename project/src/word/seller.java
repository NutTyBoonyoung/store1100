package word;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.TextArea;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;

public class seller {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTextField textField_3;
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
					seller window = new seller();
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
	public seller() {
		initialize();
		SelectSeller();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 204));
		frame.setBounds(100, 100, 1002, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("สินค้า");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(41, 43, 125, 40);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("จำนวนสินค้า");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(41, 94, 125, 40);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNumberOfProducts = new JLabel("จำนวนสินค้าที่คงเหลือ");
		lblNumberOfProducts.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumberOfProducts.setBounds(421, 94, 215, 40);
		frame.getContentPane().add(lblNumberOfProducts);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(176, 54, 125, 27);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(174, 105, 125, 27);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(688, 105, 125, 27);
		frame.getContentPane().add(textField_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 162, 367, 134);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int Myindex = table.getSelectedRow();
				textField.setText(model.getValueAt(Myindex, 0).toString());
				textField_3.setText(model.getValueAt(Myindex, 1).toString());
				textField_2.setText(model.getValueAt(Myindex, 2).toString());
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"ID", "NAME", "PRICE", "QUANTITY"
			}
		));
		
		JLabel lblNewLabel_1_1 = new JLabel("ราคาสินค้า");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(421, 43, 125, 40);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(688, 43, 125, 27);
		frame.getContentPane().add(textField_3);
		
		
		
		
		
		JButton btnNewButton_2 = new JButton("ย้อนกลับ");
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setBounds(41, 379, 125, 40);
		frame.getContentPane().add(btnNewButton_2);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("SELLER");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(886, 0, 92, 59);
		frame.getContentPane().add(lblNewLabel_2);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(518, 184, 360, 224);
		frame.getContentPane().add(textPane);
		
		JButton btnNewButton_3 = new JButton("ล้าง");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setText("*** DB **\n"+"Item.\t\tquantity.\ttotal.\n");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3.setBounds(210, 379, 125, 40);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton = new JButton("ยืนยัน");
		btnNewButton.addActionListener(new ActionListener() {
			
			
			
			public void actionPerformed(ActionEvent e) {
				int i =0;
                double a1 = Double.parseDouble(textField_3.getText());
                double a2 = Double.parseDouble(textField_1.getText());
                double a3 = a1*a2;
                double a4 = Double.parseDouble(textField_2.getText());
                int a5 = (int)a4-(int)a2;


                String url = "jdbc:mysql://localhost:3306/BB";
                String username = "root";
                String password = "1234";

                String t= Integer.toString(a5);
                textField_2.setText(t);



                if(textField_1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Missing Information");
                }
                else {

                    try {
                         Connection con = DriverManager.getConnection(url, username, password);
                         String Query = "Update BB.pd1 set NUMBER='"+a5+"'"+"where ID="+textField.getText();
                         Statement Add = con.createStatement();
                         Add.executeUpdate(Query);
                         SelectSeller();
                    }
                    catch(Exception a)
                    {
                        a.printStackTrace();
                    }

                    i++;

                    if(i==1) {
                    	textPane.setText(textPane.getText()+textField.getText()+"\t\t"+textField_1.getText()+"\t"+a3+"\n");



                    }
                    else {

                    }
                }
				
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
			login.main(null);
			frame.dispose();
				
               
			}

			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(41, 307, 125, 40);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ออกบิล");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    textPane.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(210, 307, 125, 40);
		frame.getContentPane().add(btnNewButton_1);
	}
}
