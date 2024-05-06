package cta.remote.stompbroker;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import cta.remote.service.RemoteEngine;

@Controller
public class EngineProtocol {
	
	@Autowired
	private SimpMessagingTemplate webSocket;
	
	@Autowired
	private RemoteEngine re;
	
	private String[] colores = {"red", "green", "blue", "magenta", "purple", "orange"};
	
	@MessageMapping("/manage_engine")
	//@SendTo("/channel/control") //De momento no hay necesidad de brokear
	public ModelResultData recibeMensaje(ModelResultData comando) {
		ModelResultData rd=null;
		
		/*String tipoResult;
		String[] data;*/
		System.out.println("Comando recibido"+ comando.getTipoResult());
		switch (comando.tipoResult) {
		
		case "maquinas":
			//accion sobre engine

			//callback 
			rd = new ModelResultData();
			rd.tipoResult="maquinas";
			rd.data = new String[]{"1","2"};
			this.enviarDirecto(rd);
			break;
			
		case "sistemas":
			//accion sobre engine

			//callback 
			rd = new ModelResultData();
			rd.tipoResult="sistemas";
			rd.data = re.getSistemasRegistrados();
			this.enviarDirecto(rd);
			break;
			
		case "modulos":	
			//accion sobre engine

			//callback 
			rd = new ModelResultData();
			rd.tipoResult="modulos";
			rd.data = re.getModulosRegistrados(comando.data[0]);
			//webSocket.convertAndSend("/chat/control/" + userId, rd);			
			this.enviarDirecto(rd);
			break;

		case "listeners":	
			//accion sobre engine

			//callback 
			rd = new ModelResultData();
			rd.tipoResult="listeners";
			rd.data = re.getListenersRegistrados();
			//webSocket.convertAndSend("/chat/control/" + userId, rd);			
			this.enviarDirecto(rd);
			break;
			
		case "selectSistema":
			//accion sobre engine
			re.SelectSistema(comando.data[0]);
			//callback 
			rd = new ModelResultData();
			rd.tipoResult="sistemas";
			rd.data = re.getSistemasRegistrados();
			//webSocket.convertAndSend("/chat/control/" + userId, rd);
			break;	
		case "conectar":
			re.bConectar();
			// pendiente remitir ackknowlegde
			break;

		case "desconectar":
			re.bDesconectar();
			// pendiente remitir ackknowlegde
			break;

		case "adjustnumtop":
			re.adjustNumTop(Integer.parseInt(comando.data[0]));
			// pendiente remitir ackknowlegde
			break;

		case "incluirlistenermodelfilter":
			re.incluirModelFilterAListener(comando.data[0]);
			// devolver modelfiltersActivos
			break;

		case "quitarlistenermodelfilter":
			re.borrarModelFilterDeListenerActivos(comando.data[0]);
			// devolver modelfiltersActivos
			break;
		case "selectConsulta":
			re.selectConsulta(comando.data[0]);
			//devolver AckKnowledge siempre como Response Entity
			break;
		}	
		return rd;
	}
	@MessageMapping("/mensaje")
	@SendTo("/channel/mensaje")
	public ModelMensaje recibeMensaje(ModelMensaje mensaje) {
		mensaje.setFecha(new Date().getTime());
		
		if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("nuevo usuario");
		} else {
			//chatService.guardar(mensaje);
		}
		
		return mensaje;
	}

	@MessageMapping("/escribiendo")
	@SendTo("/channel/escribiendo")
	public String estaEscribiendo(String username) {
		return username.concat(" está escribiendo ...");
	}
	
	
	public void enviarDirecto(ModelResultData mrd) {
		webSocket.convertAndSend("/channel/control", mrd);	
		System.out.println("Tratando de enviar a cliente:"+mrd);
	}
}
