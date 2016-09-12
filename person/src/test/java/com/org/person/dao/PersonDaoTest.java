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
 * Cree le 		: 	10 sept. 2016 � 11:54:07
 * Auteur		: 	admin
 * 
 * Description 	:
 * 
 *---------------------------------------------------------
 */

package com.org.person.dao;

import java.util.List;
import java.util.Random;

import org.junit.Assert;
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

import com.org.person.entity.PersonEntity;

/**
 * A Renseigner.
 * @author  : admin
 * @project : person
 * @package : com.org.person.dao
 * @date    : 10 sept. 2016 11:54:07
 */
@ContextConfiguration("/test-person-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonDaoTest
{
   
   private static final Logger logger = LoggerFactory.getLogger( PersonDaoTest.class );
   
   private static final int LENGTH = 10;
   
   @Autowired
   private PersonDao<PersonEntity> personDao;
   
   @Test
   public void test_1()
   {
      Integer primaryKey = 1;
      PersonEntity p = new PersonEntity();
      String email = generateStringRandom( LENGTH );
      String firstName = generateStringRandom( LENGTH );
      p.setFirstName( firstName );
      String lastName = generateStringRandom( LENGTH );
      p.setLastName( lastName );
      p.setEmail( email + "@email.com" );
      /**
       * 
       */
      logger.info( "save test" );
      p = personDao.save( p );
      Integer id = p.getId();
      Assert.assertNotNull( id );
      Assert.assertEquals( primaryKey, id );
      Assert.assertEquals( email + "@email.com" , p.getEmail() );
      /**
       * 
       */
      List<PersonEntity> list = personDao.findByLastName( lastName );
      Assert.assertEquals( 1, list.size() );
      p = list.get( 0 );
      Assert.assertNotNull( id );
      Assert.assertEquals( primaryKey, id );
      Assert.assertEquals( email + "@email.com", p.getEmail() );
      Assert.assertEquals( lastName, p.getLastName() );
      Assert.assertEquals( firstName, p.getFirstName() );
      /**
       * 
       */
      p = personDao.findByEmail( email + "@email.com" );
      Assert.assertNotNull( p );
      Assert.assertNotNull( p.getId() );
      Assert.assertEquals( primaryKey, p.getId() );
      Assert.assertEquals( email + "@email.com", p.getEmail() );
      Assert.assertEquals( lastName, p.getLastName() );
      Assert.assertEquals( firstName, p.getFirstName() );
      /**
       * 
       */
      list = personDao.findByFirstNameAndLastName( lastName, firstName );
      Assert.assertEquals( 1, list.size() );
      p = list.get( 0 );
      Assert.assertNotNull( id );
      Assert.assertEquals( primaryKey, id );
      Assert.assertEquals( email + "@email.com", p.getEmail() );
      Assert.assertEquals( lastName, p.getLastName() );
      Assert.assertEquals( firstName, p.getFirstName() );
      /**
      * 
      */
      logger.info( "get by id test" );
      p = personDao.findById( primaryKey );
      Assert.assertNotNull( "Person not found!", p );
      Assert.assertEquals( primaryKey, p.getId() );
      Assert.assertEquals( email + "@email.com", p.getEmail() );
      Assert.assertEquals( lastName, p.getLastName() );
      Assert.assertEquals( firstName, p.getFirstName() );
      /**
       * 
       */
      email = email + "_1@email.com";
      p.setEmail( email );
      personDao.update( p );
      /**
       * 
       */
      logger.info( "get by id test" );
      p = personDao.findById( primaryKey );
      Assert.assertNotNull( "Person not found!", p );
      Assert.assertEquals( primaryKey, p.getId() );
      Assert.assertEquals( email, p.getEmail() );
      Assert.assertEquals( lastName, p.getLastName() );
      Assert.assertEquals( firstName, p.getFirstName() );
      /**
       * 
       */
      logger.info( "load list test" );
      list = personDao.findAll();
      Assert.assertEquals( 1, list.size() );
      /**
       * 
       */
      p = new PersonEntity();
      firstName = generateStringRandom( LENGTH );
      p.setFirstName( firstName );
      lastName = generateStringRandom( LENGTH );
      p.setLastName( lastName );
      p.setEmail( email );
      p = personDao.save( p );
      Assert.assertNull( p );
   }
   
   private String generateStringRandom( int length )
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
}
