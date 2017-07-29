package com.wendy.fpt.popmov.data.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.wendy.fpt.popmov.data.PopMovDbHelper;
import com.wendy.fpt.popmov.data.contract.PopMovDBContract;

import static com.wendy.fpt.popmov.data.contract.PopMovDBContract.FavMovie.TABLE_NAME;

public class FavMovContentProvider extends ContentProvider {

    public static final int FAV_MOVIE = 100;
    public static final int FAV_MOVIE_WITH_ID = 101;

    private PopMovDbHelper popMovDbHelper;
    private UriMatcher uriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PopMovDBContract.POP_MOV_AUTHORITY, PopMovDBContract.PATH_FAV_MOVIE,
            FAV_MOVIE);
        uriMatcher.addURI(PopMovDBContract.POP_MOV_AUTHORITY,
            PopMovDBContract.PATH_FAV_MOVIE + "/#", FAV_MOVIE_WITH_ID);
        return uriMatcher;
    }

    @Override public boolean onCreate() {
        popMovDbHelper = new PopMovDbHelper(getContext());
        return true;
    }

    @Nullable @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = popMovDbHelper.getReadableDatabase();
        Cursor returnCursor = null;

        switch (uriMatcher.match(uri)) {
            case FAV_MOVIE:
                returnCursor =
                    db.query(TABLE_NAME, projection, selection, selectionArgs, null, null,
                        sortOrder);
                break;
            case FAV_MOVIE_WITH_ID:
                String movieId = uri.getPathSegments().get(1);
                returnCursor =
                    db.query(TABLE_NAME, projection, PopMovDBContract.FavMovie.COL_MOVIE_ID + "=?",
                        new String[] {movieId}, null, null, null);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return returnCursor;
    }

    @Nullable @Override public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable @Override public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = popMovDbHelper.getWritableDatabase();
        Uri returnUri = null;

        switch (uriMatcher.match(uri)) {
            case FAV_MOVIE:
                long id = db.insert(TABLE_NAME, null, values);
                if (id > 0) {
                    returnUri =
                        ContentUris.withAppendedId(PopMovDBContract.FavMovie.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override public int delete(@NonNull Uri uri, @Nullable String selection,
        @Nullable String[] selectionArgs) {
        SQLiteDatabase db = popMovDbHelper.getWritableDatabase();
        int deletedRow = 0;

        switch (uriMatcher.match(uri)) {
            case FAV_MOVIE_WITH_ID:
                String movieId = uri.getPathSegments().get(1);
                deletedRow = db.delete(TABLE_NAME, PopMovDBContract.FavMovie.COL_MOVIE_ID + "=?",
                    new String[] {movieId});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        return deletedRow;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
        @Nullable String[] selectionArgs) {
        return 0;
    }
}
