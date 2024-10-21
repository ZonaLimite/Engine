package cta;

    public class CubaTool {
	int numeroCuba; 
	boolean pres;
	boolean plate;
	boolean open;
	boolean fill;
	
	private static String[] compartimentos = {"A","B","C"};
	
	public CubaTool (int numeroCuba, boolean pres, boolean plate, boolean open, boolean fill) {
		this.numeroCuba=numeroCuba;
		this.pres = pres;
		this.plate=plate;
		this.open=open;
		this.fill=fill;
	}
	public CubaTool() {
		
	}
	public static String convertToIHMCuba(int numeroCuba) {
		return new String((Math.floorDiv(numeroCuba, 3)+1)+compartimentos[Math.floorMod(numeroCuba, 3)]);
	}
	
	public static void main(String[] args) {
		
		System.out.println("0 es = "+CubaTool.convertToIHMCuba(0));
		System.out.println("1 es = "+CubaTool.convertToIHMCuba(1));		
		System.out.println("2 es = "+CubaTool.convertToIHMCuba(2));
		System.out.println("3 es = "+CubaTool.convertToIHMCuba(3));		
	}

}
