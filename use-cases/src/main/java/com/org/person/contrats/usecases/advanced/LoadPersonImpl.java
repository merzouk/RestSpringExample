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
 * Fichier 		:	ListPersonImpl.java
 * Cree le 		: 	17 sept. 2016 : 18:29:35
 * Auteur		: 	admin
 * 
 * Description 	:
 * 
 *---------------------------------------------------------
 */

package com.org.person.contrats.usecases.advanced;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.person.contrat.PersonContrat;
import com.org.person.contrats.usecases.LoadPerson;
import com.org.person.model.PersonModel;

/**
 * To inform.
 * 
 * @author  : admin
 * @project : repository
 * @package : com.org.person.contrats.usecases.advanced
 * @date    : 17 sept. 2016 18:29:35
 */
@Service("loadPerson")
public class LoadPersonImpl implements LoadPerson
{
   
   private static final Logger        logger = LoggerFactory.getLogger( LoadPersonImpl.class );
   
   @Autowired
   private PersonContrat<PersonModel> personContrat;
   
   /**
    * 
    * @see com.org.person.contrats.usecases.LoadPerson#listPerson()
    */
   @Override
   public List<PersonModel> listPerson()
   {
      logger.info( "list Person" );
      return personContrat.findAll();
   }
   
   /**
    * 
    * @see com.org.person.contrats.usecases.LoadPerson#personByPrimaryKey(java.lang.Integer)
    */
   @Override
   public PersonModel personByPrimaryKey( Integer primaryKey )
   {
      logger.info( "personByPrimaryKey {} ", primaryKey );
      return personContrat.findById( primaryKey );
   }
   
   /**
    * 
    * @see com.org.person.contrats.usecases.LoadPerson#findPersonByLastName(java.lang.String)
    */
   @Override
   public List<PersonModel> findPersonByLastName( String lastName )
   {
      return personContrat.findByLastName( lastName );
   }
   
   /**
    * 
    * @see com.org.person.contrats.usecases.LoadPerson#findPersonByEmail(java.lang.String)
    */
   @Override
   public PersonModel findPersonByEmail( String email )
   {
      return personContrat.findByEmail( email );
   }
   
   /**
    * 
    * @see com.org.person.contrats.usecases.LoadPerson#findPersonByFirstNameAndLastName(java.lang.String, java.lang.String)
    */
   @Override
   public List<PersonModel> findPersonByFirstNameAndLastName( String firstName, String lastName )
   {
      return personContrat.findByFirstNameAndLastName( firstName, lastName );
   }
}
