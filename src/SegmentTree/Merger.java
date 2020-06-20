package SegmentTree;

/**
 * 用于线段树的具体逻辑/业务
 * @author yzze
 * @create 2020-05-10 16:01
 */
public interface Merger<E> {
    E merge(E a, E b);
}
