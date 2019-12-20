package bruteforce;

import java.io.*;

public class Main_14626_ISBN {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] num = br.readLine().toCharArray();

        for (int n = 0; n < 10; ++n) {
            int sum = 0;
            for (int i = 0; i < num.length; ++i) {
                if (num[i] != '*') {
                    if (i % 2 == 0) sum += num[i] - '0';
                    else            sum += (num[i] - '0') * 3;
                }
                else {
                    if (i % 2 == 0) sum += n;
                    else            sum += n * 3;
                }
            }

            if (sum % 10 == 0) {
                System.out.println(n);
                break;
            }
        }
    }
}
