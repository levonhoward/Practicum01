import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader
{
    static void main()
    {
        ArrayList<String> products = new ArrayList<>();

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
                    products.add(reader.readLine());
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
        System.out.println("ID\t\tName\t\tDescription\t\t\t\t\tCost");
        System.out.println("===========================================================");

        // Data
        for (String product : products)
        {
            String[] productData = product.split(", ");
            String lineData = "%-6s\t%-10s\t%-25s\t$%,.2f";
            System.out.println(String.format(lineData, productData[0], productData[1], productData[2], Double.parseDouble(productData[3])));
        }
    }
}

