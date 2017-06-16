package net.villim.villim;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static net.villim.villim.VillimKeys.KEY_ADDITIONAL_GUEST_FEE;
import static net.villim.villim.VillimKeys.KEY_ADDR_DIRECTION;
import static net.villim.villim.VillimKeys.KEY_ADDR_FULL;
import static net.villim.villim.VillimKeys.KEY_ADDR_SUMMARY;
import static net.villim.villim.VillimKeys.KEY_AMENITY_IDS;
import static net.villim.villim.VillimKeys.KEY_CANCELLATION_POLICY;
import static net.villim.villim.VillimKeys.KEY_CLEANING_FEE;
import static net.villim.villim.VillimKeys.KEY_DEPOSIT;
import static net.villim.villim.VillimKeys.KEY_DESCRIPTION;
import static net.villim.villim.VillimKeys.KEY_HOST_ID;
import static net.villim.villim.VillimKeys.KEY_HOST_NAME;
import static net.villim.villim.VillimKeys.KEY_HOST_PROFILE_PIC_URL;
import static net.villim.villim.VillimKeys.KEY_HOST_RATING;
import static net.villim.villim.VillimKeys.KEY_HOST_REVIEW_COUNT;
import static net.villim.villim.VillimKeys.KEY_HOUSE_ID;
import static net.villim.villim.VillimKeys.KEY_HOUSE_NAME;
import static net.villim.villim.VillimKeys.KEY_HOUSE_PIC_URLS;
import static net.villim.villim.VillimKeys.KEY_HOUSE_POLICY;
import static net.villim.villim.VillimKeys.KEY_HOUSE_RATING;
import static net.villim.villim.VillimKeys.KEY_HOUSE_REVIEW_COUNT;
import static net.villim.villim.VillimKeys.KEY_HOUSE_THUMBNAIL_URL;
import static net.villim.villim.VillimKeys.KEY_LATITUDE;
import static net.villim.villim.VillimKeys.KEY_LOCK_ADDR;
import static net.villim.villim.VillimKeys.KEY_LOCK_PC;
import static net.villim.villim.VillimKeys.KEY_LONGITUDE;
import static net.villim.villim.VillimKeys.KEY_NUM_BATHROOM;
import static net.villim.villim.VillimKeys.KEY_NUM_BED;
import static net.villim.villim.VillimKeys.KEY_NUM_BEDROOM;
import static net.villim.villim.VillimKeys.KEY_NUM_GUEST;
import static net.villim.villim.VillimKeys.KEY_RATE_PER_NIGHT;

/**
 * Created by seongmin on 5/30/17.
 */

public class VillimHouse implements Parcelable {

    // Raw query from server.
    public int houseId;
    public String houseName;
    public String addrFull;
    public String addrSummary;
    public String addrDirection;
    public String description;
    public int numGuest;
    public int numBedroom;
    public int numBed;
    public int numBathroom;
    public int ratePerNight;
    public int deposit;
    public int additionalGuestFee;
    public int cleaningFee;
    private int lockAddr;
    private int lockPc;
    public double latitude;
    public double longitude;
    public String housePolicy;
    public String cancellationPolicy;
    public VillimUser host;
    int hostId;
    String hostName;
    float hostRating;
    int hostReviewCount;
    String hostProfilePicUrl;
    float houseRating;
    int houseReviewCount;
    int[] amenityIds;
    String houseThumbnailUrl;
    String[] housePicUrls;

    //public VillimReview[] reviews;
    //public int[] utilities;

    public VillimHouse() {

    }

    public VillimHouse(JSONObject jsonObject) {
        try {
            houseId = jsonObject.getInt(KEY_HOUSE_ID);
            houseName = jsonObject.getString(KEY_HOUSE_NAME);
            addrFull = jsonObject.getString(KEY_ADDR_FULL);
            addrSummary = jsonObject.getString(KEY_ADDR_SUMMARY);
            addrDirection = jsonObject.getString(KEY_ADDR_DIRECTION);
            description = jsonObject.getString(KEY_DESCRIPTION);
            numGuest = jsonObject.getInt(KEY_NUM_GUEST);
            numBedroom = jsonObject.getInt(KEY_NUM_BEDROOM);
            numBed = jsonObject.getInt(KEY_NUM_BED);
            numBathroom = jsonObject.getInt(KEY_NUM_BATHROOM);
            ratePerNight = jsonObject.getInt(KEY_RATE_PER_NIGHT);
            deposit = jsonObject.getInt(KEY_DEPOSIT);
            additionalGuestFee = jsonObject.getInt(KEY_ADDITIONAL_GUEST_FEE);
            cleaningFee = jsonObject.getInt(KEY_CLEANING_FEE);
            lockAddr = jsonObject.getInt(KEY_LOCK_ADDR);
            lockPc = jsonObject.getInt(KEY_LOCK_PC);
            latitude = jsonObject.getDouble(KEY_LATITUDE);
            longitude = jsonObject.getDouble(KEY_LONGITUDE);
            housePolicy = jsonObject.getString(KEY_HOUSE_POLICY);
            cancellationPolicy = jsonObject.getString(KEY_CANCELLATION_POLICY);

            //host = VillimUser.getUserFromServer(jsonObject.getInt(KEY_HOST_ID));
            hostId = jsonObject.getInt(KEY_HOST_ID);
            hostName = jsonObject.getString(KEY_HOST_NAME);
            hostRating = (float) jsonObject.getDouble(KEY_HOST_RATING);
            hostReviewCount = jsonObject.getInt(KEY_HOST_REVIEW_COUNT);
            hostProfilePicUrl = jsonObject.getString(KEY_HOST_PROFILE_PIC_URL);
            houseRating = (float) jsonObject.getDouble(KEY_HOUSE_RATING);
            houseReviewCount = jsonObject.getInt(KEY_HOUSE_REVIEW_COUNT);

            amenityIds = VillimUtil.JSONArrayToIntArray(jsonObject.getJSONArray(KEY_AMENITY_IDS));
            housePicUrls = VillimUtil.JSONArrayToStringArray(jsonObject.getJSONArray(KEY_HOUSE_PIC_URLS));
//            reviews = VillimReview.getHouseReviewsFromServer(houseId);
        } catch (JSONException e) {

        }
    }

    private String[] JSONtoArray(JSONArray array) {
        String[] stringsArray = new String[array.length()];
        try {
            for (int i = 0; i < array.length(); i++) {
                stringsArray[i] = array.getString(i);
            }
        } catch (JSONException e) {

        }
        return stringsArray;
    }

    public static VillimHouse createHouseFromJSONObject(JSONObject jsonObject) {
        /* Create user instance */
        VillimHouse house = new VillimHouse();

         /* No need to null check here because if we dont set it, it's going to be null anyway */
        house.houseId = jsonObject.optInt(KEY_HOUSE_ID);
        house.houseName = jsonObject.optString(KEY_HOUSE_NAME);
        house.addrFull = jsonObject.optString(KEY_ADDR_FULL);
        house.addrSummary = jsonObject.optString(KEY_ADDR_SUMMARY);
        house.addrDirection = jsonObject.optString(KEY_ADDR_DIRECTION);
        house.description = jsonObject.optString(KEY_DESCRIPTION);
        house.numGuest = jsonObject.optInt(KEY_NUM_GUEST);
        house.numBedroom = jsonObject.optInt(KEY_NUM_BEDROOM);
        house.numBed = jsonObject.optInt(KEY_NUM_BED);
        house.numBathroom = jsonObject.optInt(KEY_NUM_BATHROOM);
        house.ratePerNight = jsonObject.optInt(KEY_RATE_PER_NIGHT);
        house.deposit = jsonObject.optInt(KEY_DEPOSIT);
        house.additionalGuestFee = jsonObject.optInt(KEY_ADDITIONAL_GUEST_FEE);
        house.cleaningFee = jsonObject.optInt(KEY_CLEANING_FEE);
        house.lockAddr = jsonObject.optInt(KEY_LOCK_ADDR);
        house.lockPc = jsonObject.optInt(KEY_LOCK_PC);
        house.latitude = jsonObject.optDouble(KEY_LATITUDE);
        house.longitude = jsonObject.optDouble(KEY_LONGITUDE);
        house.housePolicy = jsonObject.optString(KEY_HOUSE_POLICY);
        house.cancellationPolicy = jsonObject.optString(KEY_CANCELLATION_POLICY);

        //host = VillimUser.getUserFromServer(jsonObject.getInt(KEY_HOST_ID));
        house.hostId = jsonObject.optInt(KEY_HOST_ID);
        house.hostName = jsonObject.optString(KEY_HOST_NAME);
        house.hostRating = (float) jsonObject.optDouble(KEY_HOST_RATING);
        house.hostReviewCount = jsonObject.optInt(KEY_HOST_REVIEW_COUNT);
        house.hostProfilePicUrl = jsonObject.optString(KEY_HOST_PROFILE_PIC_URL);
        house.houseRating = (float) jsonObject.optDouble(KEY_HOUSE_RATING);
        house.houseReviewCount = jsonObject.optInt(KEY_HOUSE_REVIEW_COUNT);

        house.houseThumbnailUrl = jsonObject.optString(KEY_HOUSE_THUMBNAIL_URL);
        house.amenityIds = VillimUtil.JSONArrayToIntArray(jsonObject.optJSONArray(KEY_AMENITY_IDS));
        house.housePicUrls = VillimUtil.JSONArrayToStringArray(jsonObject.optJSONArray(KEY_HOUSE_PIC_URLS));

        return house;
    }

    public static VillimHouse[] houseArrayFromJsonArray(JSONArray jsonArray) {

        if (jsonArray == null) {
            return new VillimHouse[0];
        }

        VillimHouse[] houses = new VillimHouse[jsonArray.length()];

        try {
            for (int i = 0; i < jsonArray.length(); ++i) {
                VillimHouse review = createHouseFromJSONObject(jsonArray.getJSONObject(i));
                houses[i] = review;
            }

        } catch (JSONException e) {

        }
        return houses;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(houseId);
        dest.writeString(houseName);
        dest.writeString(addrFull);
        dest.writeString(addrSummary);
        dest.writeString(addrDirection);
        dest.writeString(description);
        dest.writeInt(numGuest);
        dest.writeInt(numBedroom);
        dest.writeInt(numBed);
        dest.writeInt(numBathroom);
        dest.writeInt(ratePerNight);
        dest.writeInt(deposit);
        dest.writeInt(additionalGuestFee);
        dest.writeInt(cleaningFee);
        dest.writeInt(lockAddr);
        dest.writeInt(lockPc);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(housePolicy);
        dest.writeString(cancellationPolicy);
        dest.writeInt(hostId);
        dest.writeString(hostName);
        dest.writeFloat(hostRating);
        dest.writeInt(hostReviewCount);
        dest.writeString(hostProfilePicUrl);
        dest.writeFloat(houseRating);
        dest.writeInt(houseReviewCount);
        dest.writeString(houseThumbnailUrl);
        dest.writeIntArray(amenityIds);
        dest.writeStringArray(housePicUrls);
//        dest.writeParcelableArray(reviews, 0);
    }

    protected VillimHouse(Parcel in) {
        houseId = in.readInt();
        houseName = in.readString();
        addrFull = in.readString();
        addrSummary = in.readString();
        addrDirection = in.readString();
        description = in.readString();
        numGuest = in.readInt();
        numBedroom = in.readInt();
        numBed = in.readInt();
        numBathroom = in.readInt();
        ratePerNight = in.readInt();
        deposit = in.readInt();
        additionalGuestFee = in.readInt();
        cleaningFee = in.readInt();
        lockAddr = in.readInt();
        lockPc = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
        housePolicy = in.readString();
        cancellationPolicy = in.readString();
        hostId = in.readInt();
        hostName = in.readString();
        hostRating = in.readFloat();
        hostReviewCount = in.readInt();
        hostProfilePicUrl = in.readString();
        houseRating = in.readFloat();
        houseReviewCount = in.readInt();
        houseThumbnailUrl = in.readString();
        amenityIds = in.createIntArray();
        housePicUrls = in.createStringArray();
//        reviews = in.createTypedArray(VillimReview.CREATOR);
    }

    public static final Creator<VillimHouse> CREATOR = new Creator<VillimHouse>() {
        @Override
        public VillimHouse createFromParcel(Parcel in) {
            return new VillimHouse(in);
        }

        @Override
        public VillimHouse[] newArray(int size) {
            return new VillimHouse[size];
        }
    };
}
