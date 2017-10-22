package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Alumno database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Alumno.findAll", query="SELECT a FROM Alumno a"),
@NamedQuery(name="Alumno.findNick", query="SELECT a FROM Alumno a WHERE a.nickname= :nickname")	
})
public class Alumno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAlumno;

	private String apellidos;

	private String nickname;

	private String nombre;

	private String password;

	public Alumno() {
	}

	public int getIdAlumno() {
		return this.idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
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

}