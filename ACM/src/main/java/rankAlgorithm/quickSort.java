package rankAlgorithm;

/*
 * 快速排序
 *
 * 从中轴元素那里开始把大的数组切割成两个小的数组(两个数组都不包含中轴元素)，
 * 接着我们通过递归的方式，让中轴元素左边的数组和右边的数组也重复同样的操作，
 * 直到数组的大小为1，此时每个元素都处于有序的位置。
 *
 * 平均时间复杂度：O(nlogn)
 * 最坏时间复杂度：O(n2)
 * 最好时间复杂度：O(nlogn)
 * 空间复杂度：O(1)
 * 非稳定排序
 */
public class quickSort {
    public static void main(String[] args) {
        int arr[] = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }

        func(arr);

        boolean isPass = true;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            if (i == 0) continue;
            if (arr[i] < arr[i - 1]) isPass = false;
        }
        System.out.println();
        System.out.println("quickSort :" + isPass);
    }

    static void func(int arr[]) {
        sort2(arr, 0, arr.length);
    }

    static void sort(int[] arr, int left, int right) {
        if (left < right) {
            int aimLocation = left;
            int i = left + 1;
            int j = right;
            while (true) {
                while (i <= j && arr[i] <= arr[aimLocation]) i++;
                while (i <= j && arr[j] >= arr[aimLocation]) j--;
                if (i >= j) break;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            int temp = arr[j];
            arr[j] = arr[aimLocation];
            arr[aimLocation] = temp;


            sort(arr, left, j - 1);
            sort(arr, j + 1, right);
        }
    }


    public static void sort2(int[] arr, int left, int right) {
        if (left >= right - 1) return;

        int choice = left;
        int i = left + 1;
        int j = right - 1;
        while (true) {
            while (i <= j && arr[i] <= arr[choice]) i++;
            while (i <= j && arr[j] >= arr[choice]) j--;

            if (i >= j) break;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        int temp = arr[j];
        arr[j] = arr[choice];
        arr[choice] = temp;

        sort2(arr, left, j);
        sort2(arr, j + 1, right);
    }
}
