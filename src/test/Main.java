package test;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {



        
        Scanner in = null;
        try {

            File sourceFile = new File("./src/test/in.txt");
            in = new Scanner(sourceFile);
        } catch (FileNotFoundException e) {
         	System.exit(0);
        }

        


        FileWriter out = null;
        try {

            File destFile = new File("./src/test/out.txt");
            out = new FileWriter(destFile);
        } catch (IOException e) {
         	System.exit(0);
        }


        int t = in.nextInt();
        in.nextLine();
        building[] bs = new building[t];
        int max = 0;
        for (int i = 0; i < t; i++) {
            String[] str = in.nextLine().split(",");
            building b = new building();
            b.start = Integer.parseInt(str[0].trim());
            b.height = Integer.parseInt(str[1].trim());
            b.end = Integer.parseInt(str[2].trim());
            if (b.end > max)
                max = b.end;
            bs[i] = b;
        }
        max++;

        int[] arr = new int[max];
        draw(bs, arr);

        StringBuilder res = new StringBuilder();
        int r = arr[0];
        for (int i = 0; i < max; i++) {
            if (arr[i] != r) {
                r = arr[i];
                res.append(i + ", " + r + ", ");
            }
        }
        res.deleteCharAt(res.length() - 1);
        res.deleteCharAt(res.length() - 1);
        try {
            out.write(res.toString());
            out.flush();
            out.close();
            in.close();
        } catch (IOException e){
        	System.exit(0);
        }
    }

    static void draw(building[] b, int[] out) {
        int n = b.length;
        if (n == 1) {
            for (int i = b[0].start; i < b[0].end; i++) {
                out[i] = Math.max(out[i], b[0].height);
            }
            return;
        }
        int m = n / 2;

        building[] b1 = new building[m];
        building[] b2 = new building[m + n % 2];
        for (int i = 0; i < m + n % 2; i++) {
            if (i < b1.length) {
                b1[i] = b[i];
            }
            b2[i] = b[i + m];
        }
        draw(b1, out);
        draw(b2, out);

    }


}

class building {
    int start, height, end;
}
