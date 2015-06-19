package fr.tpdo.teso.view;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Anthony on 19/06/2015.
 */
@Component
@Scope("prototype")
@DesignRoot
public class HomeView extends VerticalLayout implements View {

    public static final String NAV_STATE = "home";

    public HomeView(){
        Design.read(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
