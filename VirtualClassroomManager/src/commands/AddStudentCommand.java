package commands;

import models.Classroom;
import services.ClassroomService;
import services.StudentService;
import utils.LoggerUtil;

public class AddStudentCommand implements Command {
    private final ClassroomService classroomService;
    private final StudentService studentService;

    public AddStudentCommand(ClassroomService classroomService, StudentService studentService) {
        this.classroomService = classroomService;
        this.studentService = studentService;
    }

    @Override
    public void execute(String[] params) {
        if (params.length < 2 || params[0].trim().isEmpty() || params[1].trim().isEmpty()) {
            LoggerUtil.error("Usage: add_student <student_id> <class_name>");
            return;
        }

        String studentId = params[0].trim();
        String className = params[1].trim();

        Classroom classroom = classroomService.getClassroom(className);
        if (classroom == null) {
            LoggerUtil.error("Classroom [" + className + "] does not exist.");
            return;
        }

        studentService.addStudentToClassroom(classroom, studentId);
    }
}
