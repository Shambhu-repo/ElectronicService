package com.lcwd.electronic.store.exception;

public class ResourceNotFounException extends RuntimeException{

    public ResourceNotFounException(){
        super("Resouce Not Found");
    }

    public ResourceNotFounException(String message){
        super(message);
    }

}
