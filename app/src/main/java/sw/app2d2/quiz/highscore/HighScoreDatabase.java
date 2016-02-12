package sw.app2d2.quiz.highscore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HighScoreDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "highscore.db";
    public static final int CURRENT_VERSION = 1;
    public static final String TABLE_NAME = "highscore_table";

    public HighScoreDatabase(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_VERSION);
    }

    /**
     * @param userName is the user name of the player.
     * @param score    is the end game score of the player.
     * @return true if data was successfully inserted, else false.
     */
    public boolean insertData(String userName, int score) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("USERNAME", userName);
        values.put("SCORE", score);

        long result = database.insert(TABLE_NAME, null, values);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Fetch all data from the high score table.
     *
     * @return the result of the query.
     */
    public Cursor getAllData() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("select * from " + TABLE_NAME, null);
        return result;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, SCORE INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Fulkod?
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}