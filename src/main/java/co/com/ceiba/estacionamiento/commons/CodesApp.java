package co.com.ceiba.estacionamiento.commons;

public enum CodesApp {
	
	/*
	 * Errores del negocio y tecnico
	 */
	ERROR_NEG_400(400,  "Parametro invalido"),
	ERROR_NEG_401(401,  "Autorizacion negada"),
	ERROR_NEG_402(402,  "Respuesta vacia en base de datos"),
	
	/*
	 * Codigos y mensajes de respuesta
	 */
	INFO_REGISTRO_EXITOSO(10,  "Registro exitoso"),
	INFO_ESTADO_DISPONIBLE(11, "Disponible"),
	INFO_ESTADO_ACTIVO(12, "Activo"),
	INFO_ESTADO_INACTIVO(13, "Inactivo"),
	INFO_ESTADO_OCUPADO(14, "Ocupado"),
	INFO_SUNDAY(15, "sunday"),
	INFO_MONDAY(16, "monday"),
	INFO_FECHA_FORMATO(17, "yyyy-MM-dd HH:mm:ss"),
	INFO_FECHA_FORMATO_DIA(18, "EEE"),
	INFO_NO_REGISTRO(19, "No existe registro"),
        INFO_EXISTE_REGISTRO(20, "Existe el registro"),
	// Vehiculo	
	INFO_VEHICULO_MOTO(22, "Moto"),
	INFO_VEHICULO_CARRO(23, "Carro"),
	INFO_VEHICULO_PLACA_A(24, "a"),
	INFO_VEHICULO_HORA_CARRO(25, "1000"),
	INFO_VEHICULO_HORA_MOTO(26, "500"),
	INFO_VEHICULO_DIA_CARRO(27, "8000"),
	INFO_VEHICULO_DIA_MOTO(28, "4000"),
	INFO_VEHICULO_MOTO_ADICIONAL(29, "2000"),
	INFO_VEHICULO_NO_COBRO(30, "Vehiculo no genera cobro"),
	INFO_VEHICULO_MOTO_CILINDRAJE(31, "Cilindraje vacio"),	
	// Bahia
	INFO_BAHIA_NO_DISPONIBLES(40, "No hay bahias disponibles");
	
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
    private CodesApp(int codigo, String mensaje) {
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
