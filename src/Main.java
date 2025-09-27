import commands.*;
import services.*;
import utils.LoggerUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClassroomService classroomService = new ClassroomService();
        StudentService studentService = new StudentService();
        AssignmentService assignmentService = new AssignmentService();

        Map<Integer, Command> commandMap = new HashMap<>();
        commandMap.put(1, new AddClassroomCommand(classroomService));
        commandMap.put(2, new AddStudentCommand(classroomService, studentService));
        commandMap.put(3, new ScheduleAssignmentCommand(classroomService, assignmentService));
        commandMap.put(4, new SubmitAssignmentCommand(classroomService, assignmentService));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        LoggerUtil.info("Welcome to Virtual Classroom Manager!");
        printMenu();

        while (running) {
            System.out.print("\nEnter choice (1-7): ");
            String input = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                LoggerUtil.error("Invalid input. Enter a number from 1 to 7.");
                continue;
            }

            try {
                switch (choice) {
                    case 1: // add_classroom
                        System.out.print("Enter classroom name: ");
                        String className = scanner.nextLine().trim();
                        commandMap.get(1).execute(new String[]{className});
                        break;

                    case 2: // add_student
                        System.out.print("Enter student ID: ");
                        String studentId = scanner.nextLine().trim();
                        System.out.print("Enter classroom name: ");
                        String className2 = scanner.nextLine().trim();
                        commandMap.get(2).execute(new String[]{studentId, className2});
                        break;

                    case 3: // schedule_assignment
                        System.out.print("Enter classroom name: ");
                        String className3 = scanner.nextLine().trim();
                        System.out.print("Enter assignment details: ");
                        String assignmentDetails = scanner.nextLine().trim();
                        commandMap.get(3).execute(new String[]{className3, assignmentDetails});
                        break;

                    case 4: // submit_assignment
                        System.out.print("Enter student ID: ");
                        String studentId2 = scanner.nextLine().trim();
                        System.out.print("Enter classroom name: ");
                        String className4 = scanner.nextLine().trim();
                        System.out.print("Enter assignment details: ");
                        String assignmentDetails2 = scanner.nextLine().trim();
                        commandMap.get(4).execute(new String[]{studentId2, className4, assignmentDetails2});
                        break;

                    case 5: // list_classrooms
                        classroomService.listClassrooms();
                        break;

                    case 6: // list_students
                        System.out.print("Enter classroom name: ");
                        String className5 = scanner.nextLine().trim();
                        studentService.listStudents(classroomService.getClassroom(className5));
                        break;

                    case 7: // exit
                        LoggerUtil.info("Exiting Virtual Classroom Manager.");
                        running = false;
                        break;

                    default:
                        LoggerUtil.error("Invalid choice. Enter a number from 1 to 7.");
                }
            } catch (Exception e) {
                LoggerUtil.error("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        LoggerUtil.info("Menu Options:");
        LoggerUtil.info("1. Add Classroom");
        LoggerUtil.info("2. Add Student");
        LoggerUtil.info("3. Schedule Assignment");
        LoggerUtil.info("4. Submit Assignment");
        LoggerUtil.info("5. List Classrooms");
        LoggerUtil.info("6. List Students");
        LoggerUtil.info("7. Exit");
    }
}
