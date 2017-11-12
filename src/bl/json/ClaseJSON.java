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
	private String loginDocente;

	public ClaseJSON(){
		
	}
	public ClaseJSON(int idClase, int fecha, String tematica, String loginDocente) {
		super();
		this.idClase = idClase;
		this.fecha = fecha;
		this.tematica = tematica;
		this.loginDocente = loginDocente;
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
	public String getLoginDocente() {
		return loginDocente;
	}
	public void setLoginDocente(String loginDocente) {
		this.loginDocente = loginDocente;
	}
	
	
	

}
