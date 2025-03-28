package dao.interfaceDAO;

public interface DAO<T,k> {
    void createTable(T t);
    void readTable(k id);
    void uptadeTable(T t);
    void deleteTable(k id);
}
