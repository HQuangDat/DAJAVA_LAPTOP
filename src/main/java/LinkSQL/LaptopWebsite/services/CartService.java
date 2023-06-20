package LinkSQL.LaptopWebsite.services;

import LinkSQL.LaptopWebsite.model.*;
import LinkSQL.LaptopWebsite.repository.IOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@SessionScope
public class CartService {
    @Autowired
    private IOrderRepository orderRepository;
    private List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }

    public void addToCart(Product product) {
        CartItem findCart = cartItems.stream()
                .filter(item -> item.getId().equals(product.getId()))
                .findFirst().orElse(null);
        if(findCart != null)
        {
            findCart.setQuantity(findCart.getQuantity()+1);
        }
        else
        {
            System.out.print("case item = null");
            findCart = new CartItem();
            findCart.setQuantity(1);
            findCart.setId(product.getId());
            findCart.setName(product.getProductname());
            findCart.setImage(product.getImage());
            findCart.setPrice(product.getPrice());
            findCart.setCategory(product.getCategory());
            cartItems.add(findCart);
        }
    }

    public void updateCartItem(Long productId, int quantity) {
        CartItem findCart = cartItems.stream()
                .filter(item -> item.getId().equals(productId))
                .findFirst().orElse(null);
        if(findCart != null)
        {
            findCart.setQuantity(quantity);
        }
    }

    public void removeFromCart(Long productId) {
        cartItems.removeIf(cartItem -> cartItem.getId().equals(productId));
    }

    //Service đơn hàng
    @Transactional
    public void orderCart(User user) {
    // Create a new Order
        Orders order = new Orders();
        order.setOrder_date(new Date());
        order.setIsPaid(false);
        order.setUser(user);
    // Iterate over cart items and create OrderDetails
        List<OrdersDetail> orderDetails = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrdersDetail orderDetail = new OrdersDetail();
            orderDetail.setOrder(order);
            orderDetail.setPrice(cartItem.getPrice());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetails.add(orderDetail);
        }
    // Set order details in the order
        order.setOrderDetails(orderDetails);
    // Save the order to the database
        orderRepository.save(order);
    // Clear the cart
        clearCart();
    }
}