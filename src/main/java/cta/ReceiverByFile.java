package cta;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import cta.designe.listener.Algoritmos;
import cta.remote.stompbroker.ModelEventTrace;



public class ReceiverByFile implements Runnable {
	
	private Visualizador vis;
	private ConsultaTarea cTarea;
	private SimpMessagingTemplate smt;
	

	Algoritmos algoritmos;
	Logger log = Logger.getLogger("ReceiverByFile");


	public void run() {
		//Registrar este receiver para el sistema dado (max. 3 hilos)
		
		vis.getThreadReceiverByFileRegistry().put(cTarea.getNameSocketSistema(), this);
		vis.refreshLedsSocketsStatus();
		
		String cadenaMensaje;
		algoritmos = new Algoritmos();

		String nameConsulta = cTarea.getNombreConsultaFull();
		
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try {

			fr = new FileReader(cTarea.getNameFileSource());
			br = new  BufferedReader(fr);
			String[] sArrayFilter = null;
			
			do {
				
				
				try {
					//El visualizador es comun a todos los hilos [singleton] y el Text Area es el mimso para todos
					//el catalogFilter es construido por cada consulta activa, lo que implica que la 
					//construccion del catalog Filter debe elaborarse en base a todas las consultas activas y/o modo
	
					// filtrar por catalogo de filtros texto (normalmente por cada linea)
					sArrayFilter = vis.getCatalogFiltersRegistry(nameConsulta);
					Thread.sleep(10);
					cadenaMensaje = br.readLine();
	
	
					if(algoritmos.filterMatch(cadenaMensaje, sArrayFilter, vis.getFilterExclusive().isSelected())) {
						handlerWriteLine(cadenaMensaje);
					}
					
				
	
				} catch (Exception e) {
					String nameConsultaFull = cTarea.getNombreConsultaFull();
					Vector<String> paraVerMasks = new Vector<String>();
					for (String mask: vis.getCatalogFiltersRegistry(nameConsultaFull)) {
						paraVerMasks.add(mask);
					}
					System.err.println("Hilo "+ cTarea.nombreConsultaTareaFull() +" "+this +" trabajando Mascaras :"+paraVerMasks+ " "+ e.getMessage());
				}
				cadenaMensaje=br.readLine();
			
			} while ((vis.getFlagDisconnectRegistry()).get(cTarea.getNameSocketSistema()) == 0 |
				cadenaMensaje==null	);
		
		} catch (IOException e) {
			System.out.println("Problema al tratar fichero :" + cTarea.getNameFileSource());
			System.out.println(e.getMessage());
		}finally {
			//hacemos limpieza en el registro
			vis.getThreadReceiverByFileRegistry().remove(cTarea.getNameSocketSistema());
			vis.refreshLedsSocketsStatus();
			handlerWriteLine("Hilo ByFile Finalizado");
			log.info("Hilo ByFile de sistema " + this.cTarea.getNameSocketSistema() + ":" +this+ " finalizado.");
			
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	public void handlerWriteLine(String cadena) {
		
		//A�adimos identificador de sistema origen a la cadena
		cadena = cTarea.getNameSocketSistema().concat(" "+cadena);

		// Configuracion 1		
		// Solo Imprimimos el paquete recibido a caja visualizador(opcionalmente)
		if(vis.getCheckMostrarLineasTextArea().isSelected()) {
			vis.getTextArea().append(cadena.concat(System.getProperty("line.separator")));
		}
		// Poblamos stack Lineas de referencia (opcionalmente)
		if(vis.getChckbxBufferearConsulta().isSelected()) {
			synchronized(vis.getCadenasFiltradas()){
				try {
					vis.getCadenasFiltradas().add(cadena);
				}catch(java.lang.IllegalStateException ise) {
					vis.getCadenasFiltradas().poll();
					vis.getCadenasFiltradas().add(cadena);
				}
			}
			vis.getLinkedListCounter().setText(vis.getCadenasFiltradas().size()+"/"+ vis.Max_Size_Queue +" lineas");
		}
		
		// Configuracion 2
		// Escribimos linea a fichero
		

		// Configuracion 3
		// Comprobar Listeners
		if (vis.getCheckListener1().isSelected()) {  
		
			if (algoritmos.filterMatch(cadena,vis.getCatalogListener(), true)) {
				vis.getTextAreaHandlers().append(cadena.concat(System.getProperty("line.separator")));
			}
		}

		// Configuracion 4
		// Dispatching evento
		if(vis.chckbxPublishToWebsocket.isSelected()) {
			if (algoritmos.filterMatch(cadena,vis.getCatalogListener(), true)) {
				smt.convertAndSend("/channel/traces", new ModelEventTrace("eventTrace",cadena));
			}
			

			
		}
			
	}	

	public ReceiverByFile( Visualizador visualizador, ConsultaTarea cTarea, SimpMessagingTemplate smt ) {
		
		this.vis = visualizador;
		this.cTarea = cTarea;
		this.smt=smt;
	}
}
