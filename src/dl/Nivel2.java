package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Nivel2 database table.
 * 
 */
@Entity
@NamedQuery(name="Nivel2.findAll", query="SELECT n FROM Nivel2 n")
public class Nivel2 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNivel2;

	@Column(name="Audio")
	private String audio;

	@Column(name="Palabra")
	private String palabra;

	@Column(name="Tildada")
	private int tildada;

	//bi-directional many-to-one association to Clase
	@ManyToOne
	@JoinColumn(name="Clase_idClase")
	private Clase clase;

	public Nivel2() {
	}

	public int getIdNivel2() {
		return this.idNivel2;
	}

	public void setIdNivel2(int idNivel2) {
		this.idNivel2 = idNivel2;
	}

	public String getAudio() {
		return this.audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public String getPalabra() {
		return this.palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public int getTildada() {
		return this.tildada;
	}

	public void setTildada(int tildada) {
		this.tildada = tildada;
	}

	public Clase getClase() {
		return this.clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

}