package dl;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Clase database table.
 * 
 */
@Entity
@NamedQuery(name="Clase.findAll", query="SELECT c FROM Clase c")
public class Clase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idClase;

	@Column(name="Fecha")
	private int fecha;

	@Column(name="Tematica")
	private String tematica;

	//bi-directional many-to-one association to Docente
	@ManyToOne
	@JoinColumn(name="Docente_idDocente")
	private Docente docente;

	//bi-directional many-to-one association to Nivel1
	@OneToMany(mappedBy="clase")
	private List<Nivel1> nivel1s;

	//bi-directional many-to-one association to Nivel2
	@OneToMany(mappedBy="clase")
	private List<Nivel2> nivel2s;

	//bi-directional many-to-one association to Resultado
	@OneToMany(mappedBy="clase")
	private List<Resultado> resultados;

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

	public List<Nivel1> getNivel1s() {
		return this.nivel1s;
	}

	public void setNivel1s(List<Nivel1> nivel1s) {
		this.nivel1s = nivel1s;
	}

	public Nivel1 addNivel1(Nivel1 nivel1) {
		getNivel1s().add(nivel1);
		nivel1.setClase(this);

		return nivel1;
	}

	public Nivel1 removeNivel1(Nivel1 nivel1) {
		getNivel1s().remove(nivel1);
		nivel1.setClase(null);

		return nivel1;
	}

	public List<Nivel2> getNivel2s() {
		return this.nivel2s;
	}

	public void setNivel2s(List<Nivel2> nivel2s) {
		this.nivel2s = nivel2s;
	}

	public Nivel2 addNivel2(Nivel2 nivel2) {
		getNivel2s().add(nivel2);
		nivel2.setClase(this);

		return nivel2;
	}

	public Nivel2 removeNivel2(Nivel2 nivel2) {
		getNivel2s().remove(nivel2);
		nivel2.setClase(null);

		return nivel2;
	}

	public List<Resultado> getResultados() {
		return this.resultados;
	}

	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}

	public Resultado addResultado(Resultado resultado) {
		getResultados().add(resultado);
		resultado.setClase(this);

		return resultado;
	}

	public Resultado removeResultado(Resultado resultado) {
		getResultados().remove(resultado);
		resultado.setClase(null);

		return resultado;
	}

}