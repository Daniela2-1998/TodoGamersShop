package Daniela.TodoGamersShop.Services;

import Daniela.TodoGamersShop.Exceptions.AlmacenExcepcion;
import Daniela.TodoGamersShop.Exceptions.FileNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class AlmacenServicioImpl implements AlmacenServicio{

                 //  VARIABLES
    @Value("${storage.location}")
    private String storageLocation;

                //   METODOS
    //  INICIO ALMACEN
    @Override
    public void iniciarAlmacenDeArchivos() {
        try{
            Files.createDirectories(Paths.get(storageLocation));
        }catch (IOException exception){
            throw new AlmacenExcepcion("Error al iniciar el almacen de archivos" + exception);
        }

    }

    // ALMACENAMIENTO DE ARCHIVOS
    @Override
    public String almacenarArchivo(MultipartFile archivo) {
        String nombreArchivo = archivo.getOriginalFilename();
        // verificación de que el archivo no este vacio
        if (archivo.isEmpty()){
            throw new AlmacenExcepcion("No es posible almacenar un archivo vacio");
        } else {

           try {
               InputStream inputStream = archivo.getInputStream();
               Files.copy(
                       inputStream,
                       Paths.get(storageLocation).resolve(nombreArchivo),
                       StandardCopyOption.REPLACE_EXISTING);
           }catch (IOException exception){
               throw new AlmacenExcepcion("Error al almacenar el archivo" + nombreArchivo, exception);
           }

        }
        return nombreArchivo;
    }


    // CARGAR ARCHIVO
    @Override
    public Path cargarArchivo(String nombreArchivo) {
        return Paths.get(storageLocation).resolve(nombreArchivo);
    }


    // CARGAR COMO RECURSO
    @Override
    public Resource cargarComoRecurso(String nombreArchivo) {

        try{
            Path archivo = cargarArchivo(nombreArchivo);
            Resource recurso = new UrlResource(archivo.toUri());

            // comprobación si el archivo existe y si se puede leer/obtener
            if (recurso.exists() || recurso.isReadable()){
                return recurso;
            }else{
                throw new FileNotFoundException("No se encontro el archivo" + nombreArchivo);
            }

        }catch (MalformedURLException exception){
            throw new FileNotFoundException("No se encontro el archivo" + nombreArchivo, exception);
        }
    }

    // ELIMINAR UN ARCHIVO
    @Override
    public void eliminarArchivo(String nombreArchivo) {
        // recuperar archivo en concreto
        Path archivo = cargarArchivo(nombreArchivo);

        // borrar archivo en concreto
        try{
            FileSystemUtils.deleteRecursively(archivo);
        }catch (Exception exception){
            System.out.println(exception);
        }

    }
    
}
