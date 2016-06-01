package org.apache.cordova.lmplugin.LMPlugin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.open.GameAppOperation;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

public class LMPlugin extends CordovaPlugin {


    private boolean test(JSONArray args, CallbackContext callbackContext) throws JSONException {
        currentCallbackContext = callbackContext;
        JSONObject json = args.getJSONObject(0);
        LMPlugin.this.webView.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, msg), callbackContext.getCallbackId());
    }
    
    

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        mTencent.onActivityResultData(requestCode,resultCode,intent,loginListener);
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_LOGIN) {
                Tencent.handleResultData(intent, loginListener);
            }
        }
        if (requestCode == Constants.REQUEST_QQ_SHARE) {
            if (resultCode == Constants.ACTIVITY_OK) {
                Tencent.handleResultData(intent, qqShareListener);
            }
        }
        if (requestCode == Constants.REQUEST_QZONE_SHARE) {
            if (resultCode == Constants.ACTIVITY_OK) {
                Tencent.handleResultData(intent, qZoneShareListener);
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTencent != null) {
            mTencent.releaseResource();
        }
    }
}
