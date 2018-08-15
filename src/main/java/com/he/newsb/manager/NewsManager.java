package com.he.newsb.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.he.newsb.common.AppContext;
import com.he.newsb.model.News;
import com.he.newsb.repository.NewsRepository;

/**
 * this class will read the data from CSV file and put into the DB since its one
 * time acitvity
 * 
 * @author somendra1.raj
 *
 */
public class NewsManager {
	
	private static final Logger log = LoggerFactory.getLogger(NewsManager.class);
	
	@Autowired
	NewsRepository newsRepository = AppContext.getContext().getBean(NewsRepository.class);

	static File TARGET_FILE = new File("C:/Users/Somendra1.Raj/Desktop/news_bulletin666200b.csv");
	static String split = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

	public void saveDataIntoDB(){
		try {
			log.info("Reading data from csv file");
			FileReader fileWriter = new FileReader(TARGET_FILE);
			BufferedReader br = new BufferedReader(fileWriter);
			String input = "";
			List<News> newsList = new ArrayList<News>();
			try {
				while ((input = br.readLine()) != null) {
					String[] line = input.split(split);
					//log.info("Line "+line.length+" "+line[1]+" "+line[2]+" "+line[3]+" "+line[4]+" "+line[5]+" "+line[6]);
					News news = new News();
					news.setTitle(line[1].replaceAll("\"", ""));
					news.setNewsUrl(line[2].replaceAll("\"", ""));
					news.setPublisherName(line[3].replaceAll("\"", ""));
					news.setGrade(line[4].replaceAll("\"", ""));
					news.setBaseUrl(line[5].replaceAll("\"", ""));
					news.setTime(new Date(line[6].replaceAll("\"", "")));
					log.info("Line "+news.getTitle()+news.getBaseUrl());
					newsList.add(news);
				}
				log.info("List "+newsList);
				log.info("List Size: "+newsList.size());
				
				
				int i = 1;
				for(News n : newsList){
					newsRepository.save(n);
					log.info("Save "+i++);
				}
				newsRepository.saveAll(newsList);
				
				
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

	public static void main(String args[]) {
		//NewsManager newManager = new NewsManager();

		try {
			//newManager.saveDataIntoDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
