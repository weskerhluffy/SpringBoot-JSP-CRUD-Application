/**
 * 
 */
package com.app;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Fatih Totrakanlı
 *
 */

@Entity // Proje oluşturulurken Üyeler için JPA ile standart entity modeli
		// oluşturulmuştur.
public class User { // Projede H2 DB kullanılmıştır. H2 db bir memory db olduğu için sunucu
					// durdurulduğunda veriler gidecektir.

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String surname;
	private String adress;
	@NotNull
	private String telefono;
	private Integer userId;
	private String title;
	private String body;
	private Boolean authenticated;
	private String correo;
	// XXX:
	// http://www.logicbig.com/how-to/code-snippets/jcode-spring-framework-datetimeformat/
	// XXX:
	// https://stackoverflow.com/questions/33595651/spring-mvc-different-customdateeditor-binders-for-various-fields
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	private Date fecha1;

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public User() {
		super();
		this.authenticated = false;
	}

	public User(String name, String surname, String adress) {
		super();

		this.name = name;
		this.surname = surname;
		this.adress = adress;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha1() {
		return fecha1;
	}

	public void setFecha1(Date fecha1) {
		this.fecha1 = fecha1;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
