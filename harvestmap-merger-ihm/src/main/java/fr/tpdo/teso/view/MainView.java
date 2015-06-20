package fr.tpdo.teso.view;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;
import fr.tpdo.teso.model.Node;
import fr.tpdo.teso.service.MergerService;
import fr.tpdo.teso.service.NodeService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Anthony on 19/06/2015.
 */
@Component
@Scope("prototype")
@DesignRoot
public class MainView extends VerticalLayout implements View, Upload.Receiver, Upload.SucceededListener {

    public static final String NAV_STATE = "main";

    private Button button;
    private Upload upload;
    private Table tableNodes;

    @Autowired
    private MergerService mergerService;

    @Autowired
    private NodeService nodeService;

    private OutputStream outputStream;

    public MainView(){
        Design.read(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        upload.setReceiver(this);
        upload.addSucceededListener(this);
    }

    @Override
    public OutputStream receiveUpload(String s, String s1) {
        outputStream =  new ByteArrayOutputStream();
        return outputStream;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent succeededEvent) {
        byte[] data = ((ByteArrayOutputStream)outputStream).toByteArray();
        String lua = new String(data, Charset.forName("UTF-8"));
        List<Node> nodes = mergerService.getNodes(lua);
        updateNodesTable(nodes);
    }

    public void updateNodesTable(List<Node> nodes){
        BeanItemContainer<Node> container = new BeanItemContainer<>(Node.class,nodes);
        tableNodes.setContainerDataSource(container);
        tableNodes.setVisibleColumns("x","y","zone","description");
        tableNodes.setColumnHeaders("X","Y","Zone","Description");
    }
}
