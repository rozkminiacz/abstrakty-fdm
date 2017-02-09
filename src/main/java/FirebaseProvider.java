import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import com.google.gson.JsonObject;
import rx.Observable;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by michalik on 06.02.17
 */
public class FirebaseProvider {

    static public Observable<FirebaseApp> initFirebase(){
        return Observable.create(subscriber -> {
            try{
                FileInputStream credential = new FileInputStream("serviceAccountKey.json");
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setServiceAccount(credential)
                        .setDatabaseUrl("https://fizyka-dla-medyka.firebaseio.com/")
                        .build();
                FirebaseApp app = FirebaseApp.initializeApp(options);
                subscriber.onNext(FirebaseApp.getInstance());
            }
            catch (IOException e){
                System.out.println("Init firebase error");
                subscriber.onError(e);
            }
        });
    }

    static public Observable<String> getBackup(FirebaseApp app){
        System.out.println(app.getName());
        return Observable.create(subscriber -> {
            DatabaseReference ref = FirebaseDatabase
                    .getInstance(app)
                    .getReference("/profile");

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("onDataChange ");
                    String res = dataSnapshot.toString();
                    System.out.println(res);
                    subscriber.onNext("db snapshot "+res);
//                    subscriber.onCompleted();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("onCancelled ");
                    System.out.println(databaseError.getMessage());
                    subscriber.onError(databaseError.toException());
                }
            });
        });
    }
}
