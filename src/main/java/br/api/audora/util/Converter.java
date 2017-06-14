package br.api.audora.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converter {
	
	/**
     * Converte um Objeto para um array de bytes
     * @param object
     * @return byte[]
     */
	public static byte[] convertObjectToByteArray(Object object) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
            byteArrayOutputStream.close();
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        return bytes;
    }
	
	
	/**
     * Converte um array de bytes para um objeto
     * @param bytes Array de bytes
     * @return object
     */
    public static Object convertByteArrayToObject(byte[] bytes) {
        Object object = null;
 
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
         
        return object;
    }
    
    public static Date converterStringToDate(String data){
    	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    	Date date;
		
    	try {
			date = formato.parse(data);
		} catch (ParseException e) {
			date = new Date();
		}
		
    	return date;
    }

}
