package bl.json;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClasesJSON {
	
	@XmlElement
	private List<ClaseJSON> listaClases;

	//Constructor
	public ClasesJSON(List<ClaseJSON> listaClases) {
		super();
		this.listaClases = listaClases;
	}

	//Getters and Setters
	public List<ClaseJSON> getListaClases() {
		return listaClases;
	}

	public void setListaClases(List<ClaseJSON> listaClases) {
		this.listaClases = listaClases;
	}
	

}
