import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator
{
    static void main()
    {
        ArrayList<String> people = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        String record = "";
        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        int YOB = -1;

        boolean isDone = false;

        String fileName = "";

        // Get data
        do
        {
            ID = SafeInput.getRegExString(in, "Enter the person's six-digit ID", "^\\d{6}$");
            firstName = SafeInput.getNonZeroLenString(in, "Enter their first name");
            lastName = SafeInput.getNonZeroLenString(in, "Enter their last name");
            title = SafeInput.getNonZeroLenString(in, "Enter their title");
            YOB = SafeInput.getRangedInt(in, "Enter the year they were born ", 1000, 9999);

            record = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;
            people.add(record);

            isDone = !SafeInput.getYNConfirm(in, "Would you like to enter another name?");
        } while (!isDone);

        fileName = SafeInput.getNonZeroLenString(in, "Enter the name of the file to save data to");

        // Write data
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName + ".txt");

        try
        {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String person : people)
            {
                writer.write(person, 0, person.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data saved to " + fileName + ".txt");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
