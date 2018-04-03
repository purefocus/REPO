package edu.vcu.cmsc.assignment4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class SynonymDatabase extends SQLiteOpenHelper
{
	
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_NAME = "synonyms.db";
	private static final String SQL_CREATE_TABLE = "CREATE TABLE synonyms (id INTEGER PRIMARY KEY, word1 TEXT, word2 TEXT)";
	private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS synonyms";
	
	public SynonymDatabase(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		Log.e("SQL", "Database");
		
	}
	
	public void addPair(String word1, String word2)
	{
		SQLiteDatabase db = getWritableDatabase();
		
		db.insert(SynonymTable.TABLE_NAME, null, SynonymTable.createInsert(word1, word2));
	}
	
	public String getSynonym(String word)
	{
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor cursor = db.query(SynonymTable.TABLE_NAME,
		                         new String[]{"id", "word1", "word2"},
		                         "word1 = ? OR word2 = ?",
		                         new String[]{word, word},
		                         null, null, "id DESC");
		
		if (cursor.moveToFirst())
		{
			String ret = cursor.getString(cursor.getColumnIndex("word1"));
			if (ret.equals(word))
			{
				ret = cursor.getString(cursor.getColumnIndex("word2"));
			}
			
			return ret;
		}
		
		return "Word not found!";
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		Log.e("SQL", "Create");
		db.execSQL(SQL_CREATE_TABLE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL(SQL_DELETE_TABLE);
		onCreate(db);
	}
	
	private static class SynonymTable implements BaseColumns
	{
		
		public static final String TABLE_NAME = "synonyms";
		public static final String COL_NAME_ID = "id";
		public static final String COL_NAME_WORD1 = "word1";
		public static final String COL_NAME_WORD2 = "word2";
		
		static final String[] PROJECTION =
				{
						COL_NAME_ID,
						COL_NAME_WORD1,
						COL_NAME_WORD2
				};
		
		static ContentValues createInsert(String word1, String word2)
		{
			ContentValues values = new ContentValues();
			values.put(COL_NAME_WORD1, word1);
			values.put(COL_NAME_WORD2, word2);
			return values;
		}
		
		static String createSelect(String word)
		{
			return String.format("%s = ? or %s = ?", COL_NAME_WORD1, COL_NAME_WORD2);
		}
		
	}
}
