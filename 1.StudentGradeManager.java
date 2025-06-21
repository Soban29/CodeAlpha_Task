import java.util.Scanner;

 class StudentGradeArray {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int numStudents = input.nextInt();
        input.nextLine();

        String[] names = new String[numStudents];
        double[] grades = new double[numStudents];

        // Input student data
        for (int i = 0; i < numStudents; i++) {
            System.out.print("\nEnter name for student " + (i + 1) + ": ");
            names[i] = input.nextLine();

            System.out.print("Enter grade for " + names[i] + ": ");
            grades[i] = input.nextDouble();
            input.nextLine(); // Consume leftover newline
        }

        // Calculate statistics
        double total = 0;
        double highest = grades[0];
        double lowest = grades[0];

        for (int i = 0; i < numStudents; i++) {
            total += grades[i];
            if (grades[i] > highest) highest = grades[i];
            if (grades[i] < lowest) lowest = grades[i];
        }

        double average = total / numStudents;

        // Display summary
        System.out.println("\n--- Summary Report ---");
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Name: " + names[i] + ", Grade: " + grades[i]);
        }

        System.out.printf("\nAverage Grade: " +  average);
        System.out.printf("\nHighest Grade: " +  highest);
        System.out.printf("\nLowest Grade: " +  lowest);

    }
}
