package com.example.WebsiteBanNhacCu_DoAn.Services;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Cart;
import com.example.WebsiteBanNhacCu_DoAn.Entities.Invoice;
import com.example.WebsiteBanNhacCu_DoAn.Entities.ItemInvoice;
import com.example.WebsiteBanNhacCu_DoAn.Entities.User;
import com.example.WebsiteBanNhacCu_DoAn.Repositories.IInvoiceRepository;
import com.example.WebsiteBanNhacCu_DoAn.Repositories.IItemInvoiceRepository;
import com.example.WebsiteBanNhacCu_DoAn.Repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private  IInvoiceRepository invoiceRepository;
    @Autowired
    private  IUserRepository userRepository;
    @Autowired
    private  CartService cartService;
    @Autowired
    private IItemInvoiceRepository itemInvoiceRepository;

    public void saveInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
    public Invoice createInvoice(String username,String Address) {

        User user = userRepository.findByUsername(username).get();

        List<Cart> cartItems = cartService.getCartItems();
        double total = cartService.calculateTotal();

        Invoice invoice = new Invoice();
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setUser(user);
        invoice.setPrice(total);
        invoice.setCustomerAddress(Address);
        invoice = invoiceRepository.save(invoice);

        for (Cart item : cartItems) {
            ItemInvoice detail = new ItemInvoice();
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            detail.setInvoice(invoice);
            itemInvoiceRepository.save(detail);
        }

        cartService.clearCart();
        return invoice;
    }

    public List<ItemInvoice> getItemInvoice(Long id){
        return itemInvoiceRepository.findByInvoiceID(id);
    }
}
