package at.fhj.swd.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import at.fhj.swd.business.NewsBO;
import at.fhj.swd.business.UserBO;
import at.fhj.swd.domain.News;
import at.fhj.swd.domain.User;

public class NewsBean {

    private String entry;
    private String title; 


    private NewsBO _bo;

    public NewsBean() {
        _bo = new NewsBO();
    }

    
    public String addNow() {
        
        News _newNews = new News();
        _newNews.setEntry(this.getEntry());
        _newNews.setTitle(this.getTitle());
        _newNews.setDate(new Date());
        

        if (_bo.create(_newNews)) {
            return "new_home";
        } else {
            return "news-failed";
        }
    }

 

    public Collection<News> getAll() {
        return _bo.getAll();
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
}
