package dao.interfaceDAO;

public interface DAO<T,k> {
    void createTable(T t);
    void readTable(k id);
    void updateTable(T t);
    void deleteTable(k id);
}
