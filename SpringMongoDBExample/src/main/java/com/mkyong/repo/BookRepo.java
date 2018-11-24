package com.mkyong.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.solr.repository.Query;

import com.mkyong.model.Book;

public interface BookRepo extends CrudRepository<com.mkyong.model.Book, Long>
{

		@Query("title:?0")
		public Book findByBookTitle(String name);
		
}
