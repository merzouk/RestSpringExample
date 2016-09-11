
package com.org.dao;

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
public interface ObjectDao<T>
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
   List<T> findByLastName( String lastName );
   
   /**
    * 
    * @param email
    * @return
    */
   T findByEmail( String email );
   
   /**
    * 
    * @param lastName
    * @param firstName
    * @return
    */
   List<T> findByFirstNameAndLastName( String lastName, String firstName );
   
   /**
    * 
    * @param person
    * @return
    */
   T save( Person person );
   
   /**
    * 
    * @param person
    * @return
    */
   T update( T person );
   
   /**
   * 
   * @param primaryKey
   */
   void deleteById( Integer primaryKey );
   
   /**
    * 
    * @return
    */
   List<T> findAll();
   
   /**
    * 
    */
   void deleteAll();
   
   /**
    * 
    * @param person
    * @return
    */
   boolean isExist( T person );
}
