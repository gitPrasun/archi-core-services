package com.prasun.archi.core.util;

import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Stream;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class ArchiIDGenerator implements IdentifierGenerator, Configurable {

	private String prefix;
	
	@Override
	public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {
		// TODO Auto-generated method stub
		prefix = properties.getProperty("prefix");

	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
		// TODO Auto-generated method stub
//		String query = String.format("select %s from %s", 
//	            session.getEntityPersister(obj.getClass().getName(), obj)
//	              .getIdentifierPropertyName(),
//	            obj.getClass().getSimpleName());
//
//	        Stream ids = session.createQuery(query).stream();
//
//	        Long max = 0L;
////	           ids.map(o -> ((Properties) o).replace(prefix + "-", ""))
////	          .mapToLong(Long::parseLong)
////	          .max()
////	          .orElse(0L);
//
//	        return prefix + "-" + (max + 1);
		return UUID.randomUUID().toString().replace("-", "");
	}

}
