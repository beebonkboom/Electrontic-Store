# Electronics Store - GUI

## Overview
A JavaFX-based GUI for an Electronics Store using the MVC paradigm. Users can manage inventory, add items to a cart, complete sales, and track store performance.

## Setup

1. Download and extract **JavaFX SDK** from [GluonHQ](https://gluonhq.com/products/javafx/).
2. Configure JavaFX in IntelliJ:
   - Add the **JavaFX lib** folder to project dependencies.
   - Set VM options in **Run Configurations**:
     ```sh
     --module-path "path-to-javafx/lib" --add-modules javafx.controls,javafx.fxml
     ```
3. Run `ElectronicStoreApp.java`.

## Usage

- **Add to Cart**: Select an item and click "Add to Cart."
- **Remove from Cart**: Select an item and click "Remove."
- **Complete Sale**: Click "Complete Sale" to finalize transactions.
- **Reset Store**: Restore initial state.
