package com.example.networkmoudle;


import com.blankj.utilcode.util.Utils;
import com.example.networkmoudle.interceptor.SignInterceptor;
import com.example.networkmoudle.utils.X509Utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class SignRetrofit implements HttpImpl{

    private SignRetrofit(){}
    private static Retrofit retrofit;

    @Override
    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static class Build{

        public Build(){
            createRetrofit();
        }

        private void createRetrofit(){
            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            InputStream inputStream = null;
            try {
                inputStream = Utils.getApp().getAssets().open("tomcat.cer");
                X509TrustManager manager = X509Utils.createX509TrustManager(inputStream);
                okBuilder.sslSocketFactory(X509Utils.createSSLSocket(manager),manager);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnrecoverableKeyException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            okBuilder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            okBuilder.addInterceptor(new SignInterceptor());
            okBuilder.addInterceptor(interceptor);
            okBuilder.connectTimeout(30*1000, TimeUnit.MILLISECONDS);
            okBuilder.writeTimeout(30*1000,TimeUnit.MILLISECONDS);
            okBuilder.readTimeout(30*1000,TimeUnit.MILLISECONDS);
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.client(okBuilder.build());
            builder.baseUrl(BaseConstant.BaseUrl);
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            builder.addConverterFactory(GsonConverterFactory.create());
            retrofit = builder.build();
        }

        public SignRetrofit build(){
            return new SignRetrofit();
        }
    }
}
