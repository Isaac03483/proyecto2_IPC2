package com.ameri.converter;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.util.List;

public abstract class Converter<T> {

    private Gson gson;
    private final Class<T> typeConverter;

    public Converter(Class<T> typeConverter){
        this.gson = null;
        this.typeConverter = typeConverter;
    }

    public T fromJson(String json) {
        this.gson = new GsonBuilder().create();
        return gson.fromJson(json, typeConverter);
    }

    public String toJson(T object) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object, typeConverter);
    }

    public String toJson(List<T> object){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        String string = "[";
        for(T data: object){
            string+=gson.toJson(data, typeConverter)+",";
        }
        string= string.substring(0, string.length()-1)+"]";

        return string;
    }

}
