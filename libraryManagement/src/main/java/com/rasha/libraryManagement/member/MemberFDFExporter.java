package com.rasha.libraryManagement.member;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MemberFDFExporter {
    private List<Member> listMember;


    public  MemberFDFExporter(List<Member> listMember) {
        this.listMember = listMember;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Member ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("FirstName", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("LastName", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("DateOfBirth", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("NationalCode", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Member member : listMember) {
            table.addCell(String.valueOf(member.getId()));
            table.addCell(member.getFirstName());
            table.addCell(member.getLastName());
            table.addCell(String.valueOf(member.getDateOfBirth()));
            table.addCell(String.valueOf(member.getNationalCode()));

        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Mambers", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
