package edu.vcu.cmsc.assignment4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		
		Intent intent = getIntent();
		String word = intent.getStringExtra("word");
		String synonym = intent.getStringExtra("synonym");
		
		TextView text = findViewById(R.id.label_result);
		text.setText(String.format("The synonym for\n\n\"%s\"\n\nis\n\n\"%s\"", word, synonym));
	}
	
	public void btn_back(View view)
	{
		finish();
	}
}
