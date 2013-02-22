package at.fhj.swd.selenium;

import at.fhj.swd.selenium.pages.Admin;
import at.fhj.swd.selenium.pages.Documents;
import at.fhj.swd.selenium.pages.Groups;
import at.fhj.swd.selenium.pages.MySite;
import at.fhj.swd.selenium.pages.News;
import at.fhj.swd.selenium.pages.Settings;
import at.fhj.swd.selenium.pages.User;


public interface IPage {

    public abstract MySite clickMySide();

    public abstract User clickUser();

    public abstract Groups clickGroups();

    public abstract Documents clickDocuments();

    public abstract Settings clickSettings();

    public abstract Admin clickAdmin();
    
    public abstract News clickNews();

}
