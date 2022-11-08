package com.laptrinhjavaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import com.laptrinhjavaweb.utils.StringUtils;

@Repository
public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<CustomerEntity> searchCustomer(CustomerSearchBuilder customerSearchBuilder,int page , int limit) {
		StringBuilder finalQuery = new StringBuilder(" SELECT * FROM customer c");
		finalQuery = buildSqlJoin(customerSearchBuilder, finalQuery);
		finalQuery.append(SystemConstant.one_equal_one);
		finalQuery = buildSqlCommon(customerSearchBuilder, finalQuery);
		finalQuery = buildQuerySpecial(customerSearchBuilder, finalQuery);
		finalQuery.append("\nGROUP BY c.id");
		if(page != 0 || limit != 0) {
			page = (page - 1 ) *  limit;
			finalQuery.append(" limit "+ page +","+ limit);
		}
		
		javax.persistence.Query query = entityManager.createNativeQuery(finalQuery.toString(),CustomerEntity.class); // chuyển nó về kiểu BuildingEntity
        List<CustomerEntity> entities =  query.getResultList();	
        Page<CustomerEntity> customerEntities = new PageImpl<>(entities);
		return customerEntities;
	}
	
	private StringBuilder buildSqlCommon(CustomerSearchBuilder customerSearchBuilder, StringBuilder sql)
	{
		Field[] fields = customerSearchBuilder.getClass().getDeclaredFields();
		
		for(Field item : fields)
		{
			item.setAccessible(true);
			
			try {
				if(!item.getName().equals("staff")) {
					
					if(item.get(customerSearchBuilder) != null) {
						 String value =   String.valueOf(item.get(customerSearchBuilder));
						 

						 if(  item.getType().equals(String.class) && !StringUtils.isNull(value))
						 {		
							 sql.append("\nAND c."+item.getName().toLowerCase()+" LIKE '%")
							 .append(value+"%'");
							 
						 }
						 else if(!StringUtils.isNull(value)) {	
							 sql.append("\nAND c."+item.getName() +" = ")
							 .append(value);
						 }
						 
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return sql;
	}
	
	private StringBuilder buildQuerySpecial(CustomerSearchBuilder customerSearchBuilder, StringBuilder sql)
	{
		Long staffId = customerSearchBuilder.getStaff();
		
		if (null != staffId ) { 
            sql.append("\nAND u.id  = ")
                    .append(staffId);
        }
		
		return sql;
	}
	
	private StringBuilder buildSqlJoin(CustomerSearchBuilder customerSearchBuilder , StringBuilder sql) {
		
		Long staffId = customerSearchBuilder.getStaff();
		
		if (null != staffId ) {
			sql.append("\n JOIN assignmentcustomer ac on ac.customerid = c.id  JOIN user u on u.id = ac.staffid");
         
        }
		
		return sql;
	}


}
