
package com.org.person.controller;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.org.person.entity.PersonEntity;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : person
 * @package : com.org
 * @date    : 10 sept. 2016 09:22:02
 */
public class PersonControllerClientTest
{
   
   private static final Logger logger = LoggerFactory.getLogger( PersonControllerClientTest.class );
   
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
   
   public static final String REST_SERVICE_URI = "http://localhost:8585/person/gestionPersonnel/";
   
   /**
    * GET
    */
   @SuppressWarnings("unchecked")
   private static List<LinkedHashMap<String, Object>> listAllPersons()
   {
      logger.info( "Testing listAllPersons API" );
      RestTemplate restTemplate = new RestTemplate();
      List<LinkedHashMap<String, Object>> personsMap = restTemplate.getForObject( REST_SERVICE_URI + "/persons/", List.class );
      if( personsMap != null )
      {
         for( LinkedHashMap<String, Object> map : personsMap )
         {
            logger.info( "person " + map.toString() );
         }
      }
      else
      {
         logger.info( "No person found" );
      }
      return personsMap;
   }
   
   /**
    * GET
    */
   private static void getPerson( Integer primaryKey )
   {
      logger.info( "Testing getPerson API" );
      RestTemplate restTemplate = new RestTemplate();
      PersonEntity person = restTemplate.getForObject( REST_SERVICE_URI + "/getPersonById/" + primaryKey, PersonEntity.class );
      logger.info( "" + person.toString() );
   }
   
   /**
    * POST
    */
   private static void createPerson()
   {
      logger.info( "Testing create Person API" );
      RestTemplate restTemplate = new RestTemplate();
      PersonEntity person = new PersonEntity( null, generateStringRandom( 20 ), generateStringRandom( 20 ), generateStringRandom( 20 ) + "@courriel.com" );
      URI uri = restTemplate.postForLocation( REST_SERVICE_URI + "/createPerson/", person, PersonEntity.class );
      if( uri != null )
      {
         logger.info( "Location : " + uri.toASCIIString() );
      }
   }
   
   private static String generateStringRandom( int length )
   {
      if( length == 0 )
      {
         length = 20;
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
      PersonEntity person = new PersonEntity( null, generateStringRandom( 20 ), generateStringRandom( 20 ), generateStringRandom( 20 ) + "@courriel.com" );
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
   public void allTest()
   {
      createPerson();
      createPerson();
      createPerson();
      createPerson();
      getPerson( 1 );
      createPerson();
      listAllPersons();
      updatePerson( 8 );
      listAllPersons();
      deletePerson( 11 );
      listAllPersons();
      listAllPersons();
   }
}