package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.CartFactory;
import ca.ulaval.glo4002.mockexercise.do_not_edit.Invoice;
import ca.ulaval.glo4002.mockexercise.do_not_edit.InvoiceLine;
import ca.ulaval.glo4002.mockexercise.do_not_edit.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StartByTestingThisTest {
    @Mock
    private CartFactory cartFactory; //pas "mocked" car ils sont forcément mocked dans un test
    @Mock
    private ProductRepository productRepository;
    @Mock
    private InvoiceFactory invoiceFactory;
    @Mock
    private Cart cart;
    @Mock
    private Product product;
    @Mock
    private Invoice invoice;

    @InjectMocks
    private StartByTestingThis service;

    private final double A_PRICE = 1.00;
    private final String AN_EMAIL = "an@email";
    private final String A_PRODUCT_SKU = "SKU";
    private final List<InvoiceLine> lines = Arrays.asList(new InvoiceLine(A_PRODUCT_SKU, A_PRICE));

    @BeforeEach
    public void setUp() {
        service = new StartByTestingThis(cartFactory, productRepository, invoiceFactory);

        when(cartFactory.create(anyString())).thenReturn(cart);
        when(invoiceFactory.create(anyString(), any())).thenReturn(invoice);
        //on garde anyString parce que on test que c'est le bon mail
        when(productRepository.findBySku(anyString())).thenReturn(product);
        when(cart.generateInvoiceLines()).thenReturn(lines);
    }

    @Test
    public void whenOneClickBuy_shouldCallCartFactory(){
        service.oneClickBuy(AN_EMAIL, A_PRODUCT_SKU);

        verify(cartFactory).create(AN_EMAIL);
    }

    @Test
    public void whenOneClickBuy_shouldFindProductWithProductRepository(){
        service.oneClickBuy(AN_EMAIL, A_PRODUCT_SKU);

        verify(productRepository).findBySku(A_PRODUCT_SKU);
    }

    @Test
    public void whenOneClickBuy_shouldAddProductToCart(){
        service.oneClickBuy(AN_EMAIL, A_PRODUCT_SKU);

        verify(cart).addProduct(product);
    }

    @Test
    public void whenOneClickBuy_shouldAddLineToInvoice(){
        service.oneClickBuy(AN_EMAIL, A_PRODUCT_SKU);

        verify(invoiceFactory).create(AN_EMAIL, lines);
    }

    @Test
    public void whenOneClickBuy_shouldReturnInvoice(){
        Invoice returnedInvoice = service.oneClickBuy(AN_EMAIL, A_PRODUCT_SKU);

        assertNotNull(returnedInvoice);
    }
}
