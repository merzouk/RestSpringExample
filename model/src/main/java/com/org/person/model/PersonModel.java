
package com.org.person.model;

import java.io.Serializable;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : model
 * @package : com.org.person.model
 * @date    : 16 sept. 2016 22:51:18
 */
public class PersonModel implements Serializable
{
   
   /**
    * 
    */
   private static final long serialVersionUID = 4757821058728617197L;
   
   private Integer           personId;
   
   private String            prenom;
   
   private String            nom;
   
   private String            courriel;
   
   /**
    * @param personId
    * @param prenom
    * @param nom
    * @param courriel
    */
   public PersonModel( Integer personId, String prenom, String nom, String courriel )
   {
      super();
      this.personId = personId;
      this.prenom = prenom;
      this.nom = nom;
      this.courriel = courriel;
   }
   
   public PersonModel()
   {
      personId = 0;
   }
   
   /**
    * @return the personId
    */
   public Integer getPersonId()
   {
      return personId;
   }
   
   /**
    * @param personId the personId to set
    */
   public void setPersonId( Integer personId )
   {
      this.personId = personId;
   }
   
   /**
    * @return the prenom
    */
   public String getPrenom()
   {
      return prenom;
   }
   
   /**
    * @param prenom the prenom to set
    */
   public void setPrenom( String prenom )
   {
      this.prenom = prenom;
   }
   
   /**
    * @return the nom
    */
   public String getNom()
   {
      return nom;
   }
   
   /**
    * @param nom the nom to set
    */
   public void setNom( String nom )
   {
      this.nom = nom;
   }
   
   /**
    * @return the courriel
    */
   public String getCourriel()
   {
      return courriel;
   }
   
   /**
    * @param courriel the courriel to set
    */
   public void setCourriel( String courriel )
   {
      this.courriel = courriel;
   }
   
   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "PersonModel [personId=" + personId + ", prenom=" + prenom + ", nom=" + nom + ", courriel=" + courriel + "]";
   }
   
   /**
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ( ( courriel == null ) ? 0 : courriel.hashCode() );
      result = prime * result + ( ( nom == null ) ? 0 : nom.hashCode() );
      result = prime * result + ( ( personId == null ) ? 0 : personId.hashCode() );
      result = prime * result + ( ( prenom == null ) ? 0 : prenom.hashCode() );
      return result;
   }
   
   /**
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals( Object obj )
   {
      if( this == obj ) return true;
      if( obj == null ) return false;
      if( getClass() != obj.getClass() ) return false;
      PersonModel other = (PersonModel) obj;
      if( courriel == null )
      {
         if( other.courriel != null ) return false;
      }
      else if( !courriel.equals( other.courriel ) ) return false;
      if( nom == null )
      {
         if( other.nom != null ) return false;
      }
      else if( !nom.equals( other.nom ) ) return false;
      if( personId == null )
      {
         if( other.personId != null ) return false;
      }
      else if( !personId.equals( other.personId ) ) return false;
      if( prenom == null )
      {
         if( other.prenom != null ) return false;
      }
      else if( !prenom.equals( other.prenom ) ) return false;
      return true;
   }
}
