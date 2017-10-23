package bl.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import dl.Clase;

@XmlRootElement
public class Nivel1JSON {
    @XmlElement
	private int idNivel1;

    @XmlElement
	private int correcta;

    @XmlElement
	private String palabra1;

	@XmlElement
	private String palabra2;
    
    public Nivel1JSON() {
		super();
	}
    
    public Nivel1JSON(int idNivel1, int correcta, String palabra1, String palabra2) {
		super();
		this.idNivel1 = idNivel1;
		this.correcta = correcta;
		this.palabra1 = palabra1;
		this.palabra2 = palabra2;
	}

	public int getIdNivel1() {
		return idNivel1;
	}

	public void setIdNivel1(int idNivel1) {
		this.idNivel1 = idNivel1;
	}

	public int getCorrecta() {
		return correcta;
	}

	public void setCorrecta(int correcta) {
		this.correcta = correcta;
	}

	public String getPalabra1() {
		return palabra1;
	}

	public void setPalabra1(String palabra1) {
		this.palabra1 = palabra1;
	}

	public String getPalabra2() {
		return palabra2;
	}

	public void setPalabra2(String palabra2) {
		this.palabra2 = palabra2;
	}

}
