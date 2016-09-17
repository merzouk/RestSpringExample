
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

import com.org.Controller;
import com.org.person.model.PersonModel;
import com.org.person.service.Services;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : person
 * @package : com.org.controller
 * @date    : 10 sept. 2016 09:20:30
 */
@RestController
public class PersonController implements Controller<PersonModel>
{
   
   private static final Logger logger = LoggerFactory.getLogger( PersonController.class );
   
   @Autowired
   private Services<PersonModel>       personService;
   
  /**
   * 
   * @see com.org.Controller#listAll()
   */
   @RequestMapping(value = "/persons/", method = RequestMethod.GET)
   public ResponseEntity<List<PersonModel>> listAll()
   {
      logger.info( "listAllPersons" );
      List<PersonModel> persons = personService.findAlls();
      if( persons.isEmpty() )
      {
         return new ResponseEntity<List<PersonModel>>( new ArrayList<PersonModel>(), HttpStatus.OK );
      }
      return new ResponseEntity<List<PersonModel>>( persons, HttpStatus.OK );
   }
   
  /**
   * 
   * @see com.org.Controller#getById(java.lang.Integer)
   */
   @RequestMapping(value = "/getPersonById/{primaryKey}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<PersonModel> getById( @PathVariable("primaryKey") Integer primaryKey )
   {
      logger.info( "Fetching Person with primaryKey " + primaryKey );
      PersonModel person = personService.findById( primaryKey );
      if( person == null )
      {
         logger.info( "Person with primaryKey " + primaryKey + " not found" );
         return new ResponseEntity<PersonModel>( HttpStatus.NOT_FOUND );
      }
      return new ResponseEntity<PersonModel>( person, HttpStatus.OK );
   }
   
   /**
    * 
    * @see com.org.Controller#getByEmail(java.lang.String, java.lang.String)
    */
   @RequestMapping(value = "/getPersonByEmail/{email}/{domain}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<PersonModel> getByEmail( @PathVariable("email") String email, @PathVariable("domain") String domain )
   {
      logger.info( "Fetching Person with email " + email + "." + domain );
      PersonModel person = personService.findByEmail( email + "." + domain );
      if( person == null )
      {
         logger.info( "Person with email " + email + " not found" );
         return new ResponseEntity<PersonModel>( HttpStatus.NOT_FOUND );
      }
      return new ResponseEntity<PersonModel>( person, HttpStatus.OK );
   }
   
   /**
    * 
    * @see com.org.Controller#getByLastName(java.lang.String)
    */
   @RequestMapping(value = "/getPersonByLastName/{lastName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<PersonModel>> getByLastName( @PathVariable("lastName") String lastName )
   {
      logger.info( "Fetching Person with lastName " + lastName );
      List<PersonModel> persons = personService.findByLastName( lastName );
      if( persons == null || persons.size() == 0 )
      {
         logger.info( "Person with lastName " + lastName + " not found" );
         return new ResponseEntity<List<PersonModel>>( new ArrayList<PersonModel>(), HttpStatus.OK );
      }
      return new ResponseEntity<List<PersonModel>>( persons, HttpStatus.OK );
   }
   
 /**
  * 
  * @see com.org.Controller#getByFirstAndLastName(java.lang.String, java.lang.String)
  */
   @RequestMapping(value = "/getPersonByFirstAndLastName/{firstName}/{lastName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<PersonModel>> getByFirstAndLastName(@PathVariable("firstName") String firstName , @PathVariable("lastName") String lastName )
   {
      logger.info( "get Persons with lastName " + lastName + " and firstName " + firstName );
      List<PersonModel> persons = personService.findByFirstNameAndLastName( lastName, firstName );
      if( persons == null || persons.size() == 0 )
      {
         logger.info( "Person with lastName " + lastName + " not found" );
         return new ResponseEntity<List<PersonModel>>( new ArrayList<PersonModel>(), HttpStatus.OK );
      }
      return new ResponseEntity<List<PersonModel>>( persons, HttpStatus.OK );
   }
   
   /**
    * 
    * @see com.org.Controller#create(java.lang.Object)
    */
   @RequestMapping(value = "/createPerson/", method = RequestMethod.POST)
   public ResponseEntity<PersonModel> create( @RequestBody PersonModel person )
   {
      logger.info( "Creating Person  " + person.toString() );
      String email = person.getCourriel();
      person.setPersonId( null );;
      person = personService.save( person );
      if( person != null )
      {
         return new ResponseEntity<PersonModel>( person, HttpStatus.OK );
      }
      logger.info( "A Person with email " + email + " already exist " );
      return new ResponseEntity<PersonModel>( HttpStatus.CONFLICT );
   }
   
  /**
   * 
   * @see com.org.Controller#update(java.lang.Integer, java.lang.Object)
   */
   @RequestMapping(value = "/updatePerson/{primaryKey}", method = RequestMethod.PUT)
   public ResponseEntity<PersonModel> update( @PathVariable("primaryKey") Integer primaryKey, @RequestBody PersonModel person )
   {
      logger.info( "Updating Person " + primaryKey );
      PersonModel currentPerson = personService.findById( primaryKey );
      if( currentPerson == null )
      {
         logger.info( "Person with primaryKey " + primaryKey + " not found" );
         return new ResponseEntity<PersonModel>( HttpStatus.NOT_FOUND );
      }
      currentPerson.setPrenom( person.getPrenom() );
      currentPerson.setNom( person.getNom() );
      currentPerson.setCourriel( person.getCourriel() );
      personService.update( currentPerson );
      return new ResponseEntity<PersonModel>( currentPerson, HttpStatus.OK );
   }
   
  /**
   * 
   * @see com.org.Controller#deleteById(java.lang.Integer)
   */
   @RequestMapping(value = "/deletePersonById/{primaryKey}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<PersonModel> deleteById( @PathVariable("primaryKey") Integer primaryKey )
   {
      logger.info( "get & Deleting Person with id " + primaryKey );
      PersonModel person = personService.findById( primaryKey );
      if( person == null )
      {
         logger.info( "Unable to delete. Person with id " + primaryKey + " not found" );
         return new ResponseEntity<PersonModel>( HttpStatus.NOT_FOUND );
      }
      personService.deleteById( primaryKey );
      return new ResponseEntity<PersonModel>( new PersonModel(), HttpStatus.OK );
   }
}
