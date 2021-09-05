package dao;

public interface Dao<T> {

    void create(T t) throws Exception;
}
