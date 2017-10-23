package bl.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResultadoJSON {
	
	@XmlElement
	private int idResultado;
	
	@XmlElement
	private float puntosNivel1;
	
	@XmlElement
	private float puntosNivel2;
	
	@XmlElement
	private float puntosNivel3;
	
	@XmlElement
	private float puntosNivel4;
	
	@XmlElement
	private float puntosNivel5;
	
	@XmlElement
	private float puntosNivel8;
	
	@XmlElement
	private int fecha;
	
	@XmlElement
	private int alumno;
	
	@XmlElement
	private int clase;
	
	public ResultadoJSON(){
		
	}
	
	public ResultadoJSON(int idResultado, float puntosN1,float puntosN2, float puntosN3, float puntosN4, float puntosN5, float puntosN8, int fecha, int alumno, int clase){
		this.idResultado=idResultado;
		this.puntosNivel1=puntosN1;
		this.puntosNivel2=puntosN2;
		this.puntosNivel3=puntosN3;
		this.puntosNivel4=puntosN4;
		this.puntosNivel5=puntosN5;
		this.puntosNivel8=puntosN8;
		this.fecha=fecha;
		this.alumno=alumno;
		this.clase=clase;
	}
	
	//Getters y Setters

	public int getIdResultado() {
		return idResultado;
	}

	public void setIdResultado(int idResultado) {
		this.idResultado = idResultado;
	}

	public float getPuntosNivel1() {
		return puntosNivel1;
	}

	public void setPuntosNivel1(float puntosNivel1) {
		this.puntosNivel1 = puntosNivel1;
	}

	public float getPuntosNivel2() {
		return puntosNivel2;
	}

	public void setPuntosNivel2(float puntosNivel2) {
		this.puntosNivel2 = puntosNivel2;
	}

	public float getPuntosNivel3() {
		return puntosNivel3;
	}

	public void setPuntosNivel3(float puntosNivel3) {
		this.puntosNivel3 = puntosNivel3;
	}

	public float getPuntosNivel4() {
		return puntosNivel4;
	}

	public void setPuntosNivel4(float puntosNivel4) {
		this.puntosNivel4 = puntosNivel4;
	}

	public float getPuntosNivel5() {
		return puntosNivel5;
	}

	public void setPuntosNivel5(float puntosNivel5) {
		this.puntosNivel5 = puntosNivel5;
	}

	public float getPuntosNivel8() {
		return puntosNivel8;
	}

	public void setPuntosNivel8(float puntosNivel8) {
		this.puntosNivel8 = puntosNivel8;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public int getAlumno() {
		return alumno;
	}

	public void setAlumno(int alumno) {
		this.alumno = alumno;
	}

	public int getClase() {
		return clase;
	}

	public void setClase(int clase) {
		this.clase = clase;
	}
	
	
	
	
	
	

}
