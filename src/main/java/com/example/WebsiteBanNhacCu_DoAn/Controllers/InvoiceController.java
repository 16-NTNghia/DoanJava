package com.example.WebsiteBanNhacCu_DoAn.Controllers;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Invoice;
import com.example.WebsiteBanNhacCu_DoAn.Services.CartService;
import com.example.WebsiteBanNhacCu_DoAn.Services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class InvoiceController {
    @Autowired
    private  CartService cartService;
    @Autowired
    private  InvoiceService invoiceService;

    @GetMapping("/thanhtoan")
    public String showCheckoutPage(Model model) {
//        model.addAttribute("cartItems", cartService.getCartItems());
        //model.addAttribute("total", cartService.calculateTotal());
        return "/Checkout/checkout";
    }

    @PostMapping("/xacnhan{username}")
    public String processCheckout(@RequestParam(name = "Address",required = false) String Address,
                                  @PathVariable String username,
                                  Model model) {
//         Create the invoice
        Invoice invoice = invoiceService.createInvoice(username, Address);

        // Add the invoice to the model to display the invoice page
        model.addAttribute("invoice", invoice);
        model.addAttribute("itemInvoices", invoiceService.getItemInvoice(invoice.getId()));
        return "checkout/Hoadon";
    }


}
