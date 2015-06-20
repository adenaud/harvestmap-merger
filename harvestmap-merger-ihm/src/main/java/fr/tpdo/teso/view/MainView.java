package fr.tpdo.teso.view;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.themes.ValoTheme;
import fr.tpdo.teso.ReadException;
import fr.tpdo.teso.merger.MapMerger;
import fr.tpdo.teso.model.Node;
import fr.tpdo.teso.service.MergerService;
import fr.tpdo.teso.service.NodeService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@DesignRoot
public class MainView extends VerticalLayout implements View, Upload.Receiver, Upload.SucceededListener {

    public static final String NAV_STATE = "main";

    private Upload upload;
    private Table tableNodes;
    private Button downloadBtn;

    private Label desc1Label;
    private Label inst1Label;
    private Label inst2Label;

    private Label databaseCountLabel;

    @Autowired
    private MergerService mergerService;

    @Autowired
    private NodeService nodeService;

    private OutputStream outputStream;
    private String lua;

    public MainView(){
        Design.read(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        upload.setReceiver(this);
        upload.addSucceededListener(this);

        desc1Label.addStyleName(ValoTheme.LABEL_H2);
        inst1Label.addStyleName(ValoTheme.LABEL_H4);
        inst2Label.addStyleName(ValoTheme.LABEL_H4);

        List<Node> nodes = nodeService.getAllNodes();
        updateNodesTable(nodes);
        downloadBtn.setEnabled(false);

        upload.setButtonCaption("Envoyer");

    }

    @Override
    public OutputStream receiveUpload(String s, String s1) {
        outputStream =  new ByteArrayOutputStream();
        return outputStream;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent succeededEvent) {
        byte[] data = ((ByteArrayOutputStream)outputStream).toByteArray();
        lua = new String(data, Charset.forName("UTF-8"));
        List<Node> nodes;
        try {
            nodes = mergerService.getNodes(lua);
            int imported = nodeService.saveAll(nodes);


            prepareDownload();
            Notification.show(imported + " élément(s) importés");
        }catch (ReadException e)
        {
            Notification.show(e.getMessage(), Notification.Type.WARNING_MESSAGE);
        }
        nodes = nodeService.getAllNodes();
        updateNodesTable(nodes);

    }

    public void updateNodesTable(List<Node> nodes){

        databaseCountLabel.setValue(nodes.size() + " points dans la base de donnees.");

        BeanItemContainer<Node> container = new BeanItemContainer<>(Node.class,nodes);
        tableNodes.setContainerDataSource(container);
        tableNodes.setVisibleColumns("x","y","zone","description");
        tableNodes.setColumnHeaders("X","Y","Zone","Description");

    }

    private void prepareDownload(){
        final String generated = mergerService.merge(lua);

        StreamResource.StreamSource resource = new StreamResource.StreamSource() {

            @Override
            public InputStream getStream() {
                return new ByteArrayInputStream(generated.getBytes(Charset.forName("UTF-8")));
            }
        };

        FileDownloader fileDownloader = new FileDownloader(new StreamResource(resource,"HarvestMap.lua"));
        fileDownloader.extend(downloadBtn);
        downloadBtn.setEnabled(true);
    }
}
