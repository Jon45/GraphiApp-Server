package bl.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PostNivel2JSON {
	@XmlElement
	private Nivel2JSON nivel2JSON;
	
	@XmlElement
	private String login;
	
	@XmlElement
	private String url;

	
	public PostNivel2JSON(){
		
	}
	public PostNivel2JSON(Nivel2JSON nivel2json, String login, String url) {
		super();
		nivel2JSON = nivel2json;
		this.login = login;
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
