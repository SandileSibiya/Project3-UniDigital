package za.ac.cputdigitalcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cputdigitalcard.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByStudentId(Long studentId);
//  Student findByStudentFirstName(String studentFirstName);
    Student findByEmail(String email);
}