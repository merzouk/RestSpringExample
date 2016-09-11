
package com.org.person.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.person.entity.PersonEntity;
import com.org.person.service.PersonService;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : person
 * @package : com.org.controller
 * @date    : 10 sept. 2016 09:20:30
 */
@RestController
public class PersonController
{
   
   private static final Logger logger = LoggerFactory.getLogger( PersonController.class );
   
   /**
    * Service which will do all data retrieval/manipulation work
    */
   @Autowired
   private PersonService       personService;
   
   /**
    * Retrieve All Persons
    * @return
    */
   @RequestMapping(value = "/persons/", method = RequestMethod.GET)
   public ResponseEntity<List<PersonEntity>> listAllPersons()
   {
      logger.info( "listAllPersons" );
      List<PersonEntity> persons = personService.findAllPersons();
      if( persons.isEmpty() )
      {
         return new ResponseEntity<List<PersonEntity>>( new ArrayList<PersonEntity>(), HttpStatus.OK );
      }
      return new ResponseEntity<List<PersonEntity>>( persons, HttpStatus.OK );
   }
   
   /**
    * Retrieve Single Person
    * @param id
    * @return
    */
   @RequestMapping(value = "/getPersonById/{primaryKey}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<PersonEntity> getPersonById( @PathVariable("primaryKey") Integer primaryKey )
   {
      logger.info( "Fetching Person with primaryKey " + primaryKey );
      PersonEntity person = personService.findById( primaryKey );
      if( person == null )
      {
         logger.info( "Person with primaryKey " + primaryKey + " not found" );
         return new ResponseEntity<PersonEntity>( HttpStatus.NOT_FOUND );
      }
      return new ResponseEntity<PersonEntity>( person, HttpStatus.OK );
   }
   
   /**
    * Retrieve Single Person
    * @param id
    * @return
    */
   @RequestMapping(value = "/getPersonByEmail/{email}/{domain}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<PersonEntity> getPersonByEmail( @PathVariable("email") String email, @PathVariable("domain") String domain )
   {
      logger.info( "Fetching Person with email " + email + "." + domain );
      PersonEntity person = personService.findByEmail( email + "." + domain );
      if( person == null )
      {
         logger.info( "Person with email " + email + " not found" );
         return new ResponseEntity<PersonEntity>( HttpStatus.NOT_FOUND );
      }
      return new ResponseEntity<PersonEntity>( person, HttpStatus.OK );
   }
   
   /**
    * 
    * @param lastName
    * @return
    */
   @RequestMapping(value = "/getPersonByLastName/{lastName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<PersonEntity>> getPersonByLastName( @PathVariable("lastName") String lastName )
   {
      logger.info( "Fetching Person with lastName " + lastName );
      List<PersonEntity> persons = personService.findByLastName( lastName );
      if( persons == null || persons.size() == 0 )
      {
         logger.info( "Person with lastName " + lastName + " not found" );
         return new ResponseEntity<List<PersonEntity>>( new ArrayList<PersonEntity>(), HttpStatus.OK );
      }
      return new ResponseEntity<List<PersonEntity>>( persons, HttpStatus.OK );
   }
   
   /**
    * 
    * @param lastName
    * @return
    */
   @RequestMapping(value = "/getPersonByFirstAndLastName/{lastName}/{firstName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<PersonEntity>> getPersonByFirstAndLastName( @PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName )
   {
      logger.info( "Fetching Person with lastName " + lastName + " and firstName " + firstName );
      List<PersonEntity> persons = personService.findByFirstNameAndLastName( lastName, firstName );
      if( persons == null || persons.size() == 0 )
      {
         logger.info( "Person with lastName " + lastName + " not found" );
         return new ResponseEntity<List<PersonEntity>>( new ArrayList<PersonEntity>(), HttpStatus.OK );
      }
      return new ResponseEntity<List<PersonEntity>>( persons, HttpStatus.OK );
   }
   
   /**
    * Create a Person
    * @param person
    * @param ucBuilder
    * @return
    */
   @RequestMapping(value = "/createPerson/", method = RequestMethod.POST)
   public ResponseEntity<PersonEntity> createPerson( @RequestBody PersonEntity person )
   {
      logger.info( "Creating Person  " + person.toString() );
      String email = person.getEmail();
      person.setId( null );
      person = personService.savePerson( person );
      if( person != null )
      {
         return new ResponseEntity<PersonEntity>( person, HttpStatus.OK );
      }
      logger.info( "A Person with email " + email + " already exist " );
      return new ResponseEntity<PersonEntity>( HttpStatus.CONFLICT );
   }
   
   /**
    * Update a Person
    * @param id
    * @param person
    * @return
    */
   @RequestMapping(value = "/updatePerson/{primaryKey}", method = RequestMethod.PUT)
   public ResponseEntity<PersonEntity> updatePerson( @PathVariable("primaryKey") Integer primaryKey, @RequestBody PersonEntity person )
   {
      logger.info( "Updating Person " + primaryKey );
      PersonEntity currentPerson = personService.findById( primaryKey );
      if( currentPerson == null )
      {
         logger.info( "Person with primaryKey " + primaryKey + " not found" );
         return new ResponseEntity<PersonEntity>( HttpStatus.NOT_FOUND );
      }
      currentPerson.setFirstName( person.getFirstName() );
      currentPerson.setLastName( person.getLastName() );
      currentPerson.setEmail( person.getEmail() );
      personService.updatePerson( currentPerson );
      return new ResponseEntity<PersonEntity>( currentPerson, HttpStatus.OK );
   }
   
   /**
    * Delete a Person 
    * @param primaryKey
    * @return
    */
   @RequestMapping(value = "/deletePersonById/{primaryKey}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<PersonEntity> deletePersonById( @PathVariable("primaryKey") Integer primaryKey )
   {
      logger.info( "Fetching & Deleting Person with id " + primaryKey );
      PersonEntity person = personService.findById( primaryKey );
      if( person == null )
      {
         logger.info( "Unable to delete. Person with id " + primaryKey + " not found" );
         return new ResponseEntity<PersonEntity>( HttpStatus.NOT_FOUND );
      }
      personService.deletePersonById( primaryKey );
      return new ResponseEntity<PersonEntity>( new PersonEntity(), HttpStatus.OK );
   }
   
   /**
    * Delete All Person 
    * @return
    */
   @RequestMapping(value = "/deleteAllpersons/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<PersonEntity> deleteAllPersons()
   {
      logger.info( "Deleting All Persons" );
      personService.deleteAllPersons();
      return new ResponseEntity<PersonEntity>( new PersonEntity(), HttpStatus.OK );
   }
}
