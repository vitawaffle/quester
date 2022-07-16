package by.vitalylobatsevich.quester.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import io.vavr.collection.Seq;
import io.vavr.control.Option;

import by.vitalylobatsevich.quester.entity.AppEntity;

@NoRepositoryBean
public interface AppRepository<T extends AppEntity, ID> extends Repository<T, ID> {

    Option<T> findById(ID id);

    Seq<T> findAll();

    T save(T entity);

    void delete(T entity);

    void deleteById(ID id);

}
