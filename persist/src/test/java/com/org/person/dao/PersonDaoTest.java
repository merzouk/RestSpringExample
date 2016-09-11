/*
 *--------------------------------------------------------
 * Administrateur
 *--------------------------------------------------------
 * Project     : person
 *
 * Copyright Administrateur,  All Rights Reserved.
 *
 * This software is the confidential and proprietary
 * information of Administrateur.
 * You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms
 * of the license agreement you entered into with
 * Administrateur.
 *-------------------------------------------------------- 
 * 
 * Fichier 		:	PersonDaoTest.java
 * Cree le 		: 	10 sept. 2016 à 11:54:07
 * Auteur		: 	admin
 * 
 * Description 	:
 * 
 *---------------------------------------------------------
 */

package com.org.person.dao;

import java.util.List;

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

import com.org.dao.ObjectDao;
import com.org.person.model.Person;

/**
 * A Renseigner.
 * @author  : admin
 * @project : person
 * @package : com.org.person.dao
 * @date    : 10 sept. 2016 11:54:07
 */
@ContextConfiguration("/test-dao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonDaoTest
{
   
   private static final Logger logger = LoggerFactory.getLogger( PersonDaoTest.class );
   
   @Autowired
   private ObjectDao <Person>          objectDao;
   
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
      Person p = new Person();
      String email = "mry@email.com";
      String firstName = "Ry";
      p.setFirstName( firstName );
      String lastName = "MMR";
      p.setLastName( lastName );
      p.setEmail( email );
      /**
       * 
       */
      logger.info( "save test" );
      p = objectDao.save( p );
      Integer id = p.getId();
      Assert.assertNotNull( id );
      Assert.assertEquals( primaryKey, id );
      Assert.assertEquals( email, p.getEmail() );
      /**
       * 
       */
      List<Person> list = objectDao.findByLastName( lastName );
      Assert.assertEquals( 1, list.size() );
      p = list.get( 0 );
      Assert.assertNotNull( id );
      Assert.assertEquals( primaryKey, id );
      Assert.assertEquals( email, p.getEmail() );
      Assert.assertEquals( lastName, p.getLastName() );
      Assert.assertEquals( firstName, p.getFirstName() );
      /**
       * 
       */
      p = objectDao.findByEmail( email );
      Assert.assertNotNull( p );
      Assert.assertNotNull( p.getId() );
      Assert.assertEquals( primaryKey, p.getId() );
      Assert.assertEquals( email, p.getEmail() );
      Assert.assertEquals( lastName, p.getLastName() );
      Assert.assertEquals( firstName, p.getFirstName() );
      /**
       * 
       */
      list = objectDao.findByFirstNameAndLastName( lastName, firstName );
      Assert.assertEquals( 1, list.size() );
      p = list.get( 0 );
      Assert.assertNotNull( id );
      Assert.assertEquals( primaryKey, id );
      Assert.assertEquals( email, p.getEmail() );
      Assert.assertEquals( lastName, p.getLastName() );
      Assert.assertEquals( firstName, p.getFirstName() );
      /**
      * 
      */
      logger.info( "get by id test" );
      p = objectDao.findById( primaryKey );
      Assert.assertNotNull( "Person not found!", p );
      Assert.assertEquals( primaryKey, p.getId() );
      Assert.assertEquals( email, p.getEmail() );
      Assert.assertEquals( lastName, p.getLastName() );
      Assert.assertEquals( firstName, p.getFirstName() );
      /**
       * 
       */
      email = "mry_1@email.com";
      p.setEmail( email );
      objectDao.update( p );
      /**
       * 
       */
      logger.info( "get by id test" );
      p = objectDao.findById( primaryKey );
      Assert.assertNotNull( "Person not found!", p );
      Assert.assertEquals( primaryKey, p.getId() );
      Assert.assertEquals( email, p.getEmail() );
      Assert.assertEquals( lastName, p.getLastName() );
      Assert.assertEquals( firstName, p.getFirstName() );
      /**
       * 
       */
      logger.info( "load list test" );
      list = objectDao.findAll();
      Assert.assertEquals( 1, list.size() );
      /**
       * 
       */
      p = new Person();
      email = "mry_1@email.com";
      firstName = "Ry1";
      p.setFirstName( firstName );
      lastName = "MMR1";
      p.setLastName( lastName );
      p.setEmail( email );
      p = objectDao.save( p );
      Assert.assertNull( p );
//      primaryKey = 0;
//      Assert.assertEquals( primaryKey, p.getId() );
//      Assert.assertEquals( null, p.getEmail() );
//      Assert.assertEquals( null, p.getLastName() );
//      Assert.assertEquals( null, p.getFirstName() );
   }
}
