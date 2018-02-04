/**
 * 
 */
package com.app;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Fatih Totrakanlı
 *
 */

// IUser interface'i CrudRepository sınıfını extend etmiştir.
// Crud Repository sınıfı incelendiğinde hiyerarşik olarak simpleJPARepository
// sınıfına kadar gitmektedir.
// Yani bu interface crud methodlarını yazmamazı gerektirmeyecek şekilde kendisi
// hazır yazmış ve kullanıma sunmuş olacaktır.
// Ekstradan bir Repository sınıfı ve Dao işlemlerine gerek kalmamaktadır.
public interface IUser extends CrudRepository<User, Long> {

	// XXX:
	// https://stackoverflow.com/questions/9954590/hibernate-error-querysyntaxexception-users-is-not-mapped-from-users/9954677
	// XXX:
	// https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-creating-database-queries-with-the-query-annotation/
	@Query(value = "SELECT t FROM com.app.User t WHERE t.name=:nombre ")
	User findBySSO(@Param("nombre") String sso);
}
