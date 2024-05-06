package cta.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import cta.Visualizador;
import cta.remote.service.RemoteEngine;

//@CrossOrigin(origins = { "http://192.168.1.5:4200" }) Configurado en Configuracion
@RestController
@RequestMapping("/engineTrace")
public class EngineController {

	@Autowired
	private RemoteEngine re;

	@GetMapping("/gui/sistemas")//un listado de strings con los nombres de sistema
	private String[] allSistemas() {
		return re.getSistemasRegistrados();
	}

	
	@GetMapping("/gui/consultas/{sistema}")// Devuelve las consultas registradas para un sistema dado
	private List<String> consultasBySistema(@PathVariable String nameSistema) {
		return null;
	}

	@GetMapping("/gui/{command}/{param}")
	private void remoteCommand(@PathVariable String command, @PathVariable String param) {
		switch (command) {
		case "selectSistema":
			re.SelectSistema(param);
			// pendiente remitir a clientes las consultas preparadas por Rest
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
			re.adjustNumTop(Integer.parseInt(param));
			// pendiente remitir ackknowlegde
			break;

		case "incluirlistenermodelfilter":
			re.incluirModelFilterAListener(param);
			// devolver modelfiltersActivos
			break;

		case "quitarlistenermodelfilter":
			re.borrarModelFilterDeListenerActivos(param);
			// devolver modelfiltersActivos
			break;
		case "selectConsulta":
			re.selectConsulta(param);
			//devolver AckKnowledge siempre como Response Entity
			break;
		default:

			// Default secuencia de sentencias.
		}

	}
}
