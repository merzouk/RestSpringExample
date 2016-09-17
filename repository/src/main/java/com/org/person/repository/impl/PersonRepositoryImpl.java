
package com.org.person.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.org.commons.ConstantesUtils;
import com.org.commons.Utils;
import com.org.person.contrat.PersonContrat;
import com.org.person.entity.PersonEntity;
import com.org.person.model.PersonModel;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : repository
 * @package : com.org.person.repository.impl
 * @date    : 16 sept. 2016 22:56:47
 */
@Repository("personContrat")
@Transactional
public class PersonRepositoryImpl implements PersonContrat<PersonModel>
{
   
   private static final Logger logger = LoggerFactory.getLogger( PersonRepositoryImpl.class );
   
   @PersistenceContext
   private EntityManager       entityManager;
   
   /**
    * 
    * @see com.org.contrat.ObjectContrat#findAll()
    */
   public List<PersonModel> findAll()
   {
      logger.debug( "findAll" );
      List<PersonEntity> list = null;
      try
      {
         list = entityManager.createNamedQuery( PersonEntity.QUERY_FIND_ALL, PersonEntity.class ).getResultList();
      }
      catch( Exception e )
      {
         logger.error( "Error during load list Person ", e );
      }
      return toListModel( list );
   }
   
   /**
    * 
    * @see com.org.contrat.ObjectContrat#findById(java.lang.Integer)
    */
   public PersonModel findById( Integer primaryKey )
   {
      logger.debug( "findById  {}", primaryKey );
      if( primaryKey == null || primaryKey.intValue() <= 0 )
      {
         return null;
      }
      PersonEntity entity = null;
      try
      {
         entity = (PersonEntity)entityManager.find( PersonEntity.class, primaryKey );
      }
      catch( Exception e )
      {
         logger.error( "Error during load  Person by id {} ", primaryKey, e );
      }
      return toModel( entity );
   }
   
   /**
   * 
   * @see com.org.person.contrat.PersonContrat#findByLastName(java.lang.String)
   */
   public List<PersonModel> findByLastName( String lastName )
   {
      logger.debug( "findByLastName {} ", lastName );
      if( lastName == null || lastName.trim().length() < 1 )
      {
         return new ArrayList<PersonModel>();
      }
      List<PersonEntity> entities = null;
      try
      {
         entities = entityManager.createNamedQuery( PersonEntity.QUERY_FIND_BY_PERSON_LASTNAME, PersonEntity.class ).setParameter( "lastName", lastName ).getResultList();
      }
      catch( Exception e )
      {
         logger.error( "Error during load Person by lastName {} ", lastName, e );
      }
      return toListModel( entities );
   }
   
   /**
    * 
    * @see com.org.person.contrat.PersonContrat#findByEmail(java.lang.String)
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
         obj = entityManager.createNamedQuery( PersonEntity.QUERY_FIND_BY_PERSON_EMAIL, PersonEntity.class ).setParameter( "email", email ).getSingleResult();
         return toModel( (PersonEntity) obj );
      }
      catch( NoResultException e )
      {
         logger.error( "Not found person by email {} ", email);
      }
      return null;
   }
   
   /**
   * 
   * @see com.org.person.contrat.PersonContrat#findByFirstNameAndLastName(java.lang.String, java.lang.String)
   */
   public List<PersonModel> findByFirstNameAndLastName( String firstName, String lastName )
   {
      logger.debug( "findByFirstNameAndLastName  {} {}", lastName, firstName );
      if( lastName == null || lastName.trim().length() < 1 )
      {
         return new ArrayList<PersonModel>();
      }
      List<PersonEntity> entities = null;
      try
      {
         entities = entityManager.createNamedQuery( PersonEntity.QUERY_FIND_BY_PERSON_LAST_FIRSTNAME, PersonEntity.class ).setParameter( "lastName", lastName ).setParameter( "firstName", firstName ).getResultList();
      }
      catch( Exception e )
      {
         logger.error( "Error during load  Person by lastName {} firstName {}  ", lastName, firstName, e );
      }
      return toListModel( entities );
   }
   
   /**
    * 
    * @see com.org.contrat.ObjectContrat#save(com.org.person.entity.PersonEntity)
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
            PersonEntity entity = toEntity( person );
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
    * @see com.org.contrat.ObjectContrat#update(com.org.person.entity.PersonEntity)
    */
   @Transactional
   public PersonModel update( PersonModel person )
   {
      logger.debug( "update {}", person.toString() );
      if( person != null && person.getPersonId() != null && person.getPersonId().intValue() > 0 )
      {
         try
         {
            PersonEntity entity = toEntity( person );
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
    * @see com.org.contrat.ObjectContrat#deleteById(long)
    */
   @Transactional
   public void deleteById( Integer primaryKey )
   {
      logger.debug( "deleteById {}", primaryKey );
      if( primaryKey == null || primaryKey.intValue() <= 0 )
      {
         return;
      }
      PersonEntity entity = entityManager.find( PersonEntity.class, primaryKey );
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
    * @see com.org.contrat.ObjectContrat#isExist(com.org.person.entity.PersonEntity)
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
    * @see com.org.contrat.ObjectContrat#deleteAll()
    */
   @Transactional
   public void deleteAll()
   {
      logger.debug( "deleteAll " );
      entityManager.createQuery( "delete from " + PersonEntity.class ).executeUpdate();
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
   private PersonModel toModel( PersonEntity entity )
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
   
   private List<PersonModel> toListModel( List<PersonEntity> entities )
   {
      if( entities == null || entities.isEmpty() || entities.size() == 0 )
      {
         return new ArrayList<PersonModel>();
      }
      List<PersonModel> list = new ArrayList<PersonModel>();
      for( PersonEntity entity : entities )
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
   private PersonEntity toEntity( PersonModel model )
   {
      if( model == null )
      {
         return new PersonEntity();
      }
      PersonEntity entity = new PersonEntity();
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
