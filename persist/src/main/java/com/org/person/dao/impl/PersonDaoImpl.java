
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
import com.org.person.entity.Person;
import com.org.person.model.PersonModel;
import com.org.tools.ConstantesUtils;
import com.org.tools.Utils;

@Repository("personDao")
@Transactional
public class PersonDaoImpl implements PersonDao<PersonModel>
{
   
   private static final Logger logger = LoggerFactory.getLogger( PersonDaoImpl.class );
   
   @PersistenceContext
   private EntityManager       entityManager;
   
   /**
    * 
    * @see com.org.dao.ObjectDao#findAll()
    */
   @SuppressWarnings("unchecked")
   public List<PersonModel> findAll()
   {
      logger.debug( "findAll" );
      List<Person> list = null;
      try
      {
         list = entityManager.createQuery( "select p from Person p" ).getResultList();
      }
      catch( Exception e )
      {
         logger.error( "Error during load list Person ", e );
      }
      return toListModel( list );
   }
   
   /**
    * 
    * @see com.org.dao.ObjectDao#findById(java.lang.Integer)
    */
   public PersonModel findById( Integer primaryKey )
   {
      logger.debug( "findById  {}", primaryKey );
      if( primaryKey == null || primaryKey.intValue() <= 0 )
      {
         return null;
      }
      Person entity = null;
      try
      {
         entity = entityManager.find( Person.class, primaryKey );
      }
      catch( Exception e )
      {
         logger.error( "Error during load  Person by id {} ", primaryKey, e );
      }
      return toModel( entity );
   }
   
   /**
   * 
   * @see com.org.person.dao.PersonDao#findByLastName(java.lang.String)
   */
   @SuppressWarnings("unchecked")
   public List<PersonModel> findByLastName( String lastName )
   {
      logger.debug( "findByLastName {} ", lastName );
      if( lastName == null || lastName.trim().length() < 1 )
      {
         return new ArrayList<PersonModel>();
      }
      List<Person> entities = null;
      try
      {
         entities = entityManager.createQuery( "select p from Person p where p.lastName = :lastName" ).setParameter( "lastName", lastName ).getResultList();
      }
      catch( Exception e )
      {
         logger.error( "Error during load  Person by lastName {} ", lastName, e );
      }
      return toListModel( entities );
   }
   
   /**
    * 
    * @see com.org.person.dao.PersonDao#findByEmail(java.lang.String)
    */
   public PersonModel findByEmail( String email )
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
         return toModel( (Person) obj );
      }
      catch( Exception e )
      {
         logger.error( "Eror during load person by email {} ", email, e );
      }
      return null;
   }
   
   /**
   * 
   * @see com.org.person.dao.PersonDao#findByFirstNameAndLastName(java.lang.String, java.lang.String)
   */
   @SuppressWarnings("unchecked")
   public List<PersonModel> findByFirstNameAndLastName( String firstName, String lastName )
   {
      logger.debug( "findByFirstNameAndLastName  {} {}", lastName, firstName );
      if( lastName == null || lastName.trim().length() < 1 )
      {
         return new ArrayList<PersonModel>();
      }
      List<Person> entities = null;
      try
      {
         entities = entityManager.createQuery( "select p from Person p where p.lastName = :lastName and p.firstName = :firstName" ).setParameter( "lastName", lastName ).setParameter( "firstName", firstName ).getResultList();
      }
      catch( Exception e )
      {
         logger.error( "Error during load  Person by lastName {} firstName {}  ", lastName, firstName, e );
      }
      return toListModel( entities );
   }
   
   /**
    * 
    * @see com.org.dao.ObjectDao#save(com.org.person.entity.Person)
    */
   @Transactional
   public PersonModel save( PersonModel person )
   {
      logger.debug( "save {}", person.toString() );
      if( !validatePerson( person ) )
      {
         return null;
      }
      if( findByEmail( person.getCourriel() ) == null )
      {
         try
         {
            Person entity = toEntity( person );
            entityManager.persist( entity );
            return toModel( entity );
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
    * @see com.org.dao.ObjectDao#update(com.org.person.entity.Person)
    */
   @Transactional
   public PersonModel update( PersonModel person )
   {
      logger.debug( "update {}", person.toString() );
      if( person != null && person.getPersonId() != null && person.getPersonId().intValue() > 0 )
      {
         try
         {
            Person entity = toEntity( person );
            entityManager.merge( entity );
            return toModel( entity );
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
      Person entity = entityManager.find( Person.class, primaryKey );
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
    * @see com.org.dao.ObjectDao#isExist(com.org.person.entity.Person)
    */
   public boolean isExist( PersonModel person )
   {
      logger.debug( "isExist {}", person.toString() );
      try
      {
         return findByLastName( person.getNom() ) != null;
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
      entityManager.createQuery( "delete from " + Person.class ).executeUpdate();
   }
   
   /**
    * 
    * @param person
    * @return
    */
   private boolean validatePerson( PersonModel person )
   {
      logger.debug( "validatePerson" );
      if( person == null )
      {
         return false;
      }
      if( person.getCourriel() == null || person.getCourriel().trim().length() < ConstantesUtils.EMAIL_LENGTH_MIN || !Utils.isValidEmailAddress( person.getCourriel() ) )
      {
         return false;
      }
      if( person.getNom() == null || person.getNom().trim().length() < ConstantesUtils.NAME_LENGTH_MIN )
      {
         return false;
      }
      if( person.getPrenom() == null || person.getPrenom().trim().length() < ConstantesUtils.NAME_LENGTH_MIN )
      {
         return false;
      }
      return true;
   }
   
   /**
    * 
    * @param entity
    * @return
    */
   private PersonModel toModel( Person entity )
   {
      
      if( entity == null )
      {
         return new PersonModel();
      }
      PersonModel model = new PersonModel();
      model.setCourriel( entity.getEmail() );
      model.setNom( entity.getLastName() );
      model.setPersonId( entity.getId() );
      model.setPrenom( entity.getFirstName() );
      return model;
   }
   
   private List<PersonModel> toListModel( List<Person> entities )
   {
      if( entities == null || entities.isEmpty() || entities.size() == 0 )
      {
         return new ArrayList<PersonModel>();
      }
      List<PersonModel> list = new ArrayList<PersonModel>();
      for( Person entity : entities )
      {
         list.add( toModel( entity ) );
      }
      return list;
   }
   
   /**
    * 
    * @param model
    * @return
    */
   private Person toEntity( PersonModel model )
   {
      if( model == null )
      {
         return new Person();
      }
      Person entity = new Person();
      entity.setEmail( model.getCourriel() );
      entity.setFirstName( model.getPrenom() );
      if( model.getPersonId() != null && model.getPersonId().intValue() > 0 )
      {
         entity.setId( model.getPersonId() );
      }
      else
      {
         entity.setId( 0 );
      }
      entity.setLastName( model.getNom() );
      return entity;
   }
}
