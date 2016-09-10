
package com.org.person.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.org.person.dao.PersonDao;
import com.org.person.model.Person;

@Repository("personDao")
@Transactional
public class PersonDaoImpl implements PersonDao
{
   
   @PersistenceContext
   private EntityManager entityManager;
   
   /**
    * 
    * @see com.org.person.dao.PersonDao#findAllPersons()
    */
   @SuppressWarnings("unchecked")
   public List<Person> findAllPersons()
   {
      List<Person> list = entityManager.createQuery( "select p from Person p" ).getResultList();
      return list;
   }
   
   /**
    * 
    * @see com.org.person.dao.PersonDao#findById(java.lang.Integer)
    */
   public Person findById( Integer primaryKey )
   {
      if( primaryKey == null || primaryKey.intValue() <= 0 )
      {
         return null;
      }
      return entityManager.find( Person.class, primaryKey );
   }
   
   /**
    * 
    * @see com.org.person.dao.PersonDao#findByLastName(java.lang.String)
    */
   @SuppressWarnings("unchecked")
   public List<Person> findByLastName( String lastName )
   {
      if( lastName == null || lastName.trim().length() < 1 )
      {
         return new ArrayList<Person>();
      }
      return entityManager.createQuery( "select p from Person p where p.lastName = :lastName" ).setParameter( "lastName", lastName ).getResultList();
   }
   
   /**
    * 
    * @see com.org.person.dao.PersonDao#findByEmail(java.lang.String)
    */
   public Person findByEmail( String email )
   {
      if( email == null || email.trim().length() < 10 || !isValidEmailAddress( email ) )
      {
         return null;
      }
      Object obj = null;
      try
      {
         obj = entityManager.createQuery( "select p from Person p where p.email = :email" ).setParameter( "email", email ).getSingleResult();
      }
      catch( Exception e )
      {
         // TODO: handle exception
      }
      if( obj != null )
      {
         return (Person) obj;
      }
      return null;
   }
   
   /**
    * 
    * @see com.org.person.dao.PersonDao#findByLastName(java.lang.String)
    */
   @SuppressWarnings("unchecked")
   public List<Person> findByFirstNameAndLastName( String lastName, String firstName )
   {
      if( lastName == null || lastName.trim().length() < 1 )
      {
         return new ArrayList<Person>();
      }
      return entityManager.createQuery( "select p from Person p where p.lastName = :lastName and p.firstName = :firstName" ).setParameter( "lastName", lastName ).setParameter( "firstName", firstName ).getResultList();
   }
   
   /**
    * 
    * @see com.org.person.dao.PersonDao#savePerson(com.org.person.model.Person)
    */
   @Transactional
   public Person savePerson( Person person )
   {
      if( !validatePerson( person ) )
      {
         return null;
      }
      if( findByEmail( person.getEmail() ) == null )
      {
         entityManager.persist( person );
         return person;
      }
      return null;
   }
   
   private boolean validatePerson( Person person )
   {
      if( person == null )
      {
         return false;
      }
      if( person.getEmail() == null || person.getEmail().trim().length() < 10 || !isValidEmailAddress( person.getEmail() ) )
      {
         return false;
      }
      if( person.getLastName() == null || person.getLastName().trim().length() < 1 )
      {
         return false;
      }
      if( person.getFirstName() == null || person.getFirstName().trim().length() < 1 )
      {
         return false;
      }
      return true;
   }
   
   private boolean isValidEmailAddress( String email )
   {
      String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
      java.util.regex.Pattern p = java.util.regex.Pattern.compile( ePattern );
      java.util.regex.Matcher m = p.matcher( email );
      return m.matches();
   }
   
   /**
    * 
    * @see com.org.person.dao.PersonDao#updatePerson(com.org.person.model.Person)
    */
   @Transactional
   public Person updatePerson( Person person )
   {
      if( person != null && person.getId() != null && person.getId().intValue() > 0 )
      {
         entityManager.merge( person );
      }
      return person;
   }
   
   /**
    * 
    * @see com.org.person.dao.PersonDao#deletePersonById(long)
    */
   @Transactional
   public void deletePersonById( Integer primaryKey )
   {
      
      if( primaryKey == null || primaryKey.intValue() <= 0 )
      {
         return;
      }
      Person entity = findById( primaryKey );
      if( entity != null )
      {
         entityManager.remove( entity );
      }
   }
   
   /**
    * 
    * @see com.org.person.dao.PersonDao#isPersonExist(com.org.person.model.Person)
    */
   public boolean isPersonExist( Person person )
   {
      return findByLastName( person.getLastName() ) != null;
   }
   
   /**
    * 
    * @see com.org.person.dao.PersonDao#deleteAllPersons()
    */
   @Transactional
   public void deleteAllPersons()
   {
      List<Person> list = findAllPersons();
      if( list != null && list.size() > 0 )
      {
         for( Person person : list )
         {
            deletePersonById( person.getId() );
         }
      }
   }
}
