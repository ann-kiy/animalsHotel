package com.kiyanitsa.animalsHotel.repo;

        import com.kiyanitsa.animalsHotel.domain.*;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.List;
        import java.util.Optional;

public interface AnimalRepo extends JpaRepository<Animal, Long> {
        List<Animal> findAllByOwner(User user);
        List<Animal> findAllByTypeAnimal(TypeAnimal typeAnimal);
       List<Animal> findAllByBreedAnimal(BreedAnimal breed_animal);
        List<Animal> findAllByAge(byte age);
        List<Animal> findAllBySex(Sex sex);

}
