package commands;

import models.Classroom;
import services.AssignmentService;
import services.ClassroomService;
import utils.LoggerUtil;

public class ScheduleAssignmentCommand implements Command {
    private final ClassroomService classroomService;
    private final AssignmentService assignmentService;

    public ScheduleAssignmentCommand(ClassroomService classroomService, AssignmentService assignmentService) {
        this.classroomService = classroomService;
        this.assignmentService = assignmentService;
    }

    @Override
    public void execute(String[] params) {
        if (params.length < 2 || params[0].trim().isEmpty() || params[1].trim().isEmpty()) {
            LoggerUtil.error("Usage: schedule_assignment <class_name> <assignment_details>");
            return;
        }

        String className = params[0].trim();
        String assignmentDetails = params[1].trim(); // take details as is

        Classroom classroom = classroomService.getClassroom(className);
        if (classroom == null) {
            LoggerUtil.error("Classroom [" + className + "] does not exist.");
            return;
        }

        assignmentService.scheduleAssignment(classroom, assignmentDetails);
    }
}
