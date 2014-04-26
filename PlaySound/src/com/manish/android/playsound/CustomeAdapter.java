package com.manish.android.playsound;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomeAdapter extends BaseAdapter implements OnClickListener {

	private static LayoutInflater inflater;
	private ArrayList<Song> rows;
	private Activity activity;

	public CustomeAdapter(Activity activity, ArrayList<Song> rows) {
		this.rows = rows;
		this.activity = activity;
		inflater = (LayoutInflater) this.activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return rows.size();
	}

	@Override
	public Object getItem(int position) {
		return rows.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View oneRowView, ViewGroup arg2) {
		ViewHolder holder;
		if (oneRowView == null) {
			oneRowView = inflater.inflate(R.layout.song_layout, null);
			holder = new ViewHolder();
			holder.albumName = (TextView) oneRowView
					.findViewById(R.id.albumName);
			holder.duration = (TextView) oneRowView.findViewById(R.id.duration);
			holder.songName = (TextView) oneRowView.findViewById(R.id.songName);
			holder.index = (TextView) oneRowView.findViewById(R.id.index);
			oneRowView.setTag(holder);

		} else {
			holder = (ViewHolder) oneRowView.getTag();
		}
		if (rows.size() < 1) {
			holder.songName.setText("There are no song");
		} else {
			holder.songName.setText(rows.get(position).getSongName());
			holder.albumName.setText(rows.get(position).getAlbumName());
			holder.duration.setText(rows.get(position).getDuration());
			holder.index.setText((position + 1) + ".");
		}
		oneRowView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		return oneRowView;
	}

	@Override
	public void onClick(View arg0) {

	}

	private static class ViewHolder {
		public TextView songName;
		public TextView duration;
		public TextView albumName;
		public TextView index;
	}
}
