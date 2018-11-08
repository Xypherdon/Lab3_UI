package repository;

import domain.Worker;

import java.util.ArrayList;
import java.util.List;

public class PersonInMemoryRepository implements CrudRepository<Worker> {

    public static List<Worker> workerList = new ArrayList<>();

    @Override
    public Worker findOne(Long id) {
        for (Worker i : workerList) {
            if (i.getID() == id)
                return i;
        }
        return null;
    }

    @Override
    public List<Worker> findAll() {
        return workerList;
    }

    @Override
    public Worker save(Worker worker) {
        for (Worker i : workerList) {
            if (worker.getID() == i.getID())
                return worker;
        }
        workerList.add(worker);
        return null;
    }

    @Override
    public Worker delete(Long id) {
        for (Worker i : workerList) {
            if (id == i.getID()) {
                workerList.remove(i);
                return i;
            }
        }
        return null;
    }

    @Override
    public Worker update(Worker worker) {
        for (Worker i : workerList) {
            if (i.getID() == worker.getID()) {
                this.delete(i.getID());
                this.save(worker);
                return null;
            }
        }
        return worker;
    }
}


