package controller;

import domain.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.PersonInFileRepository;
import repository.PersonInMemoryRepository;

import java.util.List;

public class SalaryController {


    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public Worker.JobPosition getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(Worker.JobPosition jobPosition) {
        this.jobPosition = jobPosition;
    }

    public class Names {

        public String firstName;
        public String lastName;
        public double salary;

        Names(String firstName, String lastName, double salary) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.salary = salary;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }
    }

    private double experience;

    private Worker.JobPosition jobPosition;

    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @FXML
    public TableView<Names> salaryTableView;

    @FXML
    public TableColumn<Object, Object> firstNameColumn;

    @FXML
    public TableColumn<Object, Object> lastNameColumn;

    @FXML
    public TableColumn<Object, Object> salaryColumn;

    public PersonInFileRepository personInFileRepository = new PersonInFileRepository();


    public void updateTableView() {

        this.firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        List<Double> salaries = controller.calculateSalaryForAll(Integer.parseInt(controller.dateText.getText()));
        this.salaryTableView.getItems().clear();

        if (controller.salaryRadioButton.isSelected()) {

            if (controller.inMemoryButton.isSelected()) {
                for (int i = 0; i < salaries.size(); i++) {
                    this.salaryTableView.getItems().add(new Names(PersonInMemoryRepository.workerList.get(i).getFirstName(), PersonInMemoryRepository.workerList.get(i).getLastName(), salaries.get(i)));
                }
            } else if (controller.onDiskButton.isSelected()) {
                for (int i = 0; i < salaries.size(); i++) {
                    this.salaryTableView.getItems().add(new Names(personInFileRepository.findAll().get(i).getFirstName(), personInFileRepository.findAll().get(i).getLastName(), salaries.get(i)));
                }
            }
        }

        if (controller.jobPositionRadioButton.isSelected()) {


            if (controller.inMemoryButton.isSelected()) {
                for (int i = 0; i < salaries.size(); i++) {
                    if (PersonInMemoryRepository.workerList.get(i).getJobPosition().toString().equals(this.jobPosition.toString())) {
                        this.salaryTableView.getItems().add(new Names(PersonInMemoryRepository.workerList.get(i).getFirstName(), PersonInMemoryRepository.workerList.get(i).getLastName(), salaries.get(i)));
                    }
                }
            } else if (controller.onDiskButton.isSelected()) {
                for (int i = 0; i < salaries.size(); i++) {
                    if (personInFileRepository.findAll().get(i).getJobPosition().toString().equals(this.jobPosition.toString())) {
                        this.salaryTableView.getItems().add(new Names(personInFileRepository.findAll().get(i).getFirstName(), personInFileRepository.findAll().get(i).getLastName(), salaries.get(i)));
                    }
                }
            }
        }

        if (controller.experienceLevelRadioButton.isSelected()) {


            if (controller.inMemoryButton.isSelected()) {
                for (int i = 0; i < salaries.size(); i++) {
                    if (PersonInMemoryRepository.workerList.get(i).getExperienceLevel() == Double.valueOf(experience).intValue()) {
                        this.salaryTableView.getItems().add(new Names(PersonInMemoryRepository.workerList.get(i).getFirstName(), PersonInMemoryRepository.workerList.get(i).getLastName(), salaries.get(i)));
                    }
                }
            } else if (controller.onDiskButton.isSelected()) {
                for (int i = 0; i < salaries.size(); i++) {
                    if (personInFileRepository.findAll().get(i).getExperienceLevel() == Double.valueOf(experience).intValue()) {
                        this.salaryTableView.getItems().add(new Names(personInFileRepository.findAll().get(i).getFirstName(), personInFileRepository.findAll().get(i).getLastName(), salaries.get(i)));
                    }
                }
            }
        }
    }


}
