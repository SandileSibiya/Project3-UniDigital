package za.ac.cputdigitalcard.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cputdigitalcard.domain.Card;
import za.ac.cputdigitalcard.domain.Student;
import za.ac.cputdigitalcard.service.CardService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cputdigitalcard.com")

public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // Method to add a card details to the database
//    @PostMapping("/AddCard")
//    public ResponseEntity<?> AddCard(@RequestBody Card card) {
//        try {
//            Card newCard = cardService.create(card);
//            return ResponseEntity.ok(newCard);
//        } catch (RuntimeException e) {
//            System.out.println("Error adding card details");
//            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
//        }
//    }

    @PostMapping(value = "/AddCard")
    public ResponseEntity<?> AddCard(
            @RequestParam String title,
            @RequestParam String initials,
            @RequestParam String surname,
            @RequestParam String course,
            @RequestParam String date,
            @RequestParam Long studentId,
            @RequestParam(required = false) MultipartFile photo) {
        try {
            byte[] photoBytes = null;
            if (photo != null && !photo.isEmpty()) {
                photoBytes = photo.getBytes();
            }

            Card card = new Card.Builder()
                    .setTitle(title)
                    .setInitials(initials)
                    .setSurname(surname)
                    .setCourse(course)
                    .setDate(date)
                    .setStudentId(studentId)
                    .setPhoto(photoBytes)
                    .build();

            Card newCard = cardService.create(card);

            // Converting student details to QRCode
            String qrContent = String.format(
                    "Student ID: %s\nTitle: %s %s %s\nCourse: %s\nPhoto: %s",
                    card.getStudentId(),
                    card.getTitle(),
                    card.getInitials(),
                    card.getSurname(),
                    card.getCourse(),
                    photo

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

            // Encode photo byte to Base64 String (if photo exists)
            String base64Photo = "";
            if (card.getPhoto() != null) {
                base64Photo = Base64.getEncoder().encodeToString(card.getPhoto());
                base64Photo = "data:image/png;base64," + base64Photo;
            }

            return ResponseEntity.ok(Map.of(
                    "studentId", card.getStudentId(),
                    "photo", base64Photo,
                    "qrCode", qrCode
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    // Method to get card details from the database using cardId
    @GetMapping("/GetCardDetails/{studentId}")
    public ResponseEntity<?> getCardDetails(@PathVariable Long studentId) {
        try {
            Card card = cardService.read(studentId);
            return ResponseEntity.ok(Map.of(
                    "studentId", card.getStudentId(),
                    "title", card.getTitle(),
                    "initials", card.getInitials(),
                    "surname", card.getSurname(),
                    "photo", card.getPhoto(),
                    "qrCode", card.getQrCode()
            ));
        } catch (RuntimeException e) {
            System.out.println("Card not found");
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Method to get card details from the database using cardId
    @GetMapping("/GetCard/{studentId}")
    public ResponseEntity<?> getCard(@PathVariable Long studentId) {
        try {
            Card card = cardService.read(studentId);
            return ResponseEntity.ok(Map.of(
                    "cardId", card.getCardId(),
                    "date", card.getDate(),
                    "studentId", card.getStudentId(),
                    "photo", card.getPhoto()
            ));
        } catch (RuntimeException e) {
            System.out.println("Card not found");
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Method to get card details from the database using cardId
    @GetMapping("/GetCardById/{cardId}")
    public ResponseEntity<?> getApplicationByCardId(@PathVariable Long cardId) {
        try {
            Card card = cardService.searchByCardId(cardId);
            return ResponseEntity.ok(Map.of(
                    "cardId", card.getCardId(),
                    "date", card.getDate(),
                    "studentId", card.getStudentId()
            ));
        } catch (RuntimeException e) {
            System.out.println("Card not found");
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
