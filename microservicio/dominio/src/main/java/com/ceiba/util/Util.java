/**
 *
 * Classname: Util
 * @version 1.0 16/02/2021
 * @author Ricardo Alejandro Morales Penilla
 *
 */
package com.ceiba.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class Util {

    private Util() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * convierte una fecha date en una fecha string
     * en el formato que devuelve  "dd/MM/yyyy"
     *
     * @param date
     * @return formatDate
     */
    public static String convertDate(Date date, String format)
    {
        DateFormat formatDate = new SimpleDateFormat(format);
        String convert = "";
        try
        {
          convert = formatDate.format(date);
        }catch(Exception e)
         {
             LOGGER.info("Inconveniente al ejecutar el metodo convertDate: "+e.getMessage());
         }
        return convert;
    }

    /**
     * retorna un campo tipo date apartir de una feha y formato
     * recibido
     * ejemplo "01/01/2021","dd/MM/yyyy"
     * @param strDate
     * @param format
     * @return dtmFecha
     */
    public static Date convertDate(String strDate, String format) {
        SimpleDateFormat formatString = new SimpleDateFormat(format);
        Date dtmDate = new Date();
        try
        {
            dtmDate = formatString.parse(strDate);
        }catch (Exception e)
        {
            LOGGER.info("Inconveniente al convertir fecha texto a Date "+e.getMessage());
        }
        return dtmDate;
    }

    /**
     * retorna el primer dia de un mes
     * @param date
     * @return firstDayOfMonth
     * @throws Exception
     */
    public static int getDayOfMonth(Date date) throws Exception
    {
        int response = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        response = cal.get(Calendar.DAY_OF_MONTH);

        return response;
    }

    /**
     * retorna el primer dia de un mes
     * @param date
     * @return firstDayOfMonth
     * @throws Exception
     */
    public static int getHourOfDay(Date date) throws Exception
    {
        int response = 0;
        Calendar cal = Calendar.getInstance();
        response =  cal.get(Calendar.HOUR_OF_DAY);

        String hora =cal.get(cal.HOUR_OF_DAY)+"";
        String minutos = cal.get(cal.MINUTE)+"";
        String segundos = cal.get(cal.SECOND)+"";

        return response;
    }

    /**
     * retorna verdadero si las fechas son iguales
     * @param fecha1
     * @param fecha2
     * @return
     */
    public static boolean esIgualFecha(Date fecha1,Date fecha2) 
    {
        boolean esIgual = false;
        String strFecha1 = "";
        String strFecha2 = "";
                              
        strFecha1 = Util.convertDate(fecha1,"yyyy-MM-dd");
        strFecha2 =  Util.convertDate(fecha2,"yyyy-MM-dd");
        if(strFecha1.equalsIgnoreCase(strFecha2))
        {
            esIgual = true;
        }
        return esIgual;
    }

    /**
     * retorna el primer dia de un mes
     * @param date
     * @return firstDayOfMonth
     * @throws Exception
     */
    public static Date getDateAdd(Date date,int value) throws Exception
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, value);
        return cal.getTime();
    }
}
