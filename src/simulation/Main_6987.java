package simulation;

import java.util.Scanner;

public class Main_6987 {
	public static void main(String[] args) {

		int country = 6;
		int vs = 3;
		int count = 0;
		int cMax = 4;
		int[][][] result = new int[cMax][country][vs];
		int[] truth = new int[cMax];		// 진실, 거짓 여부
		
		Scanner sc = new Scanner(System.in);
		
		while (count < cMax) {
			
			int winSum = 0;
			int drawSum = 0;
			int loseSum = 0;
			int[] draw = new int[country];
			
			truth[count] = 1;
			
			for (int i = 0; i < country; i++) {
				for (int j = 0; j < vs; j++) {
					result[count][i][j] = sc.nextInt();
				}
			}
			
			for (int i = 0; i < country; i++) {						// 승+무+패 = 5?
				if (result[count][i][0] + result[count][i][1] + result[count][i][2] != 5)
					truth[count] = 0;								// 거짓
			}
			
			for (int i = 0; i < country; i++)						// 무승부 비교
				draw[i] = result[count][i][1];
			for (int i = 0; i < country; i++) {
				if (draw[i] > 0) {
					for (int j = 0; j < country; j++) {
						if (i != j && draw[j] > 0) {
							draw[i]--;
							draw[j]--;
							break;
						}
					}
				}
			}
			for (int i = 0; i < country; i++) {
				if (draw[i] != 0)
					truth[count] = 0;								// 거짓
			}
			
			for (int i = 0; i < country; i++) {						// 모든 승 = 모든 패?
				winSum += result[count][i][0];
				drawSum += result[count][i][1];
				loseSum += result[count][i][2];
			}
			if (winSum != loseSum || drawSum%2 == 1)
				truth[count] = 0;
			
			/*
			for (int i = 0; i < country; i++) {
				for (int num = 5; num > 1; num--) {
					if (result[count][i][0] == num) {				// 5승
						for (int j = 0; j < country; j++) {
							if (j != i && result[count][j][0] < result[count][i][0] &&
									result[count][j][1] + result[count][j][2] <= 5 - num)
								truth[count] = 0;					// 거짓
								
							if (result[count][j][0] == (num-1)) {	// 5승이 나온 경기 중 4승
								for (int k = 0; k < country; k++) {
									if (k != i && k != j && result[count][k][0] < result[count][j][0] &&
											result[count][k][1] + result[count][k][2] <= 6 - num)
										truth[count] = 0;			// 거짓
								}
							}
						}
					}
				}
			}
			*/
			
			count++;
			
		}
		
		sc.close();
		
		for (int i = 0; i < count; i++)
			System.out.print(truth[i] + " ");
		
	}
}
