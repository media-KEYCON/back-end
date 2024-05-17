package com.kicon.repository;

import com.kicon.domain.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByOwnerName(String ownerName);
}
