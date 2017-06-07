package net.villim.villim;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
//            jsonItem.put(VillimRoom.KEY_DESCRIPTION, "1\n2\n3\n4\n5\n6");
            jsonItem.put(VillimRoom.KEY_DESCRIPTION, "1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15");
            jsonItem.put(VillimRoom.KEY_NUM_GUEST, 4);
            jsonItem.put(VillimRoom.KEY_NUM_BEDROOM, 2);
            jsonItem.put(VillimRoom.KEY_NUM_BED, 2);
            jsonItem.put(VillimRoom.KEY_NUM_BATHROOM, 1);
            jsonItem.put(VillimRoom.KEY_PRICE, 102000);
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