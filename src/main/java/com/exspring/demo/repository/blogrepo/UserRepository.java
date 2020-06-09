package com.exspring.demo.repository.blogrepo;

import com.exspring.demo.entity.blogentity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    void deleteByUsername(String username);
    Optional<User> findByUsername(String username);
    @Query("select u from User u")
    Stream<User> streamAll();
}