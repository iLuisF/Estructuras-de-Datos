import java.util.LinkedList;

public class ExpressionTokenizer{

	private LinkedList<Token> tokens;

	public ExpressionTokenizer(String cad){
		char[] s = cad.toCharArray();
		tokens = new LinkedList<>();
		int tam = s.length;
		int i = 0;
		char actual;
		StringBuffer sb;
		while (i < tam){
			actual = s[i];
			if(actual == '+' || actual == '-' || actual == '*' || actual == '/' || actual == '(' || actual == ')'){
				tokens.addLast(new TokenOperador(actual));
				i++;
			}
			else{
				if(actual == ' ' || actual == '\n'){
					i++;
				}
				else{
					sb = new StringBuffer();
					while(Character.isDigit(actual)){
						sb.append(actual);
						i++;
						if(i < tam){
							actual = s[i];						
						}
						else{
							break;
						}
					}
					//System.out.println(sb.toString());
					tokens.addLast(new TokenNum(new Integer(sb.toString()).intValue()));
				}
			}
		}	
	}

	public LinkedList<Token> getTokens(){
		return tokens;
	}
	
	public void PrintTokens(){
		for(Token x : tokens){
			x.printToken();
		}
	}	

}
