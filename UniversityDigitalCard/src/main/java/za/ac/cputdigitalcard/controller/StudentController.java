package za.ac.cputdigitalcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cputdigitalcard.domain.Student;
import za.ac.cputdigitalcard.service.StudentService;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cputdigitalcard.com")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/LinkAccount")
    public ResponseEntity<?> register(@RequestBody Student student) {
        try {
            Student registeredStudent = studentService.create(student);
            return ResponseEntity.ok(Map.of("studentId", registeredStudent));
        } catch (RuntimeException e) {
            System.out.println("Student number already registered");
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    //End point to validate user login credentials.
    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        try {
            Student student = studentService.validateUserLogIn(email, password);

            // Check user status
            if (!"Active".equalsIgnoreCase(student.getStatus())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(Map.of("error", "Account is not active"));
            }

            // Converting gender to title
            String tittle = "Ms";
            if ("Male".equalsIgnoreCase(student.getGender().trim())) {
                tittle = "Mr";
            }

            // Creating initials with firstName and MiddleName
            String initials = "";
            String profile = "";

            if (student.getFirstName() != null && !student.getFirstName().isEmpty()) {
                initials += student.getFirstName().substring(0, 1);

                if (student.getMiddleName() != null && !student.getMiddleName().isEmpty()) {
                    initials += student.getMiddleName().substring(0, 1);
                }
            }

            if (initials.isEmpty() && student.getFirstName() != null && !student.getFirstName().isEmpty()) {
                initials = student.getFirstName().substring(0, 1);
            }

            if (student.getFirstName() != null && !student.getFirstName().isEmpty()
                    && student.getSurname() != null && !student.getSurname().isEmpty()) {
                profile = student.getFirstName().substring(0, 1)
                        + student.getSurname().substring(0, 1);
            }

            // Converting student details to QRCode
            String qrContent = String.format(
                    "Name: %s %s %s\nCourse: %s\nStudent ID: %s",
                    tittle,
                    student.getFirstName(),
                    student.getSurname(),
                    student.getCourse(),
                    student.getStudentId()
//                    student.getEmail()
            );

            // Generate QR code
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            BitMatrix bitMatrix = new QRCodeWriter().encode(
                    qrContent,
                    BarcodeFormat.QR_CODE,
                    200,
                    200,
                    hints
            );

            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            byte[] qrCodeBytes = outputStream.toByteArray();
            String qrCode = Base64.getEncoder().encodeToString(qrCodeBytes);

//            // Encode photo byte to Base64 String (if photo exists)
//            String base64Photo = "";
//            if (student.getPhoto() != null) {
//                base64Photo = Base64.getEncoder().encodeToString(student.getPhoto());
//                base64Photo = "data:image/png;base64," + base64Photo;
//            }


            return ResponseEntity.ok(Map.of(
                    "studentId", student.getStudentId(),
                    "initials", initials,
                    "firstName", student.getFirstName(),
                    "surname", student.getSurname(),
                    "contactNumber", student.getContactNumber(),
                    "faculty", student.getFaculty(),
                    "course", student.getCourse(),
                    "title", tittle,
                    "profile", profile,
//                    "status", student.getStatus()
                    "qrCode", qrCode
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (WriterException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to generate QR code"));
        }
    }


    // Method to upload photo for student card
    @PostMapping(value = "/UpdateProfile")
    public ResponseEntity<?> updateProfile(
            @RequestParam("studentId") Long studentId,
            @RequestParam("firstName") String firstName,
            @RequestParam("surname") String surname,
            @RequestParam("contactNumber") String contactNumber,
            @RequestParam("faculty") String faculty,
            @RequestParam("course") String course) {
        try {
            Student student = studentService.findById(studentId);
            if (student == null) {
                throw new RuntimeException("Student not found");
            }

            // Update profile-related fields
            student = new Student.Builder()
                    .copy(student)
                    .setFirstName(firstName)
                    .setSurname(surname)
                    .setContactNumber(contactNumber)
                    .setFaculty(faculty)
                    .setCourse(course)
                    .build();

            studentService.update(student);

            return ResponseEntity.ok(Map.of("message", "Card updated successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}

