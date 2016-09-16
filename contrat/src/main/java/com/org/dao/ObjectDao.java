
package com.org.dao;

import java.util.List;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : person
 * @package : com.org.dao
 * @date    : 10 sept. 2016 09:32:32
 */
public interface ObjectDao<T> 
{
   /**
    * 
    * @param primaryKey
    * @return
    */
   T findById( Integer primaryKey );
   
   /**
    * 
    * @param t
    * @return
    */
   T save( T t );
   
   /**
    * 
    * @param t
    * @return
    */
   T update( T t );
   
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
    * @param t
    * @return
    */
   boolean isExist( T t );
}
