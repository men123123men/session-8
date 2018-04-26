import java.util.concurrent.atomic.AtomicBoolean;

public class Test2 extends AbstractTest {
    private static final AtomicBoolean val = new AtomicBoolean();

    public static void main(String... args) throws InterruptedException {
        for (int i = 0; i < 10_000; i++) {
            assert size() == 0;

            Thread th1 = th();
            Thread th2 = th();

            th1.start();
            th2.start();

            th1.join();
            th2.join();

            assert size() == 2;
            assert containsKey(1);
            assert containsKey(2);
            assert contains(th1);
            assert contains(th2);

            clear();

            System.out.println("Checked " + i);
        }
    }

    private static Thread th() {
        return new Thread(() -> {
            //put(val.getAndSet(!val.get())?1:2);


//            if(val.compareAndSet(false,true))
//                put(1);
//            else {
//                put(2);
//                val.set(false);
//            }


            if(!val.getAndSet(true)){
                put(1);
            } else {
                put(2);
                val.set(false);
            }

        });
    }

}
