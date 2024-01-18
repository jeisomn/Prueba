package ec.edu.ups.prueba.services;

import java.util.List;

import ec.edu.ups.prueba.business.Datos;
import ec.edu.ups.prueba.model.Auto;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("Autos")
public class AutoService {

	@Inject
	private Datos gDatos;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Auto auto) {
		try {
			gDatos.guardar(auto);
			return Response.ok(auto).build();
		} catch (Exception e) {
			ErrorMessage error = new ErrorMessage(999, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Auto auto) {
		try {
			gDatos.guardar(auto);
			return Response.ok(auto).build();
			}catch (Exception e) {
				ErrorMessage error = new ErrorMessage(99, e.getMessage());
				return Response.status(Response.Status.NOT_FOUND)
						.entity(error)
						.build();
			}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String remove(@QueryParam("id") int codigo) {
		try {
			gDatos.borrarAuto(codigo);
			return "Eliminado";
		} catch (Exception e) {
			return "No se pudo eliminar";
		}
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("lista")
	public Response getAutos() {
		List<Auto> au = gDatos.getAutos();
		if(au.size()>0) 
			return Response.ok(au).build();
		
		ErrorMessage error = new ErrorMessage(999, "No hay autos");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(error)
				.build();
	}
}
