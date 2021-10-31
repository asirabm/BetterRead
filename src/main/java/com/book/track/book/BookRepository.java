package com.book.track.book;

//import java.beans.JavaBean;

//import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CassandraRepository<Book, String> {

}
