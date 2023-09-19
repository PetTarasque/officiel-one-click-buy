package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.CartFactory;
import ca.ulaval.glo4002.mockexercise.do_not_edit.Invoice;
import ca.ulaval.glo4002.mockexercise.do_not_edit.InvoiceLine;
import ca.ulaval.glo4002.mockexercise.do_not_edit.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class StartByTestingThis {

    private final CartFactory cartFactory;
    private final ProductRepository productRepository;
    private final InvoiceFactory invoiceFactory;

    public StartByTestingThis(CartFactory cartFactory, ProductRepository productRepository, InvoiceFactory invoiceFactory){
        this.cartFactory = cartFactory;
        this.productRepository = productRepository;
        this.invoiceFactory = invoiceFactory;
    }

    public Invoice oneClickBuy(String clientEmail, String productSku) {
        // Étape 1 : Créer le cart avec le CartFactory
        Cart cart = cartFactory.create(clientEmail);

        // Étape 2 : Trouver le produit avec le ProductRepository
        Product foundProduct = productRepository.findBySku(productSku);

        // Étape 3 : Ajouter le produit au cart
        cart.addProduct(foundProduct);

        // Étape 4 : Pour chaque item du cart, ajouter une ligne sur l'invoice
        List<InvoiceLine> invoiceLines = cart.generateInvoiceLines();
        Invoice invoice = invoiceFactory.create(clientEmail, invoiceLines);

        // Étape 5 : Retourner l'invoice
        return invoice;
    }
}
