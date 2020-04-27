package be.technobel.ucmppg.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorServiceException extends Exception {

    private String properties;
    private String errorMessage;


    public ErrorServiceException() {
    }

    public ErrorServiceException(String properties, String errorMessage) {
        this.properties = properties;
        this.errorMessage = errorMessage;
    }



    public String toString() {
        return "Erreur de validation du champ "+ properties + " - erreur : "+ errorMessage;
    }
}
