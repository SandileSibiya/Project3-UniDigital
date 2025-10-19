package za.ac.cputdigitalcard.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cputdigitalcard.domain.Student;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    private Student student = new Student.Builder()
            .setStudentId(Long.valueOf("200975623"))
            .setFirstName("Steven")
            .setMiddleName("Enough")
            .setSurname("Sotobe")
            .setContactNumber("0607260270")
            .setFaculty("ICT : AppDev")
            .setCourse("Business Management")
            .setGender("Male")
            .setEmail("200975623@mycput.ac.za")
            .setPassword("Sib040114")
            .build();

    @Test
    @Order(1)
    void create() {
        Student savedStudent = studentService.create(student);
        assertNotNull(savedStudent);
        System.out.println("Saved Student: " + savedStudent);
    }

    @Test
    @Order(2)
    void read() {
        Student readStudent = studentService.read(student.getStudentId());
        assertNotNull(readStudent);
        System.out.println("Read Student: " + readStudent);
    }

    @Test
    @Order(3)
    void update() {
        Student updatedStudent = new Student.Builder()
                .copy(student)
                .setSurname("Mthunzi")
                .setFaculty("Entrepreneurship and Business")
                .build();
        Student result = studentService.update(updatedStudent);
        assertNotNull(result);
        System.out.println("Updated Student: " + result);
    }

    @Test
    @Order(4)
    void delete() {
        Student deletedStudent = studentService.delete(student.getStudentId());
        assertNotNull(deletedStudent);
        assertEquals(student.getStudentId(), deletedStudent.getStudentId());
        System.out.println("Deleted Student: " + deletedStudent);
    }

    @Test
    @Order(5)
    void getAllStudent() {
        for (Student student : studentService.getAllStudent()) {
            assertNotNull(student);
            System.out.println("All Student: " + student);
        }
    }
}