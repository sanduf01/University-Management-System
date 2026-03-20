package main;

import java.util.List;

import dao.IStudentDao;
import dao.StudentDao;
import model.Student;

public class UniversityManagementApp {

	public static void main(String[] args) {
		
		IStudentDao studentDao = new StudentDao();

        // Register Students
        Student s1 = new Student(
                523607111,
                "s23010111",
                "N.P.Perera",
                "nimal@ousl.lk",
                "Colombo",
                "Computer Science"
        );

        Student s2 = new Student(
                545412098,
                "s23541398",
                "K.K.Silva",
                "kamal@ousl.lk",
                "Kandy",
                "Information Technology"
        );

        studentDao.registerStudent(s1);
        studentDao.registerStudent(s2);
        System.out.println("Students registered successfully\n");

        // Display all students
        System.out.println("All Registered Students:");
        List<Student> students = studentDao.getAllStudents();
        students.forEach(System.out::println);
        System.out.println();

        // Get student by registration number
        System.out.println("Fetch student by Registration No:");
        Student student = studentDao.grtStudentById(523607111);
        System.out.println(student + "\n");

        // Update student center
        System.out.println("Updating student center...");
        if (student != null) {
            student.setCenter("Jaffna");
            studentDao.updateStudent(student);
            System.out.println("Student updated\n");
        } else {
            System.out.println("Student not found\n");
        }

        // Count students by center
        int colomboCount = studentDao.countStudentByCenter("Colombo");
        System.out.println("Students in Colombo Center: " + colomboCount + "\n");

        // Count students by program
        int csCount = studentDao.countStudentByProgram("Computer Science");
        System.out.println("Computer Science Students: " + csCount + "\n");

        // Get students by center
        System.out.println("Students in Kandy Center:");
        List<Student> kandyStudents = studentDao.getStudentsByCenter("Kandy");
        kandyStudents.forEach(System.out::println);
        System.out.println();

        // Delete student by student number
        System.out.println("Deleting student by Student Number...");
        studentDao.deleteStudentBySNo("s23541398");
        System.out.println("Student deleted\n");

        // Final student list
        System.out.println("Final Student List:");
        studentDao.getAllStudents().forEach(System.out::println);
    }

}