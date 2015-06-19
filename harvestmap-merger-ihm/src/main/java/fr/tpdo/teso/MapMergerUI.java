package fr.tpdo.teso;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import fr.tpdo.teso.view.HomeView;
import fr.tpdo.teso.view.MainView;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.annotation.WebServlet;

@Theme("valo")
public class MapMergerUI extends UI {

    private ApplicationContext context;
    private Navigator navigator;

    @WebServlet(value = "/*", asyncSupported = true)
    private static class MapMergerServlet extends VaadinServlet {
        private static final long serialVersionUID = 1L;
    }
    @Override
    protected void init(VaadinRequest request) {
        context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(VaadinServlet.getCurrent()
                        .getServletContext());

        getPage().setTitle("TESO HarvestMap Merger");

        navigator = new Navigator(this, this);

        registerView("", MainView.class);
        registerView(HomeView.NAV_STATE, HomeView.class);
    }

    private <VIEW> void registerView(String key, Class<VIEW> type) {
        VIEW view = context.getBean(type);
        navigator.addView(key, (View) view);
    }
}
