
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
   
   private static String       REST_SERVICE_URI = null;
   
   private static final int    PORT             = 8585;
   
   private static final String HOST             = "localhost";
   
   private static final int    LENGTH           = 10;
   
   @Before
   public void init()
   {
      logger.info( "init" );
      try
      {
         REST_SERVICE_URI = "http://" + HOST + ":" + PORT + "/person/gestionPersonnel/";
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
         REST_SERVICE_URI = null;
      }
      catch( Exception e )
      {
         logger.info( "Error ", e );
      }
   }
   
   /**
    * GET
    */
   private static int listAllPersons()
   {
      logger.info( "Testing listAllPersons" );
      RestTemplate restTemplate = new RestTemplate();
      @SuppressWarnings("unchecked")
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
      logger.info( "Testing getPerson" );
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
      logger.info( "Testing create Person" );
      RestTemplate restTemplate = new RestTemplate();
      PersonModel person = new PersonModel( null, generateStringRandom( LENGTH ), generateStringRandom( LENGTH ), generateStringRandom( LENGTH ) + "@courriel.com" );
      URI uri = restTemplate.postForLocation( REST_SERVICE_URI + "/createPerson/", person, PersonModel.class );
      if( uri != null )
      {
         logger.info( "Location : " + uri.toASCIIString() );
      }
   }
   
   /**
    *  PUT
    */
   private static void updatePerson( Integer primaryKey )
   {
      logger.info( "Testing update Person" );
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
      logger.info( "Testing delete Person" );
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.delete( REST_SERVICE_URI + "/deletePersonById/" + primaryKey );
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
   
   private static int counter = 0;
   
   @Test
   public void test_0() throws PersonException
   {
      counter = listAllPersons();
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
      Assert.assertEquals( counter + 5, actual );
      getPerson( 1 );
   }
   
   @Test
   public void test_3() throws PersonException
   {
      createPerson();
      int actual = listAllPersons();
      Assert.assertEquals( counter + 6, actual );
   }
   
   @Test
   public void test_4()
   {
      Integer primaryKey = searchPersonFromDataBase();
      updatePerson( primaryKey );
      primaryKey = searchPersonFromDataBase();
      if( primaryKey != null )
      {
         primaryKeyIndic = primaryKey;
         deletePerson( primaryKey );
         int actual = listAllPersons();
         Assert.assertEquals( counter + 5, actual );
      }
      PersonModel person = getPerson( primaryKey);
      primaryKey = 0;
      Assert.assertEquals( person.getPersonId(), primaryKey);
      
   }
   
   private static Integer primaryKeyIndic = 0;
   @Test
   public void test_5()
   {
      PersonModel person = getPerson( primaryKeyIndic);
      Integer key = 0;
      Assert.assertEquals( key, person.getPersonId() );
      Assert.assertEquals( null, person.getCourriel() );
   }
   
   private Integer searchPersonFromDataBase()
   {
      int i = 1;
      while( i < 100 )
      {
         PersonModel model = getPerson( i );
         if( model != null && model.getPersonId() != null && model.getPersonId().intValue() > 0 )
         {
            return i;
         }
         i++;
      }
      return null;
   }
}