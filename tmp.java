package com.tpo.tmusic;



import android.animation.*;

import android.app.*;

import android.content.*;

import android.content.Intent;

import android.content.res.*;

import android.graphics.*;

import android.graphics.Typeface;

import android.graphics.drawable.*;

import android.media.*;

import android.media.MediaPlayer;

import android.net.*;

import android.net.Uri;

import android.os.*;

import android.text.*;

import android.text.style.*;

import android.util.*;

import android.view.*;

import android.view.View;

import android.view.View.*;

import android.view.animation.*;

import android.webkit.*;

import android.widget.*;

import android.widget.ImageView;

import android.widget.LinearLayout;

import android.widget.ProgressBar;

import android.widget.TextView;

import androidx.annotation.*;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.DialogFragment;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentStatePagerAdapter;

import androidx.viewpager.widget.PagerAdapter;

import androidx.viewpager.widget.ViewPager;

import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;

import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

import com.bumptech.glide.Glide;

import com.google.android.material.color.MaterialColors;

import com.google.android.material.tabs.TabLayout;

import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import de.hdodenhof.circleimageview.*;

import java.io.*;

import java.text.*;

import java.util.*;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.regex.*;

import org.json.*;



public class HomeActivity extends AppCompatActivity {
	private BroadcastReceiver progressReceiver = new BroadcastReceiver() {
		    @Override
		    public void onReceive(Context context, Intent intent) {
			        if ("ACTION_UPDATE_PROGRESS".equals(intent.getAction())) {
				            int duration = intent.getIntExtra("duration", 0);
				            int current = intent.getIntExtra("current", 0);
				
				            progressbar.setMax(duration);
				            //c.setTimeInMillis((long)(MusicPlayerManager.instance.getDuration()));

				            //duration.setText(new SimpleDateFormat("mm:ss").format(c.getTime()));
				            progressbar.setProgress(current);
				        }
			    }
	};

		

		private boolean isPlaying = false;

		private String jsonDataArr = "";

		private HashMap<String, Object> responseMap = new HashMap<>();

		private double currentIndex = 0;

		private boolean hasSetSource = false;

		private String ggg = "";

		private boolean isLooping = false;

		private boolean isRandom = false;

		

		private ArrayList<HashMap<String, Object>> dataList = new ArrayList<>();

		

		private LinearLayout linear1;

		private TabLayout tablayout1;

		private ViewPager viewpager1;

		private LinearLayout miniControl;

		private TextView textview1;

		private LinearLayout linear2;

		private ImageView search_song;

		private ImageView settings_and_more;

		private ProgressBar progressbar;

		private LinearLayout linear4;

		private CircleImageView circleimageview1;

		private LinearLayout linear7;

		private ImageView imageview4;

		private ImageView imageview5;

		private TextView textview2;

		private TextView textview3;

		

		private FragFragmentAdapter frag;

		private RequestNetwork rq;

		private RequestNetwork.RequestListener _rq_request_listener;

		private MediaPlayer mp;

		private Intent it = new Intent();

		private Intent i = new Intent();

		

		@Override

		protected void onCreate(Bundle _savedInstanceState) {

				super.onCreate(_savedInstanceState);

				setContentView(R.layout.home);

				initialize(_savedInstanceState);

				initializeLogic();

		}

		

		private void initialize(Bundle _savedInstanceState) {

				linear1 = findViewById(R.id.linear1);

				tablayout1 = findViewById(R.id.tablayout1);

				viewpager1 = findViewById(R.id.viewpager1);

				miniControl = findViewById(R.id.miniControl);

				textview1 = findViewById(R.id.textview1);

				linear2 = findViewById(R.id.linear2);

				search_song = findViewById(R.id.search_song);

				settings_and_more = findViewById(R.id.settings_and_more);

				progressbar = findViewById(R.id.progressbar);

				linear4 = findViewById(R.id.linear4);

				circleimageview1 = findViewById(R.id.circleimageview1);

				linear7 = findViewById(R.id.linear7);

				imageview4 = findViewById(R.id.imageview4);

				imageview5 = findViewById(R.id.imageview5);

				textview2 = findViewById(R.id.textview2);

				textview3 = findViewById(R.id.textview3);

				frag = new FragFragmentAdapter(getApplicationContext(), getSupportFragmentManager());

				rq = new RequestNetwork(this);

				

				miniControl.setOnClickListener(new View.OnClickListener() {

						@Override

						public void onClick(View _view) {

								it.setClass(getApplicationContext(), ControlActivity.class);

								startActivity(it);

						}

				});

				

				imageview4.setOnClickListener(new View.OnClickListener() {

						@Override

						public void onClick(View _view) {

								if (MusicPlayerManager.instance.getCurrentIndex() > -1) {

										if (MusicPlayerManager.instance.isPlaying()) {

												imageview4.setImageResource(R.drawable.ic_mt_play);

										} else {

												imageview4.setImageResource(R.drawable.ic_mt_pause);

										}

										MusicPlayerManager.instance.togglePlayPause();

										MusicPlayerManager.instance.refresh(MusicPlayerManager.StateChangeType.PLAY_STATE_CHANGED);

								} else {

										MusicPlayerManager.instance.play();

										MusicPlayerManager.instance.setCurrentIndex(0);

										MusicPlayerManager.instance.play();

										MusicPlayerManager.instance.refresh(MusicPlayerManager.StateChangeType.INDEX_CHANGED);

										imageview4.setImageResource(R.drawable.ic_mt_pause);

								}

						}

				});

				

				_rq_request_listener = new RequestNetwork.RequestListener() {

						@Override

						public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {

								final String _tag = _param1;

								final String _response = _param2;

								final HashMap<String, Object> _responseHeaders = _param3;

								responseMap = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());

								jsonDataArr = new Gson().toJson(responseMap.get("data"));

								dataList = new Gson().fromJson(jsonDataArr, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());

								textview2.setText(dataList.get((int)0).get("title").toString());

								textview3.setText(dataList.get((int)0).get("artist").toString());

								Glide.with(getApplicationContext()).load(Uri.parse(dataList.get((int)0).get("image_url").toString())).into(circleimageview1);

								miniControl.setVisibility(View.VISIBLE);

								MusicPlayerManager.instance.setSongs(dataList);

								MusicPlayerManager.instance.refresh(MusicPlayerManager.StateChangeType.SONGS_CHANGED);

						}

						

						@Override

						public void onErrorResponse(String _param1, String _param2) {

								final String _tag = _param1;

								final String _message = _param2;

								

						}

				};

		}

		

		private void initializeLogic() {

				registerReceiver(progressReceiver, new IntentFilter("ACTION_UPDATE_PROGRESS"));

				// Init UI

				textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/fz_poppins_semibold.ttf"), 0);

				textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/fz_poppins_regular.ttf"), 0);

				textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/fz_poppins_light.ttf"), 0);

				tablayout1.setupWithViewPager(viewpager1);

				frag.setTabCount(3);

				viewpager1.setAdapter(frag);

				// Sub event

				MusicPlayerManager.instance.addListener(new MusicPlayerManager.Listener() {
						    @Override
						    public void onStateChanged(MusicPlayerManager manager, MusicPlayerManager.StateChangeType type) {
								        switch(type){
										case INDEX_CHANGED: {
												i.setClass(getApplicationContext(), MusicService.class);

												i.setAction("ACTION_PLAY_INDEX");

												i.putExtra("index", MusicPlayerManager.instance.getCurrentIndex());
												startForegroundService(i);

												textview2.setText(dataList.get((int)MusicPlayerManager.instance.getCurrentIndex()).get("title").toString());

												textview3.setText(dataList.get((int)MusicPlayerManager.instance.getCurrentIndex()).get("artist").toString());

												imageview4.setImageResource(R.drawable.ic_mt_pause);

												Glide.with(getApplicationContext()).load(Uri.parse(dataList.get((int)MusicPlayerManager.instance.getCurrentIndex()).get("image_url").toString())).into(circleimageview1);

												break;
										}

										case PLAY_STATE_CHANGED: {
												i.setClass(getApplicationContext(), MusicService.class);

												i.setAction("ACTION_TOGGLE_PLAY");

												startForegroundService(i);

												break;
										}
								}
								    }
				});

				// rq API

				rq.startRequestNetwork(RequestNetworkController.GET, "https://api.phamvantoi.id.vn/music/get-songs", "", _rq_request_listener);

				Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/fz_poppins_regular.ttf");
				
				ViewGroup tabs = (ViewGroup) ((ViewGroup) tablayout1.getChildAt(0));
				for (int i = 0; i < tabs.getChildCount(); i++) {
							    View tab = tabs.getChildAt(i);
							    if (tab instanceof ViewGroup) {
										        ViewGroup vg = (ViewGroup) tab;
										        for (int j = 0; j < vg.getChildCount(); j++) {
													            View v = vg.getChildAt(j);
													            if (v instanceof TextView) {
																                ((TextView) v).setTypeface(tf, Typeface.NORMAL);
																                ((TextView) v).setTextSize(14);
																                ((TextView) v).setTextColor(Color.WHITE); // tuỳ chỉnh nếu cần
																            }
													        }
										    }
				}

		}

		

		public class FragFragmentAdapter extends FragmentStatePagerAdapter {

				// This class is deprecated, you should migrate to ViewPager2:

				// https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2

				Context context;

				int tabCount;

				

				public FragFragmentAdapter(Context context, FragmentManager manager) {

						super(manager);

						this.context = context;

				}

				

				public void setTabCount(int tabCount) {

						this.tabCount = tabCount;

				}

				

				@Override

				public int getCount() {

						return tabCount;

				}

				

				@Override

				public CharSequence getPageTitle(int _position) {

						if (_position == 0) {

								return "Bài hát";

						}

						if (_position == 1) {

								return "Thể loại";

						}

						if (_position == 2) {

								return "D.sách phát";

						}

						return "";

				}

				

				@Override

				public Fragment getItem(int _position) {

						if (_position == 0) {

								return new SongsFragmentActivity();

						}

						if (_position == 1) {

								return new GenreFragmentActivity();

						}

						if (_position == 2) {

								return new PlaylistFragmentActivity();

						}

						return new Fragment();

				}

		}

		

		@Override

		public void onDestroy() {

				super.onDestroy();

				unregisterReceiver(progressReceiver);

		}

		public void _setCircleImage(final ImageView _img, final String _url) {

				Glide.with(this)
				        .load(_url)
				        .circleCrop()
				        .into(_img);

				

		}

		

		

		@Deprecated

		public void showMessage(String _s) {

				Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();

		}

		

		@Deprecated

		public int getLocationX(View _v) {

				int _location[] = new int[2];

				_v.getLocationInWindow(_location);

				return _location[0];

		}

		

		@Deprecated

		public int getLocationY(View _v) {

				int _location[] = new int[2];

				_v.getLocationInWindow(_location);

				return _location[1];

		}

		

		@Deprecated

		public int getRandom(int _min, int _max) {

				Random random = new Random();

				return random.nextInt(_max - _min + 1) + _min;

		}

		

		@Deprecated

		public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {

				ArrayList<Double> _result = new ArrayList<Double>();

				SparseBooleanArray _arr = _list.getCheckedItemPositions();

				for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {

						if (_arr.valueAt(_iIdx))

						_result.add((double)_arr.keyAt(_iIdx));

				}

				return _result;

		}

		

		@Deprecated

		public float getDip(int _input) {

				return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());

		}

		

		@Deprecated

		public int getDisplayWidthPixels() {

				return getResources().getDisplayMetrics().widthPixels;

		}

		

		@Deprecated

		public int getDisplayHeightPixels() {

				return getResources().getDisplayMetrics().heightPixels;

		}

}
