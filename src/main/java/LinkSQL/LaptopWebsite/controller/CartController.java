package LinkSQL.LaptopWebsite.controller;

import LinkSQL.LaptopWebsite.model.CartItem;
import LinkSQL.LaptopWebsite.model.Product;
import LinkSQL.LaptopWebsite.model.User;
import LinkSQL.LaptopWebsite.repository.IUserRepository;
import LinkSQL.LaptopWebsite.services.CartService;
import LinkSQL.LaptopWebsite.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getCartItems(Model model, HttpSession session) {
        List<CartItem> cartItems = cartService.getCartItems();
        model.addAttribute("cartItems", cartItems);
        long totalPrice = cartItems.stream()
                .mapToLong(cartItem -> cartItem.getPrice() *
                        cartItem.getQuantity())
                .sum();
        model.addAttribute("totalPrice", totalPrice);
        session.setAttribute("cartCount", cartItems.size());
        return "cart/index";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            cartService.addToCart(product);
        }
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCartItem(@RequestParam("productId") Long productId,
                                 @RequestParam("quantity") int quantity) {
        cartService.updateCartItem(productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/order")
    public String Order() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        LinkSQL.LaptopWebsite.model.User findUser = userRepository.findByUsername(user.getUsername());
        cartService.orderCart(findUser);
        return "/cart/order";
    }
}
