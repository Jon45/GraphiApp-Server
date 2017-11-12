package bl.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClaseJSON {
	@XmlElement
	private int idClase;
	
	@XmlElement
	private int fecha;
	
	@XmlElement 
	private String tematica;
	
	@XmlElement
	private int idDocente;

	public ClaseJSON(){
		
	}
	public ClaseJSON(int idClase, int fecha, String tematica, int idDocente) {
		super();
		this.idClase = idClase;
		this.fecha = fecha;
		this.tematica = tematica;
		this.idDocente = idDocente;
	}
	public int getIdClase() {
		return idClase;
	}
	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}
	public int getFecha() {
		return fecha;
	}
	public void setFecha(int fecha) {
		this.fecha = fecha;
	}
	public String getTematica() {
		return tematica;
	}
	public void setTematica(String tematica) {
		this.tematica = tematica;
	}
	public int getIdDocente() {
		return idDocente;
	}
	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}
	
	
	

}
