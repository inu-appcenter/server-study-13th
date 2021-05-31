package inu.appcenter.yunah.model.order;

import inu.appcenter.yunah.domain.Order;
import inu.appcenter.yunah.domain.Product;
import inu.appcenter.yunah.domain.status.OrderStatus;
import inu.appcenter.yunah.model.product.ProductResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {

    private Long orderId;
    private Integer totalPrice;
    private Integer count;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ProductResponse product;

    public static OrderResponse from(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.orderId = order.getId();
        orderResponse.totalPrice = order.getTotalPrice();
        orderResponse.count = order.getCount();
        orderResponse.status = order.getStatus();
        orderResponse.createdAt = order.getCreatedAt();
        orderResponse.updatedAt = order.getUpdatedAt();
        orderResponse.product = ProductResponse.from(order.getProduct());
        return orderResponse;
    }
}
