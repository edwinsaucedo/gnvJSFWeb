/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.utils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author _GEscalante
 */
public class StringUtil {

    public static String listStringToComaValues(List<String> lista) {
        StringBuilder commaSepValueBuilder = new StringBuilder();

        for (int i = 0; i < lista.size(); i++) {
            //append the value into the builder
            commaSepValueBuilder.append(lista.get(i));

            //if the value is not the last element of the list
            //then append the comma(,) as well
            if (i != lista.size() - 1) {
                commaSepValueBuilder.append(", ");
            }
        }
        return commaSepValueBuilder.toString();

    }

    public static String quitaEspacios(String texto) {
        java.util.StringTokenizer tokens = new java.util.StringTokenizer(texto);
        StringBuilder buff = new StringBuilder();
        while (tokens.hasMoreTokens()) {
            buff.append(" ").append(tokens.nextToken());
        }
        return buff.toString().trim();
    }

    public static String generarIniciales(String original) {
        String initial = "";
        String[] split = original.split(" ");

        for (String value : split) {
            initial += value.substring(0, 1);
        }

        return initial;
    }

    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }

    public static String reemplazarCaracteresRaros(String input) {
        // Cadena de caracteres original a sustituir.
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String output = input;
        for (int i = 0; i < original.length(); i++) {
            // Reemplazamos los caracteres especiales.
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }//for i
        return output;
    }//reemplazarCaracteresRaros

    public static String obtenerUltimapalabra(String frase) {
        String ultima_palabra = "";
        int posicionUltimoEspacio = 0;
        posicionUltimoEspacio = frase.lastIndexOf(" ");
        ultima_palabra = frase.substring(posicionUltimoEspacio, frase.length());
        return ultima_palabra;
    }

    public static String primeraLetraMayuscula(String palabra) {
        palabra = palabra.toLowerCase();
        palabra = Character.toString(palabra.charAt(0)).toUpperCase() + palabra.substring(1);
        return palabra;

    }

    public static String objectToString(Object objeto) {
        //http://stackoverflow.com/questions/4158830/java-reflection-accessing-fields-of-a-field
        //refleccion
        try {
            StringBuffer sb = new StringBuffer();
            Class<?> c = objeto.getClass();
            Field[] fields = c.getDeclaredFields(); //Get all fields incl. private ones
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    String key = field.getName();
                    if (!key.startsWith("_")) {
                        String value;
                        String type = field.getGenericType().toString();
                        try {
                            if (type.equals("class java.math.BigDecimal")) {
                                BigDecimal bdValue = (BigDecimal) field.get(objeto);
                                value = bdValue.toPlainString();
                            } else if (type.equals("class java.lang.Boolean")) {
                                Boolean booValue = (Boolean) field.get(objeto);
                                value = booValue.toString();
                            } else if (type.equals("class java.lang.Double")) {
                                Double douValue = (Double) field.get(objeto);
                                value = douValue.toString();
                            } else if (type.equals("class java.lang.Float")) {
                                Float floValue = (Float) field.get(objeto);
                                value = floValue.toString();
                            } else if (type.equals("class java.lang.Integer")) {
                                Integer intValue = (Integer) field.get(objeto);
                                try {
                                    value = intValue.toString();
                                } catch (NullPointerException npe) {
                                    value = "";
                                }

                            } else if (type.equals("class java.lang.Long")) {
                                Long lonValue = (Long) field.get(objeto);
                                value = lonValue.toString();
                            } else if (type.equals("class java.lang.Short")) {
                                Short shoValue = (Short) field.get(objeto);
                                value = shoValue.toString();
                            } else if (type.equals("class java.lang.String")) {
                                value = (String) field.get(objeto);
                            } else if (type.equals("class java.util.Date")) {
                                Date dValue = (Date) field.get(objeto);
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                                value = sdf.format(dValue);
                            } else if (type.startsWith("class fresnilloplc.bsc.modelo")) {
                                Field fieldRel = field.get(objeto).getClass().getDeclaredField("id");
                                fieldRel.setAccessible(true);
                                Integer intValue = (Integer) fieldRel.get(field.get(objeto));
                                value = intValue.toString();
                            } else {
                                value = (String) field.get(objeto);
                            }
                            sb.append(key).append(": ").append(value).append(" --  \n");
                        } catch (ClassCastException e) {
                            value = "";
                        }
                    }

                } catch (Exception e) {
                    e.toString();
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return objeto.toString();
        }
    }
}

