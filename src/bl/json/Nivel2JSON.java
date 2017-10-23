package bl.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
	
	public Nivel2JSON() {
		super();
	}
	
	public Nivel2JSON(int idNivel2, String audio, String palabra, int tildada) {
		super();
		this.idNivel2 = idNivel2;
		this.audio = audio;
		this.palabra = palabra;
		this.tildada = tildada;
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

}
