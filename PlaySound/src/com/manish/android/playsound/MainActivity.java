package com.manish.android.playsound;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.AudioColumns;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private MediaPlayer player;
	private TextView music;
	private TextView start;
	private TextView stop;
	private Button songList;
	private ArrayList<Song> songs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getAllSongsSDCard();
		player = MediaPlayer.create(this, R.raw.tum_hi_ho);
		player.start();
		music = (TextView) findViewById(R.id.bMusicStatus);
		start = (TextView) findViewById(R.id.bStart);
		stop = (TextView) findViewById(R.id.bStop);
		songList = (Button) findViewById(R.id.bSonglist);
		music.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (player.isPlaying()) {
					music.setText("Play Music");
					player.pause();
				} else {
					music.setText("Pause Music");
					player.start();
				}
			}
		});
		start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				player.release();
				player = MediaPlayer.create(getApplicationContext(),
						R.raw.tum_hi_ho);
				player.start();
			}
		});
		stop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				player.stop();
			}
		});
		songList.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(),
						ListViewActivity.class);
				intent.putExtra("songList", songs);
				startActivity(intent);
			}
		});

	}

	/*
	 * @Override protected void onPause() { super.onPause();
	 * if(player.isPlaying()){ player.pause(); } }
	 * 
	 * @Override protected void onResume() { super.onResume();
	 * if(!player.isPlaying()){ player.start(); } }
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		player.release();
	}

	@SuppressWarnings("deprecation")
	private void getAllSongsSDCard() {
		songs = new ArrayList<Song>();
		String[] STAR = { "*" };
		Cursor cursor;
		Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		String selection = AudioColumns.IS_MUSIC + " != 0";
		cursor = managedQuery(uri, STAR, selection, null, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					String songName = cursor.getString(cursor
							.getColumnIndex(MediaColumns.TITLE));
					String path = cursor.getString(cursor
							.getColumnIndex(MediaColumns.DATA));
					String albumName = cursor.getString(cursor
							.getColumnIndex(AudioColumns.ALBUM));
					Long milisec = cursor.getLong(cursor
							.getColumnIndex(AudioColumns.DURATION));
					String duration = (milisec / 1000)
							/ 60
							+ "."
							+ ((milisec / 1000) % 60 > 10 ? ((milisec / 1000) % 60)
									: ("0" + ((milisec / 1000) % 60)));
					int albumId = cursor.getInt(cursor
							.getColumnIndex(AudioColumns.ALBUM_ID));
					Log.d("Song", songName + ", " + albumName + ", " + duration
							+ ", Path: " + path);
					songs.add(new Song(songName, duration, albumName, path));
				} while (cursor.moveToNext());
			}
		}
	}
}
