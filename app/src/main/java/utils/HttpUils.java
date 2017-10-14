package utils;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;

import bean.BaseBean;


/**
 * 类用途：
 * 作者：史曌宇
 */

public class HttpUils {

    private static volatile HttpUils httputils;
    private   Context context;
    private Gson gson;

    private HttpUils(Context context) {
        //单例模式，封装构造方法
        this.context = context;
        gson = new Gson();
    }


    public static HttpUils getHttpUtils(Context activity) {
        //单例模式
        if (httputils == null) {
            synchronized (HttpUils.class) {
                if (httputils == null) {
                    httputils = new HttpUils(activity);
                }

            }
        }
        return httputils;
    }




    //拼接接口请求
    public void get(String url, String gtc_id, final Class zz,final ResultData resultData) throws IOException {
        /**
         *  Get请求
         */

        StringBuffer sb = new StringBuffer();
        sb.append(url);

//        for (Map.Entry<String, String> entry : params.entrySet()) {
//            entry.getKey();
//            entry.getValue();
//            sb.append(entry.getKey());
//            sb.append("=");
//            sb.append(entry.getValue());
        sb.append("&");
        sb.append("gc_id=");
        sb.append(gtc_id);


        String s = sb.toString();

        OkHttpUtils.get().url(s).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                //异常
                Log.d("HttpUtils", "onError");
            }

            @Override
            public void onResponse(String response) {
                //成功
                Log.d("HttpUtils", response);
                BaseBean basebean = (BaseBean) gson.fromJson(response, zz);

                String date = basebean.getDate();
                if (date .equals("20170927")) {
                    if (resultData != null) {
                        resultData.onSuccess(basebean);
                    }

                } else  {

                    new AlertDialog.Builder(context).setTitle("提示")
                            .setMessage("服务器错误请稍后再试").show();

                }


            }
        });
    }

    //直接请求不拼接接口
    public void get(String url, final Class zz, final ResultData resultData) {
        OkHttpClient okHttpClient=new OkHttpClient();
//        okHttpClient.setConnectTimeout(20,30);
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                //异常
//                Log.e("onError", e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                //成功
                Log.e("HttpUtils", response);
                BaseBean basebean = (BaseBean) gson.fromJson(response, zz);

                String date = basebean.getDate();

                if (date.equals("20170927")) {
                    if (resultData != null) {
                        resultData.onSuccess(basebean);
                    }

                } else  {

                    new AlertDialog.Builder(context).setTitle("提示")
                            .setMessage("服务器错误请稍后再试").show();

                }


            }
        });
    }


}

