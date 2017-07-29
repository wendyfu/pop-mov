package com.wendy.fpt.popmov.data.contract;

import android.net.Uri;
import android.provider.BaseColumns;

public class PopMovDBContract {

    public final static String POP_MOV_AUTHORITY = "com.wendy.fpt.popmov";
    public final static Uri POP_MOV_BASE_CONTENT_URI = Uri.parse("content://" + POP_MOV_AUTHORITY);
    public final static String PATH_FAV_MOVIE = "fav_movie";

    private PopMovDBContract() { }

    public static class FavMovie implements BaseColumns {

        public static final Uri CONTENT_URI =
            POP_MOV_BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAV_MOVIE).build();

        public static final String TABLE_NAME = "fav_movie";
        public static final String COL_MOVIE_ID = "mov_id";
        public static final String COL_TITLE = "title";
        public static final String COL_POSTER = "poster";
        public static final String COL_DURATION = "duration";
        public static final String COL_OVERVIEW = "overview";
        public static final String COL_RATING = "rating";
        public static final String COL_RELEASE_DATE = "release_date";
    }
}
