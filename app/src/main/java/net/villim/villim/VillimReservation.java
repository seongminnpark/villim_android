package net.villim.villim;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import static net.villim.villim.VillimKeys.KEY_CHECKOUT;
import static net.villim.villim.VillimKeys.KEY_GUEST_ID;
import static net.villim.villim.VillimKeys.KEY_HOST_ID;
import static net.villim.villim.VillimKeys.KEY_HOUSE_ID;
import static net.villim.villim.VillimKeys.KEY_RESERVATION_CODE;
import static net.villim.villim.VillimKeys.KEY_RESERVATION_ID;
import static net.villim.villim.VillimKeys.KEY_RESERVATION_STATUS;
import static net.villim.villim.VillimKeys.KEY_RESERVATION_TIME;
import static net.villim.villim.VillimKeys.KEY_CHECKIN;

/**
 * Created by seongmin on 6/15/17.
 */

public class VillimReservation implements Parcelable {
    public static final int CONFIRMED = 0;
    public static final int STAYING = 1;
    public static final int DONE = 2;

    public int reservationId;
    public int houseId;
    public int hostId;
    public int guestId;
    public Date startDate;
    public Date endDate;
    public String reservationTime;
    public int reservationStatus;
    public String reservationCode;

    public VillimReservation() {

    }

    protected VillimReservation(Parcel in) {
        reservationId = in.readInt();
        houseId = in.readInt();
        hostId = in.readInt();
        guestId = in.readInt();
        reservationTime = in.readString();
        reservationStatus = in.readInt();
        reservationCode = in.readString();
    }

    public static final Creator<VillimReservation> CREATOR = new Creator<VillimReservation>() {
        @Override
        public VillimReservation createFromParcel(Parcel in) {
            return new VillimReservation(in);
        }

        @Override
        public VillimReservation[] newArray(int size) {
            return new VillimReservation[size];
        }
    };

    public static VillimReservation createReservationFromJSONObject(JSONObject reservationInfo) {
        /* Create user instance */
        VillimReservation reservation = new VillimReservation();

         /* No need to null check here because if we dont set it, it's going to be null anyway */
        try {
            reservation.reservationId = reservationInfo.getInt(KEY_RESERVATION_ID);
            reservation.houseId = reservationInfo.getInt(KEY_HOUSE_ID);
            reservation.hostId = reservationInfo.getInt(KEY_HOST_ID);
            reservation.guestId = reservationInfo.getInt(KEY_GUEST_ID);
            reservation.startDate = net.villim.villim.VillimUtils.dateFromDateString(reservationInfo.getString(KEY_CHECKIN));
            reservation.endDate = net.villim.villim.VillimUtils.dateFromDateString(reservationInfo.getString(KEY_CHECKOUT));
            reservation.reservationTime = reservationInfo.getString(KEY_RESERVATION_TIME);
            reservation.reservationStatus = reservationInfo.getInt(KEY_RESERVATION_STATUS);
            reservation.reservationCode = reservationInfo.getString(KEY_RESERVATION_CODE);
        } catch (JSONException e) {

        }
        return reservation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(reservationId);
        dest.writeInt(houseId);
        dest.writeInt(hostId);
        dest.writeInt(guestId);
        dest.writeString(reservationTime);
        dest.writeInt(reservationStatus);
        dest.writeString(reservationCode);
    }

    public static String stringFromReservationStatus(Context context, int status) {
        switch (status) {
            case CONFIRMED:
                return context.getString(R.string.status_confirmed);
            case STAYING:
                return context.getString(R.string.status_active);
            case DONE:
                return context.getString(R.string.status_done);
            default:
                return context.getString(R.string.status_confirmed);
        }
    }
}
