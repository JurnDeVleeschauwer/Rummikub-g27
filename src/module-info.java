module Rummikub {
	exports main;
	
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens gui to javafx.graphics,javafx.fxml; 
	opens cui to javafx.graphics,javafx.fxml; 
	opens domein to javafx.base;
}