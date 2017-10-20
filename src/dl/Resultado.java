package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Resultado database table.
 * 
 */
@Entity
@NamedQuery(name="Resultado.findAll", query="SELECT r FROM Resultado r")
public class Resultado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idResultado;

	@Column(name="Fecha")
	private int fecha;

	@Column(name="PuntosNivel1")
	private float puntosNivel1;

	@Column(name="PuntosNivel2")
	private float puntosNivel2;

	@Column(name="PuntosNivel3")
	private float puntosNivel3;

	@Column(name="PuntosNivel4")
	private float puntosNivel4;

	@Column(name="PuntosNivel5")
	private float puntosNivel5;

	@Column(name="PuntosNivel8")
	private float puntosNivel8;

	//bi-directional many-to-one association to Alumno
	@ManyToOne
	@JoinColumn(name="Alumno_idAlumno")
	private Alumno alumno;

	//bi-directional many-to-one association to Clase
	@ManyToOne
	@JoinColumn(name="Clase_idClase")
	private Clase clase;

	public Resultado() {
	}

	public int getIdResultado() {
		return this.idResultado;
	}

	public void setIdResultado(int idResultado) {
		this.idResultado = idResultado;
	}

	public int getFecha() {
		return this.fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public float getPuntosNivel1() {
		return this.puntosNivel1;
	}

	public void setPuntosNivel1(float puntosNivel1) {
		this.puntosNivel1 = puntosNivel1;
	}

	public float getPuntosNivel2() {
		return this.puntosNivel2;
	}

	public void setPuntosNivel2(float puntosNivel2) {
		this.puntosNivel2 = puntosNivel2;
	}

	public float getPuntosNivel3() {
		return this.puntosNivel3;
	}

	public void setPuntosNivel3(float puntosNivel3) {
		this.puntosNivel3 = puntosNivel3;
	}

	public float getPuntosNivel4() {
		return this.puntosNivel4;
	}

	public void setPuntosNivel4(float puntosNivel4) {
		this.puntosNivel4 = puntosNivel4;
	}

	public float getPuntosNivel5() {
		return this.puntosNivel5;
	}

	public void setPuntosNivel5(float puntosNivel5) {
		this.puntosNivel5 = puntosNivel5;
	}

	public float getPuntosNivel8() {
		return this.puntosNivel8;
	}

	public void setPuntosNivel8(float puntosNivel8) {
		this.puntosNivel8 = puntosNivel8;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Clase getClase() {
		return this.clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

}