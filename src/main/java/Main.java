import rx.schedulers.Schedulers;

/**
 * Created by michalik on 06.01.17
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("asldkjaslk");
        NetworkManager.getAbstracts()
                .subscribeOn(Schedulers.immediate())
                .observeOn(Schedulers.immediate())
                .doOnError(System.out::print)
                .doOnCompleted(()->{
                    System.out.println("Completed");
                })
                .doOnTerminate(()->{
                    System.out.println("Terminated");
                })
                .flatMapIterable(abstractModels -> abstractModels)
                .subscribe(System.out::println);
    }
}
