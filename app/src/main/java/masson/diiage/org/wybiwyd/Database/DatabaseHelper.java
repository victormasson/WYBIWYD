package masson.diiage.org.wybiwyd.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

import masson.diiage.org.wybiwyd.Entities.Beer;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "WYSIWYD";

    public static String TABLE_BEER = "beer";
    public static String TABLE_BEER_ID = "beerId";
    public static String TABLE_BEER_INGREDIENT_ID = "ingredientId";
    public static String TABLE_BEER_RANGE_ID = "rangeId";
    public static String TABLE_BEER_NAME = "name";
    public static String TABLE_BEER_DESCRIPTION = "description";
    public static String TABLE_BEER_ALCOOL = "alcool";
    public static String TABLE_BEER_PRICE = "price";

    public String CREATE_TABLE_BEER =
            "CREATE TABLE `beer` ( `beerId` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `name` TEXT, `description` TEXT, `alcool` INTEGER, `price` INTEGER )";

    public static String TABLE_RANGE = "range";
    public static String TABLE_RANGE_ID = "rangeId";
    public static String TABLE_RANGE_NAME = "name";
    public static String TABLE_RANGE_DESCRIPTION = "description";

    public String CREATE_TABLE_RANGE =
        "CREATE TABLE `range` ( `rangeId` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `name` TEXT, `description` TEXT )";

    public static String TABLE_INGREDIENT = "ingredient";
    public static String TABLE_INGREDIENT_ID = "ingredientId";
    public static String TABLE_INGREDIENT_NAME = "name";
    public static String TABLE_INGREDIENT_DESCRIPTION = "description";

    public String CREATE_TABLE_INGREDIENT =
            "CREATE TABLE `ingredient` ( `ingredientId` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `name` TEXT, `description` TEXT )";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        baseUpdateTo(db, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = 2; i <= newVersion; i++) {
            baseUpdateTo(db, i);
        }
        Log.d("db", "Base mise à jour");
    }

    public void baseUpdateTo(SQLiteDatabase db, int version) {
        switch (version) {
            case 1:
                db.execSQL(CREATE_TABLE_BEER);
            case 2:
                this.addBeer(db, Beer.toContentValues("Biere 1", "Biere 1 description", 3, 12));
                this.addBeer(db, Beer.toContentValues("Biere 2", "Biere 2 description", 89, 2));
                this.addBeer(db, Beer.toContentValues("Biere 3", "Biere 3 description", 1, 43));
                this.addBeer(db, Beer.toContentValues("Biere 4", "Biere 4 description", 8, 52));

                db.execSQL(CREATE_TABLE_INGREDIENT);
                db.execSQL(CREATE_TABLE_RANGE);

                db.execSQL("ALTER TABLE " + TABLE_BEER + " ADD " + TABLE_BEER_INGREDIENT_ID + ";");
                db.execSQL("ALTER TABLE " + TABLE_BEER + " ADD " + TABLE_BEER_RANGE_ID + ";");
                break;
            case 3:

                break;
        }
    }

    public long addBeer(SQLiteDatabase db, ContentValues valuesBeer) {
        return db.insert(TABLE_BEER, null, valuesBeer);
    }

    public Beer getBeer(SQLiteDatabase db, long idBeer) {
        Cursor cursorBeer = db.query(TABLE_BEER,
                new String[] { "*" },
                TABLE_BEER_ID + " = ?",
                new String[] { String.valueOf(idBeer) },
                null,
                null,
                null);

        Beer beer = new Beer();
        while(cursorBeer.moveToNext()){
            long beerId = cursorBeer.getLong(0);
            String name = cursorBeer.getString(1);
            String description = cursorBeer.getString(2);
            long alcool = cursorBeer.getLong(3);
            long price = cursorBeer.getLong(4);

            beer = new Beer(beerId, name, description, alcool, price);
        }
        return beer;
    }

    public ArrayList<Beer> getBeer(SQLiteDatabase db) {
        Cursor cursorBeer = db.query(TABLE_BEER,
                new String[] {TABLE_BEER_ID, TABLE_BEER_NAME, TABLE_BEER_DESCRIPTION, TABLE_BEER_ALCOOL, TABLE_BEER_PRICE},
                null,
                null,
                null,
                null,
                null);

        ArrayList<Beer> listBeer = new ArrayList<Beer>();
        while(cursorBeer.moveToNext()){
            long beerId = cursorBeer.getLong(0);
            String name = cursorBeer.getString(1);
            String description = cursorBeer.getString(2);
            long alcool = cursorBeer.getLong(3);
            long price = cursorBeer.getLong(4);

            Beer beer = new Beer(beerId, name, description, alcool, price);
            listBeer.add(beer);
        }
        return listBeer;
    }

    public long updateBeer(SQLiteDatabase db, long idBeer, ContentValues valuesBeer) {
        return db.update(TABLE_BEER,
                valuesBeer,
                TABLE_BEER_ID + " = ?",
                new String[] { String.valueOf(idBeer)});
    }

    public long deleteBeer(SQLiteDatabase db, long idBeer) {
        return db.delete(TABLE_BEER,
                TABLE_BEER_ID + " = ?",
                new  String[] { String.valueOf(idBeer)});
    }

    public void deleteBeerWithTranscation(SQLiteDatabase db, long idBeer) {
        try {
            db.beginTransaction();

            long line = deleteBeer(db, idBeer);

            if (line == 0) {
                throw new Exception("Impossible !");
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        catch (Exception e) {
            db.endTransaction();
        }
    }
}
