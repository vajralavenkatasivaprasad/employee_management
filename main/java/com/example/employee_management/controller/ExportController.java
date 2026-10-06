package com.example.employee_management.controller;

import com.example.employee_management.entity.Employee;
import com.example.employee_management.repository.EmployeeRepository;
import com.example.employee_management.service.ExcelExportService;
import com.example.employee_management.service.PdfExportService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/export")
@CrossOrigin(origins = "*")
public class ExportController {

    private final EmployeeRepository employeeRepository;
    private final ExcelExportService excelExportService;
    private final PdfExportService pdfExportService;

    public ExportController(EmployeeRepository employeeRepository,
                            ExcelExportService excelExportService,
                            PdfExportService pdfExportService) {
        this.employeeRepository = employeeRepository;
        this.excelExportService = excelExportService;
        this.pdfExportService = pdfExportService;
    }

    @GetMapping("/excel")
    public ResponseEntity<InputStreamResource> exportExcel() {
        List<Employee> employees = employeeRepository.findAll();
        ByteArrayInputStream stream = excelExportService.exportEmployees(employees);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(stream));
    }

    @GetMapping("/pdf")
    public ResponseEntity<InputStreamResource> exportPdf() {
        List<Employee> employees = employeeRepository.findAll();
        ByteArrayInputStream stream = pdfExportService.exportEmployees(employees);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(stream));
    }
}
