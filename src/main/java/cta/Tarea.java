package cta;

import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Logger;

public class Tarea {
	Logger logger = Logger.getLogger("Tarea");
	String nameTarea; // Un nombre descriptivo para esta tarea
	Vector<ConsultaTarea> consultas; //El vector contenedor de las Consultas dise�ada en el manejador de consultas
	
	int numMaquina; //indica el numero de maquina para enviar comandos y recibir el stream de logs de PPC
	//se setea en la consulta a utilizar, aprovechando el campo de la consulta previsto para ese fin
	Object target; //canal de escritura de resultados confeccionado con esta consulta
	String typeTargetOutput; //a un Text Area, a un Fichero , a un Broker de mensajeria
	Object socketSource; //El socket que proporciona el flujo fuente de informacion para su tratamiento
	
	public Tarea() {
		
	}
	
	public int getNumMaquina() {
		return numMaquina;
	}

	public void setNumMaquina(int numMaquina) {
		this.numMaquina = numMaquina;
	}

	public String getNameTarea() {
		return nameTarea;
	}
	public void setNameTarea(String nameTarea) {
		this.nameTarea = nameTarea;
	}
	public Vector<ConsultaTarea> getConsultas() {
		return consultas;
	}
	public void setConsultas(Vector<ConsultaTarea> consultas) {
		this.consultas = consultas;
	}
	public Object getTarget() {
		return target;
	}
	public void setTarget(Object target) {
		this.target = target;
	}
	public String getTypeTargetOutput() {
		return typeTargetOutput;
	}
	public void setTypeTargetOutput(String typeTargetOutput) {
		this.typeTargetOutput = typeTargetOutput;
	}
	public Object getSocketSource() {
		return socketSource;
	}
	public void setSocketSource(Object socketSource) {
		this.socketSource = socketSource;
	}
	public ConsultaTarea contieneEstaConsulta(String sNameConsultaTarea) {
		ConsultaTarea consultaTarea = null;
		for (ConsultaTarea cTarea:this.consultas) {
			if( cTarea.nombreConsultaTareaFull().equals(sNameConsultaTarea)) {
				return cTarea;
			}
		}
		return consultaTarea;
	
	}
	
	public void removeConsultaTarea (String sNameConsultaTarea) {
		Iterator<ConsultaTarea> itConsultasTarea = this.consultas.iterator();
		ConsultaTarea cTarea;
		while(itConsultasTarea.hasNext()){
			cTarea = itConsultasTarea.next();
			if( cTarea.nombreConsultaTareaFull().equals(sNameConsultaTarea)) {
				itConsultasTarea.remove();
			};	
		
		}
	}
}
