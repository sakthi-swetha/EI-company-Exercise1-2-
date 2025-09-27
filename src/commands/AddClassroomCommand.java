package commands;

import services.ClassroomService;
import utils.LoggerUtil;

public class AddClassroomCommand implements Command {
    private final ClassroomService classroomService;

    public AddClassroomCommand(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @Override
    public void execute(String[] params) {
        if (params.length < 1 || params[0].trim().isEmpty()) {
            LoggerUtil.error("Usage: add_classroom <class_name>");
            return;
        }
        String className = params[0].trim();
        classroomService.addClassroom(className);
    }
}
