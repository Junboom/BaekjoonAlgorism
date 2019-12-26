#include <iostream>

using namespace std;

int main()
{
    string str;
    cin >> str;
    cout << str[0];
    for (int i = 1; i < str.length(); ++i)
    {
        if (i % 10 == 0)    cout << '\n';
        cout << str[i];
    }
}