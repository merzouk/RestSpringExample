
package com.org.person.service;

import java.util.List;

import com.org.person.entity.PersonEntity;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : person
 * @package : com.org.service
 * @date    : 10 sept. 2016 09:18:48
 */
public interface PersonService
{
   /**
    * 
    * @param id
    * @return
    */
   PersonEntity findById( Integer primaryKey );
   
   /**
    * 
    * @param lastName
    * @return
    */
   List<PersonEntity> findByLastName( String lastName );
   
   /**
    * 
    * @param email
    * @return
    */
   PersonEntity findByEmail( String email );
   
   /**
    * 
    * @param lastName
    * @param firstName
    * @return
    */
   List<PersonEntity> findByFirstNameAndLastName(  String firstName, String lastName );
   
   /**
    * 
    * @param person
    */
   PersonEntity savePerson( PersonEntity person );
   
   /**
    * 
    * @param person
    */
   PersonEntity updatePerson( PersonEntity person );
   
   /**
    * 
    * @param id
    */
   void deletePersonById( Integer primaryKey );
   
   /**
    * 
    * @return
    */
   List<PersonEntity> findAllPersons();
   
   /**
    * 
    */
   void deleteAllPersons();
   
   /**
    * 
    * @param person
    * @return
    */
   public boolean isPersonExist( PersonEntity person );
}
