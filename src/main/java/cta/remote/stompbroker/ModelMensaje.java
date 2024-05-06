package cta.remote.stompbroker;

import java.io.Serializable;

import lombok.Data;
@Data
public class ModelMensaje implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private String id;

		private String texto;
		private Long fecha;
		private String username;
		private String tipo;
		private String color;

}
