/*
 * $Id: businessREST.java Oct 22, 2015 tta1718$
 * 
 * Copyright (C) 2015 Maider Huarte Arrayago
 * 
 * This file is part of TTA1718_LS-EX_09S.zip.
 * 
 * TTA1718_LS-EX_09S.zip is based on templates by Eclipse.org and it is
 * intended for learning purpouses only.
 * 
 * TTA1718_LS-EX_09S-10.zip is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * TTA1718_LS-EX_09S-10.zip is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details <http://www.gnu.org/licenses/>.
 */

package bl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
//algunos imports necesarios
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import bl.json.*;
import dl.*;
import eus.ehu.INTEL901_504021.TTA1718.utils.FileUtils;

@Singleton//Anotación de EJB compatible con Web Service
@Path("/GraphiApp")
public class LogicREST {

	@Context private javax.servlet.http.HttpServletRequest hsr;    
    
	@PersistenceContext
	EntityManager em;
	
	@PostConstruct
	private void buildFolderTree() {
		String contextName=hsr.getContextPath().substring(1);
		String folderNames[]= {"audio"};
		
		FileUtils.generateFolderTree(contextName, folderNames);
	}
	
	@Path("/uploadFile")
	@POST
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input) {
		System.out.println("uploadFile: "+hsr.getRemoteAddr());

		Response httpResponse= FileUtils.uploadFile(input); //Recoger el fichero recibido como contenido multipart y escribirlo en la ubicación por defecto
		
		return httpResponse;		
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("/registerUser")
	public Response registerUser(AlumnoJSON alumnoJSON){
		System.out.println("addStudent: "+hsr.getRemoteAddr());
		Response response = null;
		
		
		//Generate login
		String loginPref = alumnoJSON.getNombre().substring(0, 1).toLowerCase()+alumnoJSON.getApellidos().substring(0, 1).toLowerCase();
		List<Alumno> lista= (List <Alumno>)em.createNamedQuery("Alumno.findLogin").setParameter("nickname",loginPref+"%");
		if(lista.size()>= 10){
			loginPref = loginPref + "0" + lista.size();
			alumnoJSON.setNickname(loginPref);
		}
		else{
			loginPref = loginPref + "00" + lista.size();
			alumnoJSON.setNickname(loginPref);
		}
		
		//Persist to DataBase
		em.persist(alumnoJSON);
		
		response = Response.ok().entity("El estudiante se ha registrado correctamente").build();
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("/loginUser")
	public Response loginUser(@QueryParam("nickname") String login, @QueryParam("password") String password ){
		System.out.println("addStudent: "+hsr.getRemoteAddr());
		Response response = null;
		return response;
	}
	@SuppressWarnings("unchecked")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("/postNivel1")
	public Response postNivel1(Nivel1JSON nivel1JSON, String login, int fecha){
		
		System.out.println("addStudent: "+hsr.getRemoteAddr());
		Response response = null;
		
		Docente docente = (Docente)em.createNamedQuery("Docente.findNickname").setParameter("nickname", login).getSingleResult();
		if(docente != null){
			
			//seguramente habrá algo aquí, pero no acabo de entender qué se hace en el método de referencia
			em.persist(nivel1JSON);
			response = Response.ok().entity("Ejercicio subido correctamente").build();
		}
		else{
			response = Response.ok().entity("Error de acceso: no tiene permiso para subir ejercicios").build();
		}
		return response;
		
	}
	@SuppressWarnings("unchecked")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("/postNivel2")
	public Response postNivel2(Nivel2JSON nivel2JSON, String login, int fecha){
		
		//TO-DO. Añadir servicio para subir audio que devuelva URL y poder usarlo aquí
		return Response.ok().entity("Ejercicio subido correctamente").build();
		
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getResults")
	public ResultadosJSON getResults(@QueryParam("nickname") String nickname, @QueryParam("fecha") int fecha){
		System.out.println("requestCalification: "+hsr.getRemoteAddr());
		ResultadosJSON resultadosJSON = new ResultadosJSON();
		
		Docente docente = (Docente)em.createNamedQuery("Docente.findNickname").setParameter("nickname", nickname).getSingleResult();
		if(docente != null){
			
			List<ResultadoJSON> listaResultadoJSON = new ArrayList<ResultadoJSON>();
			List<Resultado> listaResultados = (List<Resultado>)em.createNamedQuery("Resultado.findByFecha").setParameter("fecha", fecha).getResultList();
			
			for(int i = 0; i<listaResultados.size(); i++){
				Resultado r = listaResultados.get(i);
				ResultadoJSON rJSON = new ResultadoJSON(r.getIdResultado(), r.getPuntosNivel1(), r.getPuntosNivel2(),r.getPuntosNivel3(), r.getPuntosNivel4(), r.getPuntosNivel5(), r.getPuntosNivel8(), r.getFecha(), r.getAlumno().getIdAlumno(), r.getClase().getIdClase());
				listaResultadoJSON.add(rJSON);
			}
			
		}
		
		
		return resultadosJSON;
	}
	
	
	
	
}
