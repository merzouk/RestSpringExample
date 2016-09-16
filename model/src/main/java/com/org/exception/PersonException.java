/*
 *--------------------------------------------------------
 * admin
 *--------------------------------------------------------
 * Project     : persist
 *
 * Copyright admin.
 *
 *
 *-------------------------------------------------------- 
 * 
 * Fichier 		:	PersonException.java
 * Cree le 		: 	12 sept. 2016 : 21:12:37
 * Auteur		: 	admin
 * 
 * Description 	:
 * 
 *---------------------------------------------------------
 */

package com.org.exception;

/**
 * To inform.
 * 
 * @author  : admin
 * @project : persist
 * @package : com.org.dao
 * @date    : 12 sept. 2016 21:12:37
 */
public class PersonException extends RuntimeException
{
   
   /**
    * 
    */
   private static final long serialVersionUID = 2752430498902893509L;
   
   public PersonException()
   {
      super();
   }
   
   public PersonException( String message )
   {
      super( message );
   }
   
   public PersonException( String message, Throwable cause )
   {
      super( message, cause );
   }
   
   public PersonException( Throwable cause )
   {
      super( cause );
   }
   
   protected PersonException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace )
   {
      super( message, cause, enableSuppression, writableStackTrace );
   }
}
