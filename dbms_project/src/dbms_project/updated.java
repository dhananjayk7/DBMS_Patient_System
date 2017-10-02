package dbms_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Scrollbar;

import javax.swing.DropMode;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;

public class updated extends JFrame {

	private JPanel contentPane;
	private JTable updatetable;
	private JTextField fname;
	private JTextField pid;
	private JTextField lname;
	private JTextField age;
	private JTextField email;
	private JTextField txtdesc;
	 String tpid;
	 String tsid;
	 String tdesc;
	 String tcdesc;
	 String ttdesc;
	 String tcid;
	 String ttid;
	 private JTextField cdesc;
	 private JTextField txttdesc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updated frame = new updated();
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
	public updated() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 831, 671);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		final JComboBox details = new JComboBox();
		details.insertItemAt("Patient", 0);
		details.insertItemAt("Symptom", 1);
		details.insertItemAt("Condition", 2);
		details.insertItemAt("Treatment", 3);
		JLabel lblSelectWhatTo = new JLabel("Select what to update: ");
		
		
		
		
		JLabel lblSelectUpdatedDescription = new JLabel("Enter Updated Description:");
		
		txtdesc = new JTextField();
		txtdesc.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Symptom Update");
		final JPanel spanel = new JPanel();
		spanel.setVisible(false);
		final JPanel cpanel = new JPanel();
		cpanel.setVisible(false);
		final JPanel tpanel = new JPanel();
		tpanel.setVisible(false);
		final JTable updatetable = new JTable();
		updatetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		final JComboBox gender = new JComboBox();
		gender.insertItemAt("male", 0);
		gender.insertItemAt("female", 1);
		gender.insertItemAt("unknown", 2);
		updatetable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		updatetable.addMouseListener(new MouseAdapter() {
			@Override
		
			
			public void mouseClicked(MouseEvent arg0) {
			int	t1 = details.getSelectedIndex();
				if(t1==0){
				int row = updatetable.getSelectedRow();
				String tbl_id = (updatetable.getModel().getValueAt(row, 0).toString());
				String tbl_fname = (updatetable.getModel().getValueAt(row, 1).toString());
				String tbl_lname = (updatetable.getModel().getValueAt(row, 2).toString());
				String tbl_age = (updatetable.getModel().getValueAt(row, 3).toString());
				String tbl_gender = (updatetable.getModel().getValueAt(row, 4).toString());
				String tbl_email = (updatetable.getModel().getValueAt(row, 5).toString());
				pid.setText(tbl_id);
				fname.setText(tbl_fname);
				lname.setText(tbl_lname);
				age.setText(tbl_age);
				email.setText(tbl_email);
				
				}
				if(t1==1){
					int row = updatetable.getSelectedRow();
					 tpid= (updatetable.getModel().getValueAt(row, 0).toString());
					 tsid = (updatetable.getModel().getValueAt(row, 1).toString());
					tdesc = (updatetable.getModel().getValueAt(row, 2).toString());
					txtdesc.setText(tdesc);
					}
				if(t1==2){
					int row = updatetable.getSelectedRow();
					 tpid= (updatetable.getModel().getValueAt(row, 0).toString());
					 tcid = (updatetable.getModel().getValueAt(row, 1).toString());
					tcdesc = (updatetable.getModel().getValueAt(row, 2).toString());
					cdesc.setText(tcdesc);
					}
				if(t1==3){
					int row = updatetable.getSelectedRow();
					 tpid= (updatetable.getModel().getValueAt(row, 0).toString());
					 ttid = (updatetable.getModel().getValueAt(row, 1).toString());
					ttdesc = (updatetable.getModel().getValueAt(row, 2).toString());
					txttdesc.setText(ttdesc);
					}
			}
		});
		String[] patientcol = { "pid", "fname", "lname", "age", "gender", "email" };
		String[] symptomcol = { "pid","sid", "Description", "Appear Date", "End Date" };
		String[] conditioncol = {  "pid","cid", "Description", "Appear Date", "End Date" };
		String[] treatmnentcol = { "pid", "tid", "Description", "Appear Date", "End Date" };
		final DefaultTableModel patientmodel = new DefaultTableModel();
		final DefaultTableModel symptommodel = new DefaultTableModel();
		final DefaultTableModel conditionmodel = new DefaultTableModel();
		final DefaultTableModel treatmentmodel = new DefaultTableModel();
		patientmodel.setColumnIdentifiers(patientcol);
		symptommodel.setColumnIdentifiers(symptomcol);
		conditionmodel.setColumnIdentifiers(conditioncol);
		treatmentmodel.setColumnIdentifiers(treatmnentcol);



		JButton btnGo = new JButton("Go");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 int t = details.getSelectedIndex();
				System.out.println("" + t);
				if (t == 0) {
					try {
						updatetable.setModel(patientmodel);
						while (patientmodel.getRowCount() != 0)
							patientmodel.removeRow(0);
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
								"pass1234");
						String query = "select * from sampledb.patient;";
						java.sql.Statement stm = con.createStatement();
						stm.executeQuery(query);
						ResultSet rs = stm.executeQuery(query);
						while (rs.next()) {
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

							patientmodel.addRow(new Object[] { patientid, patient_fname, patient_lname, patient_age,
									patient_gender, patient_email });

						}

					} catch (Exception E) {
						System.out.println("" + E);
					}

				}
				if(t==1){
					try {
						updatetable.setModel(symptommodel);
						int sid1,pid;
						String desc;
						String sdate;
						String edate;
						spanel.setVisible(true);
						while (symptommodel.getRowCount() != 0)
							symptommodel.removeRow(0);
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
								"pass1234");
						String query = "select distinct hs.pid , hs.sid, s.description, st.startd ,st.endd from sampledb.hassymptom hs, sampledb.symptom s ,sampledb.symptomtime st where hs.sid= s.sid  and s.sid in(select st.sid from sampledb.symptomtime);";
						java.sql.Statement stm = con.createStatement();
						stm.executeQuery(query);
						ResultSet rs = stm.executeQuery(query);
						while (rs.next()) {
								pid=rs.getInt("pid");
								sid1=rs.getInt("sid");
								desc=rs.getString("description");
								sdate=rs.getString("startd");
								edate=rs.getString("endd");
								symptommodel.addRow(new Object[] {pid, sid1, desc, sdate, edate });
						}

					} catch (Exception E) {
						System.out.println("" + E);
					}
				}
					if(t==2){
						try {
							updatetable.setModel(conditionmodel);
							int cid1,pid;
							String desc;
							String sdate;
							String edate;
							spanel.setVisible(false);
							cpanel.setVisible(true);
							while (conditionmodel.getRowCount() != 0)
								conditionmodel.removeRow(0);
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
									"pass1234");
							String query = "select distinct hc.pid , hc.cid, c.cdescription, ct.startd ,ct.endd from sampledb.hascondition hc, sampledb.conditions c ,sampledb.conditionstime ct where hc.cid= c.cid  and c.cid in(select ct.cid from sampledb.conditionstime);;";
							java.sql.Statement stm = con.createStatement();
							stm.executeQuery(query);
							ResultSet rs = stm.executeQuery(query);
							while (rs.next()) {
									pid=rs.getInt("pid");
									cid1=rs.getInt("cid");
									desc=rs.getString("cdescription");
									sdate=rs.getString("startd");
									edate=rs.getString("endd");
									conditionmodel.addRow(new Object[] {pid, cid1, desc, sdate, edate });
							}

						} catch (Exception E) {
							System.out.println("" + E);
						}

						
					
				}
				
					if(t==3){
						try {
							updatetable.setModel(treatmentmodel);
							int tid1,pid;
							String desc;
							String sdate;
							String edate;
							spanel.setVisible(false);
							cpanel.setVisible(false);
							tpanel.setVisible(true);
							while (treatmentmodel.getRowCount() != 0)
								treatmentmodel.removeRow(0);
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
									"pass1234");
							String query = "select distinct tt.pid , tt.tid, t.tdescription, tt1.startd ,tt1.endd from sampledb.taketreatment tt, sampledb.treatment t ,sampledb.treatmenttime tt1 where tt.tid= t.tid  and t.tid in(select tt1.tid from sampledb.treatmenttime);;";
							java.sql.Statement stm = con.createStatement();
							stm.executeQuery(query);
							ResultSet rs = stm.executeQuery(query);
							while (rs.next()) {
									pid=rs.getInt("pid");
									tid1=rs.getInt("tid");
									desc=rs.getString("tdescription");
									sdate=rs.getString("startd");
									edate=rs.getString("endd");
									treatmentmodel.addRow(new Object[] {pid, tid1, desc, sdate, edate });
							}

						} catch (Exception E) {
							System.out.println("" + E);
						}

						
					
				}	
					
			}
		});

		JButton btnUpdateData = new JButton("Update Data");
		btnUpdateData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
							"pass1234");
					String query = "update sampledb.patient SET pfirstname='" + fname.getText() + "', plastname= '"
							+ lname.getText() + "', page= " + Integer.parseInt(age.getText()) + " ,pgender= "
							+ gender.getSelectedIndex() + " , pemail= '" + email.getText() + "'  where pid = "
							+ Integer.parseInt(pid.getText()) + "";
					java.sql.Statement stm = con.createStatement();
					stm.executeUpdate(query);

					while (patientmodel.getRowCount() != 0)
						patientmodel.removeRow(0);
					query = "select * from sampledb.patient;";
					stm = con.createStatement();
					stm.executeQuery(query);
					ResultSet rs = stm.executeQuery(query);
					while (rs.next()) {
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

						patientmodel.addRow(new Object[] { patientid, patient_fname, patient_lname, patient_age,
								patient_gender, patient_email });

					}
					JOptionPane.showMessageDialog(null, "UPDATED SUCCESSFULLY");

				} catch (Exception E) {
					System.out.println("" + E);
				}
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
							"pass1234");
					
					
					String query = " Delete from sampledb.patient where pid = " + Integer.parseInt(pid.getText()) + "";
					java.sql.Statement stm = con.createStatement();
					stm.executeUpdate(query);

					while (patientmodel.getRowCount() != 0)
						patientmodel.removeRow(0);
					query = "select * from sampledb.patient;";
					stm = con.createStatement();
					stm.executeQuery(query);
					ResultSet rs = stm.executeQuery(query);
					while (rs.next()) {
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

						patientmodel.addRow(new Object[] { patientid, patient_fname, patient_lname, patient_age,
								patient_gender, patient_email });

					}
					JOptionPane.showMessageDialog(null, "Deleted SUCCESSFULLY");
					pid.setText("");
					fname.setText("");
					lname.setText("");
					age.setText("");
					email.setText("");
				} catch (Exception E) {
					System.out.println("" + E);

				}

			}
		});
		JButton btnUpdate = new JButton("Update Symptom");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
						"pass1234");
				String query = "update sampledb.symptom SET description='" +txtdesc.getText()+"' where sid = "
						+ Integer.parseInt(tsid) + "";
				java.sql.Statement stm = con.createStatement();
				stm.executeUpdate(query);

				while (symptommodel.getRowCount() != 0)
					symptommodel.removeRow(0);
				 query = "select * from sampledb.hassymptom hs, sampledb.symptom s, sampledb.symptomtime st where hs.sid = s.sid; ;";
				 stm = con.createStatement();
				stm.executeQuery(query);
				ResultSet rs = stm.executeQuery(query);
				while (rs.next()) {
						int pid=rs.getInt("pid");
					int 	sid1=rs.getInt("sid");
					String 	desc=rs.getString("description");
					String 	sdate=rs.getString("startd");
						String edate=rs.getString("endd");
						symptommodel.addRow(new Object[] {pid, sid1, desc, sdate, edate });
				}
				JOptionPane.showMessageDialog(null, "UPDATED SUCCESSFULLY");

			} catch (Exception E) {
				System.out.println("" + E);
			}
	
				
				
				
				
			}
		});
		
		JButton btnDelete_1 = new JButton("Delete Symptom");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int pid = Integer.parseInt(tpid);
					int sid=Integer.parseInt(tsid);
					
					int sid1,pid1;
					String desc;
					String sdate;
					String edate;
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
							"pass1234");
					
					DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
					Calendar calobj = Calendar.getInstance();
					String endtime=df.format(calobj.getTime());
					
					System.out.println(df.format(calobj.getTime()));
					
					String query = " update sampledb.symptomtime SET endd= '"+endtime+"' where sid="+sid+" ;";
					java.sql.Statement stm = con.createStatement();
					System.out.println(""+query);
					stm.executeUpdate(query);
					
					query = " Delete from sampledb.hassymptom where sid="+sid+" ;";
					stm = con.createStatement();
					System.out.println(""+query);
					stm.executeUpdate(query);
					
					 query = " Delete from sampledb.symptom where sid= "+sid+";";
					 stm = con.createStatement();
					 System.out.println(""+query);
					// stm.executeUpdate(query);
					
					 while (symptommodel.getRowCount() != 0)
							symptommodel.removeRow(0);
					 query = "select distinct hs.pid , hs.sid, s.description, st.startd ,st.endd from sampledb.hassymptom hs, sampledb.symptom s ,sampledb.symptomtime st where hs.sid= s.sid  and s.sid in(select st.sid from sampledb.symptomtime); ";
					 stm = con.createStatement();
					 System.out.println(""+query);
					 stm.executeQuery(query);
					 
					ResultSet rs = stm.executeQuery(query);
					while (rs.next()) {
							pid1=rs.getInt("pid");
							sid1=rs.getInt("sid");
							desc=rs.getString("description");
							sdate=rs.getString("startd");
							edate=rs.getString("endd");
							symptommodel.addRow(new Object[] {pid1, sid1, desc, sdate, edate });
				
				}
				}
				catch(Exception E)
				{
					System.out.println(""+E);
				}
				
				
			}
		});
		
		JButton btnUpdateCondition = new JButton("Update Condition");
		btnUpdateCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
							"pass1234");
					String query = "update sampledb.conditions SET cdescription='" +cdesc.getText()+"' where cid = "
							+ Integer.parseInt(tcid) + "";
					java.sql.Statement stm = con.createStatement();
					stm.executeUpdate(query);

					while (conditionmodel.getRowCount() != 0)
						conditionmodel.removeRow(0);
					 query = "select distinct hc.pid , hc.cid, c.cdescription, ct.startd ,ct.endd from sampledb.hascondition hc, sampledb.conditions c ,sampledb.conditionstime ct where hc.cid= c.cid  and c.cid in(select ct.cid from sampledb.conditionstime); ;";
					 stm = con.createStatement();
					stm.executeQuery(query);
					ResultSet rs = stm.executeQuery(query);
					while (rs.next()) {
						int pid=rs.getInt("pid");
						int cid1=rs.getInt("cid");
						String desc=rs.getString("cdescription");
						String 	sdate=rs.getString("startd");
						String edate=rs.getString("endd");
						conditionmodel.addRow(new Object[] {pid, cid1, desc, sdate, edate });
				}
					JOptionPane.showMessageDialog(null, "UPDATED SUCCESSFULLY");

				} catch (Exception E) {
					System.out.println("" + E);
				}				
			}
		});
		
		JButton btnDeleteCondition = new JButton("Delete Condition");
		btnDeleteCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int pid = Integer.parseInt(tpid);
					int cid=Integer.parseInt(tcid);
					
					int cid1,pid1;
					String desc;
					String sdate;
					String edate;
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
							"pass1234");
					
					DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
					Calendar calobj = Calendar.getInstance();
					String endtime=df.format(calobj.getTime());
					
					System.out.println(df.format(calobj.getTime()));
					
					String query = " update sampledb.conditionstime SET endd= '"+endtime+"' where cid="+cid+" ;";
					java.sql.Statement stm = con.createStatement();
					System.out.println(""+query);
					stm.executeUpdate(query);
					
					query = " Delete from sampledb.hascondition where cid="+cid+" ;";
					stm = con.createStatement();
					System.out.println(""+query);
					stm.executeUpdate(query);
					
					 query = " Delete from sampledb.conditions where cid="+cid+";";
					 stm = con.createStatement();
					 System.out.println(""+query);
				//	 stm.executeUpdate(query);
					
					 while (conditionmodel.getRowCount() != 0)
						 conditionmodel.removeRow(0);
					 query = "select distinct hc.pid , hc.cid, c.cdescription, ct.startd ,ct.endd from sampledb.hascondition hc, sampledb.conditions c ,sampledb.conditionstime ct where hc.cid= c.cid  and c.cid in(select ct.cid from sampledb.conditionstime); ";
					 stm = con.createStatement();
					 System.out.println(""+query);
					 stm.executeQuery(query);
					 
					ResultSet rs = stm.executeQuery(query);
					while (rs.next()) {
							pid1=rs.getInt("pid");
							cid1=rs.getInt("cid");
							desc=rs.getString("cdescription");
							sdate=rs.getString("startd");
							edate=rs.getString("endd");
							conditionmodel.addRow(new Object[] {pid1, cid1, desc, sdate, edate });
				
				}
				}
				catch(Exception E)
				{
					System.out.println(""+E);
				}
				
			}
		});

		JButton btnUpdateTreatment = new JButton("Update Treatment");
		btnUpdateTreatment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
							"pass1234");
					String query = "update sampledb.treatment SET tdescription='" +txttdesc.getText()+"' where tid = "
							+ Integer.parseInt(ttid) + "";
					java.sql.Statement stm = con.createStatement();
					stm.executeUpdate(query);

					while (treatmentmodel.getRowCount() != 0)
						treatmentmodel.removeRow(0);
					
					 query = "select distinct tt.pid , tt.tid, t.tdescription, tt1.startd ,tt1.endd from sampledb.taketreatment tt, sampledb.treatment t ,sampledb.treatmenttime tt1 where tt.tid= t.tid  and t.tid in(select tt1.tid from sampledb.treatmenttime);;";
					 stm = con.createStatement();
					stm.executeQuery(query);
					ResultSet rs = stm.executeQuery(query);
					while (rs.next()) {
							int pid=rs.getInt("pid");
							int  tid1=rs.getInt("tid");
							String desc=rs.getString("tdescription");
							String sdate=rs.getString("startd");
							String edate=rs.getString("endd");
							treatmentmodel.addRow(new Object[] {pid, tid1, desc, sdate, edate });
					}
					JOptionPane.showMessageDialog(null, "UPDATED SUCCESSFULLY");

				} catch (Exception E) {
					System.out.println("" + E);
				}				
				
				
			}
		});
		
		JButton btnDelete_2 = new JButton("Delete");
		btnDelete_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb","pass1234");
					int pid = Integer.parseInt(tpid);
					int tid=Integer.parseInt(ttid);
					
					int tid1,pid1;
					String desc;
					String sdate;
					String edate;
					
					System.out.println("error ");
					DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
					Calendar calobj = Calendar.getInstance();
					String endtime=df.format(calobj.getTime());
					
					System.out.println(df.format(calobj.getTime()));
					
					String query = " update sampledb.treatmenttime SET endd= '"+endtime+"' where tid="+tid+" ;";
					java.sql.Statement stm = con.createStatement();
					System.out.println(""+query);
					stm.executeUpdate(query);
					
					query = " Delete from sampledb.taketreatment where tid="+tid+" ;";
					stm = con.createStatement();
					System.out.println(""+query);
					stm.executeUpdate(query);
					
					 query = " Delete from sampledb.conditions where tid="+tid+";";
					 stm = con.createStatement();
					 System.out.println(""+query);
				//	 stm.executeUpdate(query);
					
					 while (treatmentmodel.getRowCount() != 0)
						 treatmentmodel.removeRow(0);
					 	query = "select distinct tt.pid , tt.tid, t.tdescription, tt1.startd ,tt1.endd from sampledb.taketreatment tt, sampledb.treatment t ,sampledb.treatmenttime tt1 where tt.tid= t.tid  and t.tid in(select tt1.tid from sampledb.treatmenttime);;";
						 stm = con.createStatement();
						stm.executeQuery(query);
						ResultSet rs = stm.executeQuery(query);
						while (rs.next()) {
								pid=rs.getInt("pid");
								tid1=rs.getInt("tid");
								desc=rs.getString("tdescription");
								sdate=rs.getString("startd");
								edate=rs.getString("endd");
								treatmentmodel.addRow(new Object[] {pid, tid1, desc, sdate, edate });
						}
				}
				catch(Exception E)
				{
					System.out.println("this is "+E);
				}
				
			}
		});
		
		
		fname = new JTextField();
		fname.setColumns(40);

		JLabel lblUpdateName = new JLabel("Update First Name: ");

		JLabel lblPatientId = new JLabel("Patient ID :");

		pid = new JTextField();
		pid.setEditable(false);
		pid.setColumns(10);

		JLabel lblEnterTheInformation = new JLabel("Enter the information to update:  ");
		lblEnterTheInformation.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblUpdatedLastName = new JLabel("Updated Last Name:");

		lname = new JTextField();

		lname.setColumns(30);

		JLabel lblUpdatedAge = new JLabel("Updated age:");

		JLabel lblUpdatedAge_1 = new JLabel("Updated Email:");

		age = new JTextField();
		age.setColumns(40);

		email = new JTextField();
		email.setColumns(40);

		JLabel lblUpdatedGender = new JLabel("Updated Gender");
		
		
		
		
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(updatetable, GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblSelectWhatTo)
							.addPreferredGap(ComponentPlacement.RELATED, 448, Short.MAX_VALUE)
							.addComponent(details, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnGo)
							.addGap(49))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEnterTheInformation)
								.addComponent(lblUpdatedAge)
								.addComponent(lblUpdatedGender)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPatientId)
										.addComponent(lblUpdateName)
										.addComponent(lblUpdatedLastName)
										.addComponent(lblUpdatedAge_1))
									.addGap(47)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(email, 0, 0, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addComponent(lname, 0, 0, Short.MAX_VALUE)
											.addComponent(fname, 0, 0, Short.MAX_VALUE)
											.addComponent(pid)
											.addComponent(age, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
											.addComponent(gender, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnUpdateData)
									.addGap(34)
									.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addGap(42)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(spanel, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(tpanel, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
								.addComponent(cpanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(details, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSelectWhatTo)
						.addComponent(btnGo))
					.addGap(12)
					.addComponent(updatetable, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblEnterTheInformation)
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(pid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPatientId))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUpdateName)
								.addComponent(fname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(tpanel, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addComponent(spanel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
							.addGap(6)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblUpdatedLastName)
									.addGap(26)
									.addComponent(lblUpdatedAge)
									.addGap(32)
									.addComponent(lblUpdatedGender))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(age, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(gender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(8)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUpdatedAge_1)
								.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(34)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnUpdateData)
								.addComponent(btnDelete)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cpanel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblTreatmentUpdate = new JLabel("Treatment Update");
		
		JLabel label_1 = new JLabel("Enter Updated Description:");
		
		txttdesc = new JTextField();
		txttdesc.setColumns(10);
		
		
		GroupLayout gl_tpanel = new GroupLayout(tpanel);
		gl_tpanel.setHorizontalGroup(
			gl_tpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tpanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_tpanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tpanel.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txttdesc, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
						.addGroup(gl_tpanel.createSequentialGroup()
							.addComponent(btnUpdateTreatment)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete_2, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTreatmentUpdate, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_tpanel.setVerticalGroup(
			gl_tpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tpanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTreatmentUpdate)
					.addGap(18)
					.addGroup(gl_tpanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(txttdesc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_tpanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUpdateTreatment)
						.addComponent(btnDelete_2))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		tpanel.setLayout(gl_tpanel);
		
		JLabel lblConditionUpdate = new JLabel("Condition Update");
		
		JLabel label = new JLabel("Enter Updated Description:");
		
		cdesc = new JTextField();
		cdesc.setColumns(10);
		
		
		
		
		GroupLayout gl_cpanel = new GroupLayout(cpanel);
		gl_cpanel.setHorizontalGroup(
			gl_cpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cpanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_cpanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_cpanel.createSequentialGroup()
							.addComponent(btnUpdateCondition)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnDeleteCondition))
						.addGroup(gl_cpanel.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(cdesc, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(59, Short.MAX_VALUE))
				.addGroup(gl_cpanel.createSequentialGroup()
					.addComponent(lblConditionUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(183))
		);
		gl_cpanel.setVerticalGroup(
			gl_cpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cpanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblConditionUpdate)
					.addGap(18)
					.addGroup(gl_cpanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(cdesc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(gl_cpanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUpdateCondition)
						.addComponent(btnDeleteCondition))
					.addGap(21))
		);
		cpanel.setLayout(gl_cpanel);
		
		
		GroupLayout gl_spanel = new GroupLayout(spanel);
		gl_spanel.setHorizontalGroup(
			gl_spanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_spanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_spanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_spanel.createSequentialGroup()
							.addComponent(btnUpdate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete_1))
						.addComponent(lblNewLabel)
						.addGroup(gl_spanel.createSequentialGroup()
							.addComponent(lblSelectUpdatedDescription)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtdesc, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(62, Short.MAX_VALUE))
		);
		gl_spanel.setVerticalGroup(
			gl_spanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_spanel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_spanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectUpdatedDescription)
						.addComponent(txtdesc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
					.addGroup(gl_spanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUpdate)
						.addComponent(btnDelete_1))
					.addContainerGap())
		);
		spanel.setLayout(gl_spanel);
		contentPane.setLayout(gl_contentPane);

	}
}
