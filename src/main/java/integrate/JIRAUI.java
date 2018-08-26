package integrate;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.List;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import integrate.Integration;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.LinkedList;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JIRAUI {

	public JFrame frmAttachmentValidator;
	public JTextField txtHttplocalhost;
	public JTextField txtVivek;
	public JPasswordField passwordField;
	public JTextField textField_2;
	public JTextField textField_3;
	public static String SLink;
	public String SUsername;
	public char[] SPassword;
	public String Jql;
	public String Message;
	public LinkedList<String> projects= new LinkedList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIRAUI window = new JIRAUI();
					window.frmAttachmentValidator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JIRAUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmAttachmentValidator = new JFrame();
		frmAttachmentValidator.setTitle("Attachment Validator");
		frmAttachmentValidator.setBounds(100, 100, 877, 452);
		frmAttachmentValidator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAttachmentValidator.getContentPane().setLayout(null);
		
		JLabel Link = new JLabel("Link");
		Link.setBounds(12, 13, 117, 30);
		frmAttachmentValidator.getContentPane().add(Link);
		
		txtHttplocalhost = new JTextField();
		txtHttplocalhost.setText("http://localhost:8089");
		txtHttplocalhost.setBounds(67, 17, 242, 22);
		frmAttachmentValidator.getContentPane().add(txtHttplocalhost);
		txtHttplocalhost.setColumns(10);
		
		JLabel Username = new JLabel("Username");
		Username.setBounds(321, 20, 76, 16);
		frmAttachmentValidator.getContentPane().add(Username);
		
		txtVivek = new JTextField();
		txtVivek.setText("vivek");
		txtVivek.setBounds(384, 17, 116, 22);
		frmAttachmentValidator.getContentPane().add(txtVivek);
		txtVivek.setColumns(10);
		
		JLabel Password = new JLabel("Password");
		Password.setBounds(512, 20, 69, 16);
		frmAttachmentValidator.getContentPane().add(Password);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(593, 17, 122, 22);
		frmAttachmentValidator.getContentPane().add(passwordField);
		
		final TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(67, 190, 760, 180);
		frmAttachmentValidator.getContentPane().add(textArea);
		
		JButton btnLogin = new JButton("Login");
		
		btnLogin.setBounds(730, 16, 97, 25);
		frmAttachmentValidator.getContentPane().add(btnLogin);
		
		final JComboBox<Object> comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setBounds(119, 70, 190, 22);
		frmAttachmentValidator.getContentPane().add(comboBox);
		
		JLabel lblSelectProject = new JLabel("Select Project");
		lblSelectProject.setBounds(12, 73, 131, 16);
		frmAttachmentValidator.getContentPane().add(lblSelectProject);
		
		JLabel lblJql = new JLabel("Jql");
		lblJql.setBounds(321, 73, 56, 16);
		frmAttachmentValidator.getContentPane().add(lblJql);
		
		final JButton btnSearch = new JButton("Search");
		btnSearch.setEnabled(false);
		btnSearch.setBounds(618, 121, 97, 25);
		frmAttachmentValidator.getContentPane().add(btnSearch);
		
		JLabel lblPath = new JLabel("Path");
		lblPath.setBounds(12, 125, 56, 16);
		frmAttachmentValidator.getContentPane().add(lblPath);
		
		textField_2 = new JTextField();
		textField_2.setBounds(346, 70, 481, 22);
		frmAttachmentValidator.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(67, 122, 433, 22);
		frmAttachmentValidator.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		final JRadioButton rdbtnTestPlan = new JRadioButton("Test Plan");
		rdbtnTestPlan.setSelected(true);
		rdbtnTestPlan.setBounds(724, 121, 127, 25);
		frmAttachmentValidator.getContentPane().add(rdbtnTestPlan);
		
		final JRadioButton rdbtnTestExecution = new JRadioButton("Test Execution");
		rdbtnTestExecution.setBounds(724, 151, 127, 25);
		frmAttachmentValidator.getContentPane().add(rdbtnTestExecution);
		
		final JButton btnNewButton = new JButton("Browse");
		btnNewButton.setBounds(509, 121, 97, 25);
		frmAttachmentValidator.getContentPane().add(btnNewButton);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SLink=txtHttplocalhost.getText();
				SUsername=txtVivek.getText();
				SPassword=passwordField.getPassword();
				Integration intg=new Integration();
				java.util.List<String> Projects=intg.login(SUsername,SPassword);
				
				if (Integration.StatusCode==200) {
				Collections.sort(Projects);
				textArea.append("Logged in Successfully\n");
				
				if(!Projects.isEmpty()) {
				for(Object a:Projects) {
					comboBox.addItem(a);
					}
					comboBox.setEnabled(true);
					textArea.append("Projects Displayed successfully"+"\n");
					passwordField.setText("");
				}
				
				else {
					textArea.append("No Projects Fetched\n");
				}
				}
				
				else {
					textArea.append("Login Failed\n");
				}
				
				
			}
		});
		
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				textField_2.addKeyListener(new KeyAdapter() {
			        public void keyReleased(KeyEvent e) { //watch for key strokes
			            if(textField_2.getText().length() != 0 )
			                btnSearch.setEnabled(true);
			            else
			            {
			                btnSearch.setEnabled(false);
			            }
			        }
			 });
				btnSearch.setEnabled(true);
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				try {
//					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				JFileChooser choose = new JFileChooser();
				choose.setDialogTitle("Select a Folder");
				choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				choose.showDialog(null, "Select");
				textField_3.setText(choose.getSelectedFile().getAbsolutePath());
				
			}
		});
		
		rdbtnTestPlan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnTestExecution.setSelected(false);
			}
		});
		
		rdbtnTestExecution.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnTestPlan.setSelected(false);
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integration intg = new Integration();
				java.util.List<String> Issueid;
				String Status;
				Issueid=intg.GetIssueKeys("project="+comboBox.getSelectedItem().toString()+" and "+textField_2.getText());
				if (Integration.StatusCode==200) {
				Collections.sort(Issueid);
				if(!Issueid.isEmpty()) {
				for(Object b:Issueid) {
					Status=intg.CheckAttachmentsInPlan(b);
					if (Status.equalsIgnoreCase("Yes")) {
						textArea.append("Issue "+b.toString()+" has attachment"+"\n");
					}
					else {
						textArea.append("Issue "+b.toString()+" has no attachment"+"\n");
					}
				}
			}
				else {
					textArea.append("No Issues Fetched\n");
				}
				}
				else {
					textArea.append("Please check the JQL\n");
				}
			}
		});
		
	}
}
