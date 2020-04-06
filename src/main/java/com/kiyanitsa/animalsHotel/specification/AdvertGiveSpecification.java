package com.kiyanitsa.animalsHotel.specification;

import com.kiyanitsa.animalsHotel.domain.AdvertisementGive;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AdvertGiveSpecification implements Specification<AdvertisementGive> {
    private SearchCriteria criteria;

    public AdvertGiveSpecification(SearchCriteria criteria) {
        this.criteria=criteria;
    }
    @Override
    public Predicate toPredicate(Root<AdvertisementGive> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
       if(criteria.getKey().equals("condition") || criteria.getKey().equals("create_date")
               || criteria.getKey().equals("date_end")
       || criteria.getKey().equals("date_start") || criteria.getKey().equals("state")){
           if (criteria.getOperation().equalsIgnoreCase(">")) {
               return builder.greaterThanOrEqualTo(
                       root.<String> get(criteria.getKey()), criteria.getValue().toString());
           }
           else if (criteria.getOperation().equalsIgnoreCase("<")) {
               return builder.lessThanOrEqualTo(
                       root.<String> get(criteria.getKey()), criteria.getValue().toString());
           }
           else if (criteria.getOperation().equalsIgnoreCase(":")) {
               if (root.get(criteria.getKey()).getJavaType() == String.class) {
                   return builder.like(
                           root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
               } else {
                   return builder.equal(root.get(criteria.getKey()), criteria.getValue());
               }
           }
       }else {
           if (criteria.getOperation().equalsIgnoreCase(">")) {
               return builder.greaterThanOrEqualTo(
                       root.get("animal").<String>get(criteria.getKey()), criteria.getValue().toString());
           } else if (criteria.getOperation().equalsIgnoreCase("<")) {
               return builder.lessThanOrEqualTo(
                       root.get("animal").<String>get(criteria.getKey()), criteria.getValue().toString());
           } else if (criteria.getOperation().equalsIgnoreCase(":")) {
               if (root.get("animal").get(criteria.getKey()).getJavaType() == String.class) {
                   return builder.like(
                           root.get("animal").<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
               } else {
                   return builder.equal(root.get("animal").get(criteria.getKey()), criteria.getValue());
               }
           }
       }
        return null;
    }
}
