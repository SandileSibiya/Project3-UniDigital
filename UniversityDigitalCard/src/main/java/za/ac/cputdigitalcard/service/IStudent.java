package za.ac.cputdigitalcard.service;

import za.ac.cputdigitalcard.domain.Student;

import java.util.List;


public interface IStudent extends IService <Student, Long> {

    List<Student> getAllStudent();
}
