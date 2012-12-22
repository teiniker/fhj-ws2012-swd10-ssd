package at.fhj.swd.business;


import java.util.Collection;

import at.fhj.swd.application.Application;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.News;


public class NewsBO extends ABusinessObject {
    

    private IDataContext<News> _context;
   
    public NewsBO(){
        super();
        
        this._context = Application.getInstance().getNewsContext();
    }
    
    public Collection<News> getAll() {
        try {
            
            return _context.readAll(News.class);
            
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }
    

    public boolean create(News news) {

        try {
            
            if (_context.create(news)) {
                
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    

   
    
   
    
 
    


}
