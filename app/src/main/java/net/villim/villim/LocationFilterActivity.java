package net.villim.villim;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LocationFilterActivity extends AppCompatActivity {
    public final static String LOCATION = "location";

    private Toolbar toolbar;
    private RelativeLayout searchContainer;
    private EditText searchField;
    private ListViewCompat popularLocationsListView;
    private VillimLocation[] popularLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_filter);

        /* Toolbar */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(ContextCompat.getDrawable(getApplicationContext(), R.drawable.back_arrow_dark));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        /* Relative layout that contains the search area */
        searchContainer = (RelativeLayout) findViewById(R.id.search_container);
        searchField = (EditText) findViewById(R.id.search_field);
        int iconSize = getResources().getDimensionPixelSize(R.dimen.location_filter_icon_size);
        Drawable searchIcon = getResources().getDrawable(R.drawable.btn_search);
        Drawable clearIcon = getResources().getDrawable(R.drawable.btn_delete_gray);
        searchIcon.setBounds(0, 0, iconSize, iconSize);
        clearIcon.setBounds(0, 0, iconSize, iconSize);
        searchField.setCompoundDrawables(searchIcon, null, clearIcon, null);

        /* Popular locations list */
        popularLocationsListView = (ListViewCompat) findViewById(R.id.search_popular_locations_listview);
        TextView textView = new TextView(getApplicationContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15.0f);
        textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.search_suggestion_title));
        textView.setText(getString(R.string.search_popular_locations));
        popularLocationsListView.addHeaderView(textView);
        popularLocations = new VillimLocation[] {
                new VillimLocation("강남구", "강남구", "서울특별시 강남구", "서울특별시 강남구"),
                new VillimLocation("강남역", "강남역", "서초구 강남", "서초구 강남"),
                new VillimLocation("대한민국", "대한민국", "서울특별시 강남대로 서초구", "서울특별시 강남대로 서초구"),
                new VillimLocation("고속버스 터미널", "강남 고속버스 터미널", "서초구 강남 고속버스 터미널12312112121221211", "서초구 강남 고속버스 터미널")};
        popularLocationsListView.setAdapter(new SearchSuggestionListViewAdapter(getApplicationContext(), popularLocations));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private class SearchSuggestionListViewAdapter extends ArrayAdapter<VillimLocation> {
        private VillimLocation[] locations;

        public SearchSuggestionListViewAdapter(Context context, VillimLocation[] popularLocations) {
            super(context, R.layout.search_suggestion_list_item, popularLocations);
            this.locations = popularLocations;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_suggestion_list_item, parent, false);
            }

            final VillimLocation location = locations[position];

            /* Location Name */
            TextView locationName = (TextView) convertView.findViewById(R.id.location_name);
            locationName.setText(location.name);

            /* Location detail */
            TextView locationDetail = (TextView) convertView.findViewById(R.id.location_detail);
            locationDetail.setText(location.addrSummary);

            /* Set touch listeners */
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra(LOCATION, location);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }
            });

            return convertView;
        }
    }


}
