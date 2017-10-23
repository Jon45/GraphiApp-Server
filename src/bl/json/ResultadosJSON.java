package bl.json;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResultadosJSON {
	
	@XmlElement
	List<ResultadoJSON> listaResultadoJSON;
	
	public ResultadosJSON(){
		
	}
	public ResultadosJSON(List<ResultadoJSON> listaResultadoJSON){
		this.listaResultadoJSON = listaResultadoJSON;
	}
	public List<ResultadoJSON> getListaResultadoJSON() {
		return listaResultadoJSON;
	}
	public void setListaResultadoJSON(List<ResultadoJSON> listaResultadoJSON) {
		this.listaResultadoJSON = listaResultadoJSON;
	}

}
