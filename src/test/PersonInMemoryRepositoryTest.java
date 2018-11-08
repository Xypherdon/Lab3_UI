package test;

import domain.Person;
import domain.Worker;
import repository.PersonInMemoryRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonInMemoryRepositoryTest {

    PersonInMemoryRepository personInMemoryRepository = new PersonInMemoryRepository();

    public Worker workerFactory(){
        return new Worker(1,"Andrei","Andrei","acasa la andrei",3,4,5.6, Worker.JobPosition.TeamLeader);
    }

    public Worker worker2Factory(){
        return new Worker(2,"Mihai","Mihai","acasa la Mihai",4,2,5.4, Worker.JobPosition.Developer);
    }

    public List<Worker> workersFactory(){
        List<Worker> workers = new ArrayList<>();
        workers.add(workerFactory());
        workers.add(worker2Factory());
        return workers;
    }

    @org.junit.jupiter.api.Test
    void findOne() {
        PersonInMemoryRepository.workerList.clear();
        List<Worker> workers = workersFactory();
        PersonInMemoryRepository.workerList.add(workers.get(0));
        PersonInMemoryRepository.workerList.add(workers.get(1));
        assertEquals(workers.get(0),personInMemoryRepository.findOne(workers.get(0).getID()));
    }

    @org.junit.jupiter.api.Test
    void findAll() {
        PersonInMemoryRepository.workerList.clear();
        List<Worker> workers = workersFactory();
        PersonInMemoryRepository.workerList.add(workers.get(0));
        PersonInMemoryRepository.workerList.add(workers.get(1));
        assertEquals(workers,PersonInMemoryRepository.workerList);
    }

    @org.junit.jupiter.api.Test
    void save() {
        PersonInMemoryRepository.workerList.clear();
        List<Worker> workers = workersFactory();
        personInMemoryRepository.save(workers.get(0));
        assertEquals(workers.get(0), PersonInMemoryRepository.workerList.get(0));
    }

    @org.junit.jupiter.api.Test
    void delete() {
        PersonInMemoryRepository.workerList.clear();
        List<Worker> workers = workersFactory();
        PersonInMemoryRepository.workerList.add(workers.get(0));
        PersonInMemoryRepository.workerList.add(workers.get(1));
        personInMemoryRepository.delete(workers.get(0).getID());
        assertNotEquals(workers.get(0), PersonInMemoryRepository.workerList.get(0));
    }

    @org.junit.jupiter.api.Test
    void update() {
        PersonInMemoryRepository.workerList.clear();
        List<Worker> workers = workersFactory();
        PersonInMemoryRepository.workerList.add(workers.get(0));
        Worker worker=workerFactory();
        worker.setFirstName("altceva");
        personInMemoryRepository.update(worker);
        assertEquals(worker, PersonInMemoryRepository.workerList.get(0));
    }
}