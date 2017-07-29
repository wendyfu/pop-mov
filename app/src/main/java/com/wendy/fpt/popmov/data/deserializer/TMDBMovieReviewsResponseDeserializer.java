package com.wendy.fpt.popmov.data.deserializer;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.wendy.fpt.popmov.data.model.TMDBMovieReviewsResponse;

import java.lang.reflect.Type;
import java.util.List;

public class TMDBMovieReviewsResponseDeserializer implements
        JsonDeserializer<TMDBMovieReviewsResponse> {

    private final String TAG = getClass().getSimpleName();

    @Override
    public TMDBMovieReviewsResponse deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        try {
            JsonObject content = json.getAsJsonObject();
            TMDBMovieReviewsResponse tmdbMovieReviewsResponse = new Gson().fromJson(json,
                    TMDBMovieReviewsResponse.class);

            JsonElement results = content.get("results");
            Type listType = new TypeToken<List<TMDBMovieReviewsResponse.MovieReview>>() {
            }.getType();
            tmdbMovieReviewsResponse.setReviews(
                    (List<TMDBMovieReviewsResponse.MovieReview>) new Gson()
                            .fromJson(results, listType));
            Log.d(TAG, tmdbMovieReviewsResponse.toString());

            return tmdbMovieReviewsResponse;
        } catch (JsonParseException e) {
            Log.i(TAG, e.getMessage());
        }

        return null;
    }
}