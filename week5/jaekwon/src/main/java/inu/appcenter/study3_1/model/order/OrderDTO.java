package inu.appcenter.study3_1.model.order;

import inu.appcenter.study3_1.domain.Order;
import inu.appcenter.study3_1.domain.Product;
import inu.appcenter.study3_1.model.product.ProductDto;
import lombok.Data;

@Data
public class OrderDTO {

    private Long id;
    private int totalPrice;
    private int count;
    private ProductDto product;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.count = order.getCount();
        this.product = new ProductDto(order.getProduct());
    }
}
