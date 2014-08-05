package vitorvigano.com.led;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Vitor on 05/08/2014.
 */
public interface LedService {

    @GET("/led")
    void power(@Query("command") int command, Callback<Integer> callback);
}
