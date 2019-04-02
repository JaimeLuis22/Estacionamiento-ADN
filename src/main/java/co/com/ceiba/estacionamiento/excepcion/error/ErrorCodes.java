package co.com.ceiba.estacionamiento.excepcion.error;

public enum ErrorCodes {
	
	/*
	 * Errores del negocio y tecnico
	 */
	ERROR_NEG_400(400,  "Parametro invalido"),
	ERROR_NEG_401(401,  "Autorizacion negada"),
	ERROR_NEG_402(402,  "Respuesta vacia en base de datos");
	
	/*
	 * Atributos de los errores
	 */
    private final int codigo;
    private final String mensaje;

    /**
     * Constructor
     * @param codigo
     * @param mensaje
     */
    private ErrorCodes(int codigo, String mensaje) {
        this.codigo = codigo;
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
     * Metodo que obtiene el mensaje
     * @return
     */
    public String getMensaje() {
        return mensaje;
    }
}
