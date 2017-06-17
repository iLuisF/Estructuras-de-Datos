public abstract class Token{


	public static final int ESNUM = 0;
	public static final int ESSUMA = 1;
	public static final int ESRESTA = 2;
	public static final int ESPRODUCTO = 3;
	public static final int ESDIVISION = 4;
	public static final int ESPARIZQ = 5;
	public static final int ESPARDER = 6;
	public static final int TOKENDESCONOCIDO = -1;

	protected int tipo;
	
	public abstract int getTipo();

	public abstract void printToken();

}
