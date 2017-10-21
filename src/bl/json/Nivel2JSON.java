package bl.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import dl.Clase;

@XmlRootElement
public class Nivel2JSON {
	@XmlElement
	private int idNivel2;
	
	@XmlElement
	private String audio;
	
	@XmlElement
	private String palabra;

	@XmlElement
	private int tildada;

	@XmlElement
	private Clase clase;
	
	public Nivel2JSON(int idNivel2, String audio, String palabra, int tildada, Clase clase) {
		super();
		this.idNivel2 = idNivel2;
		this.audio = audio;
		this.palabra = palabra;
		this.tildada = tildada;
		this.clase = clase;
	}

	public int getIdNivel2() {
		return idNivel2;
	}

	public void setIdNivel2(int idNivel2) {
		this.idNivel2 = idNivel2;
	}

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public int getTildada() {
		return tildada;
	}

	public void setTildada(int tildada) {
		this.tildada = tildada;
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

}
