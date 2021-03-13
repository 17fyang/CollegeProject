package mutiTread;

import java.util.concurrent.Callable;

class MyThread implements Callable<Double>{
    private int min;
    private int max;
    private int step;

    public MyThread(int min, int max, int step) {
        this.min = min;
        this.max = max;
        this.step = step;
    }

    public Double call() throws Exception {
        int dived = min;
        double sum = 0.0;
        while (dived <= max){
            sum += 1.0 / dived;
            dived += 2;
            sum -= 1.0 / dived;
            dived += step;
        }
        return sum;
    }
}
