package sf.Q5_31;

import java.util.*;

/**
 * ClassName: Q04
 * Description:
 * date: 2020/5/31 19:46
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class Q04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            if (n==0) break;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            Arrays.sort(arr);
            int m = sc.nextInt();
            if (m<5) {
                System.out.println(m);
            }else if(m==5||n==1){
                System.out.println(m-arr[n-1]);
            }else{
                int[][] arrays = new int[n-1][m-4];
                for (int i = 0; i < m-4; i++) {
                    if (i<arr[0]) {
                        arrays[0][i] = 0;
                    }else{
                        arrays[0][i] = arr[0];
                    }
                }
                for (int i = 1; i < n-1; i++) {
                    for (int j = 0; j < m-4; j++) {
                        if (j>=arr[i]&&arrays[i-1][j]<arrays[i-1][j-arr[i]]+arr[i]) {
                            arrays[i][j] = arrays[i-1][j-arr[i]] + arr[i];
                        }else{
                            arrays[i][j] = arrays[i-1][j];
                        }
                    }
                }
                System.out.println(m-arrays[n-2][m-5]-arr[n-1]);
            }
        }
    }
}
