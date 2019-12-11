import java.io.*;

public class Main_14624_전북대학교 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N % 2 == 0) {
            System.out.println("I LOVE CBNU");
        }
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; ++i)
                sb.append("*");
            int[] star = { N/2, N/2 };
            while (0 <= star[0] && star[1] < N) {
                sb.append("\n");
                for (int j = 0; j < N; ++j) {
                    if (j == star[0]) {
                        sb.append("*");
                        if (j == N/2)
                            break;
                    }
                    else if (j == star[1]) {
                        sb.append("*");
                        break;
                    }
                    else
                        sb.append(" ");
                }
                --star[0];
                ++star[1];
            }
            System.out.println(sb.toString());
        }
    }
}
