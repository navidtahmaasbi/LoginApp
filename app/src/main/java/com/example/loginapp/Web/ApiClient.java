package com.example.loginapp.Web;

import com.example.loginapp.Utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(String authToken) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                    .addInterceptor(logging);
//                    .build();

            //Add Authorization header if authToken is provided
            if (authToken != null && !authToken.isEmpty()) {
                clientBuilder.addInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + authToken)
                            .build();
                    return chain.proceed(request);
                });
            }

            OkHttpClient client = clientBuilder.build();
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();
            } else {
                // Recreate Retrofit Instance with updated client if authToken changes
                retrofit = retrofit.newBuilder().client(client).build();
            }
//            AuthService authService = retrofit.create(AuthService.class);

        return retrofit;
    }
}
