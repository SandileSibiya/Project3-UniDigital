package za.ac.cputdigitalcard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cputdigitalcard.domain.Student;
import za.ac.cputdigitalcard.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService implements IStudent {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository Repo) {
        this.studentRepository = Repo;
    }
    //Overriding methods from IStudent interface for CRUD operations

    @Override
    // Method to check if StudentId exist in database and register if not
    public Student create(Student student) {
        if (studentRepository.findByStudentId(student.getStudentId()) != null) {
            throw new RuntimeException("Student already registered");
        }
        // Save the student if not already registered
        return this.studentRepository.save(student);
    }

    @Override
    // Method to check if StudentId exist in database and read if it does.
    public Student read(Long aLong) {
        if (studentRepository.findByStudentId(aLong) == null) {
            throw new RuntimeException("Student not found");
        }
        System.out.println("Student found: ");
        return this.studentRepository.findByStudentId(aLong);
    }

    @Override
    // Method to check if StudentId exist in database and update if it does
    public Student update(Student student) {
        if (studentRepository.findByStudentId(student.getStudentId()) == null) {
            throw new RuntimeException("Student not found for update");
        }
        return studentRepository.save(student);
    }

    @Override
    // Method to check if StudentId exist in database and delete if it does
    public Student delete(Long aLong) {
        Student student = studentRepository.findByStudentId(aLong);
        if (student == null) {
            throw new RuntimeException("Student not found for deletion");
        }
        studentRepository.delete(student);
        return student;
    }
    @Override
    // Method to get all students from the database
    public List<Student> getAllStudent() {
        return this.studentRepository.findAll();
    }

    //Method to check and validates user login credentials
    public Student validateUserLogIn(String email, String password) {
        Student student = studentRepository.findByEmail(email);
        if (student == null || !student.getPassword().equals(password)) {
            throw new RuntimeException("Invalid email or password");
        }
        // Check student status if is Active or Inactive
        if (!"Active".equalsIgnoreCase(student.getStatus())) {
            throw new RuntimeException("Your student account is Inactive, Please contact admin");
        }

        return student;
    }

    // Method to find user by ID for register card
    public Student findById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

}
