package dbms_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JList;

public class part1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					part1 frame = new part1();
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
	public part1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("Initialise DB");

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
							"pass1234");

					String query = "Drop DATABASE sampledb;;";
					java.sql.Statement stm = con.createStatement();
					stm.executeUpdate(query);

					query = "CREATE DATABASE sampledb;;";
					stm = con.createStatement();
					stm.executeUpdate(query);

					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "sampledb", "pass1234");

					DatabaseMetaData db = con.getMetaData();
					ResultSet tables = db.getTables(null, null, "patient", null);
					if (tables.next()) {
						stm.executeUpdate(query);
						
						query = "Drop table msg;";
						stm = con.createStatement();
						stm.executeUpdate(query);
											
						query = "Drop table taketreatment;";
						stm = con.createStatement();
						stm.executeUpdate(query);
						
						query = "Drop table treatment;";
						stm = con.createStatement();
						stm.executeUpdate(query);
						
						query = "Drop table hassymptom;";
						stm = con.createStatement();
						stm.executeUpdate(query);

						query = "Drop table symptom;";
						stm = con.createStatement();
						stm.executeUpdate(query);

						query = "Drop table conditions;";
						stm = con.createStatement();

						query = "Drop table patient;";
						stm = con.createStatement();
						stm.executeUpdate(query);

						query = "Create table patient(pid int(3), pfirstname varchar(40), plastname varchar(40),page int, pgender int, pemail varchar(40), primary key(pid),unique(pemail));";
						stm = con.createStatement();
						stm.executeUpdate(query);

						int pid = 101;
						String pfname = "dhananjay";
						String plname = "kulkarni";
						String pemail = "d.kulkarni7@gmail.com";
						int page = 22;
						int pgender = 0;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 102;
						pfname = "pinak";
						plname = "salvekar";
						pemail = "dpinak.salvekar@gmail.com";
						page = 23;
						pgender = 0;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 103;
						pfname = "shraddha";
						plname = "salvekar";
						pemail = "shraddha@yahoo.com";
						page = 23;
						pgender = 1;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 104;
						pfname = "akshay";
						plname = "mutkule";
						pemail = "akshaymutkule@yahoo.com";
						page = 24;
						pgender = 0;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 105;
						pfname = "akshay";
						plname = "pachkhede";
						pemail = "askhaypachkhede@gmail.com";
						page = 24;
						pgender = 0;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 106;
						pfname = "Gaurav";
						plname = "bajaj";
						pemail = "gauravbajaj@gmail.com";
						page = 22;
						pgender = 0;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 107;
						pfname = "Hartej";
						plname = "Dhadhval";
						pemail = "hartej@gmail.com";
						page = 29;
						pgender = 0;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 108;
						pfname = "lakshmi";
						plname = "narayan";
						pemail = "narayan@gmail.com";
						page = 28;
						pgender = 2;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 109;
						pfname = "swapna";
						plname = "deshpande";
						pemail = "swapnadeshpande@gmail.com";
						page = 22;
						pgender = 1;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 110;
						pfname = "Neha";
						plname = "sharma";
						pemail = "nehasharma@gmail.com";
						page = 23;
						pgender = 1;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						query = "Create table symptom(sid int(3) Auto_increment , description varchar(50) , primary key(sid));";
						stm = con.createStatement();
						stm.executeUpdate(query);

						query = "Create table hassymptom (pid int(3), sid int(3), primary key(pid,sid), FOREIGN KEY (pid) REFERENCES patient(pid),FOREIGN KEY (sid) REFERENCES symptom(sid));";
						stm = con.createStatement();
						stm.executeUpdate(query);
						
						query = "Create table symptomtime (pid int(3),sid int(3), startd DATETIME , endd DATETIME, primary key(sid), FOREIGN KEY (pid) REFERENCES patient(pid),FOREIGN KEY (sid) REFERENCES symptom(pid,sid) );";
						stm = con.createStatement();
						stm.executeUpdate(query);
						
						
						query = "Create table conditions(cid int(3) Auto_increment , cdescription varchar(50) , primary key(cid));";
						stm = con.createStatement();
						stm.executeUpdate(query);

						query = "Create table hascondition(pid int(3), cid int(3), primary key(pid,cid), FOREIGN KEY (pid) REFERENCES patient(pid) , FOREIGN KEY (cid) REFERENCES conditions(cid));";
						stm = con.createStatement();
						stm.executeUpdate(query);
						
						query = "Create table conditionstime(pid int(3),cid int(3), startd DATETIME , endd DATETIME, primary key(cid), FOREIGN KEY (pid) REFERENCES patient(pid),FOREIGN KEY (cid) REFERENCES conditions(pid,cid));";
						stm = con.createStatement();
						stm.executeUpdate(query);
						
						query = "Create table treatment(tid int(3) Auto_incr"
								+ "ement, tdescription varchar(50),  primary key(tid) );";
						stm = con.createStatement();
						stm.executeUpdate(query);
						
						query = "Create table taketreatment(pid int(3), tid int(3), primary key(pid,tid), FOREIGN KEY (pid) REFERENCES patient(pid) , FOREIGN KEY (tid) REFERENCES treatment(tid));";
						stm = con.createStatement();
						stm.executeUpdate(query);
						
						query = "Create table treatmenttime(pid int(3),tid int(3), startd DATETIME , endd DATETIME, primary key(pid,tid), FOREIGN KEY (pid) REFERENCES patient(pid),FOREIGN KEY (tid) REFERENCES treatment(tid));";
						stm = con.createStatement();
						stm.executeUpdate(query);
						
						query = "Create table msg(spid int(3), rpid int(3),msgid int(3) Auto_increment,subject varchar(30),content varchar(50),primary key(msgid,spid,rtid), FOREIGN KEY (spid) REFERENCES patient(pid) , FOREIGN KEY (pid) REFERENCES patient(pid));";
						stm = con.createStatement();
						stm.executeUpdate(query);

						JOptionPane.showMessageDialog(null,"Initialisation Done");
					} else {

						query = "Create table patient(pid int(3), pfirstname varchar(40), plastname varchar(40),page int, pgender int, pemail varchar(40), primary key(pid),unique(pemail));";
						stm = con.createStatement();
						stm.executeUpdate(query);

						int pid = 101;
						String pfname = "dhananjay";
						String plname = "kulkarni";
						String pemail = "d.kulkarni7@gmail.com";
						int page = 22;
						int pgender = 0;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 102;
						pfname = "pinak";
						plname = "salvekar";
						pemail = "dpinak.salvekar@gmail.com";
						page = 23;
						pgender = 0;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 103;
						pfname = "shraddha";
						plname = "salvekar";
						pemail = "shraddha@yahoo.com";
						page = 23;
						pgender = 1;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 104;
						pfname = "akshay";
						plname = "mutkule";
						pemail = "akshaymutkule@yahoo.com";
						page = 24;
						pgender = 0;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 105;
						pfname = "akshay";
						plname = "pachkhede";
						pemail = "askhaypachkhede@gmail.com";
						page = 24;
						pgender = 0;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 106;
						pfname = "Gaurav";
						plname = "bajaj";
						pemail = "gauravbajaj@gmail.com";
						page = 22;
						pgender = 0;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 107;
						pfname = "Hartej";
						plname = "Dhadhval";
						pemail = "hartej@gmail.com";
						page = 29;
						pgender = 0;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 108;
						pfname = "lakshmi";
						plname = "narayan";
						pemail = "narayan@gmail.com";
						page = 28;
						pgender = 2;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 109;
						pfname = "swapna";
						plname = "deshpande";
						pemail = "swapnadeshpande@gmail.com";
						page = 22;
						pgender = 1;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						pid = 110;
						pfname = "Neha";
						plname = "sharma";
						pemail = "nehasharma@gmail.com";
						page = 23;
						pgender = 1;
						query = "INSERT INTO patient VALUES(" + pid + ",'" + pfname + "', '" + plname + "'," + page
								+ ", " + pgender + ",'" + pemail + "');";
						stm = con.createStatement();
						stm.executeUpdate(query);

						query = "Create table symptom(sid int(3) Auto_increment , description varchar(50), primary key(sid));";
						stm = con.createStatement();
						stm.executeUpdate(query);

						query = "Create table hassymptom (pid int(3), sid int(3), primary key(pid,sid), FOREIGN KEY (pid) REFERENCES patient(pid),FOREIGN KEY (sid) REFERENCES symptom(sid));";
						stm = con.createStatement();
						stm.executeUpdate(query);
						
						query = "Create table symptomtime (pid int(3),sid int(3), startd DATETIME , endd DATETIME, primary key(sid), FOREIGN KEY (pid) REFERENCES patient(pid),FOREIGN KEY (sid) REFERENCES symptom(sid) );";
						stm = con.createStatement();
						stm.executeUpdate(query);

						query = "Create table conditions(cid int(3) Auto_increment , cdescription varchar(50) , primary key(cid));";
						stm = con.createStatement();
						stm.executeUpdate(query);

						query = "Create table hascondition(pid int(3), cid int(3), primary key(pid,cid), FOREIGN KEY (pid) REFERENCES patient(pid) , FOREIGN KEY (cid) REFERENCES conditions(cid));";
						stm = con.createStatement();
						stm.executeUpdate(query);
						
						query = "Create table conditionstime(pid int(3),cid int(3), startd DATETIME , endd DATETIME, primary key(cid), FOREIGN KEY (pid) REFERENCES patient(pid),FOREIGN KEY (cid) REFERENCES conditions(cid));";
						stm = con.createStatement();
						stm.executeUpdate(query);
					
						query = "Create table treatment(tid int(3) Auto_increment, tdescription varchar(50),  primary key(tid) );";
						stm = con.createStatement();
						stm.executeUpdate(query);
						
						query = "Create table treatmenttime(pid int(3),tid int(3), startd DATETIME , endd DATETIME, primary key(pid,tid), FOREIGN KEY (pid) REFERENCES patient(pid),FOREIGN KEY (tid) REFERENCES treatment(tid));";
						stm = con.createStatement();
						stm.executeUpdate(query);
						
						query = "Create table taketreatment(pid int(3), tid int(3), primary key(pid,tid), FOREIGN KEY (pid) REFERENCES patient(pid) , FOREIGN KEY (tid) REFERENCES treatment(tid));";
						stm = con.createStatement();
						stm.executeUpdate(query);
						
						query = "Create table msg(spid int(3), rpid int(3),msgid int(3) Auto_increment,subject varchar(30),content varchar(50),primary key(msgid,spid,rpid), FOREIGN KEY (spid) REFERENCES patient(pid) , FOREIGN KEY (rpid) REFERENCES patient(pid));";
						stm = con.createStatement();
						stm.executeUpdate(query);


						JOptionPane.showMessageDialog(null,"Initialisation Done");
					}

				} catch (Exception E) {
					System.out.println("" + E);
					JOptionPane.showMessageDialog(null, "" + E.getMessage());
				}
			}
		});

		JList list = new JList();

		JButton btnSearch = new JButton("search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFrame search = new search();
				search.setVisible(true);

			}
		});

		JButton btnAddDetails = new JButton("Add Details");
		btnAddDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame add_details = new add_details();
				add_details.setVisible(true);

			}
		});

		JButton btnNewButton_1 = new JButton(" Update Details ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame updated = new updated();
				updated.setVisible(true);
			}
		});
		
		JButton btnNewButton_2 = new JButton("Add Symptom");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						
				JFrame symptom = new symptom();
				symptom.setVisible(true);
				//part1.this.setVisible(false);
			}
		});
		
		JButton btnNewButton_3 = new JButton("Add Condition");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame condition = new condition();
				condition.setVisible(true);
			//	part1.this.setVisible(false);
				
			}
		});
		
		JButton btnNewButton_4 = new JButton("Add Treatment");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame treatment= new treatment();
				treatment.setVisible(true);
			//	part1.this.setVisible(false);
				
			}
		});
		
		JButton btnNewButton_5 = new JButton("Send Messege");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame msg = new msg();
				msg.setVisible(true);
				//part1.this.setVisible(false);
				
			}
		});
		
		JButton btnPatients = new JButton("Query 6");
		btnPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFrame frame1 = new JFrame("Query 6 Result");
				frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame1.getContentPane().setLayout(new BorderLayout());
				
				String[] columnNames = { "pid", "cid", "cdescription"};
				final DefaultTableModel model = new DefaultTableModel();
				model.setColumnIdentifiers(columnNames);
				JTable table = new JTable();
				table.setModel(model);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				table.setFillsViewportHeight(true);
				final JScrollPane scroll = new JScrollPane(table);
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				
				try {

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "sampledb", "pass1234");

					
					String query = "select distinct ct.pid,ct.cid,c.cdescription from sampledb.conditionstime ct, sampledb.conditions c where c.cdescription='diabetes' and ct.pid not in(select ct.pid from sampledb.conditionstime ct where ct.startd=ct.endd);"; 
					System.out.println("" + query);
					java.sql.Statement stm = con.createStatement();
					ResultSet s = stm.executeQuery(query);
					while (s.next()) {
						String pid = s.getString("pid");
						String cid = s.getString("cid");
						String desc = s.getString("cdescription");
						frame1.getContentPane().add(scroll);
						frame1.setVisible(true);
						frame1.setSize(400, 300);
						model.addRow(new Object[] { pid, cid, desc });
						

					}
				}
				catch(Exception E)
				{}
			}
		});
		
		JButton btnQuery = new JButton("Query 8");
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					final JFrame frame1 = new JFrame("Query 8 Result");
					frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame1.getContentPane().setLayout(new BorderLayout());
					
					String[] columnNames = { "pid"};
					final DefaultTableModel model = new DefaultTableModel();
					model.setColumnIdentifiers(columnNames);
					JTable table = new JTable();
					table.setModel(model);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					table.setFillsViewportHeight(true);
					final JScrollPane scroll = new JScrollPane(table);
					scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
					
					try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "sampledb", "pass1234");

						
						String query = "SELECT distinct tt.pid FROM sampledb.taketreatment tt where tt.tid in(select t.tid from sampledb.treatment t where t.tdescription='physical therapy' and tt.pid not in(select m.spid from sampledb.msg m where tt.pid=m.spid));"; 
						System.out.println("" + query);
						java.sql.Statement stm = con.createStatement();
						ResultSet s = stm.executeQuery(query);
						while (s.next()) {
							String pid = s.getString("pid");
							//String desc = s.getString("tdescription");
							frame1.getContentPane().add(scroll);
							frame1.setVisible(true);
							frame1.setSize(400, 300);
							model.addRow(new Object[] { pid });
							

						}
										}
					catch(Exception E)
					{
						System.out.println(""+E);
					}
				
			}
		});
		
		JButton btnQuery_1 = new JButton("Query 9");
		btnQuery_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final JFrame frame1 = new JFrame("Query 9 Result");
				frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame1.getContentPane().setLayout(new BorderLayout());
				
				String[] columnNames = { "pid", "fname", "lname", "age", "gender", "email" };
				final DefaultTableModel model = new DefaultTableModel();
				model.setColumnIdentifiers(columnNames);
				JTable table = new JTable();
				table.setModel(model);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				table.setFillsViewportHeight(true);
				final JScrollPane scroll = new JScrollPane(table);
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				
				try {

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "sampledb", "pass1234");

					
					String query = "select * from sampledb.patient p1 where p1.page= (SELECT MAX(p.page)FROM sampledb.patient p WHERE p.page < (SELECT MAX(p1.page) FROM sampledb.patient p1 ));"; 
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
						
						frame1.getContentPane().add(scroll);
						frame1.setVisible(true);
						frame1.setSize(400, 300);
						model.addRow(new Object[] { patientid, patient_fname, patient_lname, patient_age,
								patient_gender, patient_email });
						
				}
				}
				catch(Exception E)
				{
					System.out.println(""+E);
				}	
			}
		});
		
		JButton btnQuery_2 = new JButton("Query 10");
		btnQuery_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				final JFrame frame1 = new JFrame("Query 10 Result");
				frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame1.getContentPane().setLayout(new BorderLayout());
				
				String[] columnNames = { "pid", "fname", "lname", "age", "gender", "email" ,"condition"};
				final DefaultTableModel model = new DefaultTableModel();
				model.setColumnIdentifiers(columnNames);
				JTable table = new JTable();
				table.setModel(model);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				table.setFillsViewportHeight(true);
				final JScrollPane scroll = new JScrollPane(table);
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				
				try {

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "sampledb", "pass1234");

					
					String query = "select p.pid from sampledb.patient p where p.pgender='1' and p.pid in (SELECT  m.spid FROM sampledb.msg m where m.rpid in (select hs.pid from sampledb.hascondition hs where hs.pid in (select pid from sampledb.conditions c ,sampledb.conditionstime ct where c.cdescription='diabetes' and ct.startd=ct.endd)));"; 
					System.out.println("" + query);
					java.sql.Statement stm = con.createStatement();
					ResultSet s = stm.executeQuery(query);
					while (s.next()) {
						String pid = s.getString("pid");
						int ppid = Integer.parseInt(pid);
						//String desc = s.getString("cdescription");
						query = "(select * from sampledb.patient where pid= "+ppid+" );"; 
						System.out.println("" + query);
						 stm = con.createStatement();
						ResultSet rs = stm.executeQuery(query);
						while(rs.next()){
							String patientid = rs.getString("pid");
							String patient_fname = rs.getString("pfirstname");
							String patient_lname = rs.getString("plastname");
							String patient_age = rs.getString("page");
							String patient_gender = rs.getString("pgender");
							if (patient_gender.equals("0")) {
								patient_gender = "Male";
							}
							if (patient_gender.equals("1")) {
								patient_gender = "Female";
							}
							if (patient_gender.equals("2")) {
								patient_gender = "Unknown";
							}
							String patient_email = rs.getString("pemail");
							// JOptionPane.showMessageDialog(null,"Found
							// \n"+patientid+"\n FIrst name : "+ patient_fname+"\n
							// Last name: "+ patient_lname+"\n Patient age: "+
							// patient_age+"\n Patient Gender: "+
							// patient_gender+"\nPatient email: "+ patient_email);
							
							frame1.getContentPane().add(scroll);
							frame1.setVisible(true);
							frame1.setSize(400, 300);
							model.addRow(new Object[] { patientid, patient_fname, patient_lname, patient_age,
									patient_gender, patient_email });
						}

					}
				}
				catch(Exception E)
				{
					System.out.println(""+E);
				}
			
				
			}
		});
		
		JButton btnQuery_3 = new JButton("Query 7");
		btnQuery_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame query7 = new query7();
				query7.setVisible(true);
				//part1.this.setVisible(false);
				
			}
		});
		
		JButton btnQuery_4 = new JButton("Query 5");
		btnQuery_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFrame frame1 = new JFrame("Query 5 Result");
				frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame1.getContentPane().setLayout(new BorderLayout());
				
				String[] columnNames = { "pid", "fname", "lname", "age", "gender", "email"};
				final DefaultTableModel model = new DefaultTableModel();
				model.setColumnIdentifiers(columnNames);
				JTable table = new JTable();
				table.setModel(model);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				table.setFillsViewportHeight(true);
				final JScrollPane scroll = new JScrollPane(table);
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				
				try {

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "sampledb", "pass1234");

					
					String query = "select distinct hs.pid from sampledb.hassymptom hs, sampledb.symptom s1 ,sampledb.symptomtime st where hs.sid in  (select  s1.sid from sampledb.symptom s2 where s2.description ='cough'  and st.startd=st.endd and hs.pid=st.pid ) and s1.sid in (select s3.sid from sampledb.symptom s3 where s3.description ='fatigue'  and st.startd=st.endd and hs.pid=st.pid); ;"; 
					System.out.println("" + query);
					java.sql.Statement stm = con.createStatement();
					ResultSet s = stm.executeQuery(query);
					while (s.next()) {
						String pid = s.getString("pid");
						int ppid = Integer.parseInt(pid);
						//String desc = s.getString("description");
						query = "(select * from sampledb.patient where pid= "+ppid+" );"; 
						System.out.println("" + query);
						 stm = con.createStatement();
						ResultSet rs = stm.executeQuery(query);
						while(rs.next()){
							String patientid = rs.getString("pid");
							String patient_fname = rs.getString("pfirstname");
							String patient_lname = rs.getString("plastname");
							String patient_age = rs.getString("page");
							String patient_gender = rs.getString("pgender");
							if (patient_gender.equals("0")) {
								patient_gender = "Male";
							}
							if (patient_gender.equals("1")) {
								patient_gender = "Female";
							}
							if (patient_gender.equals("2")) {
								patient_gender = "Unknown";
							}
							String patient_email = rs.getString("pemail");
							// JOptionPane.showMessageDialog(null,"Found
							// \n"+patientid+"\n FIrst name : "+ patient_fname+"\n
							// Last name: "+ patient_lname+"\n Patient age: "+
							// patient_age+"\n Patient Gender: "+
							// patient_gender+"\nPatient email: "+ patient_email);
							
							frame1.getContentPane().add(scroll);
							frame1.setVisible(true);
							frame1.setSize(400, 300);
							model.addRow(new Object[] { patientid, patient_fname, patient_lname, patient_age,
									patient_gender, patient_email});
						}

					}
				}
				catch(Exception E)
				{
					System.out.println(""+E);
				}
			
				
			
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
						.addComponent(btnSearch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
						.addComponent(btnNewButton_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
						.addComponent(btnNewButton_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
						.addComponent(btnNewButton_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
						.addComponent(btnNewButton_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
						.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
						.addComponent(btnAddDetails, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnQuery_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnPatients, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnQuery_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnQuery, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
								.addComponent(btnQuery_1, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
								.addComponent(btnQuery_2, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
					.addGap(77))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnQuery_4))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddDetails)
						.addComponent(btnPatients))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_2)
						.addComponent(btnQuery_3))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_3)
						.addComponent(btnQuery))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_4))
						.addComponent(btnQuery_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSearch)
						.addComponent(btnQuery_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_5)
					.addGap(38))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
