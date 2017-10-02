package dbms_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class query7 extends JFrame {

	private JPanel contentPane;
	int pid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					query7 frame = new query7();
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
	public query7() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSelectPatientId = new JLabel("Select patient id:");
		
		final JComboBox pcombo = new JComboBox();
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "sampledb",
					"pass1234");
		
			int cnt=0;
			String query = "select * from sampledb.patient";
			java.sql.Statement stm = con.createStatement();
			ResultSet s = stm.executeQuery(query);
		 
			while (s.next()) {
				pid=s.getInt("pid");
				pcombo.insertItemAt(""+pid, cnt);
				cnt++;
			}
			
		}
		catch(Exception E)
		{
			
		}
		
		
		JButton btnNewButton = new JButton("Query 7");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFrame frame1 = new JFrame("Query 7 Result");
				frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame1.getContentPane().setLayout(new BorderLayout());
				
				String[] columnNames = { "spid", "count" };
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

				String pid1= pcombo.getSelectedItem().toString();
					String query = "(SELECT spid, COUNT(spid) as cnt  FROM  sampledb.msg where rpid="+pid1+" GROUP BY spid ORDER BY COUNT(spid) DESC ) ;"; 
					System.out.println("" + query);
					java.sql.Statement stm = con.createStatement();
					ResultSet s = stm.executeQuery(query);
					while (s.next()) {
						String senderid = s.getString("spid");
					
						String count1 = s.getString("cnt");
						frame1.getContentPane().add(scroll);
						frame1.setVisible(true);
						frame1.setSize(400, 300);
						model.addRow(new Object[] { senderid, count1 });
						
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
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSelectPatientId)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(pcombo, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton))
					.addContainerGap(270, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectPatientId)
						.addComponent(pcombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addComponent(btnNewButton)
					.addContainerGap(132, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
