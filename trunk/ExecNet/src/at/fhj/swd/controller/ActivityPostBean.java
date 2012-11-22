package at.fhj.swd.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;

public class ActivityPostBean {

    private Date date;
    private String entry;
    private boolean ispublic = true;

    private User author;

    private IDataContext<Post> _pc;
    private IRuntimeContext _rc;

    public ActivityPostBean() {
        this._pc = Application.getInstance().getPostContext();
        this._rc = Application.getInstance().getRuntime();
    }

    public String addNow() {
        User _u = _rc.getCurrentUser();

        try {
            Post _new = new Post();
            _new.setAuthor(_u);
            _new.setDate(new Date());
            _new.setEntry(this.getEntry());
            // _new.setPublic(this.ispublic);

            if (_pc.create(_new)) {
                return "activityPost-added";
            } else {
                return "activityPostadding-failed";
            }
        } catch (Exception e) {
            return "activityPostadding-failed";
        }
    }

    public String getPostDate() {
        SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder formatedDate = new StringBuilder(dateformatDDMMYYYY.format(date));
        return formatedDate.toString();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
