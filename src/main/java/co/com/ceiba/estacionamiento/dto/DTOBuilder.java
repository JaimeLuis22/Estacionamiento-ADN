package co.com.ceiba.estacionamiento.dto;

public class DTOBuilder {
	
	/**
	 * Metodo que retorna el DTOResponseContainer recibiendo como parametro un objeto
	 * @param objeto
	 * @return
	 */
	public static DTOResponseContainer toDTOResponseContainer (Object objeto) {
		DTOResponseContainer contenedor = new DTOResponseContainer();
		contenedor.setPayload(objeto);
		return contenedor;
	}
	
	/**
	 * Metodo que retorna el DTOResponseContainer recibiendo como parametro el mensaje y codigo
	 * @param mensaje
	 * @param codigo
	 * @return
	 */
	public static DTOResponseContainer toDTOResponseContainer (String mensaje, int codigo) {
		DTOResponseContainer contenedor = new DTOResponseContainer();
		DTOResponseGeneric respuestaGenerica = new DTOResponseGeneric();
		respuestaGenerica.setMensaje(mensaje);
		respuestaGenerica.setCodigo(codigo);
		contenedor.setPayload(respuestaGenerica);
		return contenedor;
	}

}
