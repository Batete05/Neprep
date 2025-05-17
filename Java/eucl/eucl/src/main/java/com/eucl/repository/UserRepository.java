package com.eucl.repository;

import com.eucl.model.Role;
import com.eucl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    boolean existsByEmailOrPhoneOrNationalId(String email, String phone, String nationalId);

    boolean existsByRole(Role role);

    Optional<User> findByNationalId(String nationalId);
}
