package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Nivel1 database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Nivel1.findAll", query="SELECT n FROM Nivel1 n"),
@NamedQuery(name="Nivel1.findClaseFecha", query="SELECT n FROM Nivel1 n WHERE n.clase.fecha= :fecha")
})
public class Nivel1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNivel1;

	private int correcta;

	private String palabra1;

	private String palabra2;

	//bi-directional many-to-one association to Clase
	@ManyToOne
	@JoinColumn(name="Clase_idClase")
	private Clase clase;

	public Nivel1() {
	}
	
	

	public Nivel1(int correcta, String palabra1, String palabra2, Clase clase) {
		super();
		this.correcta = correcta;
		this.palabra1 = palabra1;
		this.palabra2 = palabra2;
		this.clase = clase;
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