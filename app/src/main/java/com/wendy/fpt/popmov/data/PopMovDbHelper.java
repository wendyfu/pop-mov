package com.wendy.fpt.popmov.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wendy.fpt.popmov.data.contract.PopMovDBContract;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class PopMovDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PopMov.db";

    @Inject public PopMovDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE_FAV_MOVIE = "CREATE TABLE "
        + PopMovDBContract.FavMovie.TABLE_NAME
        + " ("
        + PopMovDBContract.FavMovie._ID
        + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        + PopMovDBContract.FavMovie.COL_MOVIE_ID
        + " INTEGER NOT NULL,"
        + PopMovDBContract.FavMovie.COL_TITLE
        + " TEXT NOT NULL, "
        + PopMovDBContract.FavMovie.COL_POSTER
        + " TEXT NOT NULL, "
        + PopMovDBContract.FavMovie.COL_DURATION
        + " INTEGER NOT NULL,"
        + PopMovDBContract.FavMovie.COL_OVERVIEW
        + " TEXT NOT NULL, "
        + PopMovDBContract.FavMovie.COL_RATING
        + " REAL NOT NULL, "
        + PopMovDBContract.FavMovie.COL_RELEASE_DATE
        + " TEXT NOT NULL, "
        + "UNIQUE ("
        + PopMovDBContract.FavMovie.COL_MOVIE_ID
        + ") ON CONFLICT REPLACE)";

    private static final String DROP_TABLE_FAV_MOVIE =
        "DROP TABLE IF EXISTS " + PopMovDBContract.FavMovie.TABLE_NAME;

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FAV_MOVIE);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_FAV_MOVIE);
        db.execSQL(CREATE_TABLE_FAV_MOVIE);
    }
}
