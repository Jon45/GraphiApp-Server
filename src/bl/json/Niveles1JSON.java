package bl.json;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Niveles1JSON {
	@XmlElement(name="nivel1")
	private List <Nivel1JSON> listaNivel1JSON;

	public Niveles1JSON() {
		super();
	}
	
	public Niveles1JSON(List<Nivel1JSON> listaNivel1JSON) {
		super();
		this.listaNivel1JSON = listaNivel1JSON;
	}

	public List<Nivel1JSON> getListaNivel1JSON() {
		return listaNivel1JSON;
	}

	public void setListaNivel1JSON(List<Nivel1JSON> listaNivel1JSON) {
		this.listaNivel1JSON = listaNivel1JSON;
	}
}
