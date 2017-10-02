package dbms_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class add_details extends JFrame {

	private JPanel contentPane;
	private JTextField fname;
	private JTextField lname;
	private JTextField page;
	private JTextField pemail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					add_details frame = new add_details();
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
	public add_details() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 466, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblEnterPatientName = new JLabel("Enter patient first name: ");

		fname = new JTextField();
		fname.setColumns(10);

		JLabel lblEnter = new JLabel(" Enter patient last name");

		final JLabel lblPatientId = new JLabel("Patient ID :  ");

		final JLabel lblpid = new JLabel("");
		try {
			String pid2 = null;
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "sampledb",
					"pass1234");

			String query = "select * from patient";
			java.sql.Statement stm = con.createStatement();
			ResultSet s = stm.executeQuery(query);
			while (s.next()) {
				pid2 = s.getString("pid");
				System.out.println("" + pid2);

			}
			int pid = Integer.parseInt(pid2);
			pid++;
			lblpid.setText("" + pid);

		} catch (Exception E) {
			System.out.println("" + E);
		}
		lname = new JTextField();
		lname.setColumns(10);

		JLabel lblEnterPatientAge = new JLabel("Enter patient age");

		page = new JTextField();
		page.setColumns(10);

		JLabel lblSelectGender = new JLabel("Select Gender ");

		final JComboBox gender = new JComboBox();
		gender.insertItemAt("male", 0);
		gender.insertItemAt("female", 1);
		gender.insertItemAt("unknown", 2);

		pemail = new JTextField();
		pemail.setColumns(10);

		JLabel lblEnterEmialId = new JLabel("Enter emial id");

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String pid2 = null;
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "sampledb",
							"pass1234");

					String query = "select * from patient";
					java.sql.Statement stm = con.createStatement();
					ResultSet s = stm.executeQuery(query);
					while (s.next()) {
						pid2 = s.getString("pid");
					}
					int pid = Integer.parseInt(pid2);
					pid++;

					String pfname = fname.getText();
					String plname = lname.getText();
					String pemail1 = pemail.getText();
					int page1 = Integer.parseInt(page.getText());
					int pgender = gender.getSelectedIndex();
					query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page1 + ", "
							+ pgender + ",'" + pemail1 + "');";
					stm = con.createStatement();
					stm.executeUpdate(query);

					fname.setText("");
					lname.setText("");
					pemail.setText("");
					page.setText("");
					gender.setSelectedIndex(-1);
					lblpid.setText("");

				} catch (Exception E) {
					System.out.println("" + E);
					JOptionPane.showMessageDialog(null, "" + E.getMessage());
				}
			}
		});

		JButton btnPreviousMenu = new JButton("Previous menu");
		btnPreviousMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// JFrame part1 = new part1();
				// setVisible(true);
				add_details.this.setVisible(false);

			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addGroup(gl_contentPane
						.createParallelGroup(
								Alignment.LEADING)
						.addGroup(
								gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblEnterPatientName).addComponent(lblPatientId)
												.addComponent(lblEnter).addComponent(lblSelectGender)
												.addComponent(lblEnterEmialId).addComponent(
														lblEnterPatientAge))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(pemail).addComponent(lname).addComponent(lblpid)
												.addComponent(fname).addComponent(page)
												.addComponent(gender, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnSubmit)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnPreviousMenu)))
				.addContainerGap(205, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(20)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(lblPatientId)
								.addComponent(lblpid))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(fname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEnterPatientName))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblEnter).addComponent(
						lname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(17)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblEnterPatientAge)
						.addComponent(page, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblSelectGender)
						.addComponent(gender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(pemail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEnterEmialId)).addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnSubmit)
						.addComponent(btnPreviousMenu)))

		);
		contentPane.setLayout(gl_contentPane);
	}
}
