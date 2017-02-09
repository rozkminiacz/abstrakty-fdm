import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.CountDownLatch;

/**
 * Created by michalik on 06.01.17
 */
public class Main {
    private static FirebaseProvider provider;

    public static void main(String[] args) {
        try {
            performFirebaseBackup();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    private static void getDpdAbstracts() {
        NetworkManager.getAbstracts()
                .subscribeOn(Schedulers.immediate())
                .observeOn(Schedulers.immediate())
                .doOnError(System.out::print)
                .doOnCompleted(() -> System.out.println("Completed"))
                .flatMapIterable(abstractModels -> abstractModels)
                .subscribe(model -> {
                    try {
                        FileUtil.writeToTexFile(model);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
    }

    private static void performFirebaseBackup() throws InterruptedException {
        final CountDownLatch sync = new CountDownLatch(1);

        Observable.just(null)
                .subscribeOn(Schedulers.immediate())
                .flatMap(i -> FirebaseProvider.initFirebase())
                .flatMap(FirebaseProvider::getBackup)
                .flatMap(FileUtil::writeBackupJson)
                .observeOn(Schedulers.newThread())
                .subscribe(System.out::println,
                        throwable -> {
                            System.out.println(throwable.getMessage());
                        }, () -> {
                            System.out.println("Completed ");
                            sync.countDown();
                        });
        sync.await();
    }
}
