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
 * Fichier 		:	DeletePersonImpl.java
 * Cree le 		: 	17 sept. 2016 : 19:31:09
 * Auteur		: 	admin
 * 
 * Description 	:
 * 
 *---------------------------------------------------------
 */

package com.org.person.contrats.usecases.advanced;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.person.contrat.PersonContrat;
import com.org.person.contrats.usecases.DeletePerson;
import com.org.person.model.PersonModel;

/**
 * To inform.
 * 
 * @author  : admin
 * @project : use-cases
 * @package : com.org.person.contrats.usecases.advanced
 * @date    : 17 sept. 2016 19:31:09
 */
@Service("deletePerson")
public class DeletePersonImpl implements DeletePerson
{
   
   private static final Logger        logger = LoggerFactory.getLogger( DeletePersonImpl.class );
   
   @Autowired
   private PersonContrat<PersonModel> personContrat;
   
   /**
    * @see com.org.person.contrats.usecases.DeletePerson#deletePerson(com.org.person.model.PersonModel)
    */
   @Override
   public void deletePerson( PersonModel model )
   {
      logger.info( "deletePerson" );
      if( model != null && model.getPersonId() != null && model.getPersonId().intValue() > 0 )
      {
         deletePersonById( model.getPersonId() );
      }
   }
   
   /**
    * @see com.org.person.contrats.usecases.DeletePerson#deletePersonById(java.lang.Integer)
    */
   @Override
   public void deletePersonById( Integer primaryKey )
   {
      logger.info( "deletePersonById" );
      personContrat.deleteById( primaryKey );
   }
}
