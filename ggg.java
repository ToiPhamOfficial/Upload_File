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
																		    public View getView(final int position, View convertView, ViewGroup parent) {
																											      LayoutInflater _inflater = getLayoutInflater();
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
																																																						            //homeActivity.onSongSelected(position);
																																																						          }
																																													        }
																																				      });
																											
																											      return _view;
																											    }
																		  };
}
