package pages;

import util.SeleniumExecutor;
import util.enums.PagesType;


public class AbstractPage {

    private String url;

    public AbstractPage(String url) {
        this.url = url;
    }

    public AbstractPage() {
        SeleniumExecutor.getExecutor();
        this.url = SeleniumExecutor.pageDefaultUrl;
    }

    public void openPage(PagesType page) {
        String url;
        switch (page) {
            case Asta:
                url = SeleniumExecutor.pageDefaultUrl;
                break;

            default:
                url = this.url;
                break;
        }
        SeleniumExecutor.getExecutor().openPage(url);
    }

    public void openLoginPage(PagesType page) {
        openPage(page);
    }
}
