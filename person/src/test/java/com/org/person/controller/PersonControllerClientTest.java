
package com.org.person.controller;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.org.exception.PersonException;
import com.org.person.model.PersonModel;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : person
 * @package : com.org.person.controller
 * @date    : 16 sept. 2016 22:54:02
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonControllerClientTest
{
   
   private static final Logger logger           = LoggerFactory.getLogger( PersonControllerClientTest.class );
   
   public static final String  REST_SERVICE_URI = "http://localhost:8585/person/gestionPersonnel/";
   
   private static final int    LENGTH           = 10;
   
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
   
   /**
    * GET
    */
   @SuppressWarnings("unchecked")
   private static int listAllPersons()
   {
      logger.info( "Testing listAllPersons API" );
      RestTemplate restTemplate = new RestTemplate();
      List<LinkedHashMap<String, Object>> personsMap = restTemplate.getForObject( REST_SERVICE_URI + "/persons/", List.class );
      int counter = 0;
      if( personsMap != null )
      {
         for( LinkedHashMap<String, Object> map : personsMap )
         {
            logger.info( "person " + map.toString() );
            counter++;
         }
      }
      else
      {
         logger.info( "No person found" );
      }
      return counter;
   }
   
   /**
    * GET
    */
   private PersonModel getPerson( Integer primaryKey ) throws PersonException
   {
      logger.info( "Testing getPerson API" );
      RestTemplate restTemplate = new RestTemplate();
      PersonModel person = restTemplate.getForObject( REST_SERVICE_URI + "/getPersonById/" + primaryKey, PersonModel.class );
      if( person == null )
      {
         throw new PersonException( "Not load Person by id = " + primaryKey );
      }
      Assert.assertNotNull( person );
      return person;
   }
   
   /**
    * POST
    */
   private static void createPerson()
   {
      logger.info( "Testing create Person API" );
      RestTemplate restTemplate = new RestTemplate();
      PersonModel person = new PersonModel( null, generateStringRandom( LENGTH ), generateStringRandom( LENGTH ), generateStringRandom( LENGTH ) + "@courriel.com" );
      URI uri = restTemplate.postForLocation( REST_SERVICE_URI + "/createPerson/", person, PersonModel.class );
      if( uri != null )
      {
         logger.info( "Location : " + uri.toASCIIString() );
      }
   }
   
   private static String generateStringRandom( int length )
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
   
   /**
    *  PUT
    */
   private static void updatePerson( Integer primaryKey )
   {
      logger.info( "Testing update Person API" );
      RestTemplate restTemplate = new RestTemplate();
      PersonModel person = new PersonModel( null, generateStringRandom( LENGTH ), generateStringRandom( LENGTH ), generateStringRandom( LENGTH ) + "@courriel.com" );
      restTemplate.put( REST_SERVICE_URI + "/updatePerson/" + primaryKey, person );
      logger.info( "" + person.toString() );
   }
   
   /**
    *  
    *  DELETE
    */
   private static void deletePerson( Integer primaryKey )
   {
      logger.info( "Testing delete Person API" );
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.delete( REST_SERVICE_URI + "/deletePersonById/" + primaryKey );
   }
   
   /**
    *  
    *  DELETE 
    */
   @SuppressWarnings("unused")
   private static void deleteAllPersons()
   {
      logger.info( "Testing all delete Persons API" );
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.delete( REST_SERVICE_URI + "/deleteAllpersons/" );
   }
   
   @Test
   public void test_1() throws PersonException
   {
      createPerson();
      getPerson( 1 );
      createPerson();
      getPerson( 2 );
      createPerson();
      getPerson( 3 );
      createPerson();
      getPerson( 4 );
      createPerson();
      getPerson( 5 );
   }
   
   @Test
   public void test_2() throws PersonException
   {
      int actual = listAllPersons();
      Assert.assertEquals( 5, actual );
      getPerson( 1 );
   }
   
   @Test
   public void test_3() throws PersonException
   {
      createPerson();
      int actual = listAllPersons();
      Assert.assertEquals( 6, actual );
   }
   
   @Test
   public void test_4()
   {
      updatePerson( 3 );
      deletePerson( 4 );
      int actual = listAllPersons();
      Assert.assertEquals( 5, actual );
   }
   
   @Test
   public void test_5()
   {
      PersonModel person = getPerson( 4 );
      Integer key = 0;
      Assert.assertEquals( key, person.getPersonId() );
   }
}