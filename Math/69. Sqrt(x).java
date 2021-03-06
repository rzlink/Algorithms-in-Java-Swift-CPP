// Newton

public class Solution {
    public int mySqrt(int a) {
        long x = a;
        while (x * x > a) {
            x = (x + a / x) / 2;
        }
        return (int)x;
    }
}

// a = (a + x/a)/2
public class Solution {
    public int mySqrt(int x) {
        long a = x;
        while (a * a > x) {
            a = (a + x/a)/2;
        }
        return (int)a;
    }
}

// Binary search

public class Solution {
    public int mySqrt(int a) {
        if (a == 0) return 0;
        int l = 1, r = Integer.MAX_VALUE;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mid > a / mid) {
                r = mid - 1;
            } else {
                if (mid + 1 > a / (mid + 1))
                    return mid;
                l = mid + 1;
            }
        }
        return l;
    }
}

// not necessary, but reasonable
public class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        int l = 0, r = x;
        while (l <= r) {
            int mid = l + (r - l)/2;
            if (mid > x/mid) {
                if (mid-1 < x/(mid-1)) return mid - 1;
                r = mid - 1;
            } else if (mid < x/mid) {
                if (mid+1 > x/(mid+1)) return mid;
                l = mid + 1;
            } else return mid;
        }
        return l;
    }
}
