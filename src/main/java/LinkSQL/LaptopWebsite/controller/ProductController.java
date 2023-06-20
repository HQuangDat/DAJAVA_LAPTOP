package LinkSQL.LaptopWebsite.controller;

import LinkSQL.LaptopWebsite.model.Category;
import LinkSQL.LaptopWebsite.model.Product;
import LinkSQL.LaptopWebsite.services.ProductService;
import LinkSQL.LaptopWebsite.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    private String showAllProducts(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 5; // Adjust the page size as needed
        Page<Product> products = productService.GetAll(page, pageSize);
        int totalPages = products.getTotalPages();
        model.addAttribute("products", products);
        model.addAttribute("totalPages", totalPages);
        return "product/list";
    }


//    @GetMapping
//    public String index(Model model,
//                        @RequestParam(defaultValue = "0") int page,
//                        @RequestParam(defaultValue = "2") int size)
//    {
//        Page<Product> products = productService.GetAll(page, size);
//        model.addAttribute("listproduct", products);
//        model.addAttribute("totalPages", products.getTotalPages());
//        return "product/list";
//    }

    @GetMapping("/add")
    public String addProductForm(Model model){
        model.addAttribute("product",new Product());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "product/add";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product){
        productService.addProduct(product);
        return "redirect:/products";
    }

    //Lấy thông tin của một product dựa vào ID và đồng thời lấy tất cả loại máy dùng cho việc edit
    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        Optional<Product> editProduct = Optional.ofNullable(productService.getProductById(id));
        if (editProduct.isPresent()) {
            Product product = editProduct.get();
            model.addAttribute("product", product);
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("selectedCategoryId", product.getCategory().getId());
            return "product/edit";
        } else {
            return "not-found";
        }
    }


    @PostMapping("/edit")
    public String editProduct(@ModelAttribute("product") Product product) {
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    //Search
    @GetMapping("/search")
    public String searchProducts(Model model, @RequestParam String key,
                                 @RequestParam(defaultValue = "0") int pageNo,
                                 @RequestParam(defaultValue = "2") int pageSize)
    {
        Page<Product> products = productService.GetSearchProducts(key,
                pageNo, pageSize);
        int totalPages = products.getTotalPages();
        if (products.isEmpty()) {
            model.addAttribute("searchMessage", "No products found.");
        } else {
            model.addAttribute("products", products);
        }
        model.addAttribute("totalPages", totalPages);
        return "product/list";
    }



}
