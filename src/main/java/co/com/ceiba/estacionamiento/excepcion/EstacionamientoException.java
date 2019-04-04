package co.com.ceiba.estacionamiento.excepcion;

public class EstacionamientoException extends RuntimeException {

	/**
	 * Serial de la excepcion
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributo que representa el mensaje de la excepcion
	 */
	private final String mensaje;
	
	/**
	 * Atributo que representa el codigo de la excepcion
	 */
	private final int codigo;
	
	/**
	 * Constructor que recibe como parametro el mensaje
	 * @param mensaje
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
	 * Metodo que obtiene el codigo
	 * @return
	 */
	public int getCodigo() {
		return codigo;
	}	
	
}
