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

    public double[] getOutput(){return output;}
    public double[] getInput(){return input;}
    public double[] getPredictedOutput(){return predictedOutput;}
    public double[] getHidden(){return hidden;}
    //public double[][] getOutWeights(){return outWeights;}
                                                              // L=10        M=8
    public double[][] WeightsInitialization(double[][]weights, int rew, int column){
        weights = new double[rew][column];
        double min = -2.0;
        double max = 2.0;
        Random random = new Random();
        //double value = min + (max - min) * random.nextDouble();
        for (int i=0 ; i<rew ; i++) {
            for (int j=0 ; j<column ; j++) {
                // normalize the random double to be in the range
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
            // split the record data
            // fixed one column of hidden neurons in this Algorithm
            String[] splitValues = buffer.split(" ");
            // Number of input neurons
            M = Integer.parseInt(splitValues[0]);
            input = new double[M];
            // Number of hidden neurons
            L = Integer.parseInt(splitValues[1]);
            // Number of output neurons
            N = Integer.parseInt(splitValues[2]);
            output = new double[N];
            // avoiding the next line  "number of rew data"
            scannerReader.nextLine();
            scannerReader.close();

            // opening the normalized file data
            FileInputStream fileInputStream=new FileInputStream("normData.txt");
            Scanner scannerRead=new Scanner(fileInputStream);    //file to be scanned


            BackPropagation backPropagation = new BackPropagation();

            // Weights Initialization Randomly
            hiddenWeights = data.WeightsInitialization(hiddenWeights, L, M); //10*8
            outWeights = data.WeightsInitialization(outWeights, N, L);

            // Normalization
            normalization.fileNormalization("train.txt", "normData.txt");

            hidden = forward.forwardPropagation(input, hiddenWeights);
            predictedOutput = forward.forwardPropagation(hidden, outWeights);


            int i, j;
            double mse;
            while(scannerRead.hasNextLine())
            {

                //input data
                for(i =0 ; i<M ; i++){
                    input[i] = scannerRead.nextDouble();

                }
                //output data
                for(j =0 ; j<N ; j++){
                    output[j] = scannerRead.nextDouble();
                }
                scannerRead.nextLine();


                for (i=0 ; i<500 ; i++) {

                    //mse = backPropagation.MSE();
                    if (backPropagation.MSE() > 0.9) {
                        System.out.print("MSE ");
                        System.out.println(backPropagation.MSE());

                        // back propagation
                        backPropagation.backPropagation(outWeights, hiddenWeights);

                        // forward propagation
                        hidden = forward.forwardPropagation(input, hiddenWeights);
                        predictedOutput = forward.forwardPropagation(hidden, outWeights);

                    }
                    else {break;}
                }


                //buffer = scannerRead.nextLine();      //returns the line that was skipped
                break;
            }

            //System.out.print("MSE ");
            //System.out.println(backPropagation.MSE());
/*
            for (i=0 ; i<hiddenWeights.length; i++){
                for (j=0 ; j<hiddenWeights[0].length ; j++){
                    System.out.print(hiddenWeights[i][j]);
                    System.out.print("   ");
                }
                System.out.println();
            }

/*
            for (i=0 ; i< N ; i++)
                System.out.println(output[i]);

            for (i=0 ; i< M ; i++)
                System.out.println(input[i]);

            /*
            for (String a : splitValues)
                System.out.println(a);
             */
            scannerRead.close();     //closes the scanner
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}


