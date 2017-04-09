package test;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class MultiplyPolynomials {



    public static int[] polyMult1(int[] p, int[] q) {
        int n = Math.max(p.length, q.length);

        if (n == 1) {
            return new int[]{p[0] * q[0]};
        }

        int d = n / 2;
        int k = d + n % 2;
        int[] a0 = new int[d];
        int[] b0 = new int[d];
        for (int i = 0; i < d; i++) {
            if (i < p.length) {
                a0[i] = p[i];
            }
            if (i < q.length) {
                b0[i] = q[i];
            }
        }
        int[] a1 = new int[k];
        int[] b1 = new int[k];
        for (int i = d; i < n; i++) {
            if (i < p.length)
                a1[i - d] = p[i];
            if (i < q.length)
                b1[i - d] = q[i];
        }

        int[] a = new int[k];
        int[] b = new int[k];


        for (int i = 0; i < k; i++) {
            if (i < a0.length)
                a[i] += a0[i];
            if (i < a1.length)
                a[i] += a1[i];
            if (i < b0.length)
                b[i] += b0[i];
            if (i < b1.length)
                b[i] += b1[i];
        }


        int[] y = polyMult1(a, b);
        int[] u = polyMult1(a0, b0);
        int[] z = polyMult1(a1, b1);

        int[] pq = new int[2 * n - 1];

        for (int i = 0; i < n; i++) {
            int t = 0;
            if (i < y.length) {
                t = y[i];
            }
            if (i < u.length) {
                pq[i] += u[i];
                t -= u[i];
            }

            if (i < z.length) {
                pq[i + (2 * d)] += z[i];
                t -= z[i];
            }
            pq[i + d] += t;
        }
        return pq;
    }

    public static void main(String[] args) {


        Scanner in = null;
        try {

            File sourceFile = new File("./src/test/in2.txt");
            in = new Scanner(sourceFile);
        } catch (FileNotFoundException e) {
            System.exit(0);
        }

        FileWriter out = null;
        try {
            File destFile = new File("./src/test/out2.txt");
            out = new FileWriter(destFile);
        } catch (IOException e) {
            System.exit(0);
        }

        int n = in.nextInt();
        n++;
        in.nextLine();

        int[] p = new int[n];
        String[] str1 = in.nextLine().split(",");
        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(str1[i].trim());
        }

        int[] q = new int[n];
        String[] str2 = in.nextLine().split(",");
        for (int i = 0; i < n; i++) {
            q[i] = Integer.parseInt(str2[i].trim());
        }


        int[] pq = polyMult1(p, q);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < pq.length; i++) {
            res.append(pq[i] + ", ");
        }
        res.deleteCharAt(res.length() - 1);
        res.deleteCharAt(res.length() - 1);
        try {
            out.write(res.toString());
            out.flush();
            out.close();
            in.close();
        } catch (IOException e) {
            System.exit(0);
        }


    }

}
