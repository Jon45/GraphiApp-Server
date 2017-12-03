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
		String folderNames[]= {"audio","img"};
		
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
		List<Alumno> lista= (List <Alumno>)em.createNamedQuery("Alumno.findLogin",Alumno.class).setParameter("nickname",loginPref+"%").getResultList();
		if(lista.size()>= 10){
			loginPref = loginPref + "0" + lista.size();
			alumnoJSON.setNickname(loginPref);
		}
		else{
			loginPref = loginPref + "00" + lista.size();
			alumnoJSON.setNickname(loginPref);
		}
		
		//Persist to DataBase
		Alumno alumno = new Alumno();
		alumno.setNombre(alumnoJSON.getNombre());
		alumno.setApellidos(alumnoJSON.getApellidos());
		alumno.setNickname(alumnoJSON.getNickname());
		alumno.setPassword(alumnoJSON.getPassword());
		em.persist(alumno);
		
		response = Response.ok().entity("El estudiante se ha registrado correctamente. Su login es: " + alumno.getNickname()).build();
		return response;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("/registerTeacher")
	public Response registerTeacher(DocenteJSON docenteJSON){
		System.out.println("registerTeacher: "+hsr.getRemoteAddr());
		Response response = null;
		
		//Generate login
		String loginPref = docenteJSON.getNombre().substring(0, 1).toLowerCase() + docenteJSON.getApellidos().substring(0,1).toLowerCase();
		List<Docente> lista = (List<Docente>)em.createNamedQuery("Docente.findLogin", Docente.class).setParameter("nickname",loginPref+"%").getResultList();
		if(lista.size()>= 10){
			loginPref = loginPref + "0" + lista.size();
			docenteJSON.setNickname(loginPref);
		}
		else{
			loginPref = loginPref + "00" + lista.size();
			docenteJSON.setNickname(loginPref);
		}
		
		//Persist to Database
		Docente docente = new Docente();
		docente.setApellidos(docenteJSON.getApellidos());
		docente.setNickname(docenteJSON.getNickname());
		docente.setNombre(docenteJSON.getNombre());
		docente.setPassword(docenteJSON.getPassword());
		em.persist(docente);
		
		response = Response.ok().entity("El docente se ha registrado correctamente. Su login es " + docente.getNickname()).build();
		
		return response;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("/loginUser")
	public Response loginUser(@QueryParam("nickname") String login, @QueryParam("password") String password ){
		System.out.println("loginUser: "+hsr.getRemoteAddr());
		Response response = null;
		List<Alumno> alumno = em.createNamedQuery("Alumno.findbyNickPass", Alumno.class).setParameter("nickname", login).setParameter("password", password).getResultList();
		if(alumno.size() != 0){
			response=Response.ok().entity("0-Alumno").build();
		}
		else{
			List<Docente> docente= em.createNamedQuery("Docente.findByNicknamePass", Docente.class).setParameter("nickname", login).setParameter("password", password).getResultList();
			if(docente.size() != 0){
				response=Response.ok().entity("0-Docente").build();
			}
			else{
				response=Response.ok().entity("1-Error").build();
			}
			
		}
		return response;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/registerClass")
	public Response registerClass(ClaseJSON claseJSON){
		System.out.println("registerClass: "+hsr.getRemoteAddr());
		Response response = Response.ok().entity("Error en el servidor. No se ha ejecutado el código").build();
		
		List<Docente>docente=em.createNamedQuery("Docente.findNickname",Docente.class).setParameter("nickname", claseJSON.getLoginDocente()).getResultList();
		if(docente.size() != 0){
			Clase clase = new Clase();
			clase.setDocente(docente.get(0));
			clase.setFecha(claseJSON.getFecha());
			clase.setTematica(claseJSON.getTematica());
			
			em.persist(clase);
			List<Integer> listaClases = em.createNamedQuery("Clase.findFecha",Integer.class).setParameter("fecha", claseJSON.getFecha()).getResultList();
			int id = listaClases.get(listaClases.size()-1).intValue();
			
			response = Response.ok().entity(""+id+"").build();
		}
		else{
			response = Response.ok().entity("Error de acceso: No tienes permiso para añadir una nueva clase").build();
		}
		
		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("/postNivel1")
	public Response postNivel1(PostNivel1JSON postNivel1JSON){
		
		System.out.println("postNivel1: "+hsr.getRemoteAddr());
		Response response = null;
		String login = postNivel1JSON.getLogin();
		List<Docente> docente = em.createNamedQuery("Docente.findNickname",Docente.class).setParameter("nickname", login).getResultList();
		if(docente != null){
			
			List<Clase> clase = em.createNamedQuery("Clase.findId", Clase.class).setParameter("idClase", postNivel1JSON.getNivel1JSON().getClase()).getResultList();
			if(clase.size() != 0){
				Nivel1 nivel1 = new Nivel1();
				nivel1.setCorrecta(postNivel1JSON.getNivel1JSON().getCorrecta());
				nivel1.setPalabra1(postNivel1JSON.getNivel1JSON().getPalabra1());
				nivel1.setPalabra2(postNivel1JSON.getNivel1JSON().getPalabra2());
				nivel1.setClase(clase.get(0));
				em.persist(nivel1);
			}
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
	public Response postNivel2(PostNivel2JSON postNivel2JSON){
		
		System.out.println("postNivel2: "+hsr.getRemoteAddr());
		Response response = null;
		//TO-DO. Añadir servicio para subir audio que devuelva URL y poder usarlo aquí
		String login = postNivel2JSON.getLogin();
		List<Docente> docente = em.createNamedQuery("Docente.findNickname",Docente.class).setParameter("nickname", login).getResultList();
		if(docente.size() != 0){
			List<Clase> clase = em.createNamedQuery("Clase.findId",Clase.class).setParameter("idClase", postNivel2JSON.getNivel2JSON().getClase()).getResultList();
			if(clase.size() != 0){
				
				Nivel2 nivel2 = new Nivel2();
				nivel2.setAudio(postNivel2JSON.getUrl());
				nivel2.setPalabra(postNivel2JSON.getNivel2JSON().getPalabra());
				nivel2.setTildada(postNivel2JSON.getNivel2JSON().getTildada());
				nivel2.setClase(clase.get(0));
				em.persist(nivel2);
				
			}
			response = Response.ok().entity("Ejercicio subido correctamente").build();
		}
		else{
			response = Response.ok().entity("Error de acceso: no tiene permiso para subir ejercicios").build();
		}
		return response;
		
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
			List<Resultado> listaResultados = (List<Resultado>)em.createNamedQuery("Resultado.findByFecha",Resultado.class).setParameter("fecha", fecha).getResultList();
			
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
		List <Alumno> alumnos = (List<Alumno>) em.createNamedQuery("Alumno.findNick",Alumno.class).setParameter("nickname", nickname).getResultList(); // Qué hacer si no existe usuario?
		if (alumnos.size() == 1)
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
				Nivel1List = Nivel1List.subList(0, 10);
			}
				for(int i=0;i<Nivel1List.size();i++) {
				Nivel1 n=Nivel1List.get(i);
				Nivel1JSON lJSON=new Nivel1JSON(n.getIdNivel1(),n.getCorrecta(),n.getPalabra1(),n.getPalabra2(),n.getClase().getIdClase());
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
		List<Alumno> alumnos = (List<Alumno>) em.createNamedQuery("Alumno.findNick",Alumno.class).setParameter("nickname", nickname).getResultList(); // Qué hacer si no existe usuario?
		if (alumnos.size() == 1)
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
				Nivel2List = Nivel2List.subList(0, 10);
			}
				for(int i=0;i<Nivel2List.size();i++) {
				Nivel2 n=Nivel2List.get(i);
				Nivel2JSON lJSON=new Nivel2JSON(n.getIdNivel2(),n.getAudio(),n.getPalabra(),n.getTildada(),n.getClase().getIdClase());
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
	public Response postResult(ResultadoJSON resultado)
	{
		System.out.println("postResult: "+hsr.getRemoteAddr());
		
		Resultado nuevoResultado;
		
		List <Alumno> alumnoDB = em.createNamedQuery("Alumno.findId",Alumno.class).setParameter("idAlumno", resultado.getAlumno()).getResultList();
		if (alumnoDB.size()==1)
		{
			Alumno alumnoBean = alumnoDB.get(0);
			List <Clase> claseDB = em.createNamedQuery("Clase.findId",Clase.class).setParameter("idClase", resultado.getClase()).getResultList();
			if (claseDB.size()==1)
			{
				Clase claseBean = claseDB.get(0);
				List <Resultado> resultadoDB = em.createNamedQuery("Resultado.findByAlumnoFecha",Resultado.class).setParameter("alumno", resultado.getAlumno()).setParameter("fecha", resultado.getFecha()).getResultList();
				
				if (resultadoDB.size()!=0)
				{
					nuevoResultado=resultadoDB.get(0);
				}
				
				else
				{
					nuevoResultado = new Resultado();
				}

				nuevoResultado.setAlumno(alumnoBean);
				nuevoResultado.setClase(claseBean);
				nuevoResultado.setFecha(resultado.getFecha());
				nuevoResultado.setPuntosNivel1(resultado.getPuntosNivel1());
				nuevoResultado.setPuntosNivel2(resultado.getPuntosNivel2());
				nuevoResultado.setPuntosNivel3(resultado.getPuntosNivel3());
				nuevoResultado.setPuntosNivel4(resultado.getPuntosNivel4());
				nuevoResultado.setPuntosNivel5(resultado.getPuntosNivel5());
				nuevoResultado.setPuntosNivel8(resultado.getPuntosNivel8());
				
				em.persist(nuevoResultado);
				
				return Response.ok().entity("Resultados actualizados correctamente").build();
			}
		}
		
		return Response.serverError().entity("Error al actualizar los resultados").build();

        }
	
}
