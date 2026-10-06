package com.example.employee_management.service;

import com.example.employee_management.entity.Employee;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfExportService {

    public ByteArrayInputStream exportEmployees(List<Employee> employees) {
        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Employee Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);

            String[] headers = {
                    "ID", "First Name", "Last Name", "Email",
                    "Phone", "Department", "Salary", "Address"
            };

            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header));
                table.addCell(cell);
            }

            for (Employee emp : employees) {
                table.addCell(String.valueOf(emp.getId()));
                table.addCell(emp.getFirstName());
                table.addCell(emp.getLastName());
                table.addCell(emp.getEmail());
                table.addCell(emp.getPhone());
                table.addCell(emp.getDepartment());
                table.addCell(String.valueOf(emp.getSalary()));
                table.addCell(emp.getAddress());
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            throw new RuntimeException("PDF export failed: " + e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
