
package com.org.contrat;

import java.util.List;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : contrat
 * @package : com.org.contrat
 * @date    : 16 sept. 2016 22:51:48
 */
public interface ObjectContrat<T> 
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
