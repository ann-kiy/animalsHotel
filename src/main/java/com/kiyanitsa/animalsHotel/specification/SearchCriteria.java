package com.kiyanitsa.animalsHotel.specification;

import com.kiyanitsa.animalsHotel.domain.BreedAnimal;
import com.kiyanitsa.animalsHotel.domain.TypeAnimal;
import lombok.Data;

@Data
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
    private String split;
    public SearchCriteria(String key, String operation, Object value,  String split) {
        this.key=key;
        this.operation=operation;
        this.value=value;
        this.split=split;
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
        if(split.equals(";"))
            return true;
        return false;
    }
}
