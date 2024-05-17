package com.keycon.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Owner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId; // PK

    @Column(unique = true)
    private String ownerName; // 사장님이 로그인할 때 입력하는 ID

    @Column
    private String shopName; // 상호명

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Category> categoryList = new ArrayList<>();

    @Column
    private String password;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    /* 포인트 적립은 보류 */

    @Builder
    public Owner(String ownerName, String shopName, String password) {
        this.ownerName = ownerName;
        this.shopName = shopName;
        this.password = password;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public Owner update(String shopName, String password){
        this.shopName = shopName;
        this.password = password;

        return this;
    }

    public void addCategory(Category category) {
        this.categoryList.add(category);
    }

}
