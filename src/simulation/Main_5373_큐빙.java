package simulation;

import java.io.*;
import java.util.*;

public class Main_5373_큐빙 {

	public static void swap_u(char[][][] cube, int[] d) {
		char[] temp = cube[d[0]][0].clone();
		cube[d[0]][0] = cube[d[1]][0].clone();
		cube[d[1]][0] = cube[d[2]][0].clone();
		for (int i = 0; i < 3; ++i)
			cube[d[2]][0][i] = cube[d[3]][2][2-i];
		for (int i = 0; i < 3; ++i)
			cube[d[3]][2][2-i] = temp[i];
	}
	
	public static void swap_d(char[][][] cube, int[] d) {
		char[] temp = cube[d[0]][2].clone();
		cube[d[0]][2] = cube[d[1]][2].clone();
		cube[d[1]][2] = cube[d[2]][2].clone();
		for (int i = 0; i < 3; ++i)
			cube[d[2]][2][2-i] = cube[d[3]][0][i];
		for (int i = 0; i < 3; ++i)
			cube[d[3]][0][i] = temp[2-i];
	}
	
	public static void swap_f(char[][][] cube) {
		char[] temp = cube[0][2].clone();
		for (int i = 0; i < 3; ++i)
			cube[0][2][i] = cube[4][2-i][2];
		for (int i = 0; i < 3; ++i)
			cube[4][i][2] = cube[1][0][i];
		for (int i = 0; i < 3; ++i)
			cube[1][0][i] = cube[5][2-i][0];
		for (int i = 0; i < 3; ++i)
			cube[5][i][0] = temp[i];
	}
	
	public static void swap_f_r(char[][][] cube) {
		char[] temp = cube[0][2].clone();
		for (int i = 0; i < 3; ++i)
			cube[0][2][i] = cube[5][i][0];
		for (int i = 0; i < 3; ++i)
			cube[5][i][0] = cube[1][0][2-i];
		for (int i = 0; i < 3; ++i)
			cube[1][0][i] = cube[4][i][2];
		for (int i = 0; i < 3; ++i)
			cube[4][i][2] = temp[2-i];
	}
	
	public static void swap_b(char[][][] cube) {
		char[] temp = cube[0][0].clone();
		for (int i = 0; i < 3; ++i)
			cube[0][0][i] = cube[5][i][2];
		for (int i = 0; i < 3; ++i)
			cube[5][i][2] = cube[1][2][2-i];
		for (int i = 0; i < 3; ++i)
			cube[1][2][i] = cube[4][i][0];
		for (int i = 0; i < 3; ++i)
			cube[4][i][0] = temp[2-i];
	}
	
	public static void swap_b_r(char[][][] cube) {
		char[] temp = cube[0][0].clone();
		for (int i = 0; i < 3; ++i)
			cube[0][0][i] = cube[4][2-i][0];
		for (int i = 0; i < 3; ++i)
			cube[4][i][0] = cube[1][2][i];
		for (int i = 0; i < 3; ++i)
			cube[1][2][i] = cube[5][2-i][2];
		for (int i = 0; i < 3; ++i)
			cube[5][i][2] = temp[i];
	}
	
	public static void swap_l(char[][][] cube, int[] d) {
		char[] temp = new char[3];
		for (int i = 0; i < 3; ++i)
			temp[i] = cube[d[3]][i][0];
		for (int i = 3; i > 0; --i) {
			for (int j = 0; j < 3; ++j)
				cube[d[i]][j][0] = cube[d[i-1]][j][0];
		}
		for (int i = 0; i < 3; ++i)
			cube[d[0]][i][0] = temp[i];
	}
	
	public static void swap_r(char[][][] cube, int[] d) {
		char[] temp = new char[3];
		for (int i = 0; i < 3; ++i)
			temp[i] = cube[d[3]][i][2];
		for (int i = 3; i > 0; --i) {
			for (int j = 0; j < 3; ++j)
				cube[d[i]][j][2] = cube[d[i-1]][j][2];
		}
		for (int i = 0; i < 3; ++i)
			cube[d[0]][i][2] = temp[i];
	}
	
	public static void rotate(char[][][] cube, int d) {
		char[][] facet = new char[3][3];
		for (int i = 0; i < 2; ++i) facet[i][2] = cube[d][0][i];
		for (int i = 0; i < 2; ++i) facet[2][2-i] = cube[d][i][2];
		for (int i = 0; i < 2; ++i) facet[2-i][0] = cube[d][2][2-i];
		for (int i = 0; i < 2; ++i) facet[0][i] = cube[d][2-i][0];
		facet[1][1] = cube[d][1][1];
		for (int i = 0; i < 3; ++i)
			cube[d][i] = facet[i].clone();
	}
	
	public static void rotate_r(char[][][] cube, int d) {
		char[][] facet = new char[3][3];
		for (int i = 0; i < 2; ++i) facet[2-i][0] = cube[d][0][i];
		for (int i = 0; i < 2; ++i) facet[0][i] = cube[d][i][2];
		for (int i = 0; i < 2; ++i) facet[i][2] = cube[d][2][2-i];
		for (int i = 0; i < 2; ++i) facet[2][2-i] = cube[d][2-i][0];
		facet[1][1] = cube[d][1][1];
		for (int i = 0; i < 3; ++i)
			cube[d][i] = facet[i].clone();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		char[] color = {'w', 'y', 'r', 'o', 'g', 'b'};
		char[][][] cube_o = new char[6][3][3];
		for (int i = 0; i < 6; ++i) {
			for (int j = 0; j < 3; ++j)
				Arrays.fill(cube_o[i][j], color[i]);
		}
		
		int tc = Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < tc; ++i) {
			char[][][] cube = new char[6][3][3];
			for (int j = 0; j < 6; ++j) {
				for (int k = 0; k < 3; ++k)
					cube[j][k] = cube_o[j][k].clone();
			}
			int n = Integer.parseInt(br.readLine().trim());
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			
			for (int j = 0; j < n; ++j) {
				String t = st.nextToken();
				char d = t.charAt(0);
				char r = t.charAt(1);
				
				switch (d) {
				case 'U':
					if (r == '+') {
						rotate(cube, 0);
						swap_u(cube, new int[] {4, 2, 5, 3});
					}
					else {
						rotate_r(cube, 0);
						swap_u(cube, new int[] {5, 2, 4, 3});
					}
					break;
				case 'D':
					if (r == '+') {
						rotate(cube, 1);
						swap_d(cube, new int[] {5, 2, 4, 3});
					}
					else {
						rotate_r(cube, 1);
						swap_d(cube, new int[] {4, 2, 5, 3});
					}
					break;
				case 'F':
					if (r == '+') {
						rotate(cube, 2);
						swap_f(cube);
					}
					else {
						rotate_r(cube, 2);
						swap_f_r(cube);
					}
					break;
				case 'B':
					if (r == '+') {
						rotate(cube, 3);
						swap_b(cube);
					}
					else {
						rotate_r(cube, 3);
						swap_b_r(cube);
					}
					break;
				case 'L':
					if (r == '+') {
						rotate(cube, 4);
						swap_l(cube, new int[] {2, 1, 3, 0});
					}
					else {
						rotate_r(cube, 4);
						swap_l(cube, new int[] {2, 0, 3, 1});
					}
					break;
				case 'R':
					if (r == '+') {
						rotate(cube, 5);
						swap_r(cube, new int[] {0, 3, 1, 2});
					}
					else {
						rotate_r(cube, 5);
						swap_r(cube, new int[] {0, 2, 1, 3});
					}
					break;
				}
			}
			
			for (int ii = 0; ii < 3; ++ii) {
				for (int jj = 0; jj <3; ++jj)
					bw.write(cube[0][ii][jj]);
				bw.write('\n');
			}
		}
		
		bw.flush();
		bw.close();
	}

}
