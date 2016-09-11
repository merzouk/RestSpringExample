
package com.org.person.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.dao.ObjectDao;
import com.org.person.model.Person;
import com.org.person.service.PersonService;

@Service("personService")
public class PersonServiceImpl implements PersonService
{
   
   @Autowired
   private ObjectDao<Person> personDao;
   
   /**
    * 
    * @see com.org.person.service.PersonService#findAllPersons()
    */
   public List<Person> findAllPersons()
   {
      return personDao.findAll();
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#findById(long)
    */
   public Person findById( Integer primaryKey )
   {
      return personDao.findById( primaryKey );
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#findByFirstName(java.lang.String)
    */
   public List<Person> findByLastName( String lastName )
   {
      return personDao.findByLastName( lastName );
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#savePerson(com.org.person.model.Person)
    */
   public Person savePerson( Person person )
   {
      return personDao.save( person );
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#updatePerson(com.org.person.model.Person)
    */
   public Person updatePerson( Person person )
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
    * @see com.org.person.service.PersonService#isPersonExist(com.org.person.model.Person)
    */
   public boolean isPersonExist( Person person )
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
   public List<Person> findByFirstNameAndLastName( String lastName, String firstName )
   {
      return personDao.findByFirstNameAndLastName( lastName, firstName );
   }
   
   /**
    * 
    * @see com.org.person.service.PersonService#findByEmail(java.lang.String)
    */
   public Person findByEmail( String email )
   {
      return personDao.findByEmail( email );
   }
}
