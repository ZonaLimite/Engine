package cta.remote.stompbroker;

import java.io.Serializable;

import lombok.Data;

@Data
public class ModelEventTrace implements Serializable{
	private static final long serialVersionUID = 1L;
	String tipoResult;
	String data;
	
	public ModelEventTrace(String tipoResult, String data) {
		super();
		this.tipoResult = tipoResult;
		this.data = data;
	}
	
	

}
