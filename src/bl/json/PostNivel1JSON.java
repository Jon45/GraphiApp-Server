package bl.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PostNivel1JSON {
	
	@XmlElement
	private Nivel1JSON nivel1JSON;
	
	@XmlElement
	private String login;

	
	public PostNivel1JSON(){
		
	}
	public PostNivel1JSON(Nivel1JSON nivel1json, String login) {
		super();
		nivel1JSON = nivel1json;
		this.login = login;
	}
	public Nivel1JSON getNivel1JSON() {
		return nivel1JSON;
	}
	public void setNivel1JSON(Nivel1JSON nivel1json) {
		nivel1JSON = nivel1json;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	
	
	

}
