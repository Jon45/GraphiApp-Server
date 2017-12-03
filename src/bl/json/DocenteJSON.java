package bl.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DocenteJSON {
	
	@XmlElement
	private int idDocente;
	
	@XmlElement
	private String apellidos;
	
	@XmlElement
	private String nickname;
	
	@XmlElement
	private String nombre;
	
	@XmlElement
	private String password;

	
	//Constructors
	public DocenteJSON(){
		
	}
	public DocenteJSON(int idDocente, String apellidos, String nickname, String nombre, String password) {
		super();
		this.idDocente = idDocente;
		this.apellidos = apellidos;
		this.nickname = nickname;
		this.nombre = nombre;
		this.password = password;
	}
	public int getIdDocente() {
		return idDocente;
	}
	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
