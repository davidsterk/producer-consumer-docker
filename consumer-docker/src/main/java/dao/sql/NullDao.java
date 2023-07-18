package dao.sql;
/*
Class NullDao: Null object for Dao models
 */
import dao.domain.NullDomain;
import dao.Dao;

public class NullDao implements Dao<NullDomain>{
    @Override
    public void create(NullDomain nullDomain) {

    }
}
