package com.ameri.converter;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

}
