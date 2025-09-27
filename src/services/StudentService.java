package services;

import models.Classroom;
import models.Student;
import utils.LoggerUtil;

public class StudentService {
    public void addStudentToClassroom(Classroom classroom, String studentId) {
        if (classroom == null) {
            LoggerUtil.error("Classroom does not exist.");
            return;
        }
        if (studentId == null || studentId.isEmpty()) {
            LoggerUtil.error("Student ID cannot be empty.");
            return;
        }
        Student student = new Student(studentId);
        classroom.addStudent(student);
        LoggerUtil.info("Student [" + studentId + "] has been enrolled in [" + classroom.getName() + "].");
    }

    public void listStudents(Classroom classroom) {
        if (classroom == null) {
            LoggerUtil.error("Classroom does not exist.");
            return;
        }
        LoggerUtil.info("Students in [" + classroom.getName() + "]:");
        if (classroom.getStudents().isEmpty()) {
            LoggerUtil.info("No students enrolled.");
        } else {
            classroom.getStudents().forEach(s -> System.out.println(s.getId()));
        }
    }
}
