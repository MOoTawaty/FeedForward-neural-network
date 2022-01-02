package com.example.ann;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Data {
    public static double[] input;
    public static double[] output;
    public static double[][] hiddenWeights;// Weights between input and hidden layer
    public static double[][] outWeights;   // Weights between hidden and output layer
    public static double [] hidden;  // hidden layer output
    public static double [] predictedOutput; // predicted Output layer values
    public static int M;
    public static int L;
    public static int N;
                                                              // L        M
    public double[][] WeightsInitialization(double[][]weights, int rew, int column){
        weights = new double[rew][column];
        double min = -2.0;
        double max = 2.0;
        Random random = new Random();
        //double value = min + (max - min) * random.nextDouble();
        for (int i=0 ; i<rew ; i++) {
            for (int j=0 ; j<column ; j++) {
                weights[i][j] = min + (max - min) * random.nextDouble();
            }
        }

        return weights;
    }


    public static void main(String[] args) {
        //int M, L, N;
        hiddenWeights = new double[L][M];
        hidden = new double[M];
        predictedOutput = new double[N];
        //Normalization object to normalize the inputs
        Normalization normalization = new Normalization();
        ForwardPropagation forward = new ForwardPropagation();
        Data data = new Data();

        try
        {
            //the file to be opened for reading
            FileInputStream fis=new FileInputStream("train.txt");
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


            int i, j;
            while(scannerReader.hasNextLine())
            {
                //input data
                for(i =0 ; i<M ; i++){
                    input[i] = scannerReader.nextDouble();

                }
                //output data
                for(j =0 ; j<N ; j++){
                    output[j] = scannerReader.nextDouble();
                }

                // Normalization
                input = normalization.inputNormalization(input);
                // output = normalization.inputNormalization(output);

                // Weights Initialization Randomly
                hiddenWeights = data.WeightsInitialization(hiddenWeights, L, M);
                outWeights = data.WeightsInitialization(outWeights, N, L);


                // forward propagation
                hidden = forward.forwardPropa(input, hiddenWeights);
                predictedOutput = forward.forwardPropa(hidden, outWeights);


                buffer = scannerReader.nextLine();      //returns the line that was skipped
                break;
            }

//            for (i=0 ; i< hiddenWeights.length ; i++)
//                System.out.println(hiddenWeights[i][0]);

            /*
            for (String a : splitValues)
                System.out.println(a);
             */
            scannerReader.close();     //closes the scanner
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}


