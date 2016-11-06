package com.iware.lottery.admin.repository;

import com.iware.lottery.admin.domain.User;
import com.iware.lottery.admin.domain.User_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnma on 2016/11/2.
 */
public final class UserSpecifications  {

    private UserSpecifications() {
        throw new InstantiationError( "Must not instantiate this class" );
    }

    public static Specification<User> filterByKeywordAndStatus(final String keyword) {
        return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(keyword)) {
                predicates.add(
                        cb.or(
                                cb.like(root.get(User_.name), "%" + keyword + "%")
                        )
                );
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}

