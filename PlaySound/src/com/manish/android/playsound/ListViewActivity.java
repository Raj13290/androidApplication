package com.manish.android.playsound;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ListViewActivity extends Activity {

	private ArrayList<Song> songs;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		songs = (ArrayList<Song>) getIntent().getSerializableExtra("songList");
		ListView listview = (ListView) findViewById(R.id.list);
		CustomeAdapter adapter = new CustomeAdapter(this, songs);
		listview.setAdapter(adapter);
	}
}
