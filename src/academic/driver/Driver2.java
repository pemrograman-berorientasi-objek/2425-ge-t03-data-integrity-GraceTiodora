package academic.driver;
import java.util.Scanner;
import academic.model.Student;
/**
 * 12S23019 - Clarasia Simanjuntak
 * 12S23043 - Grace Tiodora
 */
public class Driver2 {

    public static void main(String[] _args) {
    Scanner sc = new Scanner(System.in);
    Student[] nmStudent = new Student [100];
   
    int count = 0;

    while (true){
        String input = sc.nextLine();
        if (input.equals("---")) break;

        String [] data = input.split("#");
        if (data.length == 4){
            String sourseCode = data[0];
            String studentId = data[1];
            String academicYear = data[2];
            String semester = data[3];
            nmStudent[count++] = new Student(sourseCode, studentId, academicYear, semester);
            
        }
    }
    sc.close();
    for (int i = 0; i < count; i++){
        System.out.println(nmStudent[i]);
    }

}
}