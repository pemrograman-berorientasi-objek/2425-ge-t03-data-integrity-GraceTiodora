package academic.driver;

import academic.model.Course;
import academic.model.Student;
import academic.model.Enrollment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Driver2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        ArrayList<String> invalidMessages = new ArrayList<>(); // Menyimpan pesan kesalahan

        while (true) {
            String input = sc.nextLine();
            if (input.equals("---")) break; // Berhenti saat input adalah "---"

            String[] data = input.split("#");

            if (data.length > 1) {
                String command = data[0];

                switch (command) {
                    case "course-add":
                        if (data.length == 5) { // Pastikan ada 5 bagian
                            String courseCode = data[1];
                            String courseName = data[2];
                            String sksStr = data[3];
                            String grade = data[4];

                            // Validasi apakah SKS adalah angka
                            if (isNumeric(sksStr)) {
                                int sks = Integer.parseInt(sksStr);
                                courses.add(new Course(courseCode, courseName, sks, grade));
                            } else {
                                // Simpan pesan kesalahan jika format SKS salah
                                invalidMessages.add("invalid course|" + courseCode);
                            }
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

                            // Cek apakah studentId valid
                            boolean validStudent = false;
                            for (Student s : students) {
                                if (s.getStudentId().equals(studentId)) {
                                    validStudent = true;
                                    break;
                                }
                            }
                            // Cek apakah courseCode valid
                            boolean validCourse = false;
                            for (Course c : courses) {
                                if (c.getCourseCode().equals(courseCode)) {
                                    validCourse = true;
                                    break;
                                }
                            }

                            if (validStudent && validCourse) {
                                enrollments.add(new Enrollment(courseCode, studentId, academicYear, semester, "None"));
                            } else {
                                if (!validStudent && !invalidMessages.contains("invalid student|" + studentId)) {
                                    invalidMessages.add("invalid student|" + studentId);
                                }
                                if (!validCourse && !invalidMessages.contains("invalid course|" + courseCode)) {
                                    invalidMessages.add("invalid course|" + courseCode);
                                }
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

        // Urutkan daftar course berdasarkan kode
        Collections.sort(courses, Comparator.comparing(Course::getCourseCode));
        // Urutkan daftar student berdasarkan studentId
       
        Collections.sort(enrollments, Comparator.comparing(Enrollment::getCourseCode));

        // Cetak semua pesan kesalahan terlebih dahulu
        for (String invalidMessage : invalidMessages) {
            System.out.println(invalidMessage);
        }

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

    // Fungsi untuk memeriksa apakah string bisa diparse menjadi angka
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
