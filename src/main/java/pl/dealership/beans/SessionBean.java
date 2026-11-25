package pl.dealership.beans;

import java.io.Serializable;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

    @Inject
    private AppConfigBean appConfigBean;

    private Locale locale;
    private int userActionCount = 0;

    public void switchLanguage(String langCode) {
        locale = new Locale(langCode);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        registerAction();
    }

    public void registerAction() {
        userActionCount++;
        appConfigBean.incrementViews();
    }

    public String getDealershipSummary() {
        return appConfigBean.getDealershipName() + " (1 EUR = " + appConfigBean.getEuroRate() + " PLN)";
    }

    public Locale getLocale() {
        if (locale != null) {
            return locale;
        }
        return FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    public int getUserActionCount() { return userActionCount; }
    public AppConfigBean getAppConfigBean() { return appConfigBean; }
}