package com.org;

import java.util.List;

import org.springframework.http.ResponseEntity;

/**
 * A Renseigner.
 * @author  : admin
 * @project : person
 * @package : com.org.person.controller
 * @date    : 11 sept. 2016 12:55:39
 */
public interface Controller<T>
{
   
   public ResponseEntity<List<T>> listAll();
   
   /**
    * Retrieve Simgle T by primaryKey
    * @param id
    * @return
    */
   public ResponseEntity<T> getById( Integer primaryKey );
   
   /**
   * Retrieve Single T by email
   * 
   * @param email
   * @param domain
   * @return
   */
   public ResponseEntity<T> getByEmail( String email, String domain );
   
   /**
    * Retrieve list T by lastName
    * 
    * @param lastName
    * @return list person
    */
   public ResponseEntity<List<T>> getByLastName( String lastName );
   
   /**
    * Retrieve list T by last and first Name
    * @param lastName
    * @param firstName
    * @return
    */
   public ResponseEntity<List<T>> getByFirstAndLastName( String lastName, String firstName );
   
   /**
   * Create a new T
   * 
   * @param person
   * @return
   */
   public ResponseEntity<T> create( T person );
   
   /**
    * Update a T
    * 
    * @param primaryKey
    * @param person
    * @return
    */
   public ResponseEntity<T> update( Integer primaryKey, T person );
   
   /**
    * Delete a T 
    * 
    * @param primaryKey
    * @return
    */
   public ResponseEntity<T> deleteById( Integer primaryKey );
   
   /**
    * Delete All T 
    * @return
    */
   public ResponseEntity<T> deleteAll();
}