public class TokenNum extends Token{

	private int value;


	public TokenNum(int n){
		value = n;
		tipo = ESNUM;
	}
	
	public int getValue(){
		return value;
	}
	
	public int getTipo(){
		return tipo;
	}
	
	public void printToken(){
		System.out.println(value);
	}

}
