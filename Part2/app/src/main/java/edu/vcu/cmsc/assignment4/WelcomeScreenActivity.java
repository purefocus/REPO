package edu.vcu.cmsc.assignment4;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class WelcomeScreenActivity extends Activity
{
	
	public SynonymDatabase database;
	private Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_screen);
		
		database = new SynonymDatabase(this);
		handler = new Handler();
		
	}
	
	public void btn_find(View view)
	{
		Intent intent = new Intent(this, ResultsActivity.class);
		TextView wordText = findViewById(R.id.entered_word);
		String word = wordText.getText().toString().trim();
		intent.putExtra("word", word);
		String synonym = database.getSynonym(word);
		intent.putExtra("synonym", synonym);
		wordText.setText("");
		
		startActivity(intent);
	}
	
	public void btn_enter(View view)
	{
		Intent intent = new Intent(this, EnterValuesActivity.class);
		startActivityForResult(intent, 0);
		
	}
	
	public void onDestroy()
	{
		database.close();
		super.onDestroy();
	}
	
	public void onActivityResult(int request, int resultCode, Intent intent)
	{
		String word1 = intent.getStringExtra("word1");
		String word2 = intent.getStringExtra("word2");
		
		database.addPair(word1, word2);
	}
}
