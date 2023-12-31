package com.kftech.ecommercebackend.dao;

import com.kftech.ecommercebackend.model.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalUserDAO extends JpaRepository<LocalUser, Long> {
    @Query("select l from LocalUser l where upper(l.username) = upper(?1)")
    Optional<LocalUser> findByUsernameIgnoreCase(String username);

    @Query("select l from LocalUser l where upper(l.email) = upper(?1)")
    Optional<LocalUser> findByEmailIgnoreCase(String email);

}
