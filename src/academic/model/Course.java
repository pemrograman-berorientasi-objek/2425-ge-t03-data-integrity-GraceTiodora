package academic.model;

/**
 * 12S23019 - Clarasia Simanjuntak 
 * 12S23043 - Grace Tiodora
 */
public class Course {
private String courseCode;
private String courseName; 
private int sks;
private String Nilai;

public Course(String courseCode, String courseName, int sks, String Nilai) {
this.courseCode = courseCode;
this.courseName = courseName;
this.sks = sks;
this.Nilai = Nilai;
}

public String getCourseCode() {
return courseCode;
}
 public String getCourseName() {
return courseName;
 }
    public int getSks() {
return sks;
}
public String getNilai (){
    return Nilai; 
}


@Override 
public String toString() { 
    return courseCode+ "|" + courseName+ "|" + sks+ "|"  + Nilai ;
}
}