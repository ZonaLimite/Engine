package cta;

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

import cta.designe.listener.Algoritmos;



public class Receiver implements Runnable {
	private DatagramSocket mySocket = null;
	private Visualizador vis;
	private ConsultaTarea cTarea;
	

	Algoritmos algoritmos;
	Logger log = Logger.getLogger("Receiver");


	public void run() {
		//Registrar este receiver para el sistema dado (max. 3 hilos)
		Vector<Receiver> vThreads= vis.getThreadReceiverRegistry().get(cTarea.getNameSocketSistema());
		vThreads.add(this);
		vis.getThreadReceiverRegistry().put(cTarea.getNameSocketSistema(), vThreads);
		vis.refreshLedsSocketsStatus();
		
		String cadenaMensaje;
		algoritmos = new Algoritmos();

		try {
			log.info("Tama�o ajustado de buffer DatagramSocket :"+ mySocket.getReceiveBufferSize());
			log.info("Desde Hilo "+cTarea.getNameSocketSistema()+" trabajando "+ cTarea.getNombreConsultaFull());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int sizeBufferDatagramPacket=4096;
		int sizeReadBytes=2048;
		String[] sArrayFilter = null;

		do {
			byte[] RecogerServidor_bytes = new byte[sizeBufferDatagramPacket];
		
			try {
				// EsperamoHilo Finalizado"s a recibir un paquete/
				DatagramPacket servPaquete = new DatagramPacket(RecogerServidor_bytes, sizeReadBytes);
				mySocket.receive(servPaquete);
				
				String sPacket = new String(RecogerServidor_bytes).trim();

				//El visualizador es comun a todos los hilos [singleton] y el Text Area es el mimso para todos
				//el catalogFilter es construido por cada consulta activa, lo que implica que la 
				//construccion del catalog Filter debe elaborarse en base a todas las consultas activas y/o modo


				//sArrayFilter = vis.getCatalogFilter(sistemaSocket);
				String nameConsulta = cTarea.getNombreConsultaFull();
				sArrayFilter = vis.getCatalogFiltersRegistry(nameConsulta);
				
				// Splitamos el mensaje recibido en lineas
				// Por cada LinesistemaSocket
				StringTokenizer st = new StringTokenizer(sPacket, System.getProperty("line.separator") + "|\r");
				while (st.hasMoreTokens()) {
					cadenaMensaje = st.nextToken();

					// filtrar por catalogo de filtros texto (normalmente por cada linea)
					if(algoritmos.filterMatch(cadenaMensaje, sArrayFilter, vis.getFilterExclusive().isSelected())) {
						handlerWriteLine(cadenaMensaje);
					}
				}

			} catch (Exception e) {
				String nameConsultaFull = cTarea.getNombreConsultaFull();
				Vector paraVerMasks = new Vector();
				for (String mask: vis.getCatalogFiltersRegistry(cTarea.getNombreConsultaFull())) {
					paraVerMasks.add(mask);
				}
				System.err.println("Hilo "+ cTarea.nombreConsultaTareaFull() +" "+this +" trabajando Mascaras :"+paraVerMasks+ " "+ e.getMessage());
			}
		} while ((vis.getFlagDisconnectRegistry()).get(cTarea.getNameSocketSistema()) == 0);
		//hacemos limpieza en el registro
		vThreads= vis.getThreadReceiverRegistry().get(cTarea.getNameSocketSistema());
		vThreads.remove(this);
		vis.getThreadReceiverRegistry().put(cTarea.getNameSocketSistema(), vThreads);
		vis.refreshLedsSocketsStatus();
		handlerWriteLine("Hilo Finalizado");
		log.info("Hilo de sistema " + this.cTarea.getNameSocketSistema() + ":" +this+ " finalizado.");
		mySocket = null;
	
	}

	public void handlerWriteLine(String cadena) {
		
		//A�adimos identificador de sistema origen a la cadena
		cadena = cTarea.getNameSocketSistema().concat(cadena);

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
			//Esto podria ser hecho implementando un cliente JMS, o Rabbit o Kafka
	}



	public Receiver(DatagramSocket socket, Visualizador visualizador, ConsultaTarea cTarea ) {
		this.mySocket = socket;
		this.vis = visualizador;
		this.cTarea = cTarea;
	}
}
