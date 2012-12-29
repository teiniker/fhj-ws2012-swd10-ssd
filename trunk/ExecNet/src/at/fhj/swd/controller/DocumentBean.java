package at.fhj.swd.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;
import org.richfaces.component.UIExtendedDataTable;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import at.fhj.swd.business.CommunityBO;
import at.fhj.swd.business.DocumentBO;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Document;

public class DocumentBean implements Serializable {

    private static final long serialVersionUID = -3512126679665410531L;
    private Date _datecreated;
    private String _name;
    private String _url;
    private boolean _ispublic = true;

    private List<Community> _communities;
    private Collection<Object> selection;
    private List<Community> selectionItems = new ArrayList<Community>();

    private CommunityBO _cbo;
    private DocumentBO _dbo;

    protected static final Logger logger = Logger.getLogger(DocumentBean.class.getName());

    public DocumentBean() {
        this._cbo = new CommunityBO();
        this._dbo = new DocumentBO();
        this._communities = new ArrayList<Community>(_cbo.getMy());
    }

    public Collection<Document> getAll() {
        return _dbo.getAll();
    }

    public Date getDateCreated() {
        return _datecreated;
    }

    public void setDateCreated(Date _datecreated) {
        this._datecreated = _datecreated;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        this._url = url;
    }

    public boolean isPublic() {
        return _ispublic;
    }

    public Boolean delete(Long id) {
        return _dbo.remove(id);

    }

    public void setPublic(boolean ispublic) {
        this._ispublic = ispublic;
    }

    public List<Community> getSelectionItems() {
        return selectionItems;
    }

    public void setSelectionItems(List<Community> selectionItems) {
        this.selectionItems = selectionItems;
    }

    public List<Community> getCommunities() {
        return _communities;
    }

    public void setCommunities(List<Community> communities) {
        this._communities = communities;
    }


    public void selectionListener(AjaxBehaviorEvent event) {
        UIExtendedDataTable dataTable = (UIExtendedDataTable)event.getComponent();
        Object originalKey = dataTable.getRowKey();
        selectionItems.clear();
        for (Object selectionKey : selection) {
            dataTable.setRowKey(selectionKey);
            if (dataTable.isRowAvailable()) {
                selectionItems.add((Community)dataTable.getRowData());
            }
        }
        dataTable.setRowKey(originalKey);
    }

    public Collection<Object> getSelection() {
        return selection;
    }

    public void setSelection(Collection<Object> selection) {
        this.selection = selection;
    }

    public Community getSelectionItem() {
        if (selectionItems == null || selectionItems.isEmpty()) {
            return null;
        }
        return selectionItems.get(0);
    }


    public String download(Document doc) { // called from a h:commandLink
        _dbo.download(doc);
        return "REFRESH";
    }

    public void upload(FileUploadEvent event) {

        UploadedFile _file = event.getUploadedFile();
        _dbo.upload(_file, selectionItems);
    }


}
