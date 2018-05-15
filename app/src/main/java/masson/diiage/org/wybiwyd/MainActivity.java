package masson.diiage.org.wybiwyd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import masson.diiage.org.wybiwyd.Adapter.BeerAdapter;
import masson.diiage.org.wybiwyd.Database.DatabaseHelper;
import masson.diiage.org.wybiwyd.Entities.Beer;

public class MainActivity extends AppCompatActivity {
    private ListView listViewBeer;
    private ArrayList<Beer> beers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String tagBeer = "Beer";

        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        beers = helper.getBeer(db);

        listViewBeer = (ListView) findViewById(R.id.listViewBeer);
        BeerAdapter adapter = new BeerAdapter(beers, this);
        listViewBeer.setAdapter(adapter);
    }
}
