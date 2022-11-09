package Daniela.TodoGamersShop.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    //  mensaje de excepción al no encontrar archivo
    public FileNotFoundException(String mensaje){
        super(mensaje);
    }

    // mensaje de excepcion al no encontrar archivo + excepción
    public FileNotFoundException(String mensaje, Throwable excepcion){
        super(mensaje, excepcion);
    }
}
