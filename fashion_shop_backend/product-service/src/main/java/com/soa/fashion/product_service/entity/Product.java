package com.soa.fashion.product_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "productId", nullable = false, unique = true, length = 100)
    private String productId;
    @Column(name = "title")
    private String title;
    @Column(name = "description",columnDefinition = "TEXT")
    private String description;
    @OneToMany(mappedBy="product", cascade=CascadeType.ALL)
    private List<ProductImg> images = new ArrayList<>();
    @Column(name = "price")
    private double price;
    @Column(name = "material")
    private String material;
    @Column(name = "productUsage")
    private String productUsage;
    @Column(name = "note")
    private String note;
    @OneToMany(mappedBy="product", cascade=CascadeType.ALL)
    private List<ProductSize> size = new ArrayList<>();
    @OneToMany(mappedBy="product", cascade=CascadeType.ALL)
    private List<ProductColor> color = new ArrayList<>();
    @Column(name = "category")
    private String category ;
    @Column(name = "isDeleted")
    private boolean isDeleted;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @PrePersist
    public void generatedId (){
        if(this.productId == null || this.productId.isEmpty()){
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            StringBuilder sb = new StringBuilder(6);
            Random rnd = new Random();
            for (int i = 0; i < 6; i++) {
                sb.append(chars.charAt(rnd.nextInt(chars.length())));
            }
            this.productId = sb.toString();
        }
    }

}
