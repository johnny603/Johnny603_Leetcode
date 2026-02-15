char* addBinary(const char* a, const char* b) {
    int i = strlen(a) - 1;
    int j = strlen(b) - 1;
    int carry = 0;

    // max possible length = max(lenA, lenB) + 2 (carry + '\0')
    int maxLen = (strlen(a) > strlen(b) ? strlen(a) : strlen(b)) + 2;
    char* result = (char*)malloc(maxLen);
    int k = 0;

    while (i >= 0 || j >= 0 || carry != 0) {
        int bitA = (i >= 0) ? a[i--] - '0' : 0;
        int bitB = (j >= 0) ? b[j--] - '0' : 0;

        int sum = bitA + bitB + carry;
        carry = sum / 2;

        result[k++] = (sum % 2) + '0';
    }

    result[k] = '\0';

    // reverse the string
    for (int l = 0, r = k - 1; l < r; l++, r--) {
        char tmp = result[l];
        result[l] = result[r];
        result[r] = tmp;
    }

    return result; // caller must free
}