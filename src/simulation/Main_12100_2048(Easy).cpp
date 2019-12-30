#include <iostream>
#include <vector>

using namespace std;

int ans, n;
int** map;

int** combine1(int** copy)
{
	for (int i = 0; i < n; ++i)
	{
		vector<int> v;
		vector<bool> b;

		for (int j = 0; j < n; ++j)
		{
			if (copy[j][i] != 0)
			{
				if (b.size() > 0 && b[b.size() - 1] && v[v.size() - 1] == copy[j][i])
				{
					v[v.size() - 1] *= 2;
					b[b.size() - 1] = false;
				}
				else {
					v.push_back(copy[j][i]);
					b.push_back(true);
				}
			}
		}
		
		for (int j = 0; j < n; ++j)
		{
			if (v.size() > j) copy[j][i] = v[j];
			else copy[j][i] = 0;
		}
	}

	return copy;
}

int** combine2(int** copy)
{
	for (int i = 0; i < n; ++i)
	{
		vector<int> v;
		vector<bool> b;

		for (int j = n - 1; j >= 0; --j)
		{
			if (copy[j][i] != 0)
			{
				if (b.size() > 0 && b[b.size() - 1] && v[v.size() - 1] == copy[j][i])
				{
					v[v.size() - 1] *= 2;
					b[b.size() - 1] = false;
				}
				else {
					v.push_back(copy[j][i]);
					b.push_back(true);
				}
			}
		}

		for (int j = n - 1; j >= 0; --j)
		{
			if (n - v.size() <= j) copy[j][i] = v[n - 1 - j];
			else copy[j][i] = 0;
		}
	}

	return copy;
}

int** combine3(int** copy)
{
	for (int i = 0; i < n; ++i)
	{
		vector<int> v;
		vector<bool> b;

		for (int j = 0; j < n; ++j)
		{
			if (copy[i][j] != 0)
			{
				if (b.size() > 0 && b[b.size() - 1] && v[v.size() - 1] == copy[i][j])
				{
					v[v.size() - 1] *= 2;
					b[b.size() - 1] = false;
				}
				else {
					v.push_back(copy[i][j]);
					b.push_back(true);
				}
			}
		}

		for (int j = 0; j < n; ++j)
		{
			if (v.size() > j) copy[i][j] = v[j];
			else copy[i][j] = 0;
		}
	}

	return copy;
}

int** combine4(int** copy)
{
	for (int i = 0; i < n; ++i)
	{
		vector<int> v;
		vector<bool> b;

		for (int j = n - 1; j >= 0; --j)
		{
			if (copy[i][j] != 0)
			{
				if (b.size() > 0 && b[b.size() - 1] && v[v.size() - 1] == copy[i][j])
				{
					v[v.size() - 1] *= 2;
					b[b.size() - 1] = false;
				}
				else {
					v.push_back(copy[i][j]);
					b.push_back(true);
				}
			}
		}

		for (int j = n - 1; j >= 0; --j)
		{
			if (n - v.size() <= j) copy[i][j] = v[n - 1 - j];
			else copy[i][j] = 0;
		}
	}

	return copy;
}

void rec(int** map, int cnt)
{
	if (cnt == 5) {
		int max = 0;
		for (int i = 0; i < n; ++i)
		{
			for (int j = 0; j < n; ++j)
				max = max < map[i][j] ? map[i][j] : max;
		}
		ans = ans < max ? max : ans;
		return;
	}

	int** copy = new int* [n];
	for (int i = 0; i < n; ++i)
	{
		copy[i] = new int[n];
		for (int j = 0; j < n; ++j)
			copy[i][j] = map[i][j];
	}
	rec(combine1(copy), cnt + 1);

	copy = new int* [n];
	for (int i = 0; i < n; ++i)
	{
		copy[i] = new int[n];
		for (int j = 0; j < n; ++j)
			copy[i][j] = map[i][j];
	}
	rec(combine2(copy), cnt + 1);

	copy = new int* [n];
	for (int i = 0; i < n; ++i)
	{
		copy[i] = new int[n];
		for (int j = 0; j < n; ++j)
			copy[i][j] = map[i][j];
	}
	rec(combine3(copy), cnt + 1);

	copy = new int* [n];
	for (int i = 0; i < n; ++i)
	{
		copy[i] = new int[n];
		for (int j = 0; j < n; ++j)
			copy[i][j] = map[i][j];
	}
	rec(combine4(copy), cnt + 1);
}

int main()
{
	ans = 0;
	cin >> n;
	map = new int* [n];
	for (int i = 0; i < n; ++i)
	{
		map[i] = new int[n];
		for (int j = 0; j < n; ++j)
			cin >> map[i][j];
	}

	rec(map, 0);
	cout << ans << endl;
}