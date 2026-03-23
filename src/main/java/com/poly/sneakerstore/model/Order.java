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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private String userId;

    @Column(name = "order_code")
    private String orderCode;

    @ManyToOne
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;

    private Double subtotal;
    private Double shippingFee;
    private Double discountAmount;
    private Double totalAmount;

    private String status;
    private String paymentMethod;
    private String paymentStatus;
    private String note;


}
