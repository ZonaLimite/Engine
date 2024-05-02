package cta;

import java.io.Serializable;
import java.util.Vector;

import cta.designe.listener.ModelFilter;

//Una consulta es un vector de modulos + filtro y su nombre de consulta
public class ConsultaTarea implements Serializable {

	Consulta consulta;
	String numeroMaquina;


	public Consulta getConsulta() {
		return consulta;
	}



	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}



	public String getNumeroMaquina() {
		return numeroMaquina;
	}



	public void setNumeroMaquina(String numeroMaquina) {
		this.numeroMaquina = numeroMaquina;
	}

	public String nombreConsultaTareaFull() {
		return this.consulta.getSistemaConsulta()+":"+getNumeroMaquina()+":"+this.consulta.getNameConsulta();
	}
	
	public String getNombreConsultaFull() {
		return consulta.getNombreConsultaFull();
	}
	
	public String getNameSocketSistema() {
		return consulta.getSistemaConsulta()+":"+numeroMaquina;
	}

	public ConsultaTarea(Consulta consulta, String numMaquina) {
	this.consulta = consulta;
	this.numeroMaquina = numMaquina;
	}
	

	
	
}
