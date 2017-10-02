package dbms_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;

public class search extends JFrame {

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtage;
	private JTextField txtfname;
	private JTextField txtlname;
	private JTextField txtemail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					search frame = new search();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public search() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		txtid = new JTextField();
		txtid.setColumns(10);

		JLabel lblEnterPatientId = new JLabel("enter patient id ");

		JLabel lblEnterPatientName = new JLabel("Search with age ");

		txtage = new JTextField();
		txtage.setColumns(10);

		final JComboBox gender = new JComboBox();
		gender.insertItemAt("male", 0);
		gender.insertItemAt("female", 1);
		gender.insertItemAt("unknown", 2);

		final JFrame frame1 = new JFrame("Database Search Result");
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame1.getContentPane().setLayout(new BorderLayout());
		// TableModel tm = new TableModel();
		String[] columnNames = { "pid", "fname", "lname", "age", "gender", "email" };
		final DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		// DefaultTableModel model = new DefaultTableModel(tm.getData1(),
		// tm.getColumnNames());
		// table = new JTable(model);
		JTable table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		final JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JButton btnSearch = new JButton("search");
		btnSearch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int i = 0;
				int j;

				while (model.getRowCount() != 0)
					model.removeRow(0);

				Connection con = null;

				try {

					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "sampledb", "pass1234");

					// int pid= Integer.parseInt(txtid.getText());
					// int page=Integer.parseInt(txtage.getText());

					// String query= "select * from patient where
					// pfirstname='"+txtfname.getText()+"' or pid=
					// "+txtid.getText()+" or page= "+txtage.getText()+" or
					// plastname= '"+txtlname.getText()+"' or pgender=
					// "+gender.getSelectedIndex()+" or pemail =
					// '"+txtemail.getText()+"';";

					String query = "select * from patient where ";
					int count = 0;

					// if(txtid.getText().equals(""))
					// {
					// txtid.setText("0");
					// txtage.setText("0");

					// }
					// if(txtage.getText().equals(""))
					// {
					// txtid.setText("");
					// txtage.setText("0");

					// }
					int count1 = 0;
					if (!txtid.getText().equals("")) {
						if (count == 0 && count1 == 0) {
							query = query + "pid= " + Integer.parseInt(txtid.getText()) + "";
							count = 1;
							count1 = 1;
						} else {
							query = query + " and pid= " + txtid.getText() + " ";
							count = 1;
						}
					} else {
						count = 1;
					}

					if (!txtage.getText().equals("")) {
						if (count1 == 0) {
							query = query + " page=" + txtage.getText();
							count = 2;
						} else {
							query = query + "  and  page= " + txtage.getText() + "";
							count = 3;
						}
					} else {
						count = 0;
					}

					if (!txtfname.getText().equals("")) {
						if (count1 == 0) {
							query = query + "pfirstname= '" + txtfname.getText() + "'";
							count = 4;
						} else {
							query = query + "  and pfirstname= '" + txtfname.getText() + "' ";
							count = 4;
						}
					} else {
						count = 2;
					}

					if (!txtlname.getText().equals("")) {
						if (count1 == 0) {
							query = query + " plastname='" + txtlname.getText() + "'";
							count = 5;
						} else {
							query = query + "  and  plastname= '" + txtlname.getText() + "' ";
							count = 5;
						}
					} else {
						count = 3;
					}

					int g = gender.getSelectedIndex();
					if (g != -1) {
						if (count1 == 0) {
							query = query + "  pgender= " + g + "";
							count = 6;
						} else {
							query = query + " and   pgender= " + g + "";
							count = 6;
						}
					} else {
						count = 4;
					}

					gender.setSelectedIndex(-1);

					if (!txtemail.getText().equals("")) {
						if (count == 4) {

							query = query + " pemail= '" + txtemail.getText() + "'";
							count = 7;
						} else {
							query = query + "  and pemail = '" + txtemail.getText() + "' ";
							count = 7;
						}

					} else {
						count = 6;
					}

					query = query + ";";
					System.out.println("" + query);
					java.sql.Statement stm = con.createStatement();
					ResultSet s = stm.executeQuery(query);

					while (s.next()) {
						String patientid = s.getString("pid");
						String patient_fname = s.getString("pfirstname");
						String patient_lname = s.getString("plastname");
						String patient_age = s.getString("page");
						String patient_gender = s.getString("pgender");
						if (patient_gender.equals("0")) {
							patient_gender = "Male";
						}
						if (patient_gender.equals("1")) {
							patient_gender = "Female";
						}
						if (patient_gender.equals("2")) {
							patient_gender = "Unknown";
						}
						String patient_email = s.getString("pemail");
						// JOptionPane.showMessageDialog(null,"Found
						// \n"+patientid+"\n FIrst name : "+ patient_fname+"\n
						// Last name: "+ patient_lname+"\n Patient age: "+
						// patient_age+"\n Patient Gender: "+
						// patient_gender+"\nPatient email: "+ patient_email);
						txtid.setText("");
						txtage.setText("");
						frame1.getContentPane().add(scroll);
						frame1.setVisible(true);
						frame1.setSize(400, 300);
						model.addRow(new Object[] { patientid, patient_fname, patient_lname, patient_age,
								patient_gender, patient_email });
						i++;

					}

					/*
					 * else{ JOptionPane.showMessageDialog(null," not Found");
					 * 
					 * }
					 */
					if (i < 1) {
						 JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception E) {
					System.out.println("" + E);
				}

			}

		});

		JButton btnPreviousMenu = new JButton("Previous menu");
		btnPreviousMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame part1 = new part1();
				part1.setVisible(true);
				search.this.dispose();

			}
		});
		SpringLayout sl_contentPane = new SpringLayout();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtage, -3, SpringLayout.NORTH, lblEnterPatientName);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtage, 0, SpringLayout.WEST, txtid);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblEnterPatientName, 17, SpringLayout.SOUTH,
				lblEnterPatientId);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblEnterPatientName, 0, SpringLayout.WEST, btnSearch);
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtid, -3, SpringLayout.NORTH, lblEnterPatientId);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtid, 46, SpringLayout.EAST, lblEnterPatientId);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblEnterPatientId, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblEnterPatientId, 0, SpringLayout.WEST, btnSearch);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnPreviousMenu, 0, SpringLayout.NORTH, btnSearch);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnPreviousMenu, 24, SpringLayout.EAST, btnSearch);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSearch, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSearch, -10, SpringLayout.SOUTH, contentPane);
		contentPane.setLayout(sl_contentPane);
		contentPane.add(btnSearch);
		contentPane.add(btnPreviousMenu);
		contentPane.add(lblEnterPatientId);
		contentPane.add(lblEnterPatientName);
		contentPane.add(txtage);
		contentPane.add(txtid);

		JLabel lblSearchWithFirstname = new JLabel("Search with firstname");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSearchWithFirstname, 17, SpringLayout.SOUTH,
				lblEnterPatientName);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSearchWithFirstname, 0, SpringLayout.WEST, btnSearch);
		contentPane.add(lblSearchWithFirstname);

		txtfname = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtfname, -3, SpringLayout.NORTH, lblSearchWithFirstname);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtfname, 0, SpringLayout.WEST, txtage);
		contentPane.add(txtfname);
		txtfname.setColumns(10);

		JLabel lblSearchByLast = new JLabel("Search by last name");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSearchByLast, 20, SpringLayout.SOUTH,
				lblSearchWithFirstname);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSearchByLast, 0, SpringLayout.WEST, btnSearch);
		contentPane.add(lblSearchByLast);

		txtlname = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtlname, -3, SpringLayout.NORTH, lblSearchByLast);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtlname, 0, SpringLayout.WEST, txtage);
		contentPane.add(txtlname);
		txtlname.setColumns(10);

		JLabel lblSearchByGender = new JLabel("search by gender");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSearchByGender, 0, SpringLayout.WEST, btnSearch);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblSearchByGender, -93, SpringLayout.SOUTH, contentPane);
		contentPane.add(lblSearchByGender);

		sl_contentPane.putConstraint(SpringLayout.NORTH, gender, -3, SpringLayout.NORTH, lblSearchByGender);
		sl_contentPane.putConstraint(SpringLayout.WEST, gender, 0, SpringLayout.WEST, txtage);
		sl_contentPane.putConstraint(SpringLayout.EAST, gender, -5, SpringLayout.EAST, txtage);
		contentPane.add(gender);

		JLabel lblSearchByEmail = new JLabel("search by email");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSearchByEmail, 17, SpringLayout.SOUTH, lblSearchByGender);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSearchByEmail, 0, SpringLayout.WEST, btnSearch);
		contentPane.add(lblSearchByEmail);

		txtemail = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, gender, -11, SpringLayout.NORTH, txtemail);
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtemail, -3, SpringLayout.NORTH, lblSearchByEmail);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtemail, 0, SpringLayout.WEST, txtage);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
	}
}
