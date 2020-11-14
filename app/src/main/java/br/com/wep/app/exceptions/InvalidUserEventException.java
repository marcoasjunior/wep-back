package br.com.wep.app.exceptions;

public class InvalidUserEventException extends Exception{

    final static String defaultMessage = "Erro ao procurar usuário/evento: ";

    public InvalidUserEventException(String message) {

        super(defaultMessage + message);

    }



}
