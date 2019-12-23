package simple;

import java.io.*;
import java.util.StringTokenizer;

public class Main_7469_K번째수 {

    public static void merge(int[] a, int s, int e, int m) {
        int[] ret = new int[e-s+1];
        int i = s;
        int j = m + 1;
        int copy = 0;

        while (i <= m || j <= e) {
            if (m < i)     ret[copy++] = a[j++];
            else if (e < j)     ret[copy++] = a[i++];
            else if (a[i] <= a[j])
                ret[copy++] = a[i++];
            else if (a[j] <  a[i])
                ret[copy++] = a[j++];
        }

        for (int k = 0; k < copy; ++k)
            a[s+k] = ret[k];
    }

    public static void mergeSort(int[] a, int s, int e) {
        if (s < e) {
            int m = (s + e) / 2;

            mergeSort(a, s, m);
            mergeSort(a, m+1, e);

            merge(a, s, e, m);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] a = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i)
            a[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; ++i) {
            int[] ijq = new int[3];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; ++j)
                ijq[j] = Integer.parseInt(st.nextToken());
            int[] na = new int[ijq[1]-ijq[0]+1];
            for (int j = 0; j <= ijq[1]-ijq[0]; ++j)
                na[j] = a[j+ijq[0]];
            mergeSort(na, 0, ijq[1]-ijq[0]);
            System.out.println(na[ijq[2]-1]);
        }
    }

}
