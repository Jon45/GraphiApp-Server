package bl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
//algunos imports necesarios
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
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
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("/registerUser")
	public Response registerUser(AlumnoJSON alumnoJSON){
		System.out.println("registerUser: "+hsr.getRemoteAddr());
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
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("/loginUser")
	public Response loginUser(@QueryParam("nickname") String login, @QueryParam("password") String password ){
		System.out.println("loginUser: "+hsr.getRemoteAddr());
		Response response = null;
		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("/postNivel1")
	public Response postNivel1(Nivel1JSON nivel1JSON){
		
		System.out.println("postNivel1: "+hsr.getRemoteAddr());
		Response response = null;
		String login = nivel1JSON.getClase().getDocente().getNickname();
		Docente docente = (Docente)em.createNamedQuery("Docente.findNickname").setParameter("nickname", login).getSingleResult();
		if(docente != null){
			em.persist(nivel1JSON);
			response = Response.ok().entity("Ejercicio subido correctamente").build();
		}
		else{
			response = Response.ok().entity("Error de acceso: no tiene permiso para subir ejercicios").build();
		}
		return response;
		
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("/postNivel2")
	public Response postNivel2(Nivel2JSON nivel2JSON){
		
		//TO-DO. Añadir servicio para subir audio que devuelva URL y poder usarlo aquí
		return Response.ok().entity("Ejercicio subido correctamente").build();
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getResults")
	public ResultadosJSON getResults(@QueryParam("nickname") String nickname, @QueryParam("fecha") int fecha){
		System.out.println("getResults: "+hsr.getRemoteAddr());
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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getNivel1")	
	public Niveles1JSON getNivel1(@QueryParam("nickname") String nickname, @DefaultValue("-1") @QueryParam("pin") int pin ) {
		System.out.println("getNivel1: "+hsr.getRemoteAddr());
		
		Niveles1JSON niveles1JSON=null;
		Alumno alumno = (Alumno) em.createNamedQuery("Alumno.findNick").setParameter("nickname", nickname).getSingleResult(); // Qué hacer si no existe usuario?
		if (alumno != null)
		{
			niveles1JSON=new Niveles1JSON();
			List<Nivel1JSON> Nivel1JSONList=new ArrayList<Nivel1JSON>();
			List<Nivel1> Nivel1List = null;
			
			if (pin!=-1)
			{
				Nivel1List = (List <Nivel1>)em.createNamedQuery("Nivel1.findClaseFecha",Nivel1.class).setParameter("fecha", pin).getResultList(); //Es necesario mínimo o máximo de preguntas?
			}
			else
			{
				Nivel1List = (List <Nivel1>)em.createNamedQuery("Nivel1.findAll",Nivel1.class).getResultList();
				Collections.shuffle(Nivel1List);
				Nivel1List = Nivel1List.subList(0, 9);
			}
				for(int i=0;i<Nivel1List.size();i++) {
				Nivel1 n=Nivel1List.get(i);
				Nivel1JSON lJSON=new Nivel1JSON(n.getIdNivel1(),n.getCorrecta(),n.getPalabra1(),n.getPalabra2(),n.getClase());
				Nivel1JSONList.add(lJSON);
			}
				niveles1JSON.setListaNivel1JSON(Nivel1JSONList);	
		}
		
		return niveles1JSON;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getNivel2")	
	public Niveles2JSON getNivel2(@QueryParam("nickname") String nickname, @DefaultValue("-1") @QueryParam("pin") int pin ) {
		System.out.println("getNivel2: "+hsr.getRemoteAddr());
		
		Niveles2JSON niveles2JSON=null;
		Alumno alumno = (Alumno) em.createNamedQuery("Alumno.findNick").setParameter("nickname", nickname).getSingleResult(); // Qué hacer si no existe usuario?
		if (alumno != null)
		{
			niveles2JSON=new Niveles2JSON();
			List<Nivel2JSON> Nivel2JSONList=new ArrayList<Nivel2JSON>();
			List<Nivel2> Nivel2List = null;
			
			if (pin!=-1)
			{
				Nivel2List = (List <Nivel2>)em.createNamedQuery("Nivel2.findClaseFecha",Nivel2.class).setParameter("fecha", pin).getResultList(); //Es necesario mínimo o máximo de preguntas?
			}
			else
			{
				Nivel2List = (List <Nivel2>)em.createNamedQuery("Nivel2.findAll",Nivel2.class).getResultList();
				Collections.shuffle(Nivel2List);
				Nivel2List = Nivel2List.subList(0, 9);
			}
				for(int i=0;i<Nivel2List.size();i++) {
				Nivel2 n=Nivel2List.get(i);
				Nivel2JSON lJSON=new Nivel2JSON(n.getIdNivel2(),n.getAudio(),n.getPalabra(),n.getTildada(),n.getClase());
				Nivel2JSONList.add(lJSON);
			}
				niveles2JSON.setListaNivel2JSON(Nivel2JSONList);	
		}
		
		return niveles2JSON;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("/postResult")
	public Response postResult(Resultado resultado)
	{
		System.out.println("postResult: "+hsr.getRemoteAddr());
		
		resultado.getAlumno();
		List <Resultado> resultadoDB = em.createNamedQuery("Resultado.findByAlumnoFecha",Resultado.class).setParameter("alumno", resultado.getAlumno()).setParameter("fecha", resultado.getFecha()).getResultList();
		if (resultadoDB.size()!=0)
		{
			for (Resultado resultadoItem : resultadoDB)
			{
				em.remove(resultadoItem);
			}
		}	
			em.persist(resultado);
		
		return Response.ok().entity("Resultados actualizados correctamente").build();
        }
	
}
