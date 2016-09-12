
package com.org.person.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.org.person.dao.PersonDao;
import com.org.person.entity.PersonEntity;
import com.org.tools.ConstantesUtils;
import com.org.tools.Utils;

@Repository("personDao")
@Transactional
public class PersonDaoImpl implements PersonDao<PersonEntity>
{
   
   private static final Logger logger = LoggerFactory.getLogger( PersonDaoImpl.class );
   
   @PersistenceContext
   private EntityManager       entityManager;
   
   /**
    * 
    * @see com.org.dao.ObjectDao#findAll()
    */
   @SuppressWarnings("unchecked")
   public List<PersonEntity> findAll()
   {
      logger.debug( "findAll" );
      List<PersonEntity> list = null;
      try
      {
         list = entityManager.createQuery( "select p from Person p" ).getResultList();
      }
      catch( Exception e )
      {
         logger.error( "Error during load list Person ", e );
      }
      return list;
   }
   
   /**
    * 
    * @see com.org.dao.ObjectDao#findById(java.lang.Integer)
    */
   public PersonEntity findById( Integer primaryKey )
   {
      logger.debug( "findById  {}", primaryKey );
      if( primaryKey == null || primaryKey.intValue() <= 0 )
      {
         return null;
      }
      try
      {
         return entityManager.find( PersonEntity.class, primaryKey );
      }
      catch( Exception e )
      {
         logger.error( "Error during load  Person by id {} ", primaryKey, e );
      }
      return null;
   }
   
   /**
   * 
   * @see com.org.person.dao.PersonDao#findByLastName(java.lang.String)
   */
   @SuppressWarnings("unchecked")
   public List<PersonEntity> findByLastName( String lastName )
   {
      logger.debug( "findByLastName {} ", lastName );
      if( lastName == null || lastName.trim().length() < 1 )
      {
         return new ArrayList<PersonEntity>();
      }
      try
      {
         return entityManager.createQuery( "select p from Person p where p.lastName = :lastName" ).setParameter( "lastName", lastName ).getResultList();
      }
      catch( Exception e )
      {
         logger.error( "Error during load  Person by lastName {} ", lastName, e );
      }
      return null;
   }
   
   /**
    * 
    * @see com.org.person.dao.PersonDao#findByEmail(java.lang.String)
    */
   public PersonEntity findByEmail( String email )
   {
      logger.debug( "findByEmail  {}", email );
      if( email == null || email.trim().length() < 10 || !Utils.isValidEmailAddress( email ) )
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
         logger.error( "Eror during load person by email {} ", email, e );
      }
      if( obj != null )
      {
         return (PersonEntity) obj;
      }
      return null;
   }
   
   /**
   * 
   * @see com.org.person.dao.PersonDao#findByFirstNameAndLastName(java.lang.String, java.lang.String)
   */
   @SuppressWarnings("unchecked")
   public List<PersonEntity> findByFirstNameAndLastName( String firstName, String lastName )
   {
      logger.debug( "findByFirstNameAndLastName  {} {}", lastName, firstName );
      if( lastName == null || lastName.trim().length() < 1 )
      {
         return new ArrayList<PersonEntity>();
      }
      try
      {
         return entityManager.createQuery( "select p from Person p where p.lastName = :lastName and p.firstName = :firstName" ).setParameter( "lastName", lastName ).setParameter( "firstName", firstName ).getResultList();
      }
      catch( Exception e )
      {
         logger.error( "Error during load  Person by lastName {} firstName {}  ", lastName, firstName, e );
      }
      return null;
   }
   
   /**
    * 
    * @see com.org.dao.ObjectDao#save(com.org.person.entity.PersonEntity)
    */
   @Transactional
   public PersonEntity save( PersonEntity person )
   {
      logger.debug( "save {}", person.toString() );
      if( !validatePerson( person ) )
      {
         return null;
      }
      if( findByEmail( person.getEmail() ) == null )
      {
         try
         {
            entityManager.persist( person );
            return person;
         }
         catch( Exception e )
         {
            logger.error( "Error during save Person {}  ", person.toString(), e );
         }
      }
      return null;
   }
   
   /**
    * 
    * @see com.org.dao.ObjectDao#update(com.org.person.entity.PersonEntity)
    */
   @Transactional
   public PersonEntity update( PersonEntity person )
   {
      logger.debug( "update {}", person.toString() );
      if( person != null && person.getId() != null && person.getId().intValue() > 0 )
      {
         try
         {
            entityManager.merge( person );
            return person;
         }
         catch( Exception e )
         {
            logger.error( "Error during update Person {}  ", person.toString(), e );
         }
      }
      return null;
   }
   
   /**
    * 
    * @see com.org.dao.ObjectDao#deleteById(long)
    */
   @Transactional
   public void deleteById( Integer primaryKey )
   {
      logger.debug( "deleteById {}", primaryKey );
      if( primaryKey == null || primaryKey.intValue() <= 0 )
      {
         return;
      }
      PersonEntity entity = findById( primaryKey );
      if( entity != null )
      {
         try
         {
            entityManager.remove( entity );
         }
         catch( Exception e )
         {
            logger.error( "Error during delete Person by primaryKey {}  ", primaryKey, e );
         }
      }
   }
   
   /**
    * 
    * @see com.org.dao.ObjectDao#isExist(com.org.person.entity.PersonEntity)
    */
   public boolean isExist( PersonEntity person )
   {
      logger.debug( "isExist {}", person.toString() );
      try
      {
         return findByLastName( person.getLastName() ) != null;
      }
      catch( Exception e )
      {
         logger.error( "Error during search  Person primaryKey {}  ", person.toString(), e );
      }
      return false;
   }
   
   /**
    * 
    * @see com.org.dao.ObjectDao#deleteAll()
    */
   @Transactional
   public void deleteAll()
   {
      logger.debug( "deleteAll " );
      List<PersonEntity> list = findAll();
      if( list != null && list.size() > 0 )
      {
         for( PersonEntity person : list )
         {
            deleteById( person.getId() );
         }
      }
   }
   
   /**
    * 
    * @param person
    * @return
    */
   private boolean validatePerson( PersonEntity person )
   {
      logger.debug( "validatePerson" );
      if( person == null )
      {
         return false;
      }
      if( person.getEmail() == null || person.getEmail().trim().length() < ConstantesUtils.EMAIL_LENGTH_MIN || !Utils.isValidEmailAddress( person.getEmail() ) )
      {
         return false;
      }
      if( person.getLastName() == null || person.getLastName().trim().length() < ConstantesUtils.NAME_LENGTH_MIN )
      {
         return false;
      }
      if( person.getFirstName() == null || person.getFirstName().trim().length() < ConstantesUtils.NAME_LENGTH_MIN )
      {
         return false;
      }
      return true;
   }
}
