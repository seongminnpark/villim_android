package net.villim.villim;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DiscoverFragment extends Fragment {

    private MainActivity activity;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View discoverView = inflater.inflate(R.layout.fragment_discover, container, false);

        activity = ((MainActivity) getActivity());

        recyclerView = (RecyclerView) discoverView.findViewById(R.id.discover_recycler_view);
        layoutManager = new LinearLayoutManager(activity);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        populateView();

        return discoverView;
    }

    // Make this async.
    private void populateView() {
        // Network operation to fetch.


        JSONObject jsonItem = new JSONObject();
        try {
            jsonItem.put(VillimRoom.KEY_HOUSE_ID, 0);
            jsonItem.put(VillimRoom.KEY_HOUSE_NAME, "[HongDae]1min from Hongik Univ.Stn");
            jsonItem.put(VillimRoom.KEY_ADDR_FULL, "[HongDae]1min from Hongik Univ.Stn");
            jsonItem.put(VillimRoom.KEY_ADDR_SUMMARY, "강남구, 서울, 한국");
            jsonItem.put(VillimRoom.KEY_ADDR_DIRECTION, "[HongDae]1min from Hongik Univ.Stn");
            jsonItem.put(VillimRoom.KEY_DESCRIPTION, "- 강남역 1번 출구 바로 앞입니다\n" +
                    "- 호텔 수준의 침구류, 매 예약마다 깨끗이 세탁 (퀸 사이즈)\n" +
                    "- 신축 깨끗한 오피스텔, 좋은 전망\n" +
                    "- 요리용 주방 도구 풀세트\n" +
                    "- 무선 wifi 제공\n" +
                    "- 세탁기 & 건조기\n" +
                    "- 침실 두 개");
            jsonItem.put(VillimRoom.KEY_NUM_GUEST, 4);
            jsonItem.put(VillimRoom.KEY_NUM_BEDROOM, 2);
            jsonItem.put(VillimRoom.KEY_NUM_BED, 2);
            jsonItem.put(VillimRoom.KEY_NUM_BATHROOM, 1);
            jsonItem.put(VillimRoom.KEY_RATE_PER_NIGHT, 102000);
            jsonItem.put(VillimRoom.KEY_DEPOSIT, 102000);
            jsonItem.put(VillimRoom.KEY_ADDITIONAL_GUEST_FEE, 102000);
            jsonItem.put(VillimRoom.KEY_CLEANING_FEE, 102000);
            jsonItem.put(VillimRoom.KEY_LOCK_ADDR, 192);
            jsonItem.put(VillimRoom.KEY_LOCK_PC, 244110);
            jsonItem.put(VillimRoom.KEY_LATITUDE, 37.5172);
            jsonItem.put(VillimRoom.KEY_LONGITUDE, 127.0413);
            jsonItem.put(VillimRoom.KEY_HOST_ID, 1);
            jsonItem.put(VillimRoom.KEY_ROOM_POLICY, "Room Policy");
            jsonItem.put(VillimRoom.KEY_REFUND_POLICY, "Refund Policy");
            jsonItem.put(VillimRoom.KEY_HOST_ID, 0);
            jsonItem.put(VillimRoom.KEY_HOST_NAME, "Kim Woobin, 김우빈");
            jsonItem.put(VillimRoom.KEY_HOST_RATING, 4.7);
            jsonItem.put(VillimRoom.KEY_HOST_REVIEW_COUNT, 143);
            jsonItem.put(VillimRoom.KEY_HOUSE_RATING, 3.6);
            jsonItem.put(VillimRoom.KEY_HOUSE_REVIEW_COUNT, 72);
            JSONArray amenitiesArray = new JSONArray();
            amenitiesArray.put(1);
            amenitiesArray.put(2);
            amenitiesArray.put(3);
            amenitiesArray.put(4);
            amenitiesArray.put(5);
            amenitiesArray.put(6);
            amenitiesArray.put(7);
            jsonItem.put(VillimRoom.KEY_AMENITY_IDS, amenitiesArray);
        } catch (JSONException e) {

        }

        VillimRoom obj = new VillimRoom(jsonItem);

        VillimRoom[] exampleArray = {obj, obj, obj, obj, obj, obj, obj, obj, obj};

        adapter = new DiscoverRecyclerAdapter(exampleArray);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}