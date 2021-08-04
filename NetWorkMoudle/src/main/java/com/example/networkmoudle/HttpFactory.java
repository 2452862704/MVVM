package com.example.networkmoudle;

public class HttpFactory {

    private HttpFactory(){}

    private static HttpFactory instance;

    public static HttpFactory getInstance(){
        if (instance == null)
            instance = new HttpFactory();
        return instance;
    }

    private HttpImpl upload,sign,tokenSign,token;

    public HttpImpl factory(HttpType type){
        HttpImpl http = null;
        switch (type){
            case UPLOADTYPE:
                if (upload == null)
                    upload = new UploadRetrofit.Build().build();
                http = upload;
                break;
            case SIGNTYPE:
                if (sign == null)
                    sign = new SignRetrofit.Build().build();
                http = sign;
                break;
            case TOKENSIGNTYPE:
                if (tokenSign == null)
                    tokenSign = new TokenSignRetrofit.Build().build();
                http = tokenSign;
                break;
            case TOKEN:
                if (token == null)
                    token = new TokenRetrofit.Build().build();
                http = token;
                break;
        }
        return http;
    }

}
