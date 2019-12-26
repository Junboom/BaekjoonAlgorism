#include <stdio.h>
#include <time.h>

int main()
{
    time_t timer = time(NULL);
    struct tm *datetime = localtime(&timer);
    printf("%d-%02d-%02d", datetime ->tm_year + 1900, datetime->tm_mon + 1, datetime->tm_mday);
}