package Daniela.TodoGamersShop.Exceptions;

public class AlmacenExcepcion extends RuntimeException{

    private static final long serialVersionUID= 1L;

    // envio de mensaje de excepción
    public AlmacenExcepcion(String mensaje){
        super(mensaje);
    }

    // envio de mensaje de excepción + excepción
    public AlmacenExcepcion(String mensaje, Throwable excepcion){
        super(mensaje, excepcion);
    }
}
