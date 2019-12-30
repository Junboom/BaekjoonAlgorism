#include <iostream>

using namespace std;

int main()
{
    int n, sum = 0;
    string num;
    cin >> n >> num;
    for (int i = 0; i < n; ++i)
        sum += num[i] - '0';
    cout << sum << endl;
}