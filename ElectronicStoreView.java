
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;


public class ElectronicStoreView extends Pane{
    // define necessary attributes
    private ListView<Product>   pList;

    private ListView<Product> popList;
    private ListView<String> cList;

    private TextField s1Field, rField, s2Field;

    private Button reset, add, remove, comp;
    private double total;
    private Label label3;



    //update method to fill in model information
    public  void update(ElectronicStore model){
        // disabling certain buttons initially
        add.setDisable(true);
        remove.setDisable(true);
        comp.setDisable(true);

        // create lists to populate list area
        List<Product> pro = new ArrayList<Product>();
        List<Product> popPro = new ArrayList<Product>();
        List<String> curPro = new ArrayList<>();

        for(String s:  model.getCurCart().getViewCart()){
            if (s != null){
                curPro.add(s);
            }
        }


        // check if current cart as any items then complete sale button is allowed
        if (model.getCurCart().getCartInfo().isEmpty() == false){
            comp.setDisable(false);
        }

        // declaring totals value to update
        total = model.getCurCart().getTotal();
        if (total >= 0){
            label3.setText("Current Cart ($"+total+"):");
            label3.relocate(540, 20);
        }


        //populating lists
        for(Product p: model.getStock()){
            if(p != null && p.getStockQuantity() >0) {
                pro.add(p);
            }
        }


        // fill in popular list - needs to only show top 3 sold products
        if (model.getSoldCart().size() == 0){
            int i = 3;
            for(Product p: model.getStock()){
                if (i>0 && p != null){
                    popPro.add(p);
                }
                i --;
            }
        }else{
            for(Product p: model.calcPopular()){
                if (p != null){
                //if (p != null){
                    popPro.add(p);
                }
            }


        }


        // setting lists to observable lists
        ObservableList<Product> proOL = FXCollections.observableArrayList(pro);
        ObservableList<Product> popOL = FXCollections.observableArrayList(popPro);

        ObservableList<String> curOL = FXCollections.observableArrayList(curPro);
        cList.setItems(curOL);


        // final - setting view to model information
        pList.setItems(proOL);
        popList.setItems(popOL);


        // updating sales info
        s1Field.setText(model.getSales()+"");
        rField.setText(model.getRevenue()+"");
        s2Field.setText(model.getAverage()+"");






    }

    ElectronicStoreView(){

        // Create the labels
        Label label1 = new Label("Store Summary:");
        label1.relocate(20, 20);
        Label label2 = new Label("Stork Stock:");
        label2.relocate(300, 20);
        total = 0.0;
        label3 = new Label("Current Cart ($"+total+"):");
        label3.relocate(540, 20);

        Label label4 = new Label("Most Popular Items:");
        label4.relocate(20, 130);

        Label label5 = new Label("# Sales:");
        label5.relocate(20, 40);

        Label label6 = new Label("Revenue:");
        label6.relocate(20, 70);

        Label label7 = new Label("$ / Sale:");
        label7.relocate(20, 100);


        // Create the TextFields
        s1Field = new TextField();
        s1Field.relocate(90, 40);
        s1Field.setPrefSize(90,25);

        rField = new TextField();
        rField.relocate(90, 70);
        rField.setPrefSize(90,25);

        s2Field = new TextField();
        s2Field.relocate(90, 100);
        s2Field.setPrefSize(90,25);

        // default text-field info
        s1Field.setText("0");
        rField.setText("00");
        s2Field.setText("N/A");


        // create buttons
        reset = new Button("Reset Store");
        reset.relocate(45, 340);
        reset.setPrefSize(110, 20);

        add = new Button("Add to Cart");
        add.relocate(280, 340);
        add.setPrefSize(110, 20);

        remove = new Button("Remove Item");
        remove.relocate(485, 340);
        remove.setPrefSize(110, 20);

        comp = new Button("Complete Sale");
        comp.relocate(605, 340);
        comp.setPrefSize(110, 20);



        // Create the lists
        pList = new ListView<Product>();
        pList.setItems(FXCollections.observableArrayList());
        pList.relocate(200, 40);
        pList.setPrefSize(255, 290);

        popList = new ListView<Product>();
        popList.setItems(FXCollections.observableArrayList());
        popList.relocate(20, 150);
        popList.setPrefSize(160, 180);

        cList = new ListView<String>();
        cList.setItems(FXCollections.observableArrayList());
        cList.relocate(460, 40);
        cList.setPrefSize(255, 290);


        // add all elements
        getChildren().addAll(label1, label2, label3, label4, label5, label6, label7,s1Field, rField,
                s2Field, pList, popList, cList, reset, remove, add, comp);

        setPrefSize(800, 400);


    }

    public Button getReset(){return reset;}
    public Button getAdd(){return add;}
    public Button getRemove() {return remove;}
    public Button getComp(){return comp;}
    public ListView<Product> getpList() {return pList;}
    public ListView<Product> getPopList() {return popList;}
    public ListView<String>  getCList() {return cList;}
}
