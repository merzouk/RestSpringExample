
package com.org.person.contrat;

import java.util.List;

import com.org.contrat.ObjectContrat;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : contrat
 * @package : com.org.person.contrat
 * @date    : 16 sept. 2016 22:51:57
 */
public interface PersonContrat<T> extends ObjectContrat<T>
{
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
}
