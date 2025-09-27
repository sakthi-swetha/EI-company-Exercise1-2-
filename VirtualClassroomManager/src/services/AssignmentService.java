package services;

import models.Assignment;
import models.Classroom;
import models.Student;
import utils.LoggerUtil;

public class AssignmentService {

    public void scheduleAssignment(Classroom classroom, String details) {
        if (classroom == null) {
            LoggerUtil.error("Classroom does not exist.");
            return;
        }
        if (details == null || details.isEmpty()) {
            LoggerUtil.error("Assignment details cannot be empty.");
            return;
        }
        Assignment assignment = new Assignment(details);
        classroom.addAssignment(assignment);
        LoggerUtil.info("Assignment for [" + classroom.getName() + "] has been scheduled.");
    }

    public void submitAssignment(Classroom classroom, String studentId, String assignmentDetails) {
        if (classroom == null) {
            LoggerUtil.error("Classroom does not exist.");
            return;
        }

        Student student = classroom.getStudents().stream()
                .filter(s -> s.getId().equals(studentId))
                .findFirst().orElse(null);

        if (student == null) {
            LoggerUtil.error("Student [" + studentId + "] not found in classroom [" + classroom.getName() + "].");
            return;
        }

        Assignment assignment = classroom.getAssignments().stream()
                .filter(a -> a.getDetails().equals(assignmentDetails))
                .findFirst().orElse(null);

        if (assignment == null) {
            LoggerUtil.error("Assignment [" + assignmentDetails + "] not found in classroom [" + classroom.getName() + "].");
            return;
        }

        assignment.submit();
        LoggerUtil.info("Assignment submitted by Student [" + studentId + "] in [" + classroom.getName() + "].");
    }
}
