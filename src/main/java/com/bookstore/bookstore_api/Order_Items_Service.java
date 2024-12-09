package com.bookstore.bookstore_api;

// import com.bookstore.bookstore_api.bookRepository;
// import com.bookstore.cart_dtos.OrderItemDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.RoundingMode; // Ensure this is imported
import java.math.BigDecimal;
import java.util.List;

@Service
public class Order_Items_Service {

    private final Order_Items_Repository orderItemsRepository;
    private final OrderRepository orderRepository;
    private final bookRepository bookRepository;

    @Autowired
    public Order_Items_Service(Order_Items_Repository orderItemsRepository, OrderRepository orderRepository, bookRepository bookRepository) {
        this.orderItemsRepository = orderItemsRepository;
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository; // Inject BookRepository
    }

    public List<Order_Items_Model> getAllOrderItems() {
        return orderItemsRepository.findAll();
    }

    public Order_Items_Model getOrderItemById(Long orderItemId) {
        return orderItemsRepository.findById(orderItemId).orElse(null);
    }

    

    public Order_Items_Model createOrderItem(Long orderId, Long bookId, Integer quantity) {
        // Fetch the order
        OrderModel order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));

        // Fetch the book (convert Long to Integer)
        Book book = bookRepository.findById(bookId.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + bookId));

        // Create the order item
        Order_Items_Model orderItem = new Order_Items_Model();
        orderItem.setOrder(order);
        orderItem.setBookId(bookId);
        orderItem.setQuantity(quantity);

        // Convert price to BigDecimal and round to 2 decimal places using RoundingMode
        BigDecimal price = BigDecimal.valueOf(book.getPrice()).setScale(2, RoundingMode.HALF_UP);
        orderItem.setPrice(price);

        return orderItemsRepository.save(orderItem);
    }


    public void deleteOrderItem(Long orderItemId) {
        if (!orderItemsRepository.existsById(orderItemId)) {
            throw new IllegalArgumentException("Order item not found with ID: " + orderItemId);
        }
        orderItemsRepository.deleteById(orderItemId);
    }

    public List<Order_Items_Model> getOrderItemsByOrderId(Long orderId) {
        OrderModel order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return orderItemsRepository.findByOrder(order);
    }
}
