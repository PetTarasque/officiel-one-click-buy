package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.Invoice;
import ca.ulaval.glo4002.mockexercise.do_not_edit.InvoiceLine;

import java.util.List;

public class InvoiceFactory {
    public Invoice create(String clientEmail, List<InvoiceLine> lines){
        return new Invoice( clientEmail,lines);
    }
}
