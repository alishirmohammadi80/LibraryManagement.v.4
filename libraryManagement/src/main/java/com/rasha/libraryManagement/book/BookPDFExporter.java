package com.rasha.libraryManagement.book;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;


class BookPDFExporter {
    private List<Book> listBook;

    public BookPDFExporter(List<Book> listBook) {
        this.listBook = listBook;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Book ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("BookName", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("NumberOfPage", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("AuthorName", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Publisher", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Book book : listBook) {
            table.addCell(String.valueOf(book.getId()));
            table.addCell(book.getBookName());
            table.addCell(book.getNumberOfPage());
            table.addCell(book.getAuthorName());
            table.addCell(book.getPublisher());

        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Books", font);
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

