/*
 *--------------------------------------------------------
 * admin
 *--------------------------------------------------------
 * Project     : use-cases
 *
 * Copyright admin.
 *
 *
 *-------------------------------------------------------- 
 * 
 * Fichier 		:	DeletePerson.java
 * Cree le 		: 	17 sept. 2016 : 19:29:19
 * Auteur		: 	admin
 * 
 * Description 	:
 * 
 *---------------------------------------------------------
 */

package com.org.person.contrats.usecases;

import com.org.person.model.PersonModel;

/**
 * To inform.
 * 
 * @author  : admin
 * @project : use-cases
 * @package : com.org.person.contrats.usecases
 * @date    : 17 sept. 2016 19:29:19
 */
public interface DeletePerson
{
   /**
    * deleting person
    * @param model
    */
   void deletePerson( PersonModel model );
   
   /**
    * deleting person by primakey
    * @param primaryKey
    */
   void deletePersonById( Integer primaryKey );
}
