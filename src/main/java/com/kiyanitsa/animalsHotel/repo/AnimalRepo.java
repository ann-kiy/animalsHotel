package com.kiyanitsa.animalsHotel.repo;

        import com.kiyanitsa.animalsHotel.domain.Animal;
        import com.kiyanitsa.animalsHotel.domain.User;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepo extends JpaRepository<Animal, Long> {
}
