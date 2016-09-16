
package com.org.person.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.person.contrat.PersonContrat;
import com.org.person.model.PersonModel;
import com.org.person.service.Services;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : service
 * @package : com.org.person.service.impl
 * @date    : 16 sept. 2016 22:53:33
 */
@Service("personService")
public class PersonServiceImpl implements Services<PersonModel>
{
   
   private static final Logger        logger = LoggerFactory.getLogger( PersonServiceImpl.class );
   
   @Autowired
   private PersonContrat<PersonModel> personContrat;
   
   /**
    * 
    * @see com.org.person.service.Services#findAllPersons()
    */
   public List<PersonModel> findAlls()
   {
      logger.debug( "findAllPersons" );
      return personContrat.findAll();
   }
   
   /**
    * 
    * @see com.org.person.service.Services#findById(long)
    */
   public PersonModel findById( Integer primaryKey )
   {
      logger.debug( "findById {} ", primaryKey );
      return personContrat.findById( primaryKey );
   }
   
   /**
    * 
    * @see com.org.person.service.Services#findByFirstName(java.lang.String)
    */
   public List<PersonModel> findByLastName( String lastName )
   {
      logger.debug( "findByLastName {} ", lastName );
      return personContrat.findByLastName( lastName );
   }
   
   /**
    * 
    * @see com.org.person.service.Services#savePerson(com.org.person.entity.Person)
    */
   public PersonModel save( PersonModel person )
   {
      logger.debug( "savePersonModel  {} ", person.toString() );
      return personContrat.save( person );
   }
   
   /**
    * 
    * @see com.org.person.service.Services#updatePerson(com.org.person.entity.Person)
    */
   public PersonModel update( PersonModel person )
   {
      logger.debug( "updatePerson  {} ", person.toString() );
      return personContrat.update( person );
   }
   
   /**
    * 
    * @see com.org.person.service.Services#deletePersonById(long)
    */
   public void deleteById( Integer primaryKey )
   {
      logger.debug( "deletePersonById {} ", primaryKey );
      personContrat.deleteById( primaryKey );
   }
   
   /**
    * 
    * @see com.org.person.service.Services#isPersonExist(com.org.person.entity.Person)
    */
   public boolean isExist( PersonModel person )
   {
      logger.debug( "isPersonExist {} ", person.toString() );
      try
      {
         return ( personContrat.findByEmail( person.getCourriel() ) != null );
      }
      catch( Exception e )
      {
         logger.error( "Error during load Person by email  ", e );
      }
      return false;
   }
   
   /**
    * 
    * @see com.org.person.service.Services#deleteAllPersons()
    */
   public void deleteAlls()
   {
      logger.debug( "deleteAllPersons" );
      personContrat.deleteAll();
   }
   
   /**
    * 
    * @see com.org.person.service.Services#findByFirstNameAndLastName(java.lang.String, java.lang.String)
    */
   public List<PersonModel> findByFirstNameAndLastName( String firstName, String lastName )
   {
      logger.debug( "findByFirstNameAndLastName {} {} ", firstName, lastName );
      return personContrat.findByFirstNameAndLastName( firstName, lastName );
   }
   
   /**
    * 
    * @see com.org.person.service.Services#findByEmail(java.lang.String)
    */
   public PersonModel findByEmail( String email )
   {
      logger.debug( "findByEmail {} ", email );
      return personContrat.findByEmail( email );
   }
}
