package com.example.arsenedata.repository;

import com.example.arsenedata.dataEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Security, this talks to the database and brings in the credentials
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByUsername(String userName);
}
