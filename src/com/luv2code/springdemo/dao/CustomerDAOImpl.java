package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
	Session session = sessionFactory.getCurrentSession();	
	Query<Customer> query = session.createQuery("from Customer"
			+ " order by lastName ASC", Customer.class);
	List<Customer> customers =  query.getResultList();
		return customers;
	}
	@Override
	public void saveCustomer(Customer customer) {
		//Get session for the current database state
		Session session = sessionFactory.getCurrentSession();
		//Use the current state and hibernate will save...
		
		//For update if the id comes in as whitespace it will create a new registery else it will update
		//given if the id is prepopulated...
		session.saveOrUpdate(customer);
	}
	
	@Override
	public Customer getCustomer(int theId) {
		//get hibernate current session.
		Session session =  sessionFactory.getCurrentSession();
		//now retreive/read from database using the primary key...
		return session.get(Customer.class, theId);
	}
	@Override
	public void deleteCustomer(int theId) {
		//get database current session.
		Session session = sessionFactory.getCurrentSession();
		//delete object trough id...
		Query theQuery = session.createQuery("DELETE FROM Customer where id=:customerId");
		theQuery.setParameter("customerId",theId);
		//works for updates and deletes
		theQuery.executeUpdate();
	}
}
