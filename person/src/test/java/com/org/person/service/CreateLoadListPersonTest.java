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
 * Fichier 		:	ListPersonTest.java
 * Cree le 		: 	17 sept. 2016 : 18:34:58
 * Auteur		: 	admin
 * 
 * Description 	:
 * 
 *---------------------------------------------------------
 */

package com.org.person.service;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.org.person.contrats.usecases.CreateOrUpdatePerson;
import com.org.person.contrats.usecases.DeletePerson;
import com.org.person.contrats.usecases.LoadPerson;
import com.org.person.model.PersonModel;

/**
 * To inform.
 * 
 * @author  : admin
 * @project : use-cases
 * @package : com.org.person.contrats.usecases
 * @date    : 17 sept. 2016 18:34:58
 */
@ContextConfiguration("/test-person-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateLoadListPersonTest
{
   
   private static final Logger  logger = LoggerFactory.getLogger( CreateLoadListPersonTest.class );
   
   private static final int     LENGTH = 10;
   
   @Autowired
   private LoadPerson           listPerson;
   
   @Autowired
   private CreateOrUpdatePerson createOrUpdate;
   
   @Autowired
   private DeletePerson         deletePerson;
   
   @Before
   public void init()
   {
      logger.info( "init" );
      try
      {
         Assert.assertNotNull( listPerson );
      }
      catch( Exception e )
      {
         logger.info( "Error ", e );
      }
   }
   
   @After
   public void destroy()
   {
      logger.info( "destroy" );
      try
      {
         
      }
      catch( Exception e )
      {
         logger.info( "Error ", e );
      }
   }
   
   private PersonModel buildPersonModel()
   {
      PersonModel model = new PersonModel( null, generateStringRandom( LENGTH ), generateStringRandom( LENGTH ), generateStringRandom( LENGTH ) + "@courriel.com" );
      return model;
   }
   
   @Test
   public void test_1()
   {
      PersonModel p1 = buildPersonModel();
      p1 = createOrUpdate.createPerson( p1 );
      Assert.assertNotNull( p1.getPersonId() );
      Integer primaryKey = 1;
      Assert.assertEquals( primaryKey, p1.getPersonId() );
      /**
       * 
       */
      p1 = buildPersonModel();
      p1 = createOrUpdate.createPerson( p1 );
      Assert.assertNotNull( p1.getPersonId() );
      primaryKey = 2;
      Assert.assertEquals( primaryKey, p1.getPersonId() );
      /**
       * 
       */
      p1 = buildPersonModel();
      p1 = createOrUpdate.createPerson( p1 );
      Assert.assertNotNull( p1.getPersonId() );
      primaryKey = 3;
      Assert.assertEquals( primaryKey, p1.getPersonId() );
      /**
       * 
       */
      p1 = buildPersonModel();
      p1 = createOrUpdate.createPerson( p1 );
      Assert.assertNotNull( p1.getPersonId() );
      primaryKey = 4;
      Assert.assertEquals( primaryKey, p1.getPersonId() );
      /**
       * 
       */
      primaryKey = p1.getPersonId();
      List<PersonModel> list = listPerson.listPerson();
      Assert.assertNotNull( list );
      Assert.assertEquals( 4, list.size() );
      deletePerson.deletePersonById( primaryKey );
      /**
       * 
       */
      PersonModel deleting = listPerson.personByPrimaryKey( primaryKey );
      Assert.assertEquals( null, deleting.getCourriel() );
      Assert.assertEquals( null, deleting.getNom() );
      Assert.assertEquals( null, deleting.getPrenom() );
      list = listPerson.listPerson();
      Assert.assertNotNull( list );
      Assert.assertEquals( 3, list.size() );
      /**
       * 
       */
      primaryKey = 3;
      PersonModel personToUpdating = listPerson.personByPrimaryKey( primaryKey );
      Assert.assertNotNull( personToUpdating.getPersonId() );
      Assert.assertEquals( primaryKey, personToUpdating.getPersonId() );
      String nom = generateStringRandom( 10 );
      String prenom = generateStringRandom( 10 );
      personToUpdating.setNom( nom );
      personToUpdating.setPrenom( prenom );
      createOrUpdate.updatePerson( personToUpdating );
      /**
       * 
       */
      PersonModel personBuffer = listPerson.personByPrimaryKey( primaryKey );
      Assert.assertNotNull( p1.getPersonId() );
      Assert.assertEquals( primaryKey, personBuffer.getPersonId() );
      Assert.assertEquals( nom, personBuffer.getNom() );
      Assert.assertEquals( prenom, personBuffer.getPrenom() );
      /**
       * 
       */
      List<PersonModel> personList = listPerson.findPersonByLastName( nom );
      Assert.assertEquals( 1, personList.size() );
      p1 = personList.get( 0 );
      Assert.assertNotNull( p1.getPersonId() );
      Assert.assertEquals( primaryKey, personBuffer.getPersonId() );
      Assert.assertEquals( nom, personBuffer.getNom() );
      Assert.assertEquals( prenom, personBuffer.getPrenom() );
      /**
       * 
       */
      personList = listPerson.findPersonByFirstNameAndLastName( prenom, nom );
      Assert.assertEquals( 1, personList.size() );
      p1 = personList.get( 0 );
      Assert.assertNotNull( p1.getPersonId() );
      Assert.assertEquals( primaryKey, personBuffer.getPersonId() );
      Assert.assertEquals( nom, personBuffer.getNom() );
      Assert.assertEquals( prenom, personBuffer.getPrenom() );
   }
   
   private String generateStringRandom( int length )
   {
      if( length == 0 )
      {
         length = 15;
      }
      char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
      StringBuilder sb = new StringBuilder();
      Random random = new Random();
      for( int i = 0; i < length; i++ )
      {
         char c = chars[random.nextInt( chars.length )];
         sb.append( c );
      }
      return sb.toString();
   }
}
