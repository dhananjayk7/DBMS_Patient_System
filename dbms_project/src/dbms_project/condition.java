package dbms_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.util.JDatePickerUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.Button;
import javax.swing.JComboBox;
import java.awt.Font;

public class condition extends JFrame {

	private JPanel contentPane;
	private JTextField desc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					symptom frame = new symptom();
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
	public condition() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 428, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblEnterYourPatient = new JLabel("Enter Your Patient Id :");
		
		JLabel lblDescribeTheSymptom = new JLabel("Describe the condition :");
		
		desc = new JTextField(50); 
		
		
		

		class DateLabelFormatter extends AbstractFormatter {

		    private String datePattern = "yyyy-MM-dd";
		    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		    @Override
		    public Object stringToValue(String text) throws ParseException {
		        return dateFormatter.parseObject(text);
		    }

		    @Override
		    public String valueToString(Object value) throws ParseException {
		        if (value != null) {
		            Calendar cal = (Calendar) value;
		            return dateFormatter.format(cal.getTime());
		        }

		        return "";
		    }

		}
		
		final UtilDateModel model = new UtilDateModel();
		final UtilDateModel model1 = new UtilDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p);
		// Don't know about the formatter, but there it is...
		final JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		final JComboBox pidcombo = new JComboBox();
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
					"pass1234");
			int pid;
			int cnt=0;
			String query = "select * from sampledb.patient";
			java.sql.Statement stm = con.createStatement();
			ResultSet s = stm.executeQuery(query);
		 
			while (s.next()) {
				pid=s.getInt("pid");
				pidcombo.insertItemAt(""+pid, cnt);
				cnt++;
			}
			
		}
		catch(Exception E)
		{}
		
		
		
		JLabel lblEnterStartDate = new JLabel("Enter Start Date:");
		
		JButton btnSubmitDetails = new JButton("Submit Details");
		btnSubmitDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
							"pass1234");
			
					int cid1 = 0;
					//int pid=Integer.parseInt(txtpid.getText());
									
					String query = "select * from sampledb.conditions";
					java.sql.Statement stm = con.createStatement();
					ResultSet s = stm.executeQuery(query);
					String pid1= pidcombo.getSelectedItem().toString();
					System.out.println(""+pid1);
					while (s.next()) {
						cid1 = s.getInt("cid");
						System.out.println(""+cid1);
					}
					cid1++;
					int yyyy= datePicker.getModel().getYear();
					int mm=datePicker.getModel().getMonth()+1;
					int dd = datePicker.getModel().getDay();
					String start=""+yyyy+"-"+mm+"-"+dd;
					
				/*	int yyyy1= datePicker1.getModel().getYear();
					int mm1=datePicker1.getModel().getMonth()+1;
					int dd1 = datePicker1.getModel().getDay();
					//String end=""+yyyy1+"-"+mm1+"-"+dd1;*/
					
					DateFormat df = new SimpleDateFormat("HH:mm:ss");
					Calendar calobj = Calendar.getInstance();
					String starttime=df.format(calobj.getTime());
					
					System.out.println(df.format(calobj.getTime()));
					
					
					query = "insert into sampledb.conditions values("+cid1+",'"+desc.getText()+"');";
					stm = con.createStatement();
					stm.executeUpdate(query);
					System.out.println(""+query);
					
					query = "insert into sampledb.conditionstime values("+pid1+","+cid1+",'"+start+" "+starttime+"','"+start+" "+starttime+"');";
					stm = con.createStatement();
					stm.executeUpdate(query);
					System.out.println(""+query);
					
					query = "insert into sampledb.hascondition values("+pid1+","+cid1+");";
				    stm = con.createStatement();
					stm.executeUpdate(query);
				    System.out.println(""+query);
					
				}
				catch(Exception E)
				{
					System.out.println(""+E);
				}
			}
		});
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pidcombo.setSelectedIndex(-1);
				desc.setText("");
				model.setSelected(false);
				model1.setSelected(false);
			}
		});
	
		
		JButton btnPreviousMenu = new JButton("Previous Menu");
		btnPreviousMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFrame part1= new part1();
				condition.this.setVisible(false);
				part1.setVisible(true);
			}
		});
		
		JPanel panel = new JPanel();
		
		panel.add(datePicker);
		
		JLabel lblAddCondition = new JLabel("Add Condition ");
		lblAddCondition.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSubmitDetails)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnReset)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPreviousMenu))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblEnterStartDate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblDescribeTheSymptom)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(desc, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblEnterYourPatient)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(pidcombo, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblAddCondition))
					.addContainerGap(93, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblAddCondition)
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterYourPatient)
						.addComponent(pidcombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescribeTheSymptom)
						.addComponent(desc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addComponent(lblEnterStartDate))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSubmitDetails)
						.addComponent(btnReset)
						.addComponent(btnPreviousMenu))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
