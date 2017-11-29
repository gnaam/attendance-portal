import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AttendanceView  {
    JPanel panels; //a panel that uses CardLayout
    JPanel header = new JPanel();
    JPanel homePanel = new JPanel();
    JPanel loginPanel = new JPanel();
    JPanel studentListPanel= new JPanel();
    JScrollPane scrollPane = new JScrollPane(studentListPanel);
    JPanel loginBorder = new JPanel();
    JTextField username = new JTextField(10);
    JPasswordField password = new JPasswordField(10);
    JLabel welcom = new JLabel("Welcom Faculty name");
    JButton back = new JButton("Back!");
    JButton login = new JButton("Login");
    JLabel note = new JLabel("Attendance portal");
    ActionListener ScreenButton;
    ItemListener listeningItem;
    AttendanceView()
    {
        JFrame frame = new JFrame("Attendance Portal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        
        initializeLoginPanel();
        
        panels = new JPanel(new CardLayout());
        panels.add(loginPanel, "loginPanel");
        panels.add(homePanel,"homePanel");
        panels.add(scrollPane,"studentListPanel");
        Font font1 = new Font("SansSerif", Font.BOLD, 30);
        note.setFont(font1);
        note.setBorder(new EmptyBorder(20, 50,15, 50));
        header.add(note);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 1, Color.BLACK));
        note.setBounds(50, 100, 100, 50);
        // pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(header, BorderLayout.PAGE_START);
        pane.add(panels, BorderLayout.CENTER);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      //  frame.getContentPane().setLayout(null);
        frame.setVisible(true);
       
    }
    
    public void initializeLoginPanel(){
    	loginPanel.setLayout(null);
        JLabel uname = new JLabel("Username:");
        JLabel pswd = new JLabel("Password");

        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("./data/logo.png").getImage().getScaledInstance(200, 220, Image.SCALE_DEFAULT));
        label.setIcon(imageIcon);
        label.setBounds(350, 130, 750, 400);
        loginPanel.add(label);
        uname.setBounds(594, 256, 100, 50);
        username.setBounds(691, 267, 200, 30);
        pswd.setBounds(594, 337, 100, 50);
        password.setBounds(691, 348, 200, 30);
        login.setBounds(630, 450, 200, 30);
        loginBorder.setBounds(300, 130, 750, 400);
        loginBorder.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
        loginBorder.setOpaque(true);
        loginBorder.setBackground(new Color(30,59,214,12));
        loginPanel.add(uname);
        loginPanel.add(username);
        loginPanel.add(pswd);
        loginPanel.add(password);
        loginPanel.add(login);
       // loginPanel.setBorder(new EmptyBorder(50, 50,0, 50));
        loginPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
       // loginPanel.setPreferredSize(new Dimension(500,500));
        loginPanel.add(loginBorder);
        
    }
    public String getUsername() {
    	return username.getText();
    }
    public String getPassword() {
    	return new String(password.getPassword());
    }
    public void addCalculateListener(ActionListener ScreenButton){
    	this.ScreenButton = ScreenButton;
		login.addActionListener(ScreenButton);
	}
    public void addStudentListener(ItemListener listeningItem) {
    	this.listeningItem = listeningItem;
    }
	public JPanel gethomePanel() {
		return homePanel;
	}
	public void showhomePanel() {
		CardLayout cl = (CardLayout)(panels.getLayout());
    	cl.show(panels,"homePanel");
	}

	public void showLoginPanel() {
		// TODO Auto-generated method stub
		CardLayout cl = (CardLayout)(panels.getLayout());
    	cl.show(panels,"loginPanel");
		
	}

	public JPanel getloginPanel() {
		// TODO Auto-generated method stub
		return loginPanel;
	}

	public JTextField getUsernameE() {
		// TODO Auto-generated method stub
		return username;
	}

	public JPasswordField getPasswordE() {
		// TODO Auto-generated method stub
		return password;
	}

	public void showStudentListPanel() {
		CardLayout cl = (CardLayout)(panels.getLayout());
    	cl.show(panels,"studentListPanel");
	}

	public JPanel getStudentListPanel() {
		// TODO Auto-generated method stub
		return studentListPanel;
	}
	
	public void createhomePanel(ArrayList<String> classes) {
		homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
		JPanel topPanel = new JPanel();
		JLabel welcom = new JLabel("Welcome " + username.getText());
		homePanel.add(welcom);
		homePanel.add(Box.createVerticalStrut(20));
		JPanel middlePanel = new JPanel();
		int size = classes.size();
		JButton[] buttons = new JButton[size];
		for(int i=0;i<size;i++)
		{	
			System.out.println(classes.get(i));
			buttons[i]= new JButton(classes.get(i));
			middlePanel.add(buttons[i]);
			buttons[i].addActionListener(ScreenButton);
			buttons[i].setBorder(new EmptyBorder(50, 23, 50, 50));
			
		}
		JButton logout = new JButton("logout");
		homePanel.add(middlePanel);
		//homePanel.add(Box.createVerticalStrut(20));
		homePanel.add(logout,BorderLayout.WEST);
		logout.addActionListener(ScreenButton);		
	}
	public void createStudentListPanel(ArrayList<String> students,String current_class,JCheckBox boxes[],
	JLabel labels[],boolean check) {
		JLabel fname= new JLabel("Student List Of Class "+current_class);
		studentListPanel.setLayout(new BoxLayout(studentListPanel, BoxLayout.Y_AXIS));
		studentListPanel.add(fname);
		if(!check)
		{	
			int size = students.size();
			JLabel rollnums[]= new JLabel[size];
			JPanel boxPanel = new JPanel();
			JPanel rollPanel = new JPanel();
			JPanel studentPanel = new JPanel();
			JPanel backsubmit = new JPanel();
			JPanel middlePanel = new JPanel();
			for(int i=0;i<size;i++)
			{	
				//System.out.println(students[i]);
				rollnums[i] = new JLabel(String.valueOf(i+1));
				labels[i]= new JLabel(students.get(i));
				rollPanel.add(rollnums[i]);
				rollnums[i].setBorder(new EmptyBorder(20,20,20,20));
				studentPanel.add(labels[i]);
				labels[i].setBorder(new EmptyBorder(20,20,20,20));
				boxes[i] = new JCheckBox();
				boxes[i].setBorder(new EmptyBorder(20,20,20,20));
				boxes[i].addItemListener(listeningItem);
				boxPanel.add(boxes[i]);
			}
			studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));
			boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
			rollPanel.setLayout(new BoxLayout(rollPanel, BoxLayout.Y_AXIS));
			backsubmit.setLayout(new BoxLayout(backsubmit, BoxLayout.X_AXIS));
			
			//FlowLayout layout = (FlowLayout)studentListPanel.getLayout();
			//layout.setHgap(0);
		
			middlePanel.add(rollPanel);
			middlePanel.add(boxPanel);
			middlePanel.add(studentPanel);
			studentListPanel.add(middlePanel);
			JButton back = new JButton("back");
			rollPanel.add(back);
			back.addActionListener(ScreenButton);
			JButton submit = new JButton("submit");
			JLabel some=new JLabel(" ");
			boxPanel.add(some);
			studentPanel.add(submit);
			submit.addActionListener(ScreenButton);
			studentListPanel.add(backsubmit);
		}
		else
		{
			JLabel message = new JLabel("Attendance for this class is Already Submitted!");
			studentListPanel.add(message);
			JButton back = new JButton("back");
			back.addActionListener(ScreenButton);
			studentListPanel.add(back);
			
		}
	}


}
