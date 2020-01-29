package com.kiyanitsa.animalsHotel.repo;

import com.kiyanitsa.animalsHotel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepo extends JpaRepository<User,String> {

    User findByName(String name);

    Optional<User> findByIdWeb(String id);
}
