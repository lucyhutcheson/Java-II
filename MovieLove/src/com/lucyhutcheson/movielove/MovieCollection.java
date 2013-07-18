package com.lucyhutcheson.movielove;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import com.lucyhutcheson.lib.MovieProvider;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MovieCollection extends Activity implements OnClickListener {
	
	private final String TAG = "MovieCollection";
	private EditText editURI;
	private Button searchButton;
	private ListView listView;
	private View listHeader;
	
	private ArrayList<HashMap<String, String>> myList = new ArrayList<HashMap<String, String>>();
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.latestmovieslist);

		
		// GET TEXT AND INITALIZE DATA
		editURI = (EditText) this.findViewById(R.id.searchField);
		editURI.setText(MovieProvider.MovieData.CONTENT_URI.toString());

		// SEARCH BUTTON AND HANDLER
		searchButton = (Button) this.findViewById(R.id.searchButton);
		searchButton.setOnClickListener(this);

		// DISMISS THE KEYBOARD SO WE CAN SEE OUR TEXT
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(((EditText) findViewById(R.id.searchField)).getWindowToken(), 0);
		
		//searchInfo = (TextView) this.findViewById(R.id.searchTextView);
		
		// SETUP LISTVIEW AND HEADER AND POPULATE IF DATA AVAILABLE
		listView = (ListView) findViewById(R.id.listview);
		listHeader = getLayoutInflater().inflate(R.layout.latestmovies_header, null);
		listView.addHeaderView(listHeader);
		

		String[] from = new String[] { "Title", "Year", "Rating" };
		int[] to = new int[] { R.id.movietitle, R.id.year, R.id.rating };

		if (savedInstanceState != null) {
			Log.d(TAG, "Saved Instance");
			
			myList = (ArrayList<HashMap<String,String>>) savedInstanceState.getSerializable("saved");
			if (myList != null) {
				SimpleAdapter adapter = new SimpleAdapter(this, myList, R.layout.latestmovies_row, from, to);
				listView.setAdapter(adapter);
			}
			else {
				Log.d("MOVIECOLLECTION", "Saved is NULL");
			}
		} else {
			
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
