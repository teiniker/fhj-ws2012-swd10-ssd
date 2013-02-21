package at.fhj.swd.selenium.test;

import junit.framework.Assert;

import org.junit.Test;

import at.fhj.swd.selenium.AbstractTest;
import at.fhj.swd.selenium.pages.Documents;

public class DocumentsTest extends AbstractTest {


    @Test
    public void uploadDokument() throws Exception {
        Documents documentsPage = super.page.clickDocuments();
        String documentName = "Desert.jpg";

        int documentsCountBefore = documentsPage.getDocumentsCount();

        String lastDocument = documentsPage.uploadDocument(documentName);

        int documentsCountAfter = documentsPage.getDocumentsCount();

        Assert.assertTrue(documentsCountAfter > documentsCountBefore);
        Assert.assertEquals(documentName, lastDocument);
    }

    @Test
    public void selectDokumentCommunity() throws Exception {
        Documents documentsPage = super.page.clickDocuments();
        int pos = 1;
        documentsPage.selectDocumentCommunity(pos);

        Assert.assertTrue(documentsPage.isDocumentCommunitySelected(pos));
    }

    @Test
    public void deleteDokument() throws Exception {
        Documents documentsPage = super.page.clickDocuments();
        int documentsCountBefore = documentsPage.getDocumentsCount();
        documentsPage.deleteDocument();
        int documentsCountAfter = documentsPage.getDocumentsCount();
        Assert.assertTrue(documentsCountAfter < documentsCountBefore);
    }
}
