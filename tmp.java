package com.tpo.tmusic;



import android.animation.*;

import android.app.*;

import android.content.*;

import android.content.res.*;

import android.graphics.*;

import android.graphics.drawable.*;

import android.media.*;

import android.net.*;

import android.os.*;

import android.text.*;

import android.text.style.*;

import android.util.*;

import android.view.*;

import android.view.View.*;

import android.view.animation.*;

import android.webkit.*;

import android.widget.*;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.BaseAdapter;

import android.widget.LinearLayout;

import android.widget.ListView;

import androidx.annotation.*;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.DialogFragment;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;

import androidx.media.*;

import com.bumptech.glide.Glide;

import com.google.android.material.color.MaterialColors;

import java.io.*;

import java.text.*;

import java.text.DecimalFormat;

import java.util.*;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.regex.*;

import org.json.*;



public class SongsFragmentActivity extends Fragment {
	// Phương thức thiết lập ListView với adapter
	private void setupListView() {
			    if (listView == null || songs == null) {
					        return;
					    }
			    
			    // Tạo adapter cho ListView
			    BaseAdapter adapter = createSongAdapter();
			    
			    // Set adapter cho ListView
			    listView.setAdapter(adapter);
	}

	// Phương thức này để HomeActivity gọi khi có dữ liệu bài hát mới
	public void updateSongsList(ArrayList<Song> newSongs) {
			    this.songs = newSongs;
			    if (getActivity() != null) {
					        setupListView(); // Cập nhật ListView với dữ liệu mới
					    }
	}

	// Phương thức tạo adapter cho danh sách bài hát
	private BaseAdapter createSongAdapter() {
		  return new BaseAdapter() {
			    @Override
			    public int getCount() {
				      return songs.size();
				    }
			
			    @Override
			    public Object getItem(int position) {
				      return songs.get(position);
				    }
			
			    @Override
			    public long getItemId(int position) {
				      return position;
				    }
			
			    @Override
			    public View getView(final int position, View convertView,
			                        ViewGroup parent) {
				      LayoutInflater _inflater = getActivity().getLayoutInflater();
				      View _view = convertView;
				      if (_view == null) {
					        _view = _inflater.inflate(R.layout.custom_list_view, null);
					      }
				
				      // Ánh xạ các view trong layout
				      final LinearLayout linear1 = _view.findViewById(R.id.linear1);
				      final ImageView imageview1 = _view.findViewById(R.id.imageview1);
				      final LinearLayout linear2 = _view.findViewById(R.id.linear2);
				      final ImageView imageview2 = _view.findViewById(R.id.imageview2);
				      final TextView textview1 = _view.findViewById(R.id.textview1);
				      final TextView textview2 = _view.findViewById(R.id.textview2);
				
				      // Lấy đối tượng Song tại vị trí position
				      final Song song = songs.get(position);
				
				      // Hiển thị thông tin bài hát
				      textview1.setText(song.getTitle());
				      textview2.setText(song.getArtist());
				
				      // Load ảnh bài hát bằng Glide nếu có URL
				      if (song.getImageUrl() != null && !song.getImageUrl().isEmpty()) {
					        try {
						          Glide.with(getActivity())
						              .load(Uri.parse(song.getImageUrl()))
						              .placeholder(
						                  R.drawable.default_album)    // Thay bằng ảnh mặc định của bạn
						              .error(R.drawable.default_album) // Thay bằng ảnh lỗi của bạn
						              .into(imageview1);
						        } catch (Exception e) {
						          e.printStackTrace();
						          // Nếu có lỗi, hiển thị ảnh mặc định
						          imageview1.setImageResource(R.drawable.default_album);
						        }
					      } else {
					        // Nếu không có URL, hiển thị ảnh mặc định
					        imageview1.setImageResource(R.drawable.default_album);
					      }
				
				      // Xử lý sự kiện khi click vào bài hát
				      linear1.setOnClickListener(new View.OnClickListener() {
					        @Override
					        public void onClick(View v) {
						          if (homeActivity != null) {
							            // homeActivity.onSongSelected(position);
							          }
						        }
					      });
				
				      return _view;
				    }
			  };
	}

		

		private HashMap<String, Object> song = new HashMap<>();

		private String keyword = "";

		private String selected_caller = "";

		private HomeActivity homeActivity;

		

		private ArrayList<HashMap<String, Object>> dataList = new ArrayList<>();

		private ArrayList<HashMap<String, Object>> songList = new ArrayList<>();

		private ArrayList<HashMap<String, Object>> filteredSongs = new ArrayList<>();

		private  ArrayList<Song> songs = new ArrayList<>();

		

		private LinearLayout linear1;

		private ListView listView;

		

		@NonNull

		@Override

		public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {

				View _view = _inflater.inflate(R.layout.songs_fragment, _container, false);

				initialize(_savedInstanceState, _view);

				initializeLogic();

				return _view;

		}

		

		private void initialize(Bundle _savedInstanceState, View _view) {

				linear1 = _view.findViewById(R.id.linear1);

				listView = _view.findViewById(R.id.listView);

				

				listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

						@Override

						public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {

								final int _position = _param3;

								

						}

				});

		}

		

		private void initializeLogic() {

				// Lấy reference đến HomeActivity
				    homeActivity = (HomeActivity) getActivity();
				    
				    // Lấy dữ liệu từ HomeActivity (nếu đã có)
				    if (homeActivity != null && homeActivity.getSongs() != null) {
						        songs = homeActivity.getSongs();
						        setupListView(); // Thiết lập ListView ngay nếu đã có dữ liệu
						    }

				SketchwareUtil.showMessage(getContext().getApplicationContext(), String.valueOf(songs.size()));

		}

		

		public void _setCircleImage(final ImageView _img, final String _url) {

				Glide.with(this)
				        .load(_url)
				        .circleCrop()
				        .into(_img);

				

		}

		

		public class ListViewAdapter extends BaseAdapter {

				

				ArrayList<HashMap<String, Object>> _data;

				

				public ListViewAdapter(ArrayList<HashMap<String, Object>> _arr) {

						_data = _arr;

				}

				

				@Override

				public int getCount() {

						return _data.size();

				}

				

				@Override

				public HashMap<String, Object> getItem(int _index) {

						return _data.get(_index);

				}

				

				@Override

				public long getItemId(int _index) {

						return _index;

				}

				

				@Override

				public View getView(final int _position, View _v, ViewGroup _container) {

						LayoutInflater _inflater = getActivity().getLayoutInflater();

						View _view = _v;

						if (_view == null) {

								_view = _inflater.inflate(R.layout.custom_list_view, null);

						}

						

						final LinearLayout linear1 = _view.findViewById(R.id.linear1);

						final ImageView imageview1 = _view.findViewById(R.id.imageview1);

						final LinearLayout linear2 = _view.findViewById(R.id.linear2);

						final ImageView imageview2 = _view.findViewById(R.id.imageview2);

						final TextView textview1 = _view.findViewById(R.id.textview1);

						final TextView textview2 = _view.findViewById(R.id.textview2);

						

						return _view;

				}

		}

}
