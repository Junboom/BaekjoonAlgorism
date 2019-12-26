#include <iostream>

using namespace std;

int main()
{
    string str;
    do
    {
        getline(cin, str);
        cout << str << '\n';
    } while (str.length());
}
