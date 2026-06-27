package cli.commands;

import service.InternalService;
import service.EligibilityService;
import java.util.Scanner;

public class InternalCommands {

    private InternalService internalService = new InternalService();
    private EligibilityService eligibilityService = new EligibilityService();
   private Scanner sc = new Scanner(System.in);

    public void internalMenu() {

        while (true) {
            System.out.println("\n===== Internal Marks Entry =====");
            System.out.println("1. Enter Internal Marks");
            System.out.println("2. List Internal Marks");
            System.out.println("3. Import Internal Marks CSV");
            System.out.println("4. Export Internal Marks by Subject");
            System.out.println("5. Export Ineligible Students by Subject");
            System.out.println("6. Back");
            System.out.print("Enter choice: ");

            int ch;
            try {
                ch = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input!");
                continue;
            }

            switch (ch) {
                case 1 -> enterInternalFlow();
                case 2 -> internalService.listInternals();
                case 3 -> importCSVFlow();
                case 4 -> exportInternalMarksBySubject();
                case 5 -> exportIneligibleBySubject();
                case 6 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void enterInternalFlow() {

        System.out.print("Student USN: ");
        String usn = sc.nextLine().trim();

        System.out.print("Subject Code: ");
        String subjectCode = sc.nextLine().trim();

        internalService.enterInternal(usn, subjectCode, sc);
    }

    private void importCSVFlow() {
        System.out.print("Enter CSV file path: ");
        String path = sc.nextLine();
        internalService.importInternalFromCSV(path);
    }

    private void exportInternalMarksBySubject() {

    System.out.print("Enter Subject Code: ");
    String code = sc.nextLine().trim();

    System.out.print("Enter output CSV path: ");
    String path = sc.nextLine().trim();

    eligibilityService.exportInternalMarksBySubject(code, path);
}

private void exportIneligibleBySubject() {

    System.out.print("Enter Subject Code: ");
    String code = sc.nextLine().trim();

    System.out.print("Enter output CSV path: ");
    String path = sc.nextLine().trim();

    eligibilityService.exportIneligibleStudentsBySubject(code, path);
}

}
