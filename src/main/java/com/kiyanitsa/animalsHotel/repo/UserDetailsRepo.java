package com.kiyanitsa.animalsHotel.repo;

import com.kiyanitsa.animalsHotel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User,String> {

    User findByName(String name);
}
