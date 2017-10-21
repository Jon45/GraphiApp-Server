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
import java.util.Collections;
import java.util.List;

//algunos imports necesarios
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import bl.json.*;
import dl.*;

@Singleton//Anotación de EJB compatible con Web Service
@Path("/GraphiApp")
public class LogicREST {

	@Context private javax.servlet.http.HttpServletRequest hsr;    
    
	@PersistenceContext
	EntityManager em;
	
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
		System.out.println("getNivel1: "+hsr.getRemoteAddr());
		
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
}
