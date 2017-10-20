package dl;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Docente database table.
 * 
 */
@Entity
@NamedQuery(name="Docente.findAll", query="SELECT d FROM Docente d")
public class Docente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDocente;

	@Column(name="Apellidos")
	private String apellidos;

	@Column(name="Nickname")
	private String nickname;

	@Column(name="Nombre")
	private String nombre;

	@Column(name="Password")
	private String password;

	//bi-directional many-to-one association to Clase
	@OneToMany(mappedBy="docente")
	private List<Clase> clases;

	public Docente() {
	}

	public int getIdDocente() {
		return this.idDocente;
	}

	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Clase> getClases() {
		return this.clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}

	public Clase addClas(Clase clas) {
		getClases().add(clas);
		clas.setDocente(this);

		return clas;
	}

	public Clase removeClas(Clase clas) {
		getClases().remove(clas);
		clas.setDocente(null);

		return clas;
	}

}