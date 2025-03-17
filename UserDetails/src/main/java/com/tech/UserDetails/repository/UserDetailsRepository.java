package com.tech.UserDetails.repository;

import com.tech.UserDetails.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
}
