package edu.vcu.cmsc.assignment4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class EnterValuesActivity extends Activity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_values);
	}
	
	public void btn_submit(View view)
	{
		TextView text = findViewById(R.id.entered_word);
		String word = text.getText().toString().trim();
		text = findViewById(R.id.entered_synonym);
		String synonym = text.getText().toString().trim();
		
		Intent intent = new Intent(this, WelcomeScreenActivity.class);
		intent.putExtra("word1", word);
		intent.putExtra("word2", synonym);
		
		setResult(RESULT_OK, intent);
		finish();
		
	}
	
	
}
