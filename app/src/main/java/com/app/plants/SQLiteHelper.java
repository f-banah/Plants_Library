package com.app.plants;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;


public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String nom, String nomScientifique,String famille,String origine,String type,String genre,String toxicite,String usage, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Plantes VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, nom);
        statement.bindString(2, nomScientifique);
        statement.bindString(3, famille);
        statement.bindString(4, origine);
        statement.bindString(5, type);
        statement.bindString(6, genre);
        statement.bindString(7, toxicite);
        statement.bindString(8, usage);
        statement.bindBlob(9, image);

        statement.executeInsert();
    }

    public void updateData( String type, String usage, byte[] image , int id) {
        SQLiteDatabase database = getWritableDatabase();

        //String sql = "UPDATE Plantes SET nom = ?, nomScientifique = ?, famille = ?, origine = ?, type = ?, genre = ?, toxicite = ?, usage = ?, image = ? WHERE id = ?";
        String sql = "UPDATE Plantes SET  type = ?, usage = ? , image = ?  WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        //statement.bindString(1, nom);
        //statement.bindString(2, nomScientifique);
        //statement.bindString(3, famille);
        //statement.bindString(4, origine);
        statement.bindString(5, type);
        //statement.bindString(6, genre);
        //statement.bindString(7, toxicite);
        statement.bindString(8, usage);
        statement.bindBlob(9, image);
        statement.bindDouble(10, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM Plantes WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

