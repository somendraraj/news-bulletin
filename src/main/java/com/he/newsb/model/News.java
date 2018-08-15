package com.he.newsb.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "news", catalog = "he_news")
@EntityListeners(AuditingEntityListener.class)
public class News implements Serializable {
	private static final long serialVersionUID = -443075720309636378L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "news_url", nullable = false)
	private String newsUrl;

	@Column(name = "publisher_name", nullable = false)
	private String publisherName;

	@Column(name = "grade", nullable = false)
	private String grade;

	@Column(name = "base_url", nullable = false)
	private String baseUrl;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time", nullable = false)
	private Date time;

	public News() {

	}

	public News(int id, String title, String newsUrl, String publisherName, String grade, String baseUrl, Date time) {
		super();
		this.id = id;
		this.title = title;
		this.newsUrl = newsUrl;
		this.publisherName = publisherName;
		this.grade = grade;
		this.baseUrl = baseUrl;
		this.time = time;
	}

	public News(String title, String newsUrl, String publisherName, String grade, String baseUrl, Date time) {
		this.title = title;
		this.newsUrl = newsUrl;
		this.publisherName = publisherName;
		this.grade = grade;
		this.baseUrl = baseUrl;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
