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
import cta.remote.RemoteEngine;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/engineTrace")
public class EngineController {

	@Autowired
	private RemoteEngine re;

	@GetMapping("/gui/sistemas")
	private List<String> allSistemas(@PathVariable String nameSistema) {
		return null;
	}

	// Devuelve las consultas registradas para un sistema
	@GetMapping("/gui/consultas/{sistema}")
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
			//devolver AckKnowledge
			break;
		default:

			// Default secuencia de sentencias.
		}

	}
}
