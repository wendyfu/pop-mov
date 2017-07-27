package com.wendy.fpt.popmov.data.deserializer;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.wendy.fpt.popmov.data.model.TMDBMovieVideosResponse;

import java.lang.reflect.Type;
import java.util.List;

public class TMDBMovieVideosDeserializer implements JsonDeserializer<TMDBMovieVideosResponse> {

    private final String TAG = getClass().getSimpleName();

    @Override
    public TMDBMovieVideosResponse deserialize(JsonElement json, Type typeOfT,
                                               JsonDeserializationContext context) throws JsonParseException {
        try {
            JsonObject content = json.getAsJsonObject();
            TMDBMovieVideosResponse tmdbMoviesVideosResponse = new Gson().fromJson(json,
                    TMDBMovieVideosResponse.class);

            JsonElement results = content.get("results");
            Type listType = new TypeToken<List<TMDBMovieVideosResponse.MovieVideo>>() {
            }.getType();
            tmdbMoviesVideosResponse.setVideos(
                    (List<TMDBMovieVideosResponse.MovieVideo>) new Gson().fromJson(results, listType));
            Log.d(TAG, tmdbMoviesVideosResponse.toString());

            return tmdbMoviesVideosResponse;
        } catch (JsonParseException e) {
            Log.i(TAG, e.getMessage());
        }

        return null;
    }
}
