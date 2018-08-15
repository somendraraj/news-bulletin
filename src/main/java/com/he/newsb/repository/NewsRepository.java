package com.he.newsb.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.he.newsb.model.News;

public interface NewsRepository extends CrudRepository<News, Long> {

	@Query(value = "SELECT id, title, news_url, publisher_name, grade, base_url, time FROM news n WHERE n.id=?", nativeQuery = true)
	List<News> findById(int id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE news n SET n.time =? WHERE n.id =?", nativeQuery = true)
	public void updateTime(Date date, int id);

}
