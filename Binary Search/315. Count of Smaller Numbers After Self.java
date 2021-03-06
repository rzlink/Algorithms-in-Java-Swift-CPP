// same as 493. Reverse Pairs
public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList();
        List<Integer> res = new ArrayList();
        res.add(0);
        Node root = new Node(nums[nums.length - 1]);
        for (int i = nums.length - 2; i >= 0; i--) {
            buildTree(res, root, nums[i]);
        }
        Collections.reverse(res);
        return res;
    }
    
    // count[i]: the number of nodes smaller than val
    // node.count: smaller or equal to node.val
    private void buildTree(List<Integer> res, Node node, int target) {
        int count = 0;
        while (true) {
            if (target > node.val) {
                count += node.count;
                if (node.right == null) {
                    node.right = new Node(target);
                    break;
                }
                node = node.right;
            } else {
                node.count++;
                if (node.left == null) {
                    node.left = new Node(target);
                    break;
                }
                node = node.left;
            }
        }
        res.add(count);
    }
    
    class Node {
        int val;
        // node.count: smaller or equal to node.val
        int count = 1;
        Node left, right;
        public Node(int val) {
            this.val = val;
        }
    }
}

// below is slower, because list.add(idx, val) is O(n) time
public class Solution {
    class Node {
        int count = 1;
        int val;
        Node left, right;
        public Node(int val) {
            this.val = val;
        }
    }
    
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList();
        Node root = new Node(nums[nums.length - 1]);
        List<Integer> res = new ArrayList();
        res.add(0);
        for (int i = nums.length - 2; i >= 0; i--) {
            boolean insert = false;
            Node node = root;
            int count = 0;
            while (!insert) {
                if (nums[i] > node.val) {
                    count += node.count;
                    if (node.right == null) {
                        node.right = new Node(nums[i]);
                        insert = true;
                    } else node = node.right;
                } else {
                    node.count++;
                    if (node.left == null) {
                        node.left = new Node(nums[i]);
                        insert = true;
                    } else node = node.left;
                }
            }
            res.add(0, count);
        }
        return res;
    }
}

// brilliant mergeSort solution
public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        // i < j, nums[i] > nums[j]
        if (nums == null || nums.length == 0) return new ArrayList();
        
        Num[] arr = new Num[nums.length];
        for (int i = 0; i < nums.length; i++)
            arr[i] = new Num(nums[i], i);
        
        int[] smaller = new int[nums.length];
        mergeCount(smaller, arr, 0, nums.length - 1);
        
        List<Integer> res = new ArrayList();
        for (int count : smaller) res.add(count);
        return res;
    }
    
    private void mergeCount(int[] smaller, Num[] nums, int l, int r) {
        if (l >= r) return;
        int mid = l + (r - l)/2;
        mergeCount(smaller, nums, l, mid);
        mergeCount(smaller, nums, mid + 1, r);
        Num[] cache = new Num[r - l + 1];
        int j = mid + 1, k = mid + 1;
        for (int i = l, idx = 0; i <= mid; i++, idx++) {
            while (j <= r && nums[i].val > nums[j].val) j++;
            while (k <= r && nums[k].val < nums[i].val) cache[idx++] = nums[k++];
            cache[idx] = nums[i];
            smaller[nums[i].idx] += j - mid - 1;
        }
        System.arraycopy(cache, 0, nums, l, k - l);
    }
    
    class Num {
        int val, idx;
        public Num(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
}

