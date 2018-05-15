package masson.diiage.org.wybiwyd.Entities;

import android.content.ContentValues;
import masson.diiage.org.wybiwyd.Database.DatabaseHelper;

public class Beer {
    long beerId;
    String name;
    String description;
    long alcool;
    long price;
    long ingredientId;
    long rangeId;

    public ContentValues toContentValues() {
        ContentValues valuesBeer = new ContentValues();
        valuesBeer.put(DatabaseHelper.TABLE_BEER_NAME, this.getName());
        valuesBeer.put(DatabaseHelper.TABLE_BEER_DESCRIPTION, this.getDescription());
        valuesBeer.put(DatabaseHelper.TABLE_BEER_ALCOOL, this.getAlcool());
        valuesBeer.put(DatabaseHelper.TABLE_BEER_PRICE, this.getPrice());
        return valuesBeer;
    }

    public static ContentValues toContentValues(String name, String description, long alcool, long price) {
        ContentValues valuesBeer = new ContentValues();
        valuesBeer.put(DatabaseHelper.TABLE_BEER_NAME, name);
        valuesBeer.put(DatabaseHelper.TABLE_BEER_DESCRIPTION, description);
        valuesBeer.put(DatabaseHelper.TABLE_BEER_ALCOOL, alcool);
        valuesBeer.put(DatabaseHelper.TABLE_BEER_PRICE, price);
        return valuesBeer;
    }

    public Beer() {
    }

    public Beer(long beerId, String name, String description, long alcool, long price) {
        this.beerId = beerId;
        this.name = name;
        this.description = description;
        this.alcool = alcool;
        this.price = price;
    }

    public long getBeerId() {
        return beerId;
    }

    public void setBeerId(long beerId) {
        this.beerId = beerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getAlcool() {
        return alcool;
    }

    public void setAlcool(long alcool) {
        this.alcool = alcool;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public long getRangeId() {
        return rangeId;
    }

    public void setRangeId(long rangeId) {
        this.rangeId = rangeId;
    }
}
