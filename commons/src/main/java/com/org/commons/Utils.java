package com.org.commons;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : contrat
 * @package : com.org.tools
 * @date    : 16 sept. 2016 22:52:23
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
