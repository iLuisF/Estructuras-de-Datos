/**
* Clase que crea una excepción que será lanzada cuando se intente crear una fracción con denominador cero.
* Por definición de una fracción, el denominador nunca puede ser cero.
* @author Armando Ballinas
* @version 1.0
*/
public class IlegalDenominatorException extends Exception{
	/**
	*Constructor vacío de la clase.
	*/
	public IlegalDenominatorException(){ ; }
	/**
	*Constructor que recibe una cadena que será el mensaje desplegado por esta excepción.
	*/
	public IlegalDenominatorException(String mensaje){
		super(mensaje);
	}
}
