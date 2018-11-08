/*package test;

import domain.Person;
import domain.Worker;
import repository.PersonInFileRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonInFileRepositoryTest {

    @org.junit.jupiter.api.Test
    void readFromFile() {
        PersonInFileRepository.workerList.clear();
        PersonInFileRepository personInFileRepository = new PersonInFileRepository();
        personInFileRepository.readFromFile();
        assertNotNull(PersonInFileRepository.workerList.get(0));
    }

    @org.junit.jupiter.api.Test
  /*  void writeToFile() {
        PersonInFileRepository.workerList.clear();
        PersonInFileRepository personInFileRepository = new PersonInFileRepository();
        personInFileRepository.readFromFile();
        List<Worker> backup = PersonInFileRepository.workerList;
        PersonInFileRepository.workerList.clear();
        writeToFile();
        readFromFile();
        assertEquals(null,PersonInFileRepository.workerList.get(0));
        PersonInFileRepository.workerList=backup;
        writeToFile();
    }*/
/*
    @org.junit.jupiter.api.Test
    void findOne() {
        PersonInFileRepository.workerList.clear();
        readFromFile();

    }

    @org.junit.jupiter.api.Test
    void findAll() {
        PersonInFileRepository.workerList.clear();
    }

    @org.junit.jupiter.api.Test
    void save() {
        PersonInFileRepository.workerList.clear();
    }

    @org.junit.jupiter.api.Test
    void delete() {
        PersonInFileRepository.workerList.clear();
    }

    @org.junit.jupiter.api.Test
    void update() {
        PersonInFileRepository.workerList.clear();
    }
}*/