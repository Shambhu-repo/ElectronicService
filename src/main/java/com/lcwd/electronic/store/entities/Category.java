package com.lcwd.electronic.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
 @Table(name = "categories")
@Builder
public class Category {

    @Id
    @Column(name = "id")
    private  String categoryId;

    @Column(name = "category_title", length = 60, nullable = false)
     private String title;

    @Column(name = "category_description", length = 60, nullable = false)
     private String description;


    private String coverImage;



}
