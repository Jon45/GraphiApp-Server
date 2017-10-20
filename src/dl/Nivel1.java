package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Nivel1 database table.
 * 
 */
@Entity
@Table(name="`Nivel1`")
@NamedQuery(name="Nivel1.findAll", query="SELECT n FROM Nivel1 n")
public class Nivel1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idNivel1;

	@Column(name="Correcta")
	private int correcta;

	@Column(name="Palabra1")
	private String palabra1;

	@Column(name="Palabra2")
	private String palabra2;

	//bi-directional many-to-one association to Clase
	@ManyToOne
	@JoinColumn(name="Clase_idClase")
	private Clase clase;

	public Nivel1() {
	}

	public int getIdNivel1() {
		return this.idNivel1;
	}

	public void setIdNivel1(int idNivel1) {
		this.idNivel1 = idNivel1;
	}

	public int getCorrecta() {
		return this.correcta;
	}

	public void setCorrecta(int correcta) {
		this.correcta = correcta;
	}

	public String getPalabra1() {
		return this.palabra1;
	}

	public void setPalabra1(String palabra1) {
		this.palabra1 = palabra1;
	}

	public String getPalabra2() {
		return this.palabra2;
	}

	public void setPalabra2(String palabra2) {
		this.palabra2 = palabra2;
	}

	public Clase getClase() {
		return this.clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

}