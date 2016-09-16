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
 * Fichier 		:	Utils.java
 * Cree le 		: 	11 sept. 2016 à 11:49:10
 * Auteur		: 	admin
 * 
 * Description 	:
 * 
 *---------------------------------------------------------
 */

package com.org.tools;

/**
 * A Renseigner.
 * @author  : admin
 * @project : persist
 * @package : com.org.tools
 * @date    : 11 sept. 2016 11:49:10
 */
public class Utils
{
   /**
    * 
    * @param email
    * @return
    */
   public static boolean isValidEmailAddress( String email )
   {
      String emailPatternExpression = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
      java.util.regex.Pattern pattern = java.util.regex.Pattern.compile( emailPatternExpression );
      java.util.regex.Matcher matcher = pattern.matcher( email );
      return matcher.matches();
   }
}
