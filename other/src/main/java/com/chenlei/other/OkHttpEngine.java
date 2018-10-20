package com.chenlei.other;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author chen Lei
 * 使用单例模式简单封装了OKHttp3
 */
public class OkHttpEngine {
    private final static String TAG="OkHttpEngine";
    private static volatile  OkHttpEngine mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public static OkHttpEngine getInstance(Context context) {
        if (mInstance == null) {
            synchronized (OkHttpEngine.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpEngine(context);
                }
            }
        }
        return mInstance;
    }

    private OkHttpEngine(){}

    private OkHttpEngine(Context context) {
        File sdcache = context.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
         mOkHttpClient=builder.build();
         mHandler = new Handler();
    }

    /**
     * 异步get请求
     * @param url
     * @param callback
     */
    public void getAsynHttp(String url, ResultCallback callback) {

        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        dealResult(call, callback);
    }


    private void dealResult(Call call, final ResultCallback callback) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedCallback(call.request(), e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sendSuccessCallback(response.body().string(), callback);
            }

            private void sendSuccessCallback(final String str, final ResultCallback callback) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            try {
                                callback.onResponse(str);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }

            private void sendFailedCallback(final Request request, final Exception e, final ResultCallback callback) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null)
                            callback.onError(request, e);
                    }
                });
            }

        });
    }

    public void postAsynHttp(String url, ResultCallback callback){
        RequestBody formBody=new FormBody.Builder()
                .add("pp","tt")
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call call = mOkHttpClient.newCall(request);
        dealResult(call, callback);
    }


    public static final MediaType MEDIA_TYPE_MARKDOWN=MediaType.parse("text/x-markdown;charset=utf-8");
    public static final MediaType MEDIA_TYPE_AUDIO=MediaType.parse("audio/x-wav");

    public void asynUploadFile(File file,String url, final ResultCallback callback){

        RequestBody requestBody=new MultipartBody
                .Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("description","qwe")
                .addFormDataPart("file",file.getName(),
                        RequestBody.create(MEDIA_TYPE_MARKDOWN,file))
                .build();

        Request request=new Request
                .Builder()
                .header("Authorization","Client-ID"+"...")
                .url(url)
                .post(requestBody)
                .build();

        Log.e(TAG,"数据上传中...");

        Call call = mOkHttpClient.newCall(request);
        dealResult(call, callback);
    }

    interface ResultCallback
    {
        public abstract void onError(Request request, Exception e);

        public abstract void onResponse(String str) throws IOException;
    }
}
