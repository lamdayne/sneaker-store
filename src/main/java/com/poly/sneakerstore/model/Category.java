package com.poly.sneakerstore.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String imageUrl;
    private Integer displayOrder;
    @Column(name = "is_active")
    private Boolean active;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
