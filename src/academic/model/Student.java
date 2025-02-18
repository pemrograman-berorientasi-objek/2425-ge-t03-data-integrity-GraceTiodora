package academic.model;

/**
 * @author 12S23019_ clarasia l. simanjuntak
 * @author 12S23043_grace Tiodora
 */
public class Student{
 private String studentId;
private String studentName;
 private String academicYear;
 private String semester;

 public Student(String _studentId,String _studentName , String _academicYear, String _semester) {
 this.studentId = _studentId;
 this.studentName = _studentName;
 this.academicYear = _academicYear;
 this.semester = _semester;
 
}
public String getStudentId() {
    return studentId;
}
public String getStudentName() {
    return studentName;
}
public String getacademicYear() {
    return academicYear;
}
public String getsemester() {
    return semester;
}
@Override
public String toString(){
    return  studentId + "|" + studentName +"|"+ academicYear + "|" + semester;
}

}