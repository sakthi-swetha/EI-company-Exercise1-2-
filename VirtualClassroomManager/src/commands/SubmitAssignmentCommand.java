package commands;

import models.Classroom;
import services.AssignmentService;
import services.ClassroomService;
import utils.LoggerUtil;

public class SubmitAssignmentCommand implements Command {
    private final ClassroomService classroomService;
    private final AssignmentService assignmentService;

    public SubmitAssignmentCommand(ClassroomService classroomService, AssignmentService assignmentService) {
        this.classroomService = classroomService;
        this.assignmentService = assignmentService;
    }

    @Override
    public void execute(String[] params) {
        // Check we have exactly 3 parameters and none is empty
        if (params.length < 3 
                || params[0].trim().isEmpty() 
                || params[1].trim().isEmpty() 
                || params[2].trim().isEmpty()) {
            LoggerUtil.error("Usage: submit_assignment <student_id> <class_name> <assignment_details>");
            return;
        }

        String studentId = params[0].trim();
        String className = params[1].trim();
        String assignmentDetails = params[2].trim();

        Classroom classroom = classroomService.getClassroom(className);
        if (classroom == null) {
            LoggerUtil.error("Classroom [" + className + "] does not exist.");
            return;
        }

        // Submit assignment
        assignmentService.submitAssignment(classroom, studentId, assignmentDetails);
    }
}
