
package com.org.person.service;

import java.util.List;


/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : person
 * @package : com.org.service
 * @date    : 10 sept. 2016 09:18:48
 */
public interface Services<T>
{
   /**
    * 
    * @param id
    * @return
    */
   T findById( Integer primaryKey );
   
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
   List<T> findByFirstNameAndLastName( String firstName, String lastName );
   
   /**
    * 
    * @param person
    */
   T save( T t );
   
   /**
    * 
    * @param person
    */
   T update( T t );
   
   /**
    * 
    * @param id
    */
   void deleteById( Integer primaryKey );
   
   /**
    * 
    * @return
    */
   List<T> findAlls();
   
   /**
    * 
    */
   void deleteAlls();
   
   /**
    * 
    * @param person
    * @return
    */
   public boolean isExist( T t );
}
