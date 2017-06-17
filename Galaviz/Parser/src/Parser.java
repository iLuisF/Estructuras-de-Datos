import java.util.LinkedList;

public class Parser{

	private static LinkedList<Token> tokens;

	private static boolean parseExpresion(LinkedList<Token> tokens){
		
		return parseT(tokens) || 
		parseTMasE(tokens) || parseTMenosE(tokens) ;
	}
	
	private static boolean parseTMasE(LinkedList<Token> tokens){
		return false;
	}
	
	private static boolean parseTMenosE(LinkedList<Token> tokens){
		return false;
	}
	
	private static boolean parseT(LinkedList<Token> tokens){
		/*
		System.out.println("me invocaron");
		for(Token x : tokens){
			x.printToken();
		}*/
		
		boolean b1 = parseF(tokens);
		boolean b2 = parseFPorT(tokens);
		boolean b3 = parseFEntreT(tokens);
	//	System.out.println("parseFentreT "+ b3);
		return  b1 || b2 || b3 ;
	}
	
	private static boolean parseFPorT(LinkedList<Token> tokens){
		return false;
	}
	
	private static boolean parseFEntreT(LinkedList<Token> tokens){
		/*for(Token x : tokens){
			x.printToken();
		}*/
		if (tokens.size() < 3) return false;
		//System.out.println("me invocaron en div");
		for(int i=1; i< tokens.size()-1;i++){
			boolean es_division = tokens.get(i).getTipo() ==Token.ESDIVISION;
			if(es_division){
				
				LinkedList<Token> l1 = new LinkedList<>(tokens.subList(0,i));
				
				LinkedList<Token> l2 = new LinkedList<>(tokens.subList(i+1,tokens.size()));
				if( parseF(l1) && parseT(l2)) return true;
			}
		}
		return false;
	}
	
	
	private static boolean parseF(LinkedList<Token> tokens){
		
		return parseNum(tokens) || parseParE(tokens);
	}
	
	private static boolean parseParE(LinkedList<Token> tok){
		if(tokens.size() < 3) return false;
		LinkedList<Token> tokens = new LinkedList<>(tok);
		boolean par_izq = tokens.removeFirst().getTipo() == Token.ESPARIZQ;
		boolean par_der = tokens.removeLast().getTipo() == Token.ESPARDER;
		//System.out.println("par izq "+ par_izq);
		//System.out.println("par der "+ par_der);
		
		return par_izq && par_der && parseExpresion(tokens);
	}
	
	private static boolean parseNum(LinkedList<Token> tokens){
		return tokens.size() == 1 && tokens.getFirst().getTipo() == Token.ESNUM;
	}
	
	public Parser(LinkedList<Token> l){
		tokens = l;
	}

	public boolean esValida(){
		return parseExpresion(tokens);
	}
}