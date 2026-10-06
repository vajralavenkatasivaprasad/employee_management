package com.example.employee_management.service;

import com.example.employee_management.entity.Employee;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ExcelExportService {

    public ByteArrayInputStream exportEmployees(List<Employee> employees) {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Employees");

            Row header = sheet.createRow(0);
            String[] columns = {
                    "ID", "First Name", "Last Name", "Email", "Phone",
                    "Department", "Salary", "Address", "Photo URL"
            };

            for (int i = 0; i < columns.length; i++) {
                header.createCell(i).setCellValue(columns[i]);
            }

            int rowIndex = 1;
            for (Employee emp : employees) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(emp.getId() == null ? 0 : emp.getId());
                row.createCell(1).setCellValue(emp.getFirstName());
                row.createCell(2).setCellValue(emp.getLastName());
                row.createCell(3).setCellValue(emp.getEmail());
                row.createCell(4).setCellValue(emp.getPhone());
                row.createCell(5).setCellValue(emp.getDepartment());
                row.createCell(6).setCellValue(emp.getSalary() == null ? 0 : emp.getSalary());
                row.createCell(7).setCellValue(emp.getAddress());
                row.createCell(8).setCellValue(emp.getPhotoUrl() == null ? "" : emp.getPhotoUrl());
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Excel export failed: " + e.getMessage());
        }
    }
}
