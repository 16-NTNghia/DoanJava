//package com.example.WebsiteBanNhacCu_DoAn.Controllers;
//
//import com.example.WebsiteBanNhacCu_DoAn.Services.ProductService;
//import com.example.WebsiteBanNhacCu_DoAn.Services.CartService;
//import com.example.WebsiteBanNhacCu_DoAn.Services.CategoryService;
//import com.example.WebsiteBanNhacCu_DoAn.Viewmodels.*;
//import com.example.WebsiteBanNhacCu_DoAn.Entities.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/v1")
//@CrossOrigin(origins = "*")
//@RequiredArgsConstructor
//public class ApiController {
//    private final ProductService productService;
//    private final CategoryService categoryService;
//    private final CartService cartService;
//    @GetMapping("/books")
//    public ResponseEntity<List<BookGetVm>> getAllBooks(Integer pageNo,
//                                                       Integer pageSize, String sortBy) {
//        return ResponseEntity.ok(productService.getAllBooks(
//                        pageNo == null ? 0 : pageNo,
//                        pageSize == null ? 20 : pageSize,
//                        sortBy == null ? "id" : sortBy)
//                .stream()
//                .map(BookGetVm::from)
//                .toList());
//    }
//    @GetMapping("/books/id/{id}")
//    public ResponseEntity<BookGetVm> getBookById(@PathVariable Long id)
//    {
//        return ResponseEntity.ok(productService.getBookById(id)
//                .map(BookGetVm::from)
//                .orElse(null));
//    }
//    @DeleteMapping(value = "/books/{id}")
//    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
//        productService.deleteBookById(id);
//        return ResponseEntity.ok().build();
//    }
//    @GetMapping("/books/search/{keyword}")
//    public ResponseEntity<List<BookGetVm>> searchBooks(@PathVariable String keyword)
//    {
//        return ResponseEntity.ok(productService.searchBook(keyword)
//                .stream()
//                .map(BookGetVm::from)
//                .toList());
//    }
//    @PostMapping("/books/create")
//    public ResponseEntity<BookPostVm> createBook(@RequestBody BookPostVm book){
//        Product product1 = new Product();
//        product1.setTitle(book.title());
//        product1.setPrice(book.price());
//        product1.setAuthor(book.author());
//        Category category = categoryService.findById(book.categoryId());
//        product1.setCategory(category);
//        productService.addBook(product1);
//        return ResponseEntity.ok().build();
//    }
//    @PutMapping("/books/update/{id}")
//    public ResponseEntity<BookPostVm> updateBook(@RequestBody BookPostVm book,@PathVariable Long id){
//        Product product1 = productService.findBookById(id);
//        product1.setTitle(book.title());
//        product1.setPrice(book.price());
//        product1.setAuthor(book.author());
//        Category category = categoryService.findById(book.categoryId());
//        product1.setCategory(category);
//        productService.addBook(product1);
//        return ResponseEntity.ok().build();
//    }
//    @GetMapping("/autocomplete/{term}")
//    public List<String> autocomplete(@PathVariable("term") String term) {
//        List<Product> productTitle = productService.searchBookByTile(term);
//        List<Product> productAuthor = productService.searchBookByAuthor(term);
//        List<Product> productCategory = productService.searchBookByCategory(term);
//        List<String> suggestList = new ArrayList<>();
//        suggestList.addAll(productTitle.stream().map(Product::getTitle).collect(Collectors.toList()));
//        suggestList.addAll(productAuthor.stream().map(Product::getAuthor).collect(Collectors.toList()));
//        suggestList.addAll(productCategory.stream().map(book -> book.getCategory().getName()).collect(Collectors.toList()));
//        return suggestList;
//
//    }
//}
