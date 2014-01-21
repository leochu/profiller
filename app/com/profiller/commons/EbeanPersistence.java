package com.profiller.commons;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.profiller.models.ebean.BaseModel;

public class EbeanPersistence
    implements Persistence
{

    @Override
    public void save( BaseModel model )
    {
        Ebean.save( model );
    }

    // The unchecked list is empty
    @SuppressWarnings( "unchecked" )
    @Override
    public List<? extends BaseModel> find( Class<? extends BaseModel> clazz, Map<String, Object> criteria )
    {
        Query<? extends BaseModel> query = Ebean.find( clazz );

        for ( Entry<String, Object> entry : criteria.entrySet() )
        {
            query.where().eq( entry.getKey(), entry.getValue() );
        }

        List<? extends BaseModel> models = query.findList();

        return ( models == null ) ? Collections.EMPTY_LIST : models;
    }

    @Override
    public BaseModel findOne( Class<? extends BaseModel> clazz, Map<String, Object> criteria )
    {
        List<? extends BaseModel> models = this.find( clazz, criteria );

        return ( models != null && models.size() > 1 ) ? models.get( 0 ) : null;
    }
}
