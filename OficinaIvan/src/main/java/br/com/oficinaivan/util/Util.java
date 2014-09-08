package br.com.oficinaivan.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Classe utilitária.
 */
public class Util {
    
    private static final Locale LOCALE = new Locale("pt-br", "BR");
    private static final DateFormat FORMATO = new SimpleDateFormat("dd/MM/yyyy", LOCALE);
    
    
    /**
     * Implementação de método estático que retorna o dia corrente em seu formato completo.
     * @return Calendar - Retorna objeto que representa o dia corrente em seu formato completo.
     */
    public static Calendar obterDiaCorrente(){
        Calendar hoje = Calendar.getInstance();
        hoje.setTimeInMillis(System.currentTimeMillis());
        return hoje;
    }
    
    public static String formatarData(Date data){
    	if(data == null){
    		return null;
    	}
    	return FORMATO.format(data);
    }
    
    public static String formatarHora(Date data){
    	return new SimpleDateFormat("HH:mm", LOCALE).format(data);
    }
}