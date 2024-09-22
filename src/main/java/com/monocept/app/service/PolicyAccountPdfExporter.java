package com.monocept.app.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.monocept.app.entity.PolicyAccount;
import com.monocept.app.entity.WithdrawalRequests;
import com.monocept.app.exception.UserException;

import jakarta.servlet.http.HttpServletResponse;

public class PolicyAccountPdfExporter {

    private List<PolicyAccount> policyAccounts;

    public PolicyAccountPdfExporter(List<PolicyAccount> policyAccounts) {
        super();
        this.policyAccounts = policyAccounts;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Active", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Total amount paid", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("claim amount", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("agent ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("customer id", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("investment amount", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (PolicyAccount policyAccounts1 : policyAccounts) {
            table.addCell(policyAccounts1.getCustomer().getFirstName()+" "+policyAccounts1.getCustomer().getLastName());
            table.addCell(String.valueOf(policyAccounts1.getIsActive()));
            table.addCell(String.valueOf(policyAccounts1.getTotalAmountPaid()));
            table.addCell(String.valueOf(policyAccounts1.getClaimAmount()));
            if(policyAccounts1.getAgent()!=null){
                table.addCell(String.valueOf(policyAccounts1.getAgent().getAgentId()));
            }
            else table.addCell(("null"));
            table.addCell(String.valueOf(policyAccounts1.getCustomer().getCustomerId()));
            table.addCell(String.valueOf(policyAccounts1.getInvestmentAmount()));

        }
    }

    public void export(HttpServletResponse response) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setSize(18);
            font.setColor(Color.BLUE);

            Paragraph p = new Paragraph("List of policy Accounts ", font);
            p.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(p);

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100f);
            table.setWidths(new float[] { 2.0f, 2.5f, 2.0f, 2.0f, 2.5f, 2.5f, 2.5f });
            table.setSpacingBefore(10);

            writeTableHeader(table);
            writeTableData(table);

            document.add(table);

            document.close();
        }

        catch (DocumentException | IOException e) {
            throw new UserException("Error while exporting pdf");
        }

    }
}
