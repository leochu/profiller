package com.profiller.commons;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ProfillerException
    extends RuntimeException
{

    public ProfillerException( String errorMessage )
    {
        super( errorMessage );
    }

    public ProfillerException( String errorMessage, Throwable throwable )
    {
        super( errorMessage, throwable );
    }

    /**
     * 
     */
    private static final long serialVersionUID = -977528464740800473L;

    public JsonNode getErrorAsJson()
    {
        ObjectNode errorNode = JsonNodeFactory.instance.objectNode();
        errorNode.put( "errorMessage", this.getMessage() );

        return errorNode;
    }
}
