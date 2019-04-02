package co.com.ceiba.estacionamiento.excepcion;

public class EstacionamientoException extends Exception {

	/**
	 * Serial de la excepcion
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributo que representa el mensaje de la excepcion
	 */
	private String mensaje;
	
	/**
	 * Atributo que representa el codigo de la excepcion
	 */
	private int codigo;

	/**
	 * Constructor
	 */
	public EstacionamientoException() {
		// Constructor vacio
	}
	
	/**
	 * Constructor que recibe como parametro el mensaje
	 * @param mensaje
	 */
	public EstacionamientoException(String mensaje) {
		this.mensaje = mensaje;
	}
	
	/**
	 * Constructor que recibe como parametro el mensaje y el codigo
	 * @param mensaje
	 * @param codigo
	 */
	public EstacionamientoException(String mensaje, int codigo) {
		this.mensaje = mensaje;
		this.codigo = codigo;
	}

	/**
	 * Metodo que obtiene el mensaje
	 * @return
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Metodo que modifica el mensaje
	 * @param mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Metodo que obtiene el codigo
	 * @return
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Metodo que modifica el codigo
	 * @param codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}	
	
}
