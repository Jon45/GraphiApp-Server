package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Docente database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Docente.findAll", query="SELECT d FROM Docente d"),
@NamedQuery(name="Docente.findNickname", query="SELECT d FROM Docente d WHERE d.nickname = :nickname"),
@NamedQuery(name="Docente.findByNicknamePass", query="SELECT d FROM Docente d WHERE d.nickname= :nickname AND d.password= :password")})
public class Docente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDocente;

	private String apellidos;

	private String nickname;

	private String nombre;

	private String password;

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

}