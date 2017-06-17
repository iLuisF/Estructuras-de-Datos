public class TokenOperador extends Token{
	private char value;

	public TokenOperador(char c){
		value = c;
		switch(c){
			case '+' : tipo = ESSUMA;
						break;
			case '-' : tipo = ESRESTA;
						break;
			case '*' : tipo = ESPRODUCTO;
						break;
			case '/' : tipo = ESDIVISION;
						break;
			case '(' : tipo = ESPARIZQ;
						break;
			case ')' : tipo = ESPARDER;
						break;
			default : tipo = TOKENDESCONOCIDO;
			
		}
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
