package services;

import models.Classroom;
import utils.LoggerUtil;

import java.util.HashMap;
import java.util.Map;

public class ClassroomService {
    private Map<String, Classroom> classrooms = new HashMap<>();

    public void addClassroom(String name) {
        if (classrooms.containsKey(name)) {
            LoggerUtil.error("Classroom [" + name + "] already exists.");
            return;
        }
        classrooms.put(name, new Classroom(name));
        System.out.println("Classroom [" + name + "] has been created."); // Exact format
    }

    public void removeClassroom(String name) {
        if (classrooms.remove(name) != null) {
            System.out.println("Classroom [" + name + "] has been removed.");
        } else {
            LoggerUtil.error("Classroom [" + name + "] does not exist.");
        }
    }

    public Classroom getClassroom(String name) {
        if (!classrooms.containsKey(name)) {
            LoggerUtil.error("Classroom [" + name + "] not found.");
            return null;
        }
        return classrooms.get(name);
    }

    public void listClassrooms() {
        System.out.println("All Classrooms:");
        if (classrooms.isEmpty()) System.out.println("No classrooms available.");
        classrooms.keySet().forEach(System.out::println);
    }
}
