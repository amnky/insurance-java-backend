package com.monocept.app.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.monocept.app.entity.Agent;
import com.monocept.app.entity.Customer;
import com.monocept.app.entity.Transactions;
import com.monocept.app.exception.UserException;

import jakarta.servlet.http.HttpServletResponse;

public class AgentsPdfExporter {

    private List<Agent> agents;

    public AgentsPdfExporter(List<Agent> agents) {
        this.agents = agents;
    }

    // Write the table header for the current Transactions table
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Agent ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("IsActive", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Qualification", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Balance", font));
        table.addCell(cell);
    }

    // Write the table data for the current Transactions table
    private void writeTableData(PdfPTable table) {
        for (Agent agent : agents) {
            table.addCell(String.valueOf(agent.getAgentId()));
            table.addCell(String.valueOf(agent.getFirstName()+" "+agent.getLastName()));
            table.addCell(String.valueOf(agent.getIsActive()));
            table.addCell(agent.getQualification());
            table.addCell(String.valueOf(agent.getBalance()));
        }
    }

    // Export the PDF
    public void export(HttpServletResponse response) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setSize(18);
            font.setColor(Color.BLUE);

            Paragraph p = new Paragraph("List of Agents", font);
            p.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(p);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100f);
            table.setWidths(new float[] { 1.5f, 2.0f, 3.0f, 2.0f, 2.5f});
            table.setSpacingBefore(10);

            writeTableHeader(table);
            writeTableData(table);

            document.add(table);

            document.close();
        } catch (DocumentException | IOException e) {
            throw new UserException("Error while exporting pdf");
        }
    }
}
