package com.bearpot.dgjung.nileblue.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bearpot.dgjung.nileblue.VO.LocationVo;
import com.bearpot.dgjung.nileblue.VO.MemoVo;

import java.util.ArrayList;

/**
 * Created by dg.jung on 2017-11-09.
 */

public class MemoDBHelper extends SQLiteOpenHelper {
    public MemoDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // TABLE : MEMO
        db.execSQL("CREATE TABLE MEMO (" +
                "memo_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Lat double, " +
                "Lng double, " +
                "description String)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public void save (double lat, double lng, String description) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("INSERT INTO MEMO(Lat,Lng,description) VALUES ("+lat+","+lng+",'"+description+"')");
    }

    public ArrayList<MemoVo> getAllMemos() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<MemoVo> result = new ArrayList<MemoVo>();

        Cursor cursor = db.rawQuery("SELECT * FROM MEMO", null);
        while(cursor.moveToNext()) {
            int memo_id = cursor.getInt(cursor.getColumnIndex("memo_id"));
            LocationVo locationVo = new LocationVo(cursor.getDouble(cursor.getColumnIndex("Lat")), cursor.getDouble(cursor.getColumnIndex("Lng")));
            String description = cursor.getString(cursor.getColumnIndex("description"));

            Log.d("EYEDEAR", String.valueOf(memo_id));
            Log.d("EYEDEAR", description);
            Log.d("EYEDEAR", String.valueOf(locationVo.getLat()));
            Log.d("EYEDEAR", String.valueOf(locationVo.getLng()));

            result.add(new MemoVo(memo_id, locationVo, description));
        }
        return result;
    }
}
