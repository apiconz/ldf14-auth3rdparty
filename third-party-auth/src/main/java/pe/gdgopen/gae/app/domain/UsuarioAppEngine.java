package pe.gdgopen.gae.app.domain;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class UsuarioAppEngine {
	@Id
	private String correo;
	private User usuario;

	private UsuarioAppEngine() {
	}

	public UsuarioAppEngine(User usuario) {
		this.usuario = usuario;
		this.correo = usuario.getEmail();
	}

	public User getUsuario() {
		return usuario;
	}

	public Key<UsuarioAppEngine> getKey() {
		return Key.create(UsuarioAppEngine.class, correo);
	}
}
