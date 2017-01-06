import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

import java.util.List;


/**
 * Created by michalik on 06.01.17
 */
public class NetworkManager {

    private static final String BASE_URL = "http://139.59.213.12:8080";
    static Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build();

    public static Observable<List<AbstractModel>> getAbstracts(){
        return retrofit
                .create(ApiInterface.class)
                .getAbstract();
    }


    public interface ApiInterface{
        @GET("/abstract")
        Observable<List<AbstractModel>> getAbstract();
    }
}
