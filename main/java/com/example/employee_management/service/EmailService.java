package com.example.employee_management.service;

import com.example.employee_management.entity.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username:}")
    private String mailUsername;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmployeeCreatedEmail(Employee employee) {
        try {
            if (mailUsername == null || mailUsername.isBlank()) {
                System.out.println("Email skipped: MAIL_USERNAME is not configured.");
                return;
            }

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(employee.getEmail());
            message.setSubject("Employee Registration Successful");
            message.setText(
                    "Hello " + employee.getFirstName() + ",\n\n" +
                            "You have been successfully added to the Employee Management System.\n\n" +
                            "Department: " + employee.getDepartment() + "\n" +
                            "Salary: " + employee.getSalary() + "\n\n" +
                            "Regards,\nAdmin Team"
            );

            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("Email failed: " + e.getMessage());
        }
    }
}
