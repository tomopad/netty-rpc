package com.mini.rpc.serialization;

public class SerializationException extends RuntimeException{

    private static final long serialVersionUID = 268260788884724613L;

    public SerializationException(){super();}

    public SerializationException(String msg){super(msg);}

    public SerializationException(String msg, Throwable cause){super(msg,cause);}

    public SerializationException(Throwable cause){super(cause);}

}

