package SegmentTree;

/**
 * 线段树
 * @author yzze
 * @create 2020-05-10 12:22
 */
public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0,0,data.length-1);
    }

    /**
     * 在treeIndex的位置创建表示区间[l,r]的线段树
     * @param treeIndex 根节点
     * @param l         左边界
     * @param r         右边界
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if(l == r) {
            tree[treeIndex] = data[l];
            return ;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid+1, r);

        // 两个数据如何融合 取决于merge
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return 2*index+1;
    }

    /**
     * 区间查询
     * 返回区间[qL, r]的值
     * @param qL
     * @param qR 
     * @return
     */
    public E query(int qL, int qR) {
        if (qL < 0 || qL >= data.length ||
                qR < 0 || qR >= data.length || qL > qR) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return query(0, 0, data.length-1, qL, qR);
    }
    /**
     * 在以treeIndex为根的线段树的[l,r]范围里，
     * 搜索区间[qL,qR]的值
     * @param treeIndex
     * @param l
     * @param r
     * @param qL
     * @param qR
     * @return
     */
    private E query(int treeIndex, int l, int r, int qL, int qR) {
        if (l == qL && r == qR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (qL >= mid+1) {
            return query(rightTreeIndex, mid+1, r, qL, qR);
        } else if (qR <= mid) {
            return query(leftTreeIndex, l, mid, qL, qR);
        }

        E leftResult = query(leftTreeIndex, l, mid, qL, mid);
        E rightResult = query(rightTreeIndex, mid+1, r, mid+1,qR);
        return merger.merge(leftResult, rightResult);
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return 2*index+2;
    }

    /**
     * 将Index位置的值更新为e
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }

        data[index] = e;
        set(0, 0, data.length-1, index, e);
    }
    private void set(int treeIndex, int l, int r, int index, E e) {
        if(l==r) {
            tree[treeIndex] = e;
            return ;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index >= mid+1) {
            set(rightTreeIndex,mid+1, r, index, e);
        } else {
            set(leftTreeIndex,l,mid,index,e);
        }

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }
            if (i != tree.length - 1) {
                res.append(',');
            }
        }
        res.append(']');
        return res.toString();
    }

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
//        SegmentTree<Integer> segmentTree = new SegmentTree<Integer>(nums, new Merger<Integer>() {
//            @Override
//            public Integer merge(Integer a, Integer b) {
//                return a + b;
//            }
//        });
        SegmentTree<Integer> segmentTree = new SegmentTree<Integer>(nums, (a,b)->a+b);
        System.out.println(segmentTree.toString());

        System.out.println(segmentTree.query(0,2));
        System.out.println(segmentTree.query(2,5));

        segmentTree.set(2, 1);

    }
}
