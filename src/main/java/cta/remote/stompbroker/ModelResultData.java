package cta.remote.stompbroker;

import java.io.Serializable;

import lombok.Data;

@Data
public class ModelResultData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String tipoResult;
	String[] data;

}
