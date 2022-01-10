package dao;
/*
Description: Dao Interface, that create method used by all concrete Dao classes
 */
public interface Dao<T> {

    void create(T t) throws Exception;
}
