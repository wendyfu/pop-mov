package com.wendy.fpt.popmov.data.deserializer;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.wendy.fpt.popmov.data.model.TMDBMoviesResponse;

import java.lang.reflect.Type;
import java.util.List;

public class TMDBMoviesResponseDeserializer implements JsonDeserializer<TMDBMoviesResponse> {

    private final String TAG = getClass().getSimpleName();

    @Override
    public TMDBMoviesResponse deserialize(JsonElement json, Type typeOfT,
                                          JsonDeserializationContext context) throws JsonParseException {

        try {
            JsonObject content = json.getAsJsonObject();
            TMDBMoviesResponse tmdbMoviesResponse = new Gson().fromJson(json, TMDBMoviesResponse.class);

            JsonElement results = content.get("results");
            Type listType = new TypeToken<List<TMDBMoviesResponse.MovieResponse>>() { }.getType();
            tmdbMoviesResponse.setMovies(
                    (List<TMDBMoviesResponse.MovieResponse>) new Gson().fromJson(results, listType));
            return tmdbMoviesResponse;
        } catch (JsonParseException e) {
            Log.i(TAG, e.getMessage());
        }

        return null;
    }
}
