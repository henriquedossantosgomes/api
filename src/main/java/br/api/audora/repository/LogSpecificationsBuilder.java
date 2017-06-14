package br.api.audora.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import br.api.audora.domain.Log;

public class LogSpecificationsBuilder {

	private final List<SearchCriteria> params;
	 
    public LogSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }
 
    public LogSpecificationsBuilder with(String key, String operation, Object value) {
        if(value != null && !value.toString().contains("null")){
        	params.add(new SearchCriteria(key, operation, value));
        }
    	
        return this;
    }
 
    public Specification<Log> build() {
        if (params.size() == 0) {
            return null;
        }
 
        List<Specification<Log>> specs = new ArrayList<Specification<Log>>();
        for (SearchCriteria param : params) {
            specs.add(new LogSpecification(param));
        }
 
        Specification<Log> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }
}
