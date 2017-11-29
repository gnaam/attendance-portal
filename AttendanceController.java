import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.*;
public class AttendanceController {
	
	private AttendanceView theView;
	private AttendanceModel theModel;
	public AttendanceController(AttendanceView theView, AttendanceModel theModel) {
		
		this.theView = theView;
		this.theModel = theModel;
		this.theView.addCalculateListener(new CalculateListener());
		this.theView.addStudentListener(new StudentListner());
	}
	
	String username;
	String password;
	JCheckBox boxes[];
	JLabel labels[];
	String classname;
	ArrayList<String> presentStudents = new ArrayList<String>();
	
	
	
	
	class StudentListner implements ItemListener{
		public void itemStateChanged(ItemEvent ie) {
			System.out.println("Is it listening to me?");
			int size =boxes.length;
			System.out.println("Boxes sizs is : " +  size);
			presentStudents.clear();
			for(int i=0;i<size;i++)
			{
				if(boxes[i].isSelected()) {
					presentStudents.add(labels[i].getText());
					System.out.println(labels[i].getText());
				}
			}
		}
	}
	
	class CalculateListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
				System.out.println("Yes it is entering here!");
				String op = e.getActionCommand();
				System.out.println(op);
				username = theView.getUsername();
				password = theView.getPassword();
				if(op.equals("Login"))
				{	
					if(theModel.evaluate(username,password))
					if(true)
					{
						ArrayList<String> classes = theModel.getClassesOf(username);
						theView.createhomePanel(classes);
						theView.showhomePanel();
					}	
					else
					{
						JOptionPane.showMessageDialog(null, "Username/Passwod Incorrect");
					}
				}
				else if(op.equals("logout"))
				{
					theView.initializeLoginPanel();
					theView.showLoginPanel();
					JPanel homePanel = theView.gethomePanel();
					homePanel.removeAll();
					JTextField usernameE = theView.getUsernameE();
					JPasswordField passwordE = theView.getPasswordE();
					usernameE.setText(null);
					passwordE.setText(null);
					
				}
				else if(op.equals("back")) {
					JPanel studentListPanel = theView.getStudentListPanel();
					studentListPanel.removeAll();
					theView.showhomePanel();
					
				}
				else if(op.equals("submit")) {
					int reply = JOptionPane.showConfirmDialog(null,"Do you want to Submit?","Confirm",JOptionPane.YES_NO_OPTION);
			        if (reply == JOptionPane.YES_OPTION) {
			        	
			        	System.out.println(presentStudents.size());
			        	theModel.writeTodaysAttendance(presentStudents,username,classname);
			        	JOptionPane.showMessageDialog(null, "Attendance Has been Submitted Successfully!");
			        	JPanel studentListPanel = theView.getStudentListPanel();
						studentListPanel.removeAll();
						theView.showhomePanel();
			        }
				}
				else {
					theModel.readDataOf(op);
					classname = op;
					ArrayList<String> students = theModel.getIdNumbers();
					int size = students.size();
					labels   = new JLabel[size];
					boxes = new JCheckBox[size];
					boolean check = theModel.isSubmitted(username,classname);
					theView.createStudentListPanel(students,op,boxes,labels,check);
					theView.showStudentListPanel();
					
				}							
		}
		
	}

}
