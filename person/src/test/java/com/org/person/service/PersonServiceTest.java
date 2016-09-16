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

import com.org.person.model.PersonModel;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : person
 * @package : com.org.person.service
 * @date    : 16 sept. 2016 22:54:40
 */
@ContextConfiguration("/test-person-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonServiceTest
{
   
   private static final Logger logger = LoggerFactory.getLogger( PersonServiceTest.class );
   
   private static final int    LENGTH = 10;
   
   @Autowired
   private Services<PersonModel>       personService;
      
   @Before
   public void init()
   {
      logger.info( "init" );
      try
      {
         
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
   
   @Test
   public void test_1()
   {
      Integer primaryKey = 1;
      PersonModel p = new PersonModel();
      String email = generateStringRandom( LENGTH );
      String firstName = generateStringRandom( LENGTH );
      p.setPrenom( firstName );
      String lastName = generateStringRandom( LENGTH );
      p.setNom( lastName );
      p.setCourriel(  email + "@email.com" );
      /**
       * 
       */
      logger.info( "save test" );
      p = personService.save( p );
      Integer id = p.getPersonId();
      Assert.assertNotNull( id );
      Assert.assertEquals( primaryKey, id );
      Assert.assertEquals( email + "@email.com", p.getCourriel() );
      /**
       * 
       */
      List<PersonModel> list = personService.findByLastName( lastName );
      Assert.assertEquals( 1, list.size() );
      p = list.get( 0 );
      Assert.assertNotNull( id );
      Assert.assertEquals( primaryKey, id );
      Assert.assertEquals( email + "@email.com", p.getCourriel() );
      Assert.assertEquals( lastName, p.getNom() );
      Assert.assertEquals( firstName, p.getPrenom() );
      /**
       * 
       */
      p = personService.findByEmail( email + "@email.com" );
      Assert.assertNotNull( p );
      Assert.assertNotNull( p.getPersonId() );
      Assert.assertEquals( primaryKey, p.getPersonId() );
      Assert.assertEquals( email + "@email.com", p.getCourriel() );
      Assert.assertEquals( lastName, p.getNom() );
      Assert.assertEquals( firstName, p.getPrenom() );
      /**
       * 
       */
      list = personService.findByFirstNameAndLastName(firstName, lastName  );
      Assert.assertEquals( 1, list.size() );
      p = list.get( 0 );
      Assert.assertNotNull( p.getPersonId() );
      primaryKey = 1;
      Assert.assertEquals( primaryKey, id );
      Assert.assertEquals( email + "@email.com", p.getCourriel() );
      Assert.assertEquals( lastName, p.getNom() );
      Assert.assertEquals( firstName, p.getPrenom() );
      /**
      * 
      */
      logger.info( "get by id test" );
      p = personService.findById( primaryKey );
      Assert.assertNotNull( "Person not found!", p );
      Assert.assertEquals( primaryKey, p.getPersonId() );
      Assert.assertEquals( email + "@email.com", p.getCourriel() );
      Assert.assertEquals( lastName, p.getNom() );
      Assert.assertEquals( firstName, p.getPrenom() );
      /**
       * 
       */
      email = email + "_1@email.com";
      p.setCourriel(  email );
      personService.update( p );
      /**
       * 
       */
      logger.info( "get by id test" );
      p = personService.findById( primaryKey );
      Assert.assertNotNull( "Person not found!", p );
      Assert.assertEquals( primaryKey, p.getPersonId() );
      Assert.assertEquals( email, p.getCourriel() );
      Assert.assertEquals( lastName, p.getNom() );
      Assert.assertEquals( firstName, p.getPrenom() );
      /**
       * 
       */
      logger.info( "load list test" );
      list = personService.findAlls();
      Assert.assertEquals( 1, list.size() );
      /**
       * 
       */
      p = new PersonModel();
      firstName = generateStringRandom( LENGTH );
      p.setPrenom( firstName );
      lastName = generateStringRandom( LENGTH );
      p.setNom( lastName );
      p.setCourriel(email );
      p = personService.save( p );
      Assert.assertNull( p );
      /**
       * 
       */
      logger.info( "load list test" );
      list = personService.findAlls();
      Assert.assertEquals( 1, list.size() );
      /**
       * 
       */
      personService.deleteById( 1 );
      /**
       * 
       */
      logger.info( "load list test" );
      list = personService.findAlls();
      Assert.assertEquals( 0, list.size() );
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
