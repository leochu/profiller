package com.profiller.models.ebean;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BaseModel
{
    @Id
    protected UUID id;

    public String getId()
    {
        return this.id.toString();
    }

    public void setId( String id )
    {
        this.id = ( id != null ) ? UUID.fromString( id ) : null;
    }
}
