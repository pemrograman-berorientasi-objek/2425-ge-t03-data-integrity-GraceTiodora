package academic.driver;

import academic.model.Course;
import academic.model.Student;
import academic.model.Enrollment;

import java.util.*;

public class Driver2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        Set<String> invalidStudents = new HashSet<>(); // Untuk menghindari duplikasi invalid student
        Set<String> invalidCourses = new HashSet<>();
        List<String> invalidMessages = new ArrayList<>(); // Menyimpan pesan kesalahan

        while (true) {
            String input = sc.nextLine();
            if (input.equals("---")) break; // Berhenti saat input adalah "---"

            String[] data = input.split("#");

            if (data.length > 1) {
                String command = data[0];

                switch (command) {
                    case "course-add":
                        if (data.length == 5) {
                            String courseCode = data[1];
                            String courseName = data[2];
                            int sks = Integer.parseInt(data[3]);
                            String grade = data[4];

                            courses.add(new Course(courseCode, courseName, sks, grade));
                        }
                        break;

                    case "student-add":
                        if (data.length == 5) {
                            String studentId = data[1];
                            String studentName = data[2];
                            String academicYear = data[3];
                            String semester = data[4];

                            students.add(new Student(studentId, studentName, academicYear, semester));
                        }
                        break;

                    case "enrollment-add":
                        if (data.length == 5) {
                            String courseCode = data[1];
                            String studentId = data[2];
                            String academicYear = data[3];
                            String semester = data[4];

                            // Validasi apakah mahasiswa dan mata kuliah ada
                            boolean studentExists = isStudentExist(students, studentId);
                            boolean courseExists = isCourseExist(courses, courseCode);

                            // Cek student dan course, jika invalid
                            if (!studentExists) {
                                invalidStudents.add(studentId);
                            }
                            if (!courseExists) {
                                invalidCourses.add(courseCode);
                            }

                            // Jika student dan course valid, baru tambahkan enrollment
                            if (studentExists && courseExists) {
                                enrollments.add(new Enrollment(courseCode, studentId, academicYear, semester, "None"));
                            }
                        }
                        break;

                    default:
                        System.out.println("Perintah tidak dikenali: " + command);
                        break;
                }
            }
        }
        sc.close();

        // Tambahkan error ke list agar output sesuai format
        for (String studentId : invalidStudents) {
            invalidMessages.add("invalid student|" + studentId);
        }
        for (String courseCode : invalidCourses) {
            invalidMessages.add("invalid course|" + courseCode);
        }

        // Cetak semua pesan kesalahan terlebih dahulu
        for (String msg : invalidMessages) {
            System.out.println(msg);
        }

        // Urutkan daftar course berdasarkan kode
        Collections.sort(courses, Comparator.comparing(Course::getCourseCode));

        // Urutkan enrollment berdasarkan kode course
        Collections.sort(enrollments, Comparator.comparing(Enrollment::getCourseCode));

        // Cetak daftar course
        for (Course c : courses) {
            System.out.println(c);
        }

        // Cetak daftar student
        for (Student s : students) {
            System.out.println(s);
        }

        // Cetak daftar enrollment
        for (Enrollment e : enrollments) {
            System.out.println(e);
        }
    }

    // Fungsi untuk memeriksa apakah mahasiswa sudah ada dalam daftar
    public static boolean isStudentExist(ArrayList<Student> students, String studentId) {
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                return true;
            }
        }
        return false;
    }

    // Fungsi untuk memeriksa apakah mata kuliah sudah ada dalam daftar
    public static boolean isCourseExist(ArrayList<Course> courses, String courseCode) {
        for (Course c : courses) {
            if (c.getCourseCode().equals(courseCode)) {
                return true;
            }
        }
        return false;
    }
}
