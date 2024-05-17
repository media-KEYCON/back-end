package com.keycon.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Menus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menusId;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @Column
    private String menusName;

    @Column
    private int menusPrice;

    @Column
    private boolean soldOut;

    @OneToMany(mappedBy = "menus", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MenuOptions> menuOptionsList = new ArrayList<>();

    @Builder
    public Menus(String menusName, int menusPrice, boolean soldOut){
        this.menusName = menusName;
        this.menusPrice = menusPrice;
        this.soldOut = soldOut;
    }

    public Menus update(String menusName, int menusPrice, boolean soldOut) {
        this.menusName = menusName;
        this.menusPrice = menusPrice;
        this.soldOut = soldOut;

        return this;
    }

    public void addMenuOptions(MenuOptions menuOptions) {
        this.menuOptionsList.add(menuOptions);
    }

    public void addCategory(Category category) {
        this.category = category;
    }
}
