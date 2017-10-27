package bl.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PostNivel2JSON {
	@XmlElement
	private Nivel2JSON nivel2JSON;
	
	@XmlElement
	private String login;

	
	public PostNivel2JSON(){
		
	}
	public PostNivel2JSON(Nivel2JSON nivel2json, String login) {
		super();
		nivel2JSON = nivel2json;
		this.login = login;
	}
	public Nivel2JSON getNivel2JSON() {
		return nivel2JSON;
	}
	public void setNivel2JSON(Nivel2JSON nivel2json) {
		nivel2JSON = nivel2json;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

}
