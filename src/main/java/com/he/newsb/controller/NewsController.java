package com.he.newsb.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.he.newsb.common.Cache;
import com.he.newsb.common.DateUtil;
import com.he.newsb.model.News;
import com.he.newsb.repository.NewsRepository;

@RestController
@RequestMapping(value = "/news")
public class NewsController {
	
	private static final Logger log = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	NewsRepository newsRepository;

	@RequestMapping(value = "/hello")
	public static String hello() {
		return "Hello world";
	}

	@GetMapping("/news")
	public List<News> getAllNews() {
		List<News> list = null;
		if(!Cache.newList.isEmpty()){ //cache implemented to avoid multiple db calls
			return Cache.newList;
		}else{
			list = (List<News>) this.newsRepository.findAll();
			Cache.newList.addAll(list);
		}
		return  list;
	}
	
	@GetMapping("/news/{id}")
	public List<News> getNewsById(@PathVariable(value="id") int id) {
		return  newsRepository.findById(id);
	}
	
	
	@GetMapping("/update")
	public void updateNews() {
		saveDataIntoDB();
		
	}
	
	static File TARGET_FILE = new File("C:/Users/Somendra1.Raj/Desktop/news_bulletin666200b.csv");
	static String split = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

	public void saveDataIntoDB(){
		try {
			log.info("Reading data from csv file");
			FileReader fileWriter = new FileReader(TARGET_FILE);
			BufferedReader br = new BufferedReader(fileWriter);
			String input = "";
			List<News> newsList = new ArrayList<News>();
			int i = 1;
			try {
				while ((input = br.readLine()) != null) {
					String[] line = input.split(split);
					int id = Integer.parseInt(line[0].replaceAll("\"", ""));
					long time = Long.parseLong(line[6].replaceAll("\"", ""));
					log.info("Line "+id+" "+time);
					Date date = DateUtil.getFormatedDate(time);
					newsRepository.updateTime(date, id);
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
				return;
			} finally {
				try {
					if (br != null)
						br.close();
					if (fileWriter != null)
						fileWriter.close();
				} catch (Exception e) {

				}
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
			return;
		}
	}

}
