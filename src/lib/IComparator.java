package lib;

import java.util.Comparator;

public interface IComparator<T> extends Comparator<T> {
    public int compare(T obj1, T obj2);
}
