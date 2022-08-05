package com.mcamelo.msgApp.controllers.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> erros = new ArrayList<>();
    public List<FieldMessage> getErros(){
        return erros;
    }
    public void addError(String fieldName, String fieldMessage){
        erros.add(new FieldMessage(fieldName, fieldMessage));
    }
}
