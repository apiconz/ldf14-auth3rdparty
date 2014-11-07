package pe.gdgopen.gae.app.domain;

import com.googlecode.objectify.annotation.Id;

public class Perfil {

	@Id
	private String idUsuario;
	private String nombreCompleto;
	private String correo;

	private Perfil() {
	}

	public Perfil(String idUsuario, String nombreCompleto, String correo) {
		this.idUsuario = idUsuario;
		this.nombreCompleto = nombreCompleto;
		this.correo = correo;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
