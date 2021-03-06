package com.andretissot.firebaseextendednotification;

import org.json.JSONArray;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

/**
 * Created by André Augusto Tissot on 15/10/16.
 */

public class FirebaseExtendedNotification extends CordovaPlugin {
    static private Map<String, Object> lastNotificationTappedData;
    public static void setLastNotificationTappedData(Map<String, Object> notificationData){
        lastNotificationTappedData = notificationData;
    }
    public static Map<String, Object> getLastNotificationTappedData(){
        return lastNotificationTappedData;
    }

    public boolean execute(final String action, final JSONArray args,
                           final CallbackContext callbackContext) throws JSONException {
        if (action.equals("getLastNotificationTappedData")) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        Map<String, Object> lastNotificationData
                            = FirebaseExtendedNotification.getLastNotificationTappedData();
                        if(lastNotificationData == null){
                            callbackContext.error("null value");
                        } else {
                            callbackContext.success(new JSONObject(lastNotificationData));
                        }
                    } catch (Exception e){
                        callbackContext.error(e.getMessage());
                    }
                }
            });
        } else return false;
        return true;
    }
}
