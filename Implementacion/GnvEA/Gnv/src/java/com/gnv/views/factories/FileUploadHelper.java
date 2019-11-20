/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.views.factories;

import com.gnv.business.ejb.modelo.ServidorArchivos;
import com.gnv.views.utils.Propiedades;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.out;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.*;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

public class FileUploadHelper {

    private static String host;
    private static String usuario;
    private static String password;
    private static String directorioBase;
    private static Integer puerto;
    private static String nombre;
    private static FTPClient client;

    public static boolean cargarArchivos(UploadedFile archivo, ServidorArchivos servidorArchivos, String ruta) throws Exception {
        boolean success = false, isDirectorioExists = false;
        if (servidorArchivos == null) {
            return false;
        }

        host = servidorArchivos.getDireccion();
        usuario = servidorArchivos.getUsuario();
        password = servidorArchivos.getPassword();
        directorioBase = servidorArchivos.getDirectorioBase();
        puerto = servidorArchivos.getPuerto();
        nombre = servidorArchivos.getNombre();
        ruta += "/";
        try {
            if (establecerConexion()) {
                isDirectorioExists = client.changeWorkingDirectory(directorioBase);
                if (!isDirectorioExists) {
                    success = client.makeDirectory(directorioBase);
                    if (!success) {
                        return false;
                    }
                    client.changeWorkingDirectory(directorioBase);
                }

                client.setFileType(FTP.BINARY_FILE_TYPE);
                client.setStrictReplyParsing(false);
                if (!validarDirectorio(ruta)) {
                    return false;
                }
                byte[] bytes = archivo.getContents();
                InputStream inputStream = new ByteArrayInputStream(bytes);
                client.storeFile(archivo.getFileName(), inputStream);
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(FileUploadHelper.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            client.logout();
            client.disconnect();

        }
    }

    public static StreamedContent descargarArchivo(String nombreArchivo, String extension, ServidorArchivos servidorArchivos, String ruta) throws Exception {
        host = servidorArchivos.getDireccion();
        usuario = servidorArchivos.getUsuario();
        password = servidorArchivos.getPassword();
        directorioBase = servidorArchivos.getDirectorioBase();
        puerto = servidorArchivos.getPuerto();
        nombre = servidorArchivos.getNombre();
        StreamedContent streamedContent = null;
        try {
            boolean isConected = establecerConexion();
            if (isConected) {
                client.changeWorkingDirectory(directorioBase);
                //    client.setStrictReplyParsing(false);
                client.setFileType(FTP.BINARY_FILE_TYPE);
                if (validarDirectorio(ruta)) {
                    String dirLocalBase = Propiedades.getEtiqueta("archivos_temporales");
                    if (!dirLocalBase.equals("No existe la etiqueta.")) {
                        File downloadFile = new File(dirLocalBase + nombreArchivo);
                        OutputStream oStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
                        InputStream inputStream = client.retrieveFileStream(nombreArchivo);
                        byte[] bytesArray = new byte[4096];
                        int bytesRead = -1;
                        while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                            oStream.write(bytesArray, 0, bytesRead);
                        }
                        oStream.close();
                        String mimeType = "application/" + extension;
                        InputStream inputStreamTemp = new FileInputStream(dirLocalBase + nombreArchivo);
                        streamedContent = new DefaultStreamedContent(inputStreamTemp, mimeType, nombreArchivo);
                    }
                }
            }

        } catch (Exception e) {
            Logger.getLogger(FileUploadHelper.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            client.logout();
            client.disconnect();
        }
        return streamedContent;
    }

    private static boolean establecerConexion() throws Exception {
        try {
            boolean success = false;
            client = new FTPClient();
            client.connect(host, puerto);
            int responseCode = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(responseCode)) {
                out.println("E/GNV: Operacion fallida. Codigo de respuesta del servidor: " + responseCode);
                success = false;
            }
            success = client.login(usuario, password);
            if (!success) {
                out.println("E/GNV: No se pudo iniciar sesion en el servidor.");
            }
            return success;
        } catch (Exception e) {
            out.println("E/GNV: ********** Ocurrió un error al conectarse a: " + nombre + "|" + host + "**********");
            out.println("E/GNV: ********** Con usuario: " + usuario + "|" + host + "**********");
            return false;
        }

    }

    private static boolean validarDirectorio(String ruta) throws Exception {
        Boolean existeRuta = false, existeDirectorio = false;
        try {
            List<String> directorios = Arrays.asList(ruta.split("[/]"));
            for (String directorio : directorios) {
                existeDirectorio = client.changeWorkingDirectory(directorio);
                out.println("I/GNV: El directorio " + directorio + " existe: " + existeDirectorio);
                if (!existeDirectorio) {
                    existeRuta = client.makeDirectory(directorio);
                    if (!existeRuta) {
                        break;
                    } else {
                        client.changeWorkingDirectory(directorio);
                    }
                } else {
                    existeRuta = true;
                }

            }
        } catch (Exception e) {
            out.println("E/GNV: Ocurrio un error en la creación de los directorios.");
        }
        return existeRuta;
    }

}
