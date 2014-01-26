package com.jankkol.jee.service;

import java.io.IOException;

import org.slf4j.LoggerFactory;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jankkol.jee.domain.User;

@Stateless
@SessionScoped
@ManagedBean(name = "checkUser")
public class CheckLoged {

	private static final org.slf4j.Logger log = LoggerFactory
			.getLogger(CheckLoged.class);
	private Boolean isLogged = true;

	@PersistenceContext
	EntityManager em;

	public User findUser(String name) {
		return (User) em.createNamedQuery("user.find")
				.setParameter("name", name).getSingleResult();
	}

	public void checkAdmin(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String username = request.getUserPrincipal().getName();

		User user = findUser(username);
		log.info("Before function:   " + isLogged);
		if ("ADMIN".equalsIgnoreCase(user.getRole())) {
			isLogged = true;
		}
		log.info("After function:   " + isLogged);

	}

	public Boolean getIsLogged() {
		return isLogged;
	}

	public void setIsLogged(Boolean isLogged) {
		this.isLogged = isLogged;
	}

}
