public class Main{

	public static void main(String[] args){
		ExpressionTokenizer et = new ExpressionTokenizer(args[0]);
		//System.out.println("tokens:");
		//et.PrintTokens();
		Parser p = new Parser(et.getTokens());
		System.out.println(p.esValida());
	}

}
