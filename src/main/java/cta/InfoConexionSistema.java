package cta;

public class InfoConexionSistema {
	private String id;

	private String ip;
	private int topNumero;
	private String nameSocketSistema;
	private String centro;
	private String JChekBoxName;
	
	public String getJChekBoxName() {
		return JChekBoxName;
	}
	public void setJChekBoxName(String jChekBoxName) {
		JChekBoxName = jChekBoxName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getTopNumero() {
		return topNumero;
	}
	public void setTopNumero(int topNumero) {
		this.topNumero = topNumero;
	}
	public String getNameSocketSistema() {
		return nameSocketSistema;
	}
	public void setNameSocketSistema(String nameSocketSistema) {
		this.nameSocketSistema = nameSocketSistema;
	}
	public String getCentro() {
		return centro;
	}
	public void setCentro(String centro) {
		this.centro = centro;
	}
	public InfoConexionSistema(String id,String ip, int topNumero, String nameSocketSistema, String centro) {
		super();
		this.id = id;
		this.ip = ip;
		this.topNumero = topNumero;
		this.nameSocketSistema = nameSocketSistema;
		this.centro = centro;
	}
	public InfoConexionSistema() {
		super();
	}
	
}
