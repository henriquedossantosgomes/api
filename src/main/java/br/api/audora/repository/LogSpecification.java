package br.api.audora.repository;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.api.audora.domain.Log;
import br.api.audora.util.Converter;

public class LogSpecification implements Specification<Log> {

	private SearchCriteria criteria;
	
	public LogSpecification(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Log> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		
		if (criteria.getOperation().equalsIgnoreCase("between")) {
			String[] datas = criteria.getValue().toString().split(":");
            return builder.between(root.<Date> get(criteria.getKey()), Converter.converterStringToDate(datas[0]), Converter.converterStringToDate(datas[1]));
        
		} else if (criteria.getOperation().equalsIgnoreCase("=")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
	}
	

}
