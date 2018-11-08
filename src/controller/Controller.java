package controller;

import domain.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import repository.PersonInFileRepository;
import repository.PersonInMemoryRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public PersonInFileRepository personInFileRepository = new PersonInFileRepository();

    @FXML
    public RadioButton inMemoryButton = new RadioButton();

    @FXML
    public RadioButton onDiskButton = new RadioButton();

    @FXML
    public ComboBox<String> comboBox = new ComboBox<>();

    @FXML
    public TextField idText = new TextField();

    @FXML
    public TextField firstNameText;

    @FXML
    public TextField lastNameText;

    @FXML
    public TextField addressText;

    @FXML
    public Slider experienceLevelSlider;

    @FXML
    public TextField hoursPerDayText;

    @FXML
    public TextField salaryPerHourText;

    @FXML
    public Button submitButton;

    @FXML
    public TableView<Worker> tableView;

    @FXML
    public Label experienceLevelLabel;

    @FXML
    public TableColumn<Object, Object> idColumn;

    @FXML
    public TableColumn<Object, Object> firstNameColumn;

    @FXML
    public TableColumn<Object, Object> lastNameColumn;

    @FXML
    public TableColumn<Object, Object> addressColumn;

    @FXML
    public TableColumn<Object, Object> experienceLevelColumn;

    @FXML
    public TableColumn<Object, Object> hoursPerDayColumn;

    @FXML
    public TableColumn<Object, Object> salaryPerHourColumn;

    @FXML
    public TableColumn<Object, Object> jobPositionColumn;

    @FXML
    public Button deleteButton;

    @FXML
    public Label updateLabel;

    @FXML
    public ComboBox<String> jobPositionComboBox;

    @FXML
    public Button calculateSalaryButton;

    @FXML
    public RadioButton salaryRadioButton;

    @FXML
    public TextField salaryText;

    @FXML
    public TextField dateText;

    @FXML
    public ComboBox jobPositionComboBox2;

    @FXML
    public Slider experienceSlider2;
    public RadioButton jobPositionRadioButton;
    public RadioButton experienceLevelRadioButton;


    @FXML
    public void selectInMemoryButton(Event e) {
        inMemoryButton.setSelected(true);
        onDiskButton.setSelected(false);
        updateTableView();
    }

    @FXML
    public void selectOnDiskButton(Event e) {
        inMemoryButton.setSelected(false);
        onDiskButton.setSelected(true);
        updateTableView();
    }

    @FXML
    public void comboBoxClicked(Event e) {

        switch (comboBox.getValue()) {
            case "Add worker":
                idText.setVisible(true);
                firstNameText.setVisible(true);
                lastNameText.setVisible(true);
                addressText.setVisible(true);
                experienceLevelSlider.setVisible(true);
                hoursPerDayText.setVisible(true);
                salaryPerHourText.setVisible(true);
                submitButton.setVisible(true);
                tableView.setVisible(true);
                experienceLevelLabel.setVisible(true);
                jobPositionComboBox.setVisible(true);
                updateLabel.setVisible(false);
                firstNameText.clear();
                lastNameText.clear();
                addressText.clear();
                experienceLevelSlider.setValue(1);
                hoursPerDayText.clear();
                salaryPerHourText.clear();


                break;
            case "Find a worker":
                idText.setVisible(true);
                firstNameText.setVisible(false);
                lastNameText.setVisible(false);
                addressText.setVisible(false);
                experienceLevelSlider.setVisible(false);
                hoursPerDayText.setVisible(false);
                salaryPerHourText.setVisible(false);
                submitButton.setVisible(true);
                tableView.setVisible(true);
                experienceLevelLabel.setVisible(false);
                updateLabel.setVisible(false);

                break;
            case "Update worker":
                idText.setVisible(false);
                firstNameText.setVisible(true);
                lastNameText.setVisible(true);
                addressText.setVisible(true);
                experienceLevelSlider.setVisible(true);
                hoursPerDayText.setVisible(true);
                salaryPerHourText.setVisible(true);
                jobPositionComboBox.setVisible(true);
                submitButton.setVisible(true);
                tableView.setVisible(true);
                experienceLevelLabel.setVisible(true);
                updateLabel.setVisible(true);

                break;
        }
    }

    public void updateTableView() {
        tableView.getItems().clear();

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        experienceLevelColumn.setCellValueFactory(new PropertyValueFactory<>("experienceLevel"));
        hoursPerDayColumn.setCellValueFactory(new PropertyValueFactory<>("hoursPerDay"));
        salaryPerHourColumn.setCellValueFactory(new PropertyValueFactory<>("salaryPerHour"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        jobPositionColumn.setCellValueFactory(new PropertyValueFactory<>("jobPosition"));

        if (inMemoryButton.isSelected()) {
            for (Worker w : PersonInMemoryRepository.workerList) {
                tableView.getItems().add(w);
            }
        } else if (onDiskButton.isSelected()) {
            for (Worker w : personInFileRepository.findAll()) {
                tableView.getItems().add(w);
            }
        }
    }

    public void submitButtonClicked(Event e) {

        switch (comboBox.getValue()) {
            case "Add worker": {
                long id = Long.parseLong(idText.getText());
                String firstName = firstNameText.getText();
                String lastName = lastNameText.getText();
                String address = addressText.getText();
                Double expSlider = experienceLevelSlider.getValue();
                int experienceLevel = expSlider.intValue();
                int hoursPerDay = Integer.parseInt(hoursPerDayText.getText());
                double salaryPerHour = Double.parseDouble(salaryPerHourText.getText());
                Worker.JobPosition jobPosition = Worker.JobPosition.valueOf(jobPositionComboBox.getValue());
                if (inMemoryButton.isSelected()) {
                    PersonInMemoryRepository personInMemoryRepository = new PersonInMemoryRepository();
                    personInMemoryRepository.save(new Worker(id, firstName, lastName, address, experienceLevel, hoursPerDay, salaryPerHour, jobPosition));
                    updateTableView();
                } else if (onDiskButton.isSelected()) {
                    PersonInFileRepository personInFileRepository = new PersonInFileRepository();
                    personInFileRepository.save(new Worker(id, firstName, lastName, address, experienceLevel, hoursPerDay, salaryPerHour, jobPosition));
                    updateTableView();
                }

                break;
            }
            case "Update worker": {

                long id = tableView.getSelectionModel().getSelectedItem().getID();
                String firstName = firstNameText.getText();
                String lastName = lastNameText.getText();
                String address = addressText.getText();
                Double expSlider = experienceLevelSlider.getValue();
                int experienceLevel = expSlider.intValue();
                int hoursPerDay = Integer.parseInt(hoursPerDayText.getText());
                double salaryPerHour = Double.parseDouble(salaryPerHourText.getText());
                Worker.JobPosition jobPosition = Worker.JobPosition.valueOf(jobPositionComboBox.getValue());
                if (inMemoryButton.isSelected()) {
                    PersonInMemoryRepository personInMemoryRepository = new PersonInMemoryRepository();
                    personInMemoryRepository.update(new Worker(id, firstName, lastName, address, experienceLevel, hoursPerDay, salaryPerHour, jobPosition));
                    updateTableView();
                } else if (onDiskButton.isSelected()) {
                    PersonInFileRepository personInFileRepository = new PersonInFileRepository();
                    personInFileRepository.update(new Worker(id, firstName, lastName, address, experienceLevel, hoursPerDay, salaryPerHour, jobPosition));
                    updateTableView();
                }
                break;
            }
            case "Find a worker": {
                long id = Long.parseLong(idText.getText());
                for (int i = 0; i < tableView.getItems().size(); i++) {
                    if (tableView.getItems().get(i).getID() == id) {
                        tableView.getSelectionModel().select(i);
                    }
                }
                break;
            }
        }
    }

    public void deleteButtonClicked(Event e) {
        salaryText.setDisable(false);
        submitButton.setText("Delete");
        Worker w = tableView.getSelectionModel().getSelectedItem();
        if (inMemoryButton.isSelected()) {
            PersonInMemoryRepository personInMemoryRepository = new PersonInMemoryRepository();
            personInMemoryRepository.delete(w.getID());
            updateTableView();
        } else if (onDiskButton.isSelected()) {
            PersonInFileRepository personInFileRepository = new PersonInFileRepository();
            personInFileRepository.delete(w.getID());
            updateTableView();
        }
    }

    public void tableViewClicked(MouseEvent e) {
        salaryText.setDisable(true);
        comboBox.setValue("Update worker");
        comboBoxClicked(e);
        firstNameText.setText(tableView.getSelectionModel().getSelectedItem().getFirstName());
        lastNameText.setText(tableView.getSelectionModel().getSelectedItem().getLastName());
        addressText.setText(tableView.getSelectionModel().getSelectedItem().getAddress());
        experienceLevelSlider.setValue(tableView.getSelectionModel().getSelectedItem().getExperienceLevel());
        hoursPerDayText.setText(Integer.toString(tableView.getSelectionModel().getSelectedItem().getHoursPerDay()));
        salaryPerHourText.setText(Double.toString(tableView.getSelectionModel().getSelectedItem().getSalaryPerHour()));
        jobPositionComboBox.setValue(tableView.getSelectionModel().getSelectedItem().getJobPosition().toString());

    }

    public double calculateSalary(Worker worker, Integer numberOfDays) {
        return worker.getHoursPerDay() * worker.getSalaryPerHour() * numberOfDays;
    }

    public List<Double> calculateSalaryForAll(int numberOfDays) {
        List<Double> salaryForAll = new ArrayList<>();
        if (inMemoryButton.isSelected()) {
            for (Worker worker : PersonInMemoryRepository.workerList) {
                salaryForAll.add(calculateSalary(worker, numberOfDays));
            }
        } else if (onDiskButton.isSelected()) {
            for (Worker worker : personInFileRepository.findAll()) {
                salaryForAll.add(calculateSalary(worker, numberOfDays));
            }
        }
        return salaryForAll;
    }

    public void calculateSalaryButtonClicked(Event e) {
        salaryText.setDisable(false);
        if(dateText.getText().equals("")){
            salaryText.setText("Please type in number of days!");
            return;
        }

        if(!dateText.getText().equals("")){
            salaryText.clear();
        }
        if (!salaryRadioButton.isSelected() && !experienceLevelRadioButton.isSelected() && !jobPositionRadioButton.isSelected()) {
            salaryText.setText(tableView.getSelectionModel().getSelectedItem().getFirstName() +
                    " " +
                    tableView.getSelectionModel().getSelectedItem().getLastName() +
                    ": " +
                    Double.toString(calculateSalary(tableView.getSelectionModel().getSelectedItem(), Integer.parseInt(dateText.getText()))));
        } else{
            if (salaryRadioButton.isSelected()) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("salaries.fxml"));

                Parent root = null;
                try {
                    root = (Parent) fxmlLoader.load();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                SalaryController salaryController = fxmlLoader.<SalaryController>getController();
                Scene scene = new Scene(root, 300, 460);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Salaries");
                stage.show();
                salaryController.setController(this);
                salaryController.updateTableView();
            }else
            if(experienceLevelRadioButton.isSelected()){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("salaries.fxml"));

                Parent root = null;
                try {
                    root = (Parent) fxmlLoader.load();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                SalaryController salaryController = fxmlLoader.<SalaryController>getController();
                Scene scene = new Scene(root, 300, 460);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Experience level: " + Double.toString(experienceSlider2.getValue()));
                stage.show();
                salaryController.setController(this);
                salaryController.setExperience(experienceSlider2.getValue());
                salaryController.updateTableView();

            }else
            if (jobPositionRadioButton.isSelected()){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("salaries.fxml"));

                Parent root = null;
                try {
                    root = (Parent) fxmlLoader.load();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                SalaryController salaryController = fxmlLoader.<SalaryController>getController();
                Scene scene = new Scene(root, 300, 460);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle(jobPositionComboBox2.getValue().toString());
                stage.show();
                salaryController.setController(this);
                salaryController.setJobPosition(Worker.JobPosition.valueOf(jobPositionComboBox2.getValue().toString()));
                salaryController.updateTableView();

            }
        }
    }

    public void salaryRadioButtonClicked(ActionEvent actionEvent) {
        salaryRadioButton.setSelected(true);
        jobPositionRadioButton.setSelected(false);
        experienceLevelRadioButton.setSelected(false);
    }

    public void jobPositionRadioButtonClicked(ActionEvent actionEvent) {
        jobPositionRadioButton.setSelected(true);
        salaryRadioButton.setSelected(false);
        experienceLevelRadioButton.setSelected(false);
    }

    public void experienceLevelRadioButtonClicked(ActionEvent actionEvent) {
        experienceLevelRadioButton.setSelected(true);
        jobPositionRadioButton.setSelected(false);
        salaryRadioButton.setSelected(false);
    }
}
