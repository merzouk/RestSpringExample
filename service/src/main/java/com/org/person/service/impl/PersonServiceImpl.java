
package com.org.person.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.dao.ObjectDao;
import com.org.person.entity.PersonEntity;
import com.org.person.service.PersonService;

@Service("personService")
public class PersonServiceImpl implements PersonService
{
   
   @Autowired
   private ObjectDao<PersonEntity> personDao;
   
   /**
    * 
    * @see com.org.person.service.PersonService#findAllPersons()
    */
   public List<PersonEntity> findAllPersons()
   {
      return personDao.findAll();
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#findById(long)
    */
   public PersonEntity findById( Integer primaryKey )
   {
      return personDao.findById( primaryKey );
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#findByFirstName(java.lang.String)
    */
   public List<PersonEntity> findByLastName( String lastName )
   {
      return personDao.findByLastName( lastName );
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#savePerson(com.org.person.entity.PersonEntity)
    */
   public PersonEntity savePerson( PersonEntity person )
   {
      return personDao.save( person );
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#updatePerson(com.org.person.entity.PersonEntity)
    */
   public PersonEntity updatePerson( PersonEntity person )
   {
      return personDao.update( person );
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#deletePersonById(long)
    */
   public void deletePersonById( Integer primaryKey )
   {
      personDao.deleteById( primaryKey );
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#isPersonExist(com.org.person.entity.PersonEntity)
    */
   public boolean isPersonExist( PersonEntity person )
   {
      try
      {
         return ( personDao.findByEmail( person.getEmail() ) != null );
      }
      catch( Exception e )
      {
         // TODO: handle exception
      }
      return false;
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#deleteAllPersons()
    */
   public void deleteAllPersons()
   {
      personDao.deleteAll();
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#findByFirstNameAndLastName(java.lang.String, java.lang.String)
    */
   public List<PersonEntity> findByFirstNameAndLastName( String lastName, String firstName )
   {
      return personDao.findByFirstNameAndLastName( lastName, firstName );
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#findByEmail(java.lang.String)
    */
   public PersonEntity findByEmail( String email )
   {
      return personDao.findByEmail( email );
   }
}
