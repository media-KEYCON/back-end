package com.kicon.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Menus> categoryMenusList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "ownerId")
    private Owner owner;

    @Builder
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category update(String categoryName) {
        this.categoryName = categoryName;

        return this;
    }

    public void addOwner(Owner owner) {
        this.owner = owner;
    }

    public void addMenus(Menus menus) {
        this.categoryMenusList.add(menus);
    }

}
