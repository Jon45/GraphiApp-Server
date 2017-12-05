package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Clase database table.
 * 
 */
@Entity
@NamedQueries ({
@NamedQuery(name="Clase.findAll", query="SELECT c FROM Clase c"),
@NamedQuery(name="Clase.findId", query="SELECT c FROM Clase c WHERE c.idClase= :idClase"),
@NamedQuery(name="Clase.findFecha", query="SELECT c.idClase FROM Clase c WHERE c.fecha= :fecha"),
@NamedQuery(name="Clase.findByFecha", query="SELECT c FROM Clase c WHERE c.fecha= :fecha"),
@NamedQuery(name="Clase.findByTeacher", query="SELECT c FROM Clase c WHERE c.docente= :docente")
})
public class Clase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idClase;

	private int fecha;

	private String tematica;

	//bi-directional many-to-one association to Docente
	@ManyToOne
	@JoinColumn(name="Docente_idDocente")
	private Docente docente;

	public Clase() {
	}

	public int getIdClase() {
		return this.idClase;
	}

	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}

	public int getFecha() {
		return this.fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public String getTematica() {
		return this.tematica;
	}

	public void setTematica(String tematica) {
		this.tematica = tematica;
	}

	public Docente getDocente() {
		return this.docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

}