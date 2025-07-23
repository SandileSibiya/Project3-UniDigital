package za.ac.cputunidigitalcard.domain;

import jakarta.persistence.*;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    private Long studentId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Column(name = "faculty", nullable = false)
    private String faculty;

    @Column(name = "course", nullable = false)
    private String course;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Lob // Use for large object like images
    @Column(name = "photo", columnDefinition = "LONGBLOB")
    private byte[] photo;

    // Constructors, getters, and setters
    protected Student() {}

    public Student(Builder builder) {
        this.studentId = builder.studentId;
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.surname = builder.surname;
        this.contactNumber = builder.contactNumber;
        this.faculty = builder.faculty;
        this.course = builder.course;
        this.gender = builder.gender;
        this.address = builder.address;
        this.email = builder.email;
        this.password = builder.password;
        this.photo = builder.photo;
    }

    // Getters
    public Long getStudentId() {
        return studentId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public String getSurname() {
        return surname;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public String getFaculty() {
        return faculty;
    }
    public String getCourse() {
        return course;
    }
    public String getGender() {
        return gender;
    }
    public String getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public byte[] getPhoto() { return photo; }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", surname='" + surname + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", faculty='" + faculty + '\'' +
                ", course='" + course + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'';

    }

    // Setters for Lob picture column
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }


    // Builder class
    public static class Builder {
        private Long studentId;
        private String firstName;
        private String middleName;
        private String surname;
        private String contactNumber;
        private String faculty;
        private String course;
        private String gender;
        private String address;
        private String email;
        private String password;
        private byte[] photo;
        private byte[] qrCode;

        public Builder setStudentId(Long studentId) {
            this.studentId = studentId;
            return this;
        }
        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }
        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }
        public Builder setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }
        public Builder setFaculty(String faculty) {
            this.faculty = faculty;
            return this;
        }
        public Builder setCourse(String course) {
            this.course = course;
            return this;
        }
        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }
        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }
        public Builder setPhoto(byte[] photo) {
            this.photo = photo;
            return this;
        }

        // Copy constructor
        public Builder copy(Student student) {
            this.studentId = student.studentId;
            this.firstName = student.firstName;
            this.middleName = student.middleName;
            this.surname = student.surname;
            this.contactNumber = student.contactNumber;
            this.faculty = student.faculty;
            this.course = student.course;
            this.gender = student.gender;
            this.address = student.address;
            this.email = student.email;
            this.password = student.password;
            this.photo = student.photo;
            return this;
        }
        public Student build() {
            return new Student(this);
        }
    }
}

