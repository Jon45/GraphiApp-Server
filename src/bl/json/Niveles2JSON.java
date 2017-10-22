package bl.json;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Niveles2JSON {
	@XmlElement(name="nivel2")
	private List <Nivel2JSON> listaNivel2JSON;

	public Niveles2JSON() {
		super();
	}
	
	public Niveles2JSON(List<Nivel2JSON> nivel2jsonList) {
		super();
		this.listaNivel2JSON = nivel2jsonList;
	}

	public List<Nivel2JSON> getListaNivel2JSON() {
		return listaNivel2JSON;
	}

	public void setListaNivel2JSON(List<Nivel2JSON> listaNivel2JSON) {
		this.listaNivel2JSON = listaNivel2JSON;
	}
}
