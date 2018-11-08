package repository;

import domain.Worker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonInFileRepository implements CrudRepository<Worker>{

    public static List<Worker> workerList = new ArrayList<>();

    public void readFromFile(){
        File file= new File("D:\\MAP\\Laborator\\Lab3_UI\\src\\persons.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            workerList.clear();
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArray=line.split(",");
                long id=Long.parseLong(lineArray[0]);
                String firstName=lineArray[1];
                String lastName=lineArray[2];
                String address=lineArray[3];
                int experienceLevel=Integer.parseInt(lineArray[4]);
                int hoursPerDay=Integer.parseInt(lineArray[5]);
                double salaryPerHour=Double.parseDouble(lineArray[6]);
                Worker.JobPosition jobPosition=Worker.JobPosition.valueOf(lineArray[7]);
                Worker worker = new Worker(id,firstName,lastName,address,experienceLevel,hoursPerDay,salaryPerHour,jobPosition);

                workerList.add(worker);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(){
        File file =new File("D:\\MAP\\Laborator\\Lab3_UI\\src\\persons.txt");
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,false))) {
            for(Worker w:workerList){
                String id=Long.toString(w.getID());
                String firstName=w.getFirstName();
                String lastName=w.getLastName();
                String address=w.getAddress();
                String experienceLevel=Integer.toString(w.getExperienceLevel());
                String hoursPerDay=Integer.toString(w.getHoursPerDay());
                String salaryPerHour=Double.toString(w.getSalaryPerHour());
                String jobPosition=w.getJobPosition().toString();
                String line=id+","+firstName+","+lastName+","+address+","+experienceLevel+","+hoursPerDay+","+salaryPerHour+","+jobPosition+"\n";
                bufferedWriter.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Worker findOne(Long id) {
        readFromFile();
        for (Worker i : workerList) {
            if (i.getID() == id)
                return i;
        }
        return null;
    }

    @Override
    public List<Worker> findAll() {
        readFromFile();
        return workerList;
    }

    @Override
    public Worker save(Worker worker) {
        readFromFile();
        for (Worker i : workerList) {
            if (worker.getID() == i.getID())
                return worker;
        }
        workerList.add(worker);
        writeToFile();
        return null;
    }

    @Override
    public Worker delete(Long id) {
        readFromFile();
        for (Worker i : workerList) {
            if (id == i.getID()) {
                workerList.remove(i);
                writeToFile();
                return i;
            }
        }
        return null;
    }

    @Override
    public Worker update(Worker worker) {
        readFromFile();
        for (Worker i : workerList) {
            if (i.getID() == worker.getID()) {
                this.delete(i.getID());
                this.save(worker);
                return null;
            }
        }
        writeToFile();
        return worker;
    }
}
