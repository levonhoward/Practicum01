import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter
{
    static void main()
    {
        ArrayList<String> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        String productData = "";
        String ID = "";
        String name = "";
        String description = "";
        double cost = -1.0;

        boolean isDone = false;

        String fileName = "";

        // Get input
        do
        {
            ID = SafeInput.getRegExString(in, "Enter the product's six-digit ID", "^\\d{6}$");
            name = SafeInput.getNonZeroLenString(in, "Enter the product's name");
            description = SafeInput.getNonZeroLenString(in, "Enter the product's description");
            cost = SafeInput.getDouble(in, "Enter the product's cost");

            productData = ID + ", " + name + ", " + description + ", " + cost;
            products.add(productData);

            isDone = !SafeInput.getYNConfirm(in, "Would you like to enter another product?");
        } while (!isDone);

        // Write to file
        fileName = SafeInput.getNonZeroLenString(in, "Enter the name of the file to save data to");

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName + ".txt");

        try
        {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String product : products)
            {
                writer.write(product, 0, product.length());
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
