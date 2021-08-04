package com.example.networkmoudle.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class X509Utils {

    public static SSLSocketFactory createSSLSocket(X509TrustManager manager) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null,new TrustManager[]{manager},null);
        return sslContext.getSocketFactory();
    }

    public static X509TrustManager createX509TrustManager(InputStream inputStream) throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, UnrecoverableKeyException {
        //读取服务器证书信息
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");
        //读取服务器下发到客户端的cer证书
        Certificate certificate =  certificateFactory.generateCertificate(inputStream);
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        char[]password = "zxy123123.".toCharArray();
        keyStore.load(null,password);
        keyStore.setCertificateEntry("tomcat",certificate);
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore,password);
        //手动生成验证keystore证书结果
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        return (X509TrustManager) trustManagers[0];
    }

}
