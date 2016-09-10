
package com.org.person.service;

import java.util.List;

import com.org.person.model.Person;

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
   Person findById( Integer primaryKey );
   
   /**
    * 
    * @param lastName
    * @return
    */
   List<Person> findByLastName( String lastName );
   
   /**
    * 
    * @param email
    * @return
    */
   Person findByEmail( String email );
   
   /**
    * 
    * @param lastName
    * @param firstName
    * @return
    */
   List<Person> findByFirstNameAndLastName( String lastName, String firstName );
   
   /**
    * 
    * @param person
    */
   Person savePerson( Person person );
   
   /**
    * 
    * @param person
    */
   Person updatePerson( Person person );
   
   /**
    * 
    * @param id
    */
   void deletePersonById( Integer primaryKey );
   
   /**
    * 
    * @return
    */
   List<Person> findAllPersons();
   
   /**
    * 
    */
   void deleteAllPersons();
   
   /**
    * 
    * @param person
    * @return
    */
   public boolean isPersonExist( Person person );
}
