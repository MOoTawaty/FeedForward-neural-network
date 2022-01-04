package com.example.ann;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Testing {
    public static double[] hidden;
    public static double[] predictedOutput;
    public static double[] input;
    public static double[] output;
    public static int M;
    public static int N;
    public static int L;


    public void testing(String testFileName){

        try {
            //the file to be opened for reading
            FileInputStream fis=new FileInputStream(testFileName);
            Scanner scannerReader=new Scanner(fis);    //file to be scanned
            String buffer;

            // Reading first rew data
            buffer = scannerReader.nextLine();
            String[] splitValues = buffer.split(" ");
            M = Integer.parseInt(splitValues[0]);
            input = new double[M];
            L = Integer.parseInt(splitValues[1]);
            N = Integer.parseInt(splitValues[2]);
            output = new double[N];
            scannerReader.nextLine();
            scannerReader.close();



            Normalization normalization = new Normalization();
            normalization.fileNormalization(testFileName, "normTest.txt");

            ForwardPropagation forwardPropagation = new ForwardPropagation();
            Training training = new Training();

            // opening the normalized file data
            FileInputStream fileInputStream = new FileInputStream("normTest.txt");
            Scanner scannerRead = new Scanner(fileInputStream);    //file to be scanned


            BackPropagation backPropagation = new BackPropagation();


            int i, j;
            while (scannerRead.hasNextLine()) {

                //input data
                for (i = 0; i < M; i++) {
                    input[i] = scannerRead.nextDouble();

                }
                //output data
                for (j = 0; j < N; j++) {
                    output[j] = scannerRead.nextDouble();
                }
                scannerRead.nextLine();

                hidden = forwardPropagation.forwardPropagation(input, training.getHiddenWeights());
                predictedOutput = forwardPropagation.forwardPropagation(hidden, training.outWeights());

                System.out.print("Testing MSE ");
                System.out.println(backPropagation.MSE(output, predictedOutput));

            }
            scannerRead.close();     //closes the scanner

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
