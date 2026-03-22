package com.poly.sneakerstore.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "brands")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String logoUrl;
    private String description;
    @Column(name = "is_active")
    private Boolean active;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}
