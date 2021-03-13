package mutiTread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class caculate {


    public static void main(String[] args) {
        int n = 165456446;
        int threadNumber = 4;
        System.out.println(System.currentTimeMillis());
        caculate c = new caculate(n, threadNumber);
        System.out.println(System.currentTimeMillis());
    }


    public caculate(int itemCount, int threadCount) {
        //�����̳߳ض���
        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
        List<Future<Double>> lstResult = new LinkedList<>();
        for (int i = 1; i <= threadCount; i++) {
            MyThread piComputer = new MyThread(i * 4 - 3, itemCount * 2 - 1, threadCount * 4 - 2);
            lstResult.add(threadPool.submit(piComputer));
        }
        double pi = 0.0;
        while (lstResult.size() > 0) {
            for (int i = lstResult.size() - 1; i >= 0; i--) {
                Future<Double> result = lstResult.get(i);
                if (!result.isDone()) continue;
                try {
                    pi += result.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                lstResult.remove(i);
            }
        }
        pi *= 4;
        System.out.println("PI=" + pi);
        threadPool.shutdown();
    }
}