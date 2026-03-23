package com.poly.sneakerstore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "shipping_address_id")
    private String shippingAddressId;

    private Double subtotal;
    private Double shippingFee;
    private Double discountAmount;
    private Double totalAmount;

    private String status;
    private String paymentMethod;
    private String paymentStatus;
    private String note;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
