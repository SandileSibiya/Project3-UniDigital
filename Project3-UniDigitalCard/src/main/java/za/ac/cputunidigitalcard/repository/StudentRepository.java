package za.ac.cputunidigitalcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cputunidigitalcard.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    //    Student findByStudentId(String studentId);
//    Student findByStudentFirstName(String studentFirstName);
    Student findByEmail(String email);
}
