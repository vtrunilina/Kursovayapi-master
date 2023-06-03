package db;

import db.StrDB;

import java.util.List;
/**
 * Класс для интерфеса БД.
 * @author Трунилина Виктория витальевна
 * Группа бПИэ-201
 */

public interface InterDB
{
    List<StrDB> getAllStrDB();
   StrDB getStrDBById(int id);
    void addStrDB(StrDB strDB);
    void updateStrDB(StrDB strDB);

    void deleteStrDB(int id);
}
