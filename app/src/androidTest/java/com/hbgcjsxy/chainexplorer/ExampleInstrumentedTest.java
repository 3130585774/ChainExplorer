package com.hbgcjsxy.chainexplorer;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.xutils.http.RequestParams;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.hbgcjsxy.chainexplorer", appContext.getPackageName());
        System.out.println(GetBlockTransactionList());
        System.out.println("123123");

    }
    private JSONObject GetBlockTransactionList() {
        final JSONObject[] jsonObject = {null};
        RequestParams params = new RequestParams();
        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                jsonObject[0] = (JSONObject) msg.obj;
            }
        };

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("chainShortName", "BTC");
        parameters.put("height","783306");
        Uitls.HttpsGetX(handler, Constant.BASE_URL + Constant.BLOCKTRANSACTIONLIST,parameters);
        return jsonObject[0];
    }

}