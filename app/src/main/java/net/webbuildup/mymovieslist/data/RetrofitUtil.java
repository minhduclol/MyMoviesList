package net.webbuildup.mymovieslist.data;


import net.webbuildup.mymovieslist.mymovieslist.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.os.Build.VERSION_CODES.BASE;

/**
 * Created by Duc Nguyen on 6/16/2017.
 */

public class RetrofitUtil {
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    public static Retrofit create() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client())
                .baseUrl(BASE_URL)
                .build();
    }
    public static OkHttpClient client() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        //construct the url from the provided request url
                        HttpUrl url = request.url()
                                .newBuilder()
                                .addQueryParameter("api_key", BuildConfig.API_KEY)
                                .build();

                        request = request.newBuilder()
                                .url(url)
                                .build();

                        return chain.proceed(request);
                    }
                })
                .build();
    }
}
