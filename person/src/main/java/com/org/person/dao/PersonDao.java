
package com.org.person.dao;

import java.util.List;

import com.org.person.model.Person;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : person
 * @package : com.org.person.dao
 * @date    : 10 sept. 2016 09:32:32
 */
public interface PersonDao
{
   /**
    * 
    * @param primaryKey
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
    * @return
    */
   Person savePerson( Person person );
   
   /**
    * 
    * @param person
    * @return
    */
   Person updatePerson( Person person );
   
   /**
   * 
   * @param primaryKey
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
   boolean isPersonExist( Person person );
}
