package bl.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AlumnoJSON {
	
	@XmlElement
	private int idAlumno;
	
	@XmlElement
	private String nombre;
	
	@XmlElement
	private String apellidos;
	
	@XmlElement
	private String password;
	
	@XmlElement
	private String nickname;
	
	//Constructors
	public AlumnoJSON(){
		
	}
	
	public AlumnoJSON(String nombre, String apellidos, String password, String login){
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.password = password;
		this.nickname = login;
	}
	
	//Getters y setters

	public int getIdAlumno(){
		return idAlumno;
	}
	
	public void setIdAlumno(int idAlumno){
		this.idAlumno = idAlumno;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String login) {
		this.nickname = login;
	}
	
	
	

}
