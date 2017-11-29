public class Attendance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AttendanceView theView = new AttendanceView();
		AttendanceModel theModel = new AttendanceModel();
		AttendanceController theCon = new AttendanceController(theView, theModel);
	}

}
