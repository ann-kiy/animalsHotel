package com.kiyanitsa.animalsHotel.specification;

import com.kiyanitsa.animalsHotel.domain.BreedAnimal;
import com.kiyanitsa.animalsHotel.domain.TypeAnimal;
import lombok.Data;

@Data
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
    public SearchCriteria(String key, String operation, Object value) {
        this.key=key;
        this.operation=operation;
        this.value=value;
    }

//    public SearchCriteria(String key, String operation, BreedAnimal value) {
//        this.key=key;
//        this.operation=operation;
//        this.value=value;
//    }
//    public SearchCriteria(String key, String operation, TypeAnimal value) {
//        this.key=key;
//        this.operation=operation;
//        this.value=value;
//    }

    public boolean isOrPredicate() {
        return false;
    }
}
