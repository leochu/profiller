package com.profiller.commons;

import java.util.List;
import java.util.Map;

import com.profiller.models.ebean.BaseModel;

public interface Persistence
{
    public void save( BaseModel user );

    public List<? extends BaseModel> find( Class<? extends BaseModel> clazz, Map<String, Object> criteria );

    public BaseModel findOne( Class<? extends BaseModel> clazz, Map<String, Object> criteria );
}
