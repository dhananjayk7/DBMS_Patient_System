package dbms_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class msg extends JFrame {

	private JPanel contentPane;
	private JTextField txtsubject;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					msg frame = new msg();
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
	public msg() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 472, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSelectSender = new JLabel("Select sender : ");
		
		final JComboBox sendercombo = new JComboBox();
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
					"pass1234");
			int spid;
			int cnt=0;
			String query = "select * from sampledb.patient";
			java.sql.Statement stm = con.createStatement();
			ResultSet s = stm.executeQuery(query);
		 
			while (s.next()) {
				spid=s.getInt("pid");
				sendercombo.insertItemAt(""+spid, cnt);
				cnt++;
			}
			
		}
		catch(Exception E)
		{}
		
		JLabel lblSelectReceiver = new JLabel("Select receiver:");
		
		final JComboBox receivercombo = new JComboBox();
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
					"pass1234");
			int rpid;
			int cnt=0;
			String query = "select * from sampledb.patient";
			java.sql.Statement stm = con.createStatement();
			ResultSet s = stm.executeQuery(query);
		 
			while (s.next()) {
				rpid=s.getInt("pid");
				receivercombo.insertItemAt(""+rpid, cnt);
				cnt++;
			}
			
		}
		catch(Exception E)
		{}
		txtsubject = new JTextField();
		txtsubject.setColumns(10);
		
		JLabel lblEnterSubject = new JLabel("Enter Subject:");
		
		JLabel lblWriteMessege = new JLabel("Write messege:");
		
		final JTextArea txtmsg = new JTextArea();
		
		
		
		JButton btnSend = new JButton("Send ");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
							"pass1234");
					int msgid=0;
					String query = "select * from sampledb.msg";
					java.sql.Statement stm = con.createStatement();
					ResultSet s = stm.executeQuery(query);
					
					while (s.next()) {
						msgid = s.getInt("msgid");
						System.out.println(""+msgid);
					}
					msgid++;
					String spid= sendercombo.getSelectedItem().toString();
					String rpid= receivercombo.getSelectedItem().toString();
					if(spid.equals(rpid))
					{
						JOptionPane.showMessageDialog(null, "Both sender and receiver cannot be same.");
						
					}
					else{
					System.out.println(""+spid);
					 query = "insert into sampledb.msg values("+spid+","+rpid+","+msgid+",'"+txtsubject.getText()+"','"+txtmsg.getText()+"');";
					 stm = con.createStatement();
					 stm.executeUpdate(query);
					 System.out.println(""+query);	
					 JOptionPane.showMessageDialog(null, "Messege sent.");
					 	sendercombo.setSelectedIndex(-1);
						receivercombo.setSelectedIndex(-1);
						txtsubject.setText("");
						txtmsg.setText("");	
					}
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
				sendercombo.setSelectedIndex(-1);
				receivercombo.setSelectedIndex(-1);
				txtsubject.setText("");
				txtmsg.setText("");
				
			}
		});
		
		JButton btnPreviousMenu = new JButton("Previous menu");
		btnPreviousMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame part1 = new part1();
				part1.setVisible(true);
				msg.this.setVisible(false);
			
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblSelectSender)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(sendercombo, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSelectReceiver)
										.addComponent(lblEnterSubject))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtsubject, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
										.addComponent(receivercombo, 0, 80, Short.MAX_VALUE))))
							.addGap(255))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblWriteMessege)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtmsg, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSend)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnReset)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnPreviousMenu)
							.addContainerGap(197, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectSender)
						.addComponent(sendercombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectReceiver)
						.addComponent(receivercombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtsubject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEnterSubject))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWriteMessege)
						.addComponent(txtmsg, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSend)
						.addComponent(btnReset)
						.addComponent(btnPreviousMenu))
					.addGap(29))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
