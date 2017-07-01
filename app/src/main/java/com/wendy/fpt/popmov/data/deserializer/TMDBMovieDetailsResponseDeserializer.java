package com.wendy.fpt.popmov.data.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.wendy.fpt.popmov.data.model.TMDBMovieDetailsResponse;

import java.lang.reflect.Type;

public class TMDBMovieDetailsResponseDeserializer implements JsonDeserializer<TMDBMovieDetailsResponse> {

    private final String TAG = getClass().getSimpleName();

    @Override
    public TMDBMovieDetailsResponse deserialize(JsonElement json, Type typeOfT,
        JsonDeserializationContext context) throws JsonParseException {
        TMDBMovieDetailsResponse tmdbMoviesDetailsResponse = new Gson()
                .fromJson(json, TMDBMovieDetailsResponse.class);

        return tmdbMoviesDetailsResponse;
    }
}