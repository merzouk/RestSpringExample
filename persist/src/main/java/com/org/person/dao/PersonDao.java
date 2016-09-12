/*
 *--------------------------------------------------------
 * Administrateur
 *--------------------------------------------------------
 * Project     : persist
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
 * Fichier 		:	PersonDao.java
 * Cree le 		: 	11 sept. 2016 à 15:52:25
 * Auteur		: 	admin
 * 
 * Description 	:
 * 
 *---------------------------------------------------------
 */

package com.org.person.dao;

import java.util.List;

import com.org.dao.ObjectDao;

/**
 * A Renseigner.
 * @author  : admin
 * @project : persist
 * @package : com.org.dao
 * @date    : 11 sept. 2016 15:52:25
 */
public interface PersonDao<T> extends ObjectDao<T>
{
   /**
    * 
    * @param lastName
    * @return
    */
   List<T> findByLastName( String lastName );
   
   /**
    * 
    * @param email
    * @return
    */
   T findByEmail( String email );
   
   /**
    * 
    * @param lastName
    * @param firstName
    * @return
    */
   List<T> findByFirstNameAndLastName( String firstName, String lastName );
}
