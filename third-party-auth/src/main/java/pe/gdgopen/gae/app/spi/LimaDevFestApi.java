package pe.gdgopen.gae.app.spi;

import static pe.gdgopen.gae.app.service.OfyService.ofy;

import java.util.logging.Logger;

import pe.gdgopen.gae.app.domain.Perfil;
import pe.gdgopen.gae.app.domain.UsuarioAppEngine;
import pe.gdgopen.gae.app.utils.Constantes;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;

/**
 * Esta clase representar&aacute; el api de nuestra aplicacion
 * 
 * @author @apiconz
 * 
 */

@Api(name = "limadevfest", version = "v1", scopes = { Constantes.EMAIL_SCOPE }, clientIds = {
		Constantes.WEB_CLIENT_ID, Constantes.ANDROID_CLIENT_ID,
		Constantes.API_EXPLORER_CLIENT_ID }, description = "Un API de ejemplo para el devfest")
public class LimaDevFestApi {

	private static final Logger logger = Logger.getLogger(LimaDevFestApi.class
			.getSimpleName());

	@ApiMethod(name = "guardarPerfil", path = "perfil", httpMethod = HttpMethod.POST)
	public Perfil guardarPerfil(final User usuario)
			throws UnauthorizedException {
		if (usuario == null) {
			logger.info("");
			throw new UnauthorizedException("Se requiere autorizacion");
		}

		// Tratamos de obtener al usuario
		Perfil perfil = ofy().load()
				.key(Key.create(Perfil.class, obtenerIdUsuario(usuario))).now();

		// Si es nulo lo creamos
		if (perfil == null) {
			perfil = new Perfil(obtenerIdUsuario(usuario), usuario.getEmail(),
					usuario.getEmail());
		}

		ofy().save().entity(perfil).now();

		return perfil;
	}

	@ApiMethod(name = "obtenerPerfil", path = "perfil", httpMethod = HttpMethod.GET)
	public Perfil obtenerPerfil(final User usuario)
			throws UnauthorizedException {
		if (usuario == null) {
			throw new UnauthorizedException("Se requiere autorizacion");
		}
		return ofy().load()
				.key(Key.create(Perfil.class, obtenerIdUsuario(usuario))).now();
	}

	private String obtenerIdUsuario(User usuario) {
		String idUsuario = usuario.getUserId();
		if (idUsuario == null) {
			logger.info("El id de usuario es nulo, se intentará obtener desde el datastore");
			UsuarioAppEngine usuarioAppEngine = new UsuarioAppEngine(usuario);
			ofy().save().entity(usuarioAppEngine).now();

			Objectify objectify = ofy().factory().begin();
			UsuarioAppEngine usuarioGuardado = objectify.load()
					.key(usuarioAppEngine.getKey()).now();
			idUsuario = usuarioGuardado.getUsuario().getUserId();
			logger.info("Se obtuvo el id de usuario: " + idUsuario);
		}
		return idUsuario;
	}

}
