package com.kiyanitsa.animalsHotel.test;


import com.kiyanitsa.animalsHotel.domain.AdvertisementAccept;
import com.kiyanitsa.animalsHotel.domain.BreedAnimal;
import com.kiyanitsa.animalsHotel.domain.TypeAnimal;
import com.kiyanitsa.animalsHotel.repo.AdvertisementAcceptRepo;
import com.kiyanitsa.animalsHotel.repo.BreedAnimalRepo;
import com.kiyanitsa.animalsHotel.repo.TypeAnimalRepo;
import com.kiyanitsa.animalsHotel.specification.AdvertAcceptSpecification;
import com.kiyanitsa.animalsHotel.specification.SearchCriteria;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;


public class JPASpecificationsTest {

    private  AdvertisementAccept advertisementAccept1;
    private  AdvertisementAccept advertisementAccept2;



    public void JPASpecificationsTest(AdvertisementAcceptRepo advertisementAcceptRepo,TypeAnimalRepo typeAnimal, BreedAnimalRepo breedAnimal){
        advertisementAccept1=new AdvertisementAccept();
        advertisementAccept1.setBreedAnimal(breedAnimal.findById((long) 1).get());
        advertisementAccept1.setTypeAnimal(typeAnimal.findById((long) 3).get());
        advertisementAcceptRepo.save(advertisementAccept1);

        advertisementAccept2=new AdvertisementAccept();
        advertisementAccept2.setBreedAnimal(breedAnimal.findById((long) 2).get());
        advertisementAccept2.setTypeAnimal(typeAnimal.findById((long) 1).get());
        advertisementAcceptRepo.save(advertisementAccept2);
    }


    public void givenLast_whenGettingListOfUsers_thenCorrect(AdvertisementAcceptRepo advertisementAcceptRepo, TypeAnimalRepo typeAnimal,BreedAnimalRepo breedAnimal) {
        SearchCriteria c=new SearchCriteria("breedAnimal", ":", breedAnimal.findById((long) 2).get());
        System.out.println(c);
        AdvertAcceptSpecification spec =
                new AdvertAcceptSpecification(c);
        AdvertAcceptSpecification spec2 =
                new AdvertAcceptSpecification(new SearchCriteria("typeAnimal", ":", typeAnimal.findById((long) 2).get()));


        List<AdvertisementAccept> results =advertisementAcceptRepo.findAll(Specification.where(spec).and(spec2));

        assertThat(advertisementAccept1, isIn(results));
//        assertThat(advertisementAccept2, isIn(results));
    }
}
