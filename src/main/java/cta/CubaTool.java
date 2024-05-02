package cta;

    public class CubaTool {
	int numeroCuba; 
	boolean pres;
	boolean plate;
	boolean open;
	boolean fill;
	
	String[] compartimentos = {"A","B","C"};
	
	public CubaTool (int numeroCuba, boolean pres, boolean plate, boolean open, boolean fill) {
		this.numeroCuba=numeroCuba;
		this.pres = pres;
		this.plate=plate;
		this.open=open;
		this.fill=fill;
	}
	public CubaTool() {
		
	}
	public String convertToIHMCuba(int numeroCuba) {
		return new String((Math.floorDiv(numeroCuba, 3)+1)+compartimentos[Math.floorMod(numeroCuba, 3)]);
	}
	
	public static void main(String[] args) {
		CubaTool miCubaTool = new CubaTool();
		System.out.println("0 es = "+miCubaTool.convertToIHMCuba(0));
		System.out.println("1 es = "+miCubaTool.convertToIHMCuba(1));		
		System.out.println("2 es = "+miCubaTool.convertToIHMCuba(2));
		System.out.println("3 es = "+miCubaTool.convertToIHMCuba(3));		
	}

}
