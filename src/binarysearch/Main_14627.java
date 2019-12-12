package binarysearch;

import java.io.*;
import java.util.StringTokenizer;

public class Main_14627 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] L = new int[S];
        long max = 0;
        long all = 0;
        for (int i = 0; i < S; ++i) {
            L[i] = Integer.parseInt(br.readLine());
            max = max < L[i] ? L[i] : max;
            all += L[i];
        }

        long min = 1;
        ++max;
        while (min + 1 < max) {
            long mid = (min + max) / 2;
            int sum = 0;
            for (int i = 0; i < S; ++i) {
                sum += L[i] / mid;
            }
            if (C <= sum)   min = mid;
            else            max = mid;
        }

        System.out.println(all - min * C);
    }
}