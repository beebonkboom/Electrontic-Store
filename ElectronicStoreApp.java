import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
public class ElectronicStoreApp extends Application {

    private ElectronicStore model;

    // set model to electronics model
    public ElectronicStoreApp(){ model = ElectronicStore.createStore();}

        public void start (Stage primaryStage) {
            Pane aPane = new Pane();

            // Create the view
            ElectronicStoreView  view = new ElectronicStoreView();

            // add view to pane
            aPane.getChildren().add(view);
            view.update(model);

            // if stock list is selected
            view.getpList().setOnMousePressed(new EventHandler<MouseEvent>()
            {
                public void handle(MouseEvent mouseEvent) {
                    // enable add cart button
                    if (view.getpList().getSelectionModel().getSelectedItem() != null){
                        view.getAdd().setDisable(false);
                    }


                    // if add button is clicked
                    view.getAdd().setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent actionEvent) {

                            // grab item
                            //int in = view.getpList().getSelectionModel().getSelectedIndex();
                            Product item = view.getpList().getSelectionModel().getSelectedItem();
                            // add item to current cart
                            model.getCurCart().addCart(item);

                            // decrease product quantity - not real sale
                            model.sellProduct(item);

                            // update model takes care of cart total display
                            view.update(model);
                        }
                    });


                }

            });

            //if stock list is selected
            view.getCList().setOnMousePressed(new EventHandler<MouseEvent>()
            {
                public void handle(MouseEvent mouseEvent) {
                    // enable remove cart button
                    if (view.getCList().getSelectionModel().getSelectedItem() != null){
                        view.getRemove().setDisable(false);
                    }


                    view.getRemove().setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent actionEvent) {
                            // grab item
                            String item = view.getCList().getSelectionModel().getSelectedItem();
                            Product remove = model.searchProducts(item);

                            // remove item to current cart
                            model.getCurCart().removeCart(remove);

                            // increase product stock quantity
                            // decrease product stock soldQuantity
                            model.addProduct(remove);

                            // update model takes care of cart total display
                            view.update(model);


                        }
                    });
                }
            });

            // complete sale

            view.getComp().setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent actionEvent) {

                    // adjust sold quantity property
                    model.sellCart();

                    // update model information accordingly
                    model.updateRevenue(model.getCurCart().getTotal());
                    model.updateSale(1);
                    model.updateAverage(model.getRevenue(), model.getSales());

                    model.setCurCart(new Cart());


                    // update model takes care of cart total display
                    view.update(model);
                }
            });


            // rest store
            view.getReset().setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent actionEvent) {
                    model = ElectronicStore.createStore();
                    view.update(model);
                }
            });




            view.update(model);



            // set title and resizability
            primaryStage.setTitle("Electronic Store Application Watts Up Electronics");
            primaryStage.setResizable(false);
            primaryStage.setScene(new Scene(aPane));
            primaryStage.show();
        }
    public static void main(String[] args) {
        launch(args);
    }

}
