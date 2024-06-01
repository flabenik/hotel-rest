package br.com.hotel.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class Util {
	
	
	public static String mascaraTelefone(String value) {
		String pattern = "(##) #####-####";
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
	
	public static String removeCaracteresEspeciais(String telefone) {
		return telefone.replaceAll("[^0-9]+", telefone);
	}

}
