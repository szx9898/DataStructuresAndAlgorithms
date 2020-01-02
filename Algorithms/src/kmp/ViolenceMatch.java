package kmp;

public class ViolenceMatch {
    public static void main(String[] args) {
        String s1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String s2 = "尚硅谷你尚硅你";
        int index = violenceMatch(s1, s2);
        System.out.println("index = " + index);
    }

    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Len = s1.length;
        int s2Len = s2.length;
        int i = 0;//i索引指向s1
        int j = 0;//j索引指向s2
        while (i < s1Len && j < s2Len) {//保证匹配时不越界
            if (s1[i] == s2[j]) {//匹配成功
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        //判断是否匹配成功
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }

    }
}
