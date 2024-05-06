package cta.remote.stompbroker;

import java.io.Serializable;

import lombok.Data;

@Data
public class Comando implements Serializable {
	/**
	 * Pojo para encapsular contenido mensajes control
	 */
	private static final long serialVersionUID = 1L;
	String tipo;
	String command;
	String[] args;
	String userId; 
}
