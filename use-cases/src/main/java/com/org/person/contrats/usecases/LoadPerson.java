/*
 *--------------------------------------------------------
 * admin
 *--------------------------------------------------------
 * Project     : repository
 *
 * Copyright admin.
 *
 *
 *-------------------------------------------------------- 
 * 
 * Fichier 		:	ListPerson.java
 * Cree le 		: 	17 sept. 2016 : 18:27:39
 * Auteur		: 	admin
 * 
 * Description 	:
 * 
 *---------------------------------------------------------
 */

package com.org.person.contrats.usecases;

import java.util.List;

import com.org.person.model.PersonModel;

/**
 * To inform.
 * 
 * @author  : admin
 * @project : repository
 * @package : com.org.person.contrats.usecases
 * @date    : 17 sept. 2016 18:27:39
 */
public interface LoadPerson
{
   /**
    * Load from data base list all persons
    * @return
    */
   List<PersonModel> listPerson();
   
   /**
    * load from data base person by primaryKey
    * 
    * @param primaryKey
    * @return
    */
   PersonModel personByPrimaryKey( Integer primaryKey );
   
   
   /**
    * 
    * @param lastName
    * @return
    */
   List<PersonModel> findPersonByLastName( String lastName );
   
   /**
    * 
    * @param email
    * @return
    */
   PersonModel findPersonByEmail( String email );
   
   /**
    * 
    * @param lastName
    * @param firstName
    * @return
    */
   List<PersonModel> findPersonByFirstNameAndLastName( String firstName, String lastName );
}
