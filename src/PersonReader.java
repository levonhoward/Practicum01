import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonReader
{
    static void main()
    {
        ArrayList<String> people = new ArrayList<>();

        JFileChooser chooser = new JFileChooser();
        File selectedFile;

        // Read file
        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while(reader.ready())
                {
                    people.add(reader.readLine());
                }
                reader.close();
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // Print file data
        // Header
        System.out.println("ID\t\tFirstname\tLastname\tTitle\tYOB");
        System.out.println("============================================");

        // Data
        for (String person : people)
        {
            String[] individualData = person.split(", ");
            String lineData = "%-6s\t%-10s\t%-10s\t%-5s\t%-4s";
            System.out.println(String.format(lineData, individualData[0], individualData[1], individualData[2], individualData[3], individualData[4]));
        }
    }
}

