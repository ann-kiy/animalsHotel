package com.kiyanitsa.animalsHotel.specification;

import com.kiyanitsa.animalsHotel.domain.AdvertisementAccept;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdvertAcceptSpecificationsBuilder {
    private final List<SearchCriteria> params;

    public AdvertAcceptSpecificationsBuilder(){
        params = new ArrayList<SearchCriteria>();
    }

    public AdvertAcceptSpecificationsBuilder with(String key, String operation, Object value, String split) {
        params.add(new SearchCriteria(key, operation, value, split));
        return this;
    }

    public Specification<AdvertisementAccept> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(AdvertAcceptSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);
        if(specs.size()>1)
            result = params.get(0)
                    .isOrPredicate()
                    ? Specification.where(result)
                    .or(specs.get(1))
                    : Specification.where(result)
                    .and(specs.get(1));

        int i=2;
        while (i < params.size()) {
            if(params.get(i).isOrPredicate()){
                result=Specification.where(result).and(specs.get(i).or(specs.get(i+1)));
                i+=2;
            }else{
                result=Specification.where(result).and(specs.get(i));
                i++;
            }

//            result = params.get(i-1)
//                    .isOrPredicate()
//                    ? Specification.where(result)
//                    .or(specs.get(i))
//                    : Specification.where(result)
//                    .and(Specification.where(specs.get(i))  );
        }
        return result;
    }
}
