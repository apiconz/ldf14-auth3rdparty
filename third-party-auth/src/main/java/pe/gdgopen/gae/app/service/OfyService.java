package pe.gdgopen.gae.app.service;

import pe.gdgopen.gae.app.domain.Perfil;
import pe.gdgopen.gae.app.domain.UsuarioAppEngine;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {

	static {
		factory().register(Perfil.class);
		factory().register(UsuarioAppEngine.class);
	}

	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}
}
