package cta.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cta.Visualizador;
import cta.designe.listener.ModelFilter;

@Component
public class RemoteEngine {
	@Autowired
	private Visualizador vis;
	
	public void SelectSistema(String sistema) {
		vis.getComboSistemas().setSelectedItem(sistema);
	}

	public void bConectar() {
		vis.getConectar().doClick();
	}
	
	public void bDesconectar() {
		vis.getDesconectar().doClick();
	}
	

	public void adjustNumTop(int num) {
		vis.spinner.setValue(num);
	}
	
	public void incluirModelFilterAListener(String nameModelFilter) {
		vis.incluirModelFilterAListenersRemote(nameModelFilter);
	}
	
	public void borrarModelFilterDeListenerActivos(String nameModelFilterActivo) {
		ModelFilter modelFilter = vis.getCatalogoModelFilters().get(nameModelFilterActivo);
		vis.borrarModelFilterDeListener(modelFilter);
	}
	
	//Ojo la clave es compuesta con sistema:nameconsulta
	public void selectConsulta(String nameSistemaConsulta) {
		 vis.getCombo_Consultas().setSelectedItem( vis.getCatalogoConsultas().get(nameSistemaConsulta));
		 
	}
	
	
	
	
}
