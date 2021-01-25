package com.example.demo.test;

import javax.print.DocFlavor;
import javax.swing.*;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;


class Solution {

    static int i_count = 0;

    public static void main(String[] args) {

        int[] a = {1,2,3,5,4};
        PriorityQueue<Integer> pg = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

//        PriorityQueue<Integer> pg = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
//            }
//        });
//
//        int[][] a = {{1,2},{3,4,0}};
//        int[] b = {3,4,0};
//        int[] ab = new int[a.length + b.length];
//        System.arraycopy(a,0,ab,0,a.length);
//        System.arraycopy(b,0,ab,a.length,b.length);
//        Arrays.sort(ab);
//        HashMap map = new HashMap();
//        Arrays.sort(a,(o1,o2)->(o1[0] - o2[0]));
//
//
//        ReentrantLock lock = new ReentrantLock(true);
//        lock.lock();
//
//        try {
//            lock.newCondition().await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        lock.unlock();
//        Executor e = new ThreadPoolExecutor(5,5,1000,TimeUnit.SECONDS,new SynchronousQueue<Runnable>(),new ThreadPoolExecutor.AbortPolicy());
//
//        Solution solution = new Solution();
//        solution.firstUniqChar("leetcode");
//        int[] arr = {8, 9, 7, 6, 5, 4, 3, 2, 1};
        ExecutorService es = Executors.newCachedThreadPool();
        Random random = new Random();


        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (this) {
                        System.out.println("第" + i_count++ + "期双双色球开奖");
                    }
                    System.out.println(random.nextInt(10) + " - " + random.nextInt(20) + " - " + random.nextInt(30));
                    System.out.println("=============================");
                }
            });
            es.submit(thread);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //归并排序
    public static void sort(int[] arr) {
        int[] temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(arr, 0, arr.length - 1, temp);
    }

    private static void sort(int[] arr, int left, int right, int[] temp) {
//        if(left<right){
//            int mid = (left+right)/2;
//            sort(arr,left,mid,temp);//左边归并排序，使得左子序列有序
//            sort(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序
//            merge(arr,left,mid,right,temp);//将两个有序子数组合并操作
//        }


        if (left >= right) {
            return;
        } else {
            int mid = (left + right) / 2;
            sort(arr, left, mid, temp);//左边归并排序，使得左子序列有序
            sort(arr, mid + 1, right, temp);//右边归并排序，使得右子序列有序
            merge(arr, left, mid, right, temp);//将两个有序子数组合并操作
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//左序列指针
        int j = mid + 1;//右序列指针
        int t = 0;//临时数组指针
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mid) {//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while (j <= right) {//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }


    //二分法用循环实现
    public static int binSearch_1(int[] nums, int key) {
        int low = 0;
        int high = nums.length - 1;
        int middle = 0;
        if (key < nums[low] || key > nums[high] || nums.length == 0) {
            return -1;
        }

        while (low <= high) {
            middle = (low + high) / 2;
            if (nums[middle] == key) {
                break;
            } else {
                if (nums[middle] > key) {
                    high = middle;
                } else {
                    low = middle;
                }
            }

        }

        return middle;


    }

    //二分法用递归实现
    public static int binSearch_2(int[] nums, int key, int low, int high) {

        int mid = (low + high) / 2;
        if (nums[mid] == key) {
            return mid;
        } else {
            if (nums[mid] > key) {
                return binSearch_2(nums, key, low, mid);
            } else {
                return binSearch_2(nums, key, mid, high);
            }
        }
    }


    public int lengthOfLIS(int[] nums) {
        int[] cell = new int[nums.length];
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int low = 0;
            int high = res;
            while (low < high) {
                int middle = (low + high) / 2;
                if (nums[i] > cell[middle]) {
                    low = middle + 1;
                } else {
                    high = middle;
                }
            }
            cell[low] = nums[i];
            if (res == high) {
                res++;
            }

        }

        return res;

    }


    public void reverseString(char[] s) {
        char[] chars = new char[s.length];
        for (int i = 0; i < s.length; i++) {
            chars[s.length - 1 - i] = s[i];
        }

        for (char c : s) {
            System.out.println(c);
        }
        for (char c : chars) {
            System.out.println(c);
        }

    }

    public static int maxFreq(String s, int maxLetters, int minSize, int maxSize) {

        if (s.length() == 0 || maxSize == 0) {
            return 0;
        }

        HashMap res = new HashMap();
        char[] chars = s.toCharArray();

        for (int high = 0; high < s.length(); high++) {
//            for(int low = high-maxSize+1;low<= high-minSize+1;low++){
//                if(low < 0){
//                    continue;
//                }
//                if(counter(s.substring(low,high+1),maxLetters)){
//                    //将符合条件的子串放到res中，res.getOrDefault方法来获取值
//                    res.put(s.substring(low,high+1),(int)(res.getOrDefault(s.substring(low,high+1),0))+1);
//                }
//            }
            if (high - minSize + 1 < 0) {
                continue;
            }
            if (counter(s.substring(high - minSize + 1, high + 1), maxLetters)) {
                //将符合条件的子串放到res中，res.getOrDefault方法来获取值
                res.put(s.substring(high - minSize + 1, high + 1), (int) (res.getOrDefault(s.substring(high - minSize + 1, high + 1), 0)) + 1);
            }


        }

        int max = 0;
        for (Object i : res.values()) {
            max = Math.max(max, (int) i);
        }
        return max;
    }


    private static boolean counter(String s, int maxLetters) {
        char[] chars = s.toCharArray();
        Set<Character> reset = new HashSet<>();
        for (char c : chars) {
            reset.add(c);
        }

        return reset.size() <= maxLetters;
    }


    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int max = 0;
        HashMap<Character, Integer> res = new HashMap<>();
        char[] chars = s.toCharArray();

        for (int low = 0, high = 0; high < s.length(); high++) {

            if (res.containsKey(chars[high])) {
                low = Math.max((res.get(chars[high]) + 1), low);
                res.put(chars[high], high);
            } else {
                res.put(chars[high], high);

            }
            max = Math.max(max, (high - low) + 1);

        }

        return max;

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        HashSet set = new HashSet();
        ListNode res = null;
        ListNode ha = headA;
        ListNode hb = headB;

        while (ha != null) {
            set.add(ha);
            ha = ha.next;
        }

        while (hb != null) {
            if (set.contains(hb)) {
                res = hb;
                break;
            }
            hb = hb.next;
        }


        return res;

    }


    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode res = new ListNode(-1);
        ListNode low = res;
        ListNode high = head;
        res.next = head;

        for (int i = 1; i < m; i++) {
            low = low.next;
            high = high.next;
        }

        for (int i = 0; i < n - m; i++) {
            ListNode tmp = high.next;
            high.next = tmp.next;
            low.next = tmp;
            tmp.next = high;
        }


        return res.next;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();

        doPK(root, res);
        return res;
    }

    private void doPK(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        doPK(root.left, res);
        doPK(root.right, res);
        res.add(root.val);
    }

    public int[] merge1(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < nums1.length || j < nums2.length) {

            if (i >= nums1.length) {
                res[k] = nums2[j];
                j++;
                k++;
            } else {
                if (j < nums2.length) {
                    //比较大小，把小的放入
                    if (nums1[i] > nums2[j]) {
                        res[k] = nums2[j];
                        k++;
                        j++;
                    } else {
                        res[k] = nums1[i];
                        k++;
                        i++;
                    }

                } else {
                    res[k] = nums1[i];
                    i++;
                    k++;
                }

            }


        }
        return res;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int le = nums1.length;
        int i1 = m;
        int i2 = n;


        while (n >= 1 && m >= 1) {
            if (nums1[m - 1] > nums2[n - 1]) {
                nums1[le - 1] = nums1[m - 1];
                le--;
                m--;
            } else {
                nums1[le - 1] = nums2[n - 1];
                le--;
                n--;
            }
        }

        System.arraycopy(nums2, 0, nums1, 0, n);
    }


    public int maxValue(int[][] grid) {
        int line = grid.length;
        int column = grid[1].length;
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0) {
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else {
                    if (j == 0) {
                        grid[i][j] = grid[i][j] + grid[i - 1][j];
                    } else {
                        grid[i][j] = Math.max(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];
                    }
                }


            }

        }

        return grid[line - 1][column - 1];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int length1 = text1.length();
        int length2 = text2.length();
        int[][] res = new int[length1 + 1][length2 + 1];

        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    res[i][j] = res[i - 1][j - 1] + 1;
                } else {
                    res[i][j] = Math.max(res[i - 1][j], res[i][j - 1]);
                }
            }
        }

        return res[length1][length2];

    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int cur = 0;
        int i = x;
        while (x != 0) {
            cur = cur * 10 + x % 10;
            x = x / 10;
        }

        return cur == i;
    }


    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < res.length() && j < strs[i].length(); j++) {
                if (res.charAt(j) != strs[i].charAt(j)) {

                    break;
                }

            }
            res = res.substring(0, j);

        }
        return res;
    }


    public List<List<Integer>> permute_1(int[] nums) {

        List<List<Integer>> res = new LinkedList<>();
        if (nums.length == 0) {
            return res;
        }

        LinkedList<Integer> cur = new LinkedList<>();
        doQPX(nums, cur, res);
        return res;
    }


    private void doQPX(int[] nums, LinkedList<Integer> cur, List<List<Integer>> res) {
        if (nums.length == 0) {
            res.add(new LinkedList<Integer>(cur));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            cur.add(nums[i]);
            //从nums中删除该元素。
            doQPX(nums, cur, res);
            cur.removeLast();
        }
    }

    public List<String> maxString(String s) {
        if (s.length() == 0) {
            return null;
        }
        //用于保存遍历中途的字符串和他对应的长度
        Map<String, Integer> map2 = new HashMap<>();
        //用来保存该字符char是否重复
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int low = 0;
        int length = 0;
        String cur = "";

        for (int i = 0; i < chars.length; i++) {
            //读取最新的字符，判断是否已在map中
            if (map.containsKey(chars[i])) {
                //看读取的这个char是移位前还是移位后的。
                if (low > map.get(chars[i])) {
                    map.put(chars[i], i);
                    cur = cur + chars[i];
                    length = length + 1;
                    map2.put(cur, length);
                } else {
                    low = map.get(chars[i]) + 1;
                    length = i - map.get(chars[i]);
                    cur = s.substring(low, i + 1);
                    map2.put(cur, length);
                    map.put(chars[i], i);
                }

            } else {
                map.put(chars[i], i);
                cur = cur + chars[i];
                length = length + 1;
                map2.put(cur, length);
            }
        }
        //计算map2中的最大长度
        int max = 0;
        for (int i : map2.values()) {
            max = Math.max(i, max);
        }
        //计算最大长度对应的stringlist
        List<String> res = new ArrayList<>();
        System.out.println("最大长度为=" + max);
        for (String string : map2.keySet()) {

            if (map2.get(string) == max) {
                res.add(string);
            }
        }
        for (String s1 : res) {
            System.out.println(s1);
        }
        return res;
    }

    public String addStrings(String num1, String num2) {
        String res = "";
        char[] char1 = (new StringBuilder(num1)).reverse().toString().toCharArray();
        char[] char2 = (new StringBuilder(num2)).reverse().toString().toCharArray();
        int i = 0;
        int j = 0;
        int flag = 0;
        while (i < char1.length || j < char2.length) {
            if (i == char1.length) {
                int tmp = char2[j] - '0' + flag;
                flag = tmp / 10;
                res = res + tmp % 10;
                j++;
            } else {
                if (j == char2.length) {
                    int tmp = char1[i] - '0' + flag;
                    flag = tmp / 10;
                    res = res + tmp % 10;
                    i++;
                } else {
                    int tmp = char1[i] - '0' + char2[j] - '0' + flag;
                    flag = tmp / 10;
                    res = res + tmp % 10;
                    i++;
                    j++;
                }
            }
        }
        if (flag == 1) {
            res = res + 1;
        }
        return new StringBuilder(res).reverse().toString();
    }

    public int strToInt(String str) {
        int res = 0;
        str = str.replace(" ", "");
        if (str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
//        if(chars[0] == '-'){
//            if(chars.length > 1 && chars[1] == '0'){
//                return 0;
//            }
//        }
        if (chars[0] == '+') {
            if (chars.length > 1 && chars[1] == '0') {
                return 0;
            }
        }


        if (chars[0] == '-') {
            for (char c : chars) {
                if (c == '-') {
                    continue;
                }
                if (c < '0' || c > '9') {
                    break;
                }
                int tmp = res;
                res = res * 10 - (c - '0');

                if (res > tmp) {
                    res = Integer.MIN_VALUE;
                    break;


                }
            }


        } else {
            for (char c : chars) {
                if (c == '+') {
                    continue;
                }

                if (c < '0' || c > '9') {
                    break;
                }
                int tmp = res;
                res = res * 10 + (c - '0');
                if (res < tmp) {
                    res = Integer.MIN_VALUE;
                    break;
                }
            }
        }

        return res;

    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.right);
        invertTree(root.left);
        return root;
    }

    public void meiJueString(String s) {
        List<String> res = new LinkedList<>();
        StringBuilder cur = null;

        int length = s.length();
        for (int i = 0; i <= length; i++) {
            for (int j = 0; j < i; j++) {
                cur = new StringBuilder(s.substring(j, i));
                res.add(cur.toString());
            }
        }

        for (String string : res) {
            System.out.println(string);
        }
    }

    public String longestPalindrome_1(String s) {
        if (s.length() == 0) {
            return "";
        }
        int maxStart = 0;
        int maxLen = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int low = i - 1;
            int high = i + 1;
            int cur = 1;
            while (low >= 0 && chars[low] == chars[i]) {
                cur++;
                low--;
            }
            while (high < chars.length && chars[i] == chars[high]) {
                cur++;
                high++;
            }
            while (low >= 0 && high < chars.length && chars[low] == chars[high]) {
                cur = cur + 2;
                low--;
                high++;
            }

            if (cur > maxLen) {
                maxLen = cur;
                maxStart = low + 1;
            }


        }


        return s.substring(maxStart, maxStart + maxLen);
    }

    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        int[][] length = new int[s.length()][s.length()];
        int maxLength = 0;
        int sit = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (chars[chars.length - 1 - j] == chars[i]) {
                    if (i == 0 || j == 0) {
                        length[i][j] = 1;
                    } else {
                        if (Math.abs(s.length() - i - j) == length[i][j]) {
                            length[i][j] = length[i - 1][j - 1] + 1;
                        }

                    }

                }
                if (length[i][j] > maxLength) {
                    maxLength = length[i][j];
                    sit = i;
                }

            }

        }
        return s.substring(sit - maxLength + 1, sit + 1);
    }

    public int climbStairs(int n) {
        int[] d = new int[n + 1];
        d[0] = 1;
        d[1] = 1;
        for (int i = 2; i <= n; i++) {
            d[i] = d[i - 1] + d[i - 2];
        }

        return d[n];

    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        //
        if (nums.length < 3) {
            return res;
        }
        //排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            //
            if (nums[i] > 0) {
                break;
            }
            //
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {

                int tmp = nums[i] + nums[low] + nums[high];
                if (tmp == 0) {
//                    List<Integer> tmpList = new LinkedList<>();
//                    tmpList.add(nums[i]);
//                    tmpList.add(nums[low]);
//                    tmpList.add(nums[high]);
//                    //打印list
//                    System.out.println(i+" "+low+" "+high );
//                    res.add(tmpList);

                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    while (low < high && nums[low] == nums[low + 1]) {
                        low++;
                    }
                    while (low < high && nums[high] == nums[high - 1]) {
                        high--;
                    }

                    high--;
                    low++;
                } else {
                    if (tmp > 0) {
                        high--;
                    } else {
                        low++;
                    }
                }

            }
        }
        return res;
    }

    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int cursum = 0;
        int res = nums[0];
        int high = 0;
        for (; high < nums.length; high++) {
            if (cursum > 0) {
                cursum = cursum + nums[high];
            } else {
                cursum = nums[high];
            }

            res = Math.max(res, cursum);

        }

        return res;


    }

    public void maoPao(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = 0;
                    tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;

                }

            }
        }
    }

    public void quickSort(int[] nums, int low, int high) {
        //定义完成态
        if (low >= high) {
            return;
        }
        //设定左右边界
        int lowTmp = low;
        int highTmp = high;
        //取出标杆
        int tmp = nums[low];
        while (lowTmp < highTmp) {
            //从右边开始算，找到第一个小于标签的值
            while (lowTmp < highTmp && nums[highTmp] >= tmp) {
                highTmp--;
            }
            //把找到的值放到坑中
            nums[lowTmp] = nums[highTmp];
            //从左开始算，找到第一个大于标杆的值。
            while (lowTmp < highTmp && nums[lowTmp] <= tmp) {
                lowTmp++;
            }
            //放入坑中
            nums[highTmp] = nums[lowTmp];

        }
        //将标杆归位
        nums[lowTmp] = tmp;
        quickSort(nums, low, lowTmp - 1);
        quickSort(nums, highTmp + 1, high);

    }

    public boolean hasCycle(ListNode head) {
        Boolean flag = false;
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                flag = true;
                break;
            } else {
                set.add(head);
                head = head.next;
            }
        }
        return flag;
    }

    public String decodeString(String s) {
        if (s.length() == 0) {
            return "";
        }
        //String res = "";
        Stack<Integer> stack_num = new Stack<>();
        Stack<String> stack_string = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == '[') {
                int right = i + 1;
                String tmp = "";
                while (right < s.length() - 1 && (chars[right] >= 'a' && chars[right] <= 'z') || (chars[right] >= 'A' && chars[right] <= 'Z')) {
                    tmp = tmp + chars[right];
                    right++;
                }
                stack_string.push(tmp);

                int left = i - 1;
                int tmpint = 0;
                while (left >= 0 && (chars[left] >= '0' && chars[left] <= '9')) {
                    tmpint = tmpint * 10 + (chars[left] - '0');
                    left--;
                }
                //数反转。

                stack_num.push(tmpint);

            } else {
                if (chars[i] == ']') {
                    String tmpstring = stack_string.pop();
                    int tmpint = stack_num.pop();
                    String tmp2 = "";
                    for (int j = 0; j < tmpint; j++) {
                        tmp2 = tmpstring + tmp2;
                    }
                    if (stack_string.empty()) {
                        stack_string.push(tmp2);
                    } else {
                        stack_string.push(stack_string.pop() + tmp2);
                    }
                } else {
                    continue;
                }
            }
        }

        if (s.substring(s.length() - 1, s.length()).equals("]")) {
            return stack_string.pop();
        } else {
            String[] strings = s.split("]");
            return stack_string.pop() + strings[strings.length - 1];
        }

    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int max = 0;
        int cur = 0;
        for (int i : map.keySet()) {
            if (map.get(i) > max) {
                max = map.get(i);
                cur = i;
            }
        }


        return cur;


    }

    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        int res = 0;
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i], dp[i - 2] + cost[i]);
        }

        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }


    public ListNode fanZhuan(ListNode node) {


        ListNode cur = new ListNode(-1);
        ListNode low = cur;
        ListNode high = node;

        while (high != null) {
            ListNode tmp = high.next;
            high.next = low;
            low = high;
            high = tmp;

        }


        return high;

    }

    public int findKthLargest(int[] nums, int k) {


        int[] nums_1 = maoPaoPai(nums);


        return nums_1[nums.length - k];
    }

    public int[] maoPaoPai(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = 0;
                    tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
        return nums;
    }

    public ListNode fanzhuan_1(ListNode node) {
        if (node == null) {
            return node;
        }

        ListNode head = fanzhuan_1(node.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public int search(int[] nums, int target) {
        int maxflag = 0;
        int res = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] - nums[i] < 0) {
                maxflag = i;
                break;
            }
        }
        System.out.println("maxflag= " + maxflag);

        if (target < nums[maxflag]) {
            return erFen(nums, target, 0, maxflag);
        } else {
            return erFen(nums, target, maxflag, nums.length - 1);
        }


    }

    //二分查找用递归，入参：函数、目标、左右边界
    public int erFen(int[] nums, int target, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (nums[mid] > target) {
            return erFen(nums, target, low, mid - 1);
        } else {
            if (nums[mid] < target) {
                return erFen(nums, target, mid + 1, high);
            } else {
                return mid;
            }
        }

    }


    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        //用快慢指针找到链表中点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //断链
        ListNode righthead = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(righthead);
        return megre(left, right);

    }

    private ListNode megre(ListNode left, ListNode right) {
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                cur = cur.next;
                left = left.next;
            } else {
                cur.next = right;
                cur = cur.next;
                right = right.next;
            }
        }
        if (left == null) {
            cur.next = right;
        }
        if (right == null) {
            cur.next = left;
        }

        return res.next;


    }

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int[] res = new int[num1.length() + num2.length()];
        for (int i = chars1.length - 1; i >= 0; i--) {
            for (int j = chars2.length - 1; j >= 0; j--) {
                int tmp = (chars1[i] - '0') * (chars2[j] - '0') + res[i + j + 1];
                res[i + j + 1] = tmp % 10;
                res[i + j] = res[i + j] + tmp / 10;
                System.out.println(tmp);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            result.append(res[i]);
        }
        return result.toString();

    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        PriorityQueue<Integer> pg = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(b) - map.get(a);
            }
        });
        pg.addAll(map.keySet());
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pg.remove();
        }


        return res;
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        if (right != null && left != null) return root;
        return root;
    }

    public TreeNode fanZhuanTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode left = fanZhuanTree(root.right);
        TreeNode right = fanZhuanTree(root.left);
        root.left = left;
        root.right = right;

        return root;
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) {
            return root;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        if (left == right) {
            return root;
        } else {
            if (left > right) {
                return lcaDeepestLeaves(root.left);
            } else {
                return lcaDeepestLeaves(root.right);
            }
        }

    }

    private int depth(TreeNode root) {
        int i = 0;
        if (root == null) {
            return 0;
        }
        i = Math.max(depth(root.right), depth(root.left)) + 1;
        return i;
    }

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<Integer> pg = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });

        pg.addAll(map.keySet());
        return pg.remove();
    }

    public TreeNode sameFather(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = sameFather(root.left, p, q);
        TreeNode right = sameFather(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else {
            if (left == null) {
                return right;
            } else {
                return left;
            }
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode tmp = head;
        int tmp_k = k;
        while (tmp_k > 0) {
            if (tmp == null) {
                return head;
            }
            tmp = tmp.next;
            tmp_k--;
        }
        ListNode newhead = fanZhuan_2(head, tmp);
        head.next = reverseKGroup(tmp, k);
        return newhead;
    }

    private ListNode fanZhuan_2(ListNode a, ListNode b) {
        ListNode pre = null;
        ListNode cur = a;
        while (a != b) {
            ListNode tmp = a.next;
            a.next = pre;
            pre = a;
            a = tmp;
        }

        return pre;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, res, 0, nums1.length);
        System.arraycopy(nums2, 0, res, nums1.length, nums2.length);
        Arrays.sort(res);

        if (res.length % 2 == 1) {
            return res[res.length / 2];
        } else {
            return (double) (res[res.length / 2] + res[res.length / 2 - 1]) / 2;
        }

    }

    public int findTargetSumWays(int[] nums, int S) {


        if (nums.length == 1 && (nums[0] == Math.abs(S)) && S == 0) {
            return 2;
        }

        if (nums.length == 1 && (nums[0] == Math.abs(S))) {
            return 1;
        }


        if (nums.length == 0) {
            return 0;
        }

        return findTargetSumWays(qu_1(nums), S + nums[0]) + findTargetSumWays(qu_1(nums), S - nums[0]);
    }

    private int[] qu_1(int[] a) {
        int[] b = new int[a.length - 1];
        for (int i = 1; i < a.length; i++) {
            b[i - 1] = a[i];
        }
        return b;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        doInorderTraversal(root, res);
        return res;
    }

    private void doInorderTraversal(TreeNode root, List res) {
        if (root == null) {
            return;
        }

        doInorderTraversal(root.right, res);
        res.add(root.val);
        doInorderTraversal(root.left, res);
    }

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= word1.length(); i++) dp[i][0] = dp[i - 1][0] + 1;
        for (int j = 1; j <= word2.length(); j++) dp[0][j] = dp[0][j - 1] + 1;

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //取dp[i-1][j],dp[i-1][j-1],dp[i][j-1]中的最小值。
                    int tmp = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                    dp[i][j] = Math.min(tmp, dp[i - 1][j - 1] + 1);
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }

    public void quik(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int low = left;
        int high = right;
        int tmp = nums[left];
        while (low < high) {
            while (low < high && tmp <= nums[high]) {
                high--;
            }
            nums[low] = nums[high];
            while (low < high && tmp >= nums[low]) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = tmp;

        quik(nums, left, low - 1);
        quik(nums, high + 1, right);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int d = triangle.size();
        int l = triangle.get(d - 1).size();
        int[][] dp = new int[d][l];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < d; i++) {
            int cur_l = triangle.get(i).size();
            for (int j = 0; j < cur_l; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                } else {
                    if (j == cur_l - 1) {
                        dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
                    }
                }

            }
        }

        int res = dp[d - 1][0];
        for (int k : dp[d - 1]) {
            System.out.println(k);
            res = Math.min(res, k);
        }

        return res;

    }

    public int minimumTotal_1(List<List<Integer>> triangle) {
        //设dp[][]
        int[][] dp = new int[triangle.size() + 1][triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }

        }
        return dp[0][0];
    }

    public String decodeString_1(String s) {
        //初始化
        StringBuilder res = new StringBuilder();
        int muti = 0;
        Stack<Integer> muti_stack = new Stack<>();
        Stack<String> string_stack = new Stack<>();
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' || chars[i] <= '9') {
                muti = muti * 10 + Integer.parseInt(chars[i] + "");
            } else {
                if (chars[i] == '[') {
                    muti_stack.push(muti);
                    string_stack.push(res.toString());
                    muti = 0;
                    res = new StringBuilder();
                } else {
                    if (chars[i] == ']') {
                        StringBuilder tmp = new StringBuilder();
                        int cur_muti = muti_stack.pop();
                        for (int j = 0; j < cur_muti; j++) {
                            tmp.append(res);
                        }
                        res = new StringBuilder(string_stack.pop() + tmp);


                    } else {
                        res.append(chars[i]);
                    }
                }
            }
        }

        return res.toString();
    }

    public ListNode reverseList_1(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        return pre;

    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = reverseList(head.next);
        head.next.next = head;
        head.next = null;


        return tail;
    }

    public boolean repeatedSubstringPattern(String s) {
        return (s + s).substring(1, (s + s).length()).contains(s);
    }

    public int minArray(int[] numbers) {
        int res = 0;
        for (int i = 1; i < numbers.length - 1; i++) {
            if ((numbers[i] - numbers[i - 1]) * (numbers[i + 1] - numbers[i]) < 0) {
                res = numbers[i + 1];
            }
        }

        return res;
    }

    public String reverseWords(String s) {
        s = s.trim();
        int high = s.length() - 1;
        int low = s.length() - 1;
        StringBuilder res = new StringBuilder();
        while (high >= 0) {
            while (high >= 0 && s.charAt(high) != ' ') {
                high--;
            }
            res.append(s.substring(high + 1, low + 1) + " ");
            while (high >= 0 && s.charAt(high) == ' ') {
                high--;
            }
            low = high;

        }

        return res.toString().trim();
    }

    public int longestValidParentheses(String s) {
        if (s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] flag = new int[s.length()];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == '(') {
                stack.push(i);
            } else {
                if (!stack.empty()) {
                    //stack不空
                    int tmp = stack.pop();
                    flag[tmp] = 1;
                    flag[i] = 1;
                } else {
                    continue;
                }
            }

        }

        int cur = 0;
        int max = 0;
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == 1) {
                cur++;
                max = Math.max(max, cur);
            } else {
                cur = 0;
            }
        }


        return max;

    }

    public int[] singleNumber_1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

        }
        PriorityQueue<Integer> pg = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });

        pg.addAll(map.keySet());
        int[] res = new int[2];
        for (int j = 0; j < 2; j++) {
            res[j] = pg.remove();
        }

        return res;

    }

    public int minPathSum(int[][] grid) {
        int j_length = grid.length;
        int i_length = grid[0].length;
        int[][] dp = new int[i_length][j_length];

        for (int i = 0; i < i_length; i++) {
            for (int j = 0; j < j_length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[0][0];
                } else {
                    if (i == 0) {
                        dp[i][j] = dp[i][j - 1] + grid[i][j];
                    } else {
                        if (j == 0) {
                            dp[i][j] = dp[i - 1][j] + grid[i][j];
                        } else {
                            dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                        }
                    }
                }
            }
        }

        return dp[i_length - 1][j_length - 1];
    }

    public int minAddToMakeValid(String S) {
        if (S.length() == 0) {
            return 0;
        }
        char[] chars = S.toCharArray();
        int[] flag = new int[S.length()];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < S.length(); i++) {
            if (chars[i] == '(') {
                stack.push(i);
            } else {
                if (!stack.empty()) {
                    int tmp = stack.pop();
                    flag[i] = 1;
                    flag[tmp] = 1;
                }
            }

        }


        int res = 0;
        for (int i : flag) {
            if (i == 0) {
                res++;
            }
        }

        return res;

    }


    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = null;
        for (ListNode listNode : lists) {
            res = megre2Lists_1(res, listNode);
        }

        return res;

    }

    private ListNode megre2Lists_1(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = megre2Lists_1(l1.next, l2);
            return l1;
        } else {
            l2.next = megre2Lists_1(l1, l2.next);
            return l2;

        }

    }

    private ListNode megre2Lists_2(ListNode l1, ListNode l2) {
        ListNode cur = new ListNode(-1);
        ListNode res = cur;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
                cur = cur.next;

            } else {
                cur.next = l2;
                l2 = l2.next;
                cur = cur.next;
            }
        }

        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }

        return res.next;

    }

    public int largestRectangleArea(int[] heights) {
        int res = 0;
        int length = heights.length;
        for (int i = 0; i < length; i++) {
            int w = 1;
            int high = heights[i];
            int j = i;
            while (--j >= 0 && heights[j] >= high) {
                w++;
            }
            j = i;
            while (++j < length && heights[j] >= high) {
                w++;
            }

            res = Math.max(res, w * high);

        }

        return res;


    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int j = 0;
            while (true) {
                if (nums1[i] == nums2[j]) {
                    break;
                }
                j++;
            }
            int tmp = -1;
            for (; j < nums2.length; j++) {
                if (nums1[i] < nums2[j]) {
                    tmp = nums2[j];
                    break;
                }
            }
            res[i] = tmp;


        }

        return res;
    }

    public int[][] merge(int[][] intervals) {
        //先把intervals数组中的区间排序，按照第一个元素由大到小。
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        //设返回集合
        int[][] res = new int[intervals.length][2];
        int index = -1;
        //每次从intervals拿出来一个数组和res比较
        for (int[] ints : intervals) {
            //如果res为空的时候，将ints直接放进来
            if (index == -1) {
                index++;
                res[index][0] = ints[0];
                res[index][1] = ints[1];

            } else {
                if (ints[0] > res[index][1]) {
                    //当ints的最小值都大于当前区间的最大值，则表示二者不是相互覆盖，则将新的区间存在下一个维度中。
                    index++;
                    res[index][0] = ints[0];
                    res[index][1] = ints[1];
                } else {
                    res[index][1] = Math.max(res[index][1], ints[1]);
                }
            }
        }

        return Arrays.copyOf(res, index + 1);

    }

    public boolean canAttendMeetings(int[][] intervals) {


        Boolean flag = true;
        int[] tmp = new int[2];

        //对intervals进行排序，按照区间首数字
        Arrays.sort(intervals, (o1, o2) -> (o1[0] - o2[0]));
        for (int[] ints : intervals) {
            if (ints[0] >= tmp[1]) {
                tmp = ints;
            } else {
                flag = false;
            }
        }


        return flag;

    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] res = new int[intervals.length + 1][2];
        //用于控制res的循环
        int i = 0;
        //用户控制intervals的循环
        int j = 0;
        //先把intervals的前端放到res中
        while (j < intervals.length && intervals[j][1] < newInterval[0]) {
            res[i] = intervals[j];
            i++;
            j++;
        }
        while (j < intervals.length && intervals[j][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[j][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[j][1]);
            j++;
        }
        res[i] = newInterval;
        i++;
        while (j < intervals.length && newInterval[1] < intervals[j][0]) {
            res[i] = intervals[j];
            i++;
            j++;
        }
        //然后
        return Arrays.copyOf(res, i);

    }

    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int index = -1;
        int res[][] = new int[intervals.length][2];

        for (int[] ints : intervals) {
            if (index == -1) {
                index++;
                res[index] = ints;

            }
            if (res[index][1] <= ints[1] && res[index][0] >= ints[0]) {
                res[index] = ints;
            } else {
                if (res[index][1] >= ints[1] && res[index][0] <= ints[0]) {
                    continue;
                } else {
                    index++;
                    res[index] = ints;
                }
            }


        }

        return index + 1;


    }


    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new LinkedList<>();
        for (int i = left; i <= right; i++) {
            int tmp = i;
            if (ziChuShu(i)) {
                res.add(i);
            } else {
                continue;
            }

        }

        return res;


    }

    private boolean ziChuShu(int i) {
        boolean flag = false;
        int tmp_i = i;
        List<Integer> tmp = new LinkedList<>();
        if (i < 10) {
            return true;
        } else {
            while (tmp_i != 0) {
                tmp.add(tmp_i % 10);
                tmp_i = tmp_i / 10;
            }

            for (int i1 : tmp) {
                if (i1 == 0) {
                    flag = false;
                    break;
                } else {
                    if (i % i1 != 0) {
                        flag = false;
                        break;
                    } else {
                        flag = true;
                    }
                }

            }
            return flag;
        }


    }

    public int maxSubArray_1(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }

        }
        int max = dp[0];
        for (int i : dp) {
            max = Math.max(max, i);
        }
        return max;
    }

    public char firstUniqChar(String s) {
        //map用来保存每一个char出现的频率
        if (s.length() == 0) {
            return ' ';
        }
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        char res = ' ';
        for (char c : chars) {
            if (map.get(c) == 1) {
                res = c;
                break;
            }
        }


        return res;
    }

    public String reverseLeftWords(String s, int n) {
        String res = s.substring(n, s.length());
        return res + s.substring(0, n);

    }

    public ListNode deleteDuplicates(ListNode head) {

        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return head;

    }


    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }


        int mid = (right + left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, left, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, right);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //preorder为前序遍历，第一就是根节点。
        //inorder为中序遍历
        //因为每次都需要在前序中找到root，然后计算根在中序中的位置，所以，把中序序列存成map
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, map, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, Map<Integer, Integer> map, int preL, int preR, int inL, int inR) {

        if (preL > preR || inL > inR) {
            return null;
        }
        //获取根节点的值
        int int_root = preorder[preL];
        //获取根节点在中序遍历中的坐标；
        int index_root = map.get(int_root);
        TreeNode root = new TreeNode(int_root);
        root.right = buildTree(preorder, map, preL + 1, index_root - inL + preL, inL, index_root - 1);
        root.left = buildTree(preorder, map, index_root - inL + preL + 1, preR, index_root + 1, inR);

        return root;


    }

    public String compressString(String S) {
        if (S.length() == 0) {
            return "";
        }

        char[] chars = S.toCharArray();
//        for(int i = 0;i<chars.length;i++){
//            //在map中保存字符串中字母出现的频率
//            map.put(chars[i],map.getOrDefault(chars[i],0) + 1);
//        }


        StringBuilder tmp_s = new StringBuilder();
        char tmp = chars[0];
        int count = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == tmp) {
                count++;
            } else {
                tmp_s.append(tmp + "" + count);
                tmp = chars[i];
                count = 1;
            }
        }
        tmp_s.append(tmp);
        tmp_s.append(count);


        if (S.length() > tmp_s.toString().length()) {
            return tmp_s.toString();
        } else {
            return S;
        }

    }


    public double myPow_1(double x, int n) {
        long n_tmp = n;
        if (n_tmp == 0) {
            return 1;
        }
        if (n_tmp == 1) {
            return x;
        }
        if (n_tmp == -1) {
            return 1 / x;
        }
        double half = myPow_1(x, (int) n_tmp / 2);
        double rest = myPow_1(x, (int) n_tmp % 2);
        return half * half * rest;
    }

    //当b为偶数则执行n/2次x^2，b为奇数时执行n/2+1次
    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        long b = n;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        double res = 1;
        //每次遇到奇数则写回到res中。
        while (b > 0) {
            if (b % 2 == 1) res *= x;
            x *= x;
            b = b / 2;
        }

        return res;

    }

    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 || nums[i] > nums.length) {
                nums[i] = 0;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0 && nums[i] != i + 1) {
                int a = nums[i];
                nums[i] = 0;
                while (nums[a - 1] != a) {
                    if (nums[a - 1] == 0) {
                        nums[a - 1] = a;
                    } else {
                        int b = nums[a - 1];
                        nums[a - 1] = a;
                        a = b;
                    }
                }
            }


        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                res = i + 1;
                break;
            }
        }
        if (res == 0) {
            res = nums.length + 1;
        }


        return res;


    }

    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

        //递归算每个节点的贡献值
        dps(root);
        return res;

    }

    private int dps(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left_value = Math.max(0, dps(root.left));
        int right_value = Math.max(0, dps(root.right));
        res = Math.max(res, root.val + left_value + right_value);
        return root.val + Math.max(left_value, right_value);


    }

    public List<List<Integer>> subsets_1(int[] nums) {

        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> cur = new LinkedList<>();
        dps1(0, nums, res, cur);
        return res;

    }

    //i代表从数组中第几位开始算，nums为数组、res为结果集，cur是当前结果。
    private void dps1(int i, int[] nums, List res, LinkedList cur) {
        res.add(new LinkedList<>(cur));
        for (int j = i; j < nums.length; j++) {
            cur.add(nums[j]);
            dps1(j + 1, nums, res, cur);
            //回退
            cur.removeLast();

        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> cur = new LinkedList<>();
        dfs_subsets(0, nums, res, cur);
        return res;
    }

    private void dfs_subsets(int i, int[] nums, List<List<Integer>> res, LinkedList<Integer> cur) {
        for (int j = i; j < nums.length; j++) {
            cur.add(nums[i]);
            res.add(new ArrayList<>(cur));
            dfs_subsets(j + 1, nums, res, cur);
            cur.removeLast();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> cur = new LinkedList<>();
        dfs_Sum(0, candidates, target, res, cur);
        return res;
    }

    private void dfs_Sum(int i, int[] candidates, int target, List<List<Integer>> res, LinkedList<Integer> cur) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(cur));
        }
        for (int j = i; j < candidates.length; j++) {
            cur.add(candidates[j]);
            dfs_Sum(j, candidates, target - candidates[j], res, cur);
            cur.removeLast();

        }
    }


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> cur = new LinkedList<>();
        Arrays.sort(candidates);
        dfs_Sum2(0, candidates, target, res, cur);
        return res;
    }

    private void dfs_Sum2(int begin, int[] candidates, int target, List<List<Integer>> res, LinkedList<Integer> cur) {
        if (target == 0) {
            res.add(new ArrayList<>(cur));
        }
        if (target < 0) {
            return;
        }


        for (int i = begin; i < candidates.length; i++) {

            //同一层去重
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }

            cur.add(candidates[i]);
            //同一个树内进行去重
            dfs_Sum2(i + 1, candidates, target - candidates[i], res, cur);
            cur.removeLast();
        }

    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> cur = new LinkedList<>();
        dfs_permute(nums, res, cur);
        return res;

    }

    private void dfs_permute(int[] nums, List<List<Integer>> res, LinkedList<Integer> cur) {

        if (nums.length == 0) {
            res.add(new ArrayList<>(cur));
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            cur.add(nums[i]);
            //把元素nums[i]在数组nums中删除。
            dfs_permute(deleteValue(nums, i), res, cur);
            cur.removeLast();
        }
    }

    private int[] deleteValue(int[] nums, int index) {
        int[] tmp = new int[nums.length];
        for (int i = 0; i < nums.length - 1; i++) {
            if (i < index) {
                tmp[i] = nums[i];
            } else {
                tmp[i] = nums[i + 1];
            }
        }

        return Arrays.copyOf(tmp, tmp.length - 1);
    }

    public int lengthOfLastWord(String s) {
        s = s.trim();
        String s1 = new StringBuilder(s).reverse().toString();
        int i = 0;
        for (; i < s1.length(); i++) {
            if (s1.charAt(i) == ' ') {
                break;
            }
        }
        return i;


    }


    public static List<Integer> list_res = new ArrayList<>();

    public int maxAreaOfIsland(int[][] grid) {

        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    //上岸了
                    es.submit(new myThrad(grid, i, j));
                }
            }
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Integer[] tmp = (Integer[]) list_res.toArray();
        Arrays.sort(tmp);


        return tmp[tmp.length - 1];
    }

    class myThrad extends Thread {
        private int[][] grid;
        int i;
        int j;

        public myThrad(int[][] grid, int i, int j) {
            super();
            this.grid = grid;
            this.i = i;
            this.j = j;
        }

        @Override
        public void run() {
            list_res.add(area(grid, i, j));
        }
    }


    public static int area(int[][] grid, int i, int j) {
        if (notInArea(grid, i, j)) {
            return 0;
        }
        if (grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 2;
        int tmp = 1 + area(grid, i + 1, j)
                + area(grid, i, j + 1)
                + area(grid, i - 1, j)
                + area(grid, i, j - 1);
        return tmp;
    }

    private static boolean notInArea(int[][] grid, int i, int j) {
        boolean flag = false;
        if (i <= 0 || i >= grid.length || j >= grid[0].length || j <= 0) {
            flag = true;
        }
        return flag;

    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        int dpth = 0;
        rightSideView(root, res, dpth);
        return res;
    }

    public void rightSideView(TreeNode root, List<Integer> res, int dpth) {
        if (root == null) {
            return;
        }
        if (res.size() == dpth) {
            res.add(root.val);

        }
        dpth++;
        rightSideView(root.right, res, dpth);
        rightSideView(root.left, res, dpth);


    }

    public int uniquePaths(int m, int n) {

        //设dp[i][j]为当机器人到达(i,j)时，走过的不同路径数。
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    if (j != 0) {
                        dp[i][j] = dp[i][j - 1];
                    }
                } else {
                    if (j == 0) {
                        if (i != 0) {
                            dp[i][j] = dp[i - 1][j];
                        }
                    } else {
                        dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                    }
                }
            }
        }


        return dp[m - 1][n - 1];

    }
    //借助栈来判断回文
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        boolean flag = true;
        Stack<Integer> s = new Stack<>();
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            s.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        //要判断list个数的奇偶
        //当fast为null时候，list个数为偶，则slow则为后半截链接的第一个。
        //当fast不为null时，list为奇，则slow = slow.next，跳过中间node。
        if (fast != null) {
            slow = slow.next;
        }

        while (! s.empty()) {
            if(s.pop() != slow.val){
                flag = false;
                break;
            }
            slow = slow.next;
        }


    return flag;
    }
    //N皇后问题
    public List<List<String>> solveNQueens(int n) {
        //所有解的list
        List<List<String>> res = new LinkedList<>();
        //每一个解
        char[][] chars = new char[n][n];
        //初始化
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                chars[i][j] = '.';
            }
        }
        solve(res,chars,0);

        return res;

    }

    private void solve(List<List<String>> res, char[][] chars, int row){
        //如果row = n 则表示chars全都填满了，就是一个解，加入到res中
        if(row == chars.length){
            res.add(cahrsToList(chars));
            return;
        }
        for(int col = 0;col<chars.length;col++){
            //判断row，col的位置能不能放Q
            if(vaild(chars,row,col)){
                chars[row][col] = 'Q';
                solve(res,chars,row + 1);
                //回滚
                chars[row][col] = '.';
            }
        }
    }

    private boolean vaild(char[][] chars, int row,int col) {
        //判断水平线上有没有Q
        for(int i = 0;i<chars.length;i++){
            if(chars[row][i] == 'Q'){
                return false;
            }
        }
        //判断垂直线上有没有Q
        for(int i = 0;i<chars.length;i++){
            if(chars[i][col] == 'Q'){
                return false;
            }
        }
        //判断左上对角线没有Q
        for(int i = row,j=col;i>=0 && j>=0;i--,j--){
            if(chars[i][j] == 'Q'){
                return false;
            }
        }
        //判断右上对角线没有Q
        for(int i = row,j=col;i>=0 && j< chars.length;i--,j++){
            if(chars[i][j] == 'Q'){
                return false;
            }
        }

        return true;
    }

    private List<String> cahrsToList(char[][] chars) {
        List<String> tmp = new LinkedList<>();
        for(int i = 0;i<chars.length;i++){
            tmp.add(new String(chars[i]));
        }
        return tmp;
    }

    private int[] arrays;
    public Solution(int[] nums) {
        this.arrays = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return arrays;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] tmp = new int[arrays.length];
        int index = 0;
        Set<Integer> set = new HashSet<>();
        Random random = new Random();
        while (set.size() < tmp.length) {
            int tmp_num = arrays[random.nextInt(tmp.length-1)];
            if(set.add(tmp_num)){
                tmp[index] =tmp_num;
                index ++;
            }

        }
        return tmp;
    }



}
