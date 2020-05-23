package com.kiyanitsa.animalsHotel.specification;

import com.kiyanitsa.animalsHotel.domain.AdvertisementAccept;
import com.kiyanitsa.animalsHotel.domain.TypeAnimal;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

@Data
public class AdvertAcceptSpecification implements Specification<AdvertisementAccept> {
    private SearchCriteria criteria;

    public AdvertAcceptSpecification(SearchCriteria criteria) {
        this.criteria=criteria;
    }

    @Override
    public Predicate toPredicate(Root<AdvertisementAccept> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
//                return builder.greaterThanOrEqualTo(
//                        root.get(criteria.getKey()), criteria.getValue().toString());
            if (root.get(criteria.getKey()).getJavaType() == LocalDate.class) {
                return builder.greaterThanOrEqualTo(
                        root.<LocalDate>get(criteria.getKey()), (LocalDate) criteria.getValue());
            } else {
                return builder.greaterThanOrEqualTo(
                        root.<String>get(criteria.getKey()), criteria.getValue().toString());
            }
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            if (root.get(criteria.getKey()).getJavaType() == LocalDate.class) {
                return builder.lessThanOrEqualTo(
                        root.<LocalDate>get(criteria.getKey()), (LocalDate) criteria.getValue());
            } else {
                return builder.lessThanOrEqualTo(
                        root.<String>get(criteria.getKey()), criteria.getValue().toString());
            }
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                if(criteria.getValue()==null)
                    return builder.isNull(root.get(criteria.getKey()));
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
