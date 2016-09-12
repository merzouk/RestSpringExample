
package com.org.person.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.person.dao.PersonDao;
import com.org.person.entity.PersonEntity;
import com.org.person.service.PersonService;

@Service("personService")
public class PersonServiceImpl implements PersonService
{
   
   private static final Logger     logger = LoggerFactory.getLogger( PersonServiceImpl.class );
   
   @Autowired
   private PersonDao<PersonEntity> personDao;
   
   /**
    * 
    * @see com.org.person.service.PersonService#findAllPersons()
    */
   public List<PersonEntity> findAllPersons()
   {
      logger.debug( "findAllPersons" );
      return personDao.findAll();
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#findById(long)
    */
   public PersonEntity findById( Integer primaryKey )
   {
      logger.debug( "findById {} ", primaryKey );
      return personDao.findById( primaryKey );
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#findByFirstName(java.lang.String)
    */
   public List<PersonEntity> findByLastName( String lastName )
   {
      logger.debug( "findByLastName {} ", lastName );
      return personDao.findByLastName( lastName );
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#savePerson(com.org.person.entity.PersonEntity)
    */
   public PersonEntity savePerson( PersonEntity person )
   {
      logger.debug( "savePerson  {} ", person.toString() );
      return personDao.save( person );
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#updatePerson(com.org.person.entity.PersonEntity)
    */
   public PersonEntity updatePerson( PersonEntity person )
   {
      logger.debug( "updatePerson  {} ", person.toString() );
      return personDao.update( person );
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#deletePersonById(long)
    */
   public void deletePersonById( Integer primaryKey )
   {
      logger.debug( "deletePersonById {} ", primaryKey );
      personDao.deleteById( primaryKey );
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#isPersonExist(com.org.person.entity.PersonEntity)
    */
   public boolean isPersonExist( PersonEntity person )
   {
      logger.debug( "isPersonExist {} ", person.toString() );
      try
      {
         return ( personDao.findByEmail( person.getEmail() ) != null );
      }
      catch( Exception e )
      {
         logger.error( "Error during load Person by email  ", e );
      }
      return false;
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#deleteAllPersons()
    */
   public void deleteAllPersons()
   {
      logger.debug( "deleteAllPersons" );
      personDao.deleteAll();
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#findByFirstNameAndLastName(java.lang.String, java.lang.String)
    */
   public List<PersonEntity> findByFirstNameAndLastName( String firstName, String lastName )
   {
      logger.debug( "findByFirstNameAndLastName {} {} ", firstName, lastName );
      return personDao.findByFirstNameAndLastName( firstName, lastName );
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#findByEmail(java.lang.String)
    */
   public PersonEntity findByEmail( String email )
   {
      logger.debug( "findByEmail {} ", email );
      return personDao.findByEmail( email );
   }
}
