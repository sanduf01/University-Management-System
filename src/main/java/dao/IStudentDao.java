package dao;

import java.util.List;
import model.Student;

public interface IStudentDao {
	
	void registerStudent (Student student);
	void updateStudent (Student student);
	Student grtStudentById (int regNo);
	List<Student> getAllStudents ();
	int deleteStudent (int regNo);

	int countStudentByCenter (String center);
	int countStudentByProgram (String program);
	
	List<Student> getStudentsByCenter (String center);
	
	void deleteStudentBySNo (String sNo);

}