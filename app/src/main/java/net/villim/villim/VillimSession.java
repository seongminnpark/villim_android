package net.villim.villim;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static net.villim.villim.VillimKeys.KEY_EMAIL;
import static net.villim.villim.VillimKeys.KEY_FIRSTNAME;
import static net.villim.villim.VillimKeys.KEY_FULLNAME;
import static net.villim.villim.VillimKeys.KEY_ID;
import static net.villim.villim.VillimKeys.KEY_LASTNAME;
import static net.villim.villim.VillimKeys.KEY_PROFILE_PIC_URL;

/**
 * Created by seongmin on 6/9/17.
 */

public class VillimSession {
    public final static String KEY_LOGGED_IN = "logged_in";

    private SharedPreferences prefs;

    public VillimSession(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /* Logged in */
    public void setLoggedIn(boolean loggedIn) {
        if (!loggedIn) {
            prefs.edit().clear().apply();
        }
        prefs.edit().putBoolean(KEY_LOGGED_IN, loggedIn).apply();
    }

    public boolean getLoggedIn() {
        return prefs.getBoolean(KEY_LOGGED_IN, false);
    }

    /* Id */
    public void setId(int id) {
        prefs.edit().putInt(KEY_ID, id).apply();
    }

    public int getId() {
        int id = prefs.getInt(KEY_ID,0);
        return id;
    }


    /* Fullname */
    public void setFullName(String fullname) {
        prefs.edit().putString(KEY_FULLNAME, fullname).apply();
    }

    public String getFullName() {
        String usename = prefs.getString(KEY_FULLNAME,"");
        return usename;
    }

    /* Firstname */
    public void setFirstName(String fullname) {
        prefs.edit().putString(KEY_FIRSTNAME, fullname).apply();
    }

    public String getFirstName() {
        String usename = prefs.getString(KEY_FIRSTNAME,"");
        return usename;
    }

    /* Lastname */
    public void setLastName(String fullname) {
        prefs.edit().putString(KEY_LASTNAME, fullname).apply();
    }

    public String getLastName() {
        String usename = prefs.getString(KEY_LASTNAME,"");
        return usename;
    }

    /* Email */
    public void setEmail(String email) {
        prefs.edit().putString(KEY_EMAIL, email).apply();
    }

    public String getEmail() {
        String email = prefs.getString(KEY_EMAIL,"");
        return email;
    }

    /* Profile pic url */
    public void setProfilePicUrl(String url) {
        prefs.edit().putString(KEY_PROFILE_PIC_URL, url).apply();
    }

    public String getProfilePicUrl() {
        String url = prefs.getString(KEY_PROFILE_PIC_URL,"");
        return url;
    }
}
