package com.example.ann;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Training {
    public static double[] input;
    public static double[] output;
    public static double[] hidden;  // hidden layer output
    public static double[] predictedOutput;  // predicted Output layer values
    public static double[][] hiddenWeights;
    public static double[][] outWeights;

    public double[] getOutput(){return output;}
    public double[] getInput(){return input;}
    public double[] getPredictedOutput(){return predictedOutput;}
    public double[] getHidden(){return hidden;}
    public double[][] getHiddenWeights(){return hiddenWeights;}
    public double[][] outWeights(){return outWeights;}


    public void Setting(){
        Data data = new Data();
        input = data.getInput();
        output = data.getOutput();
        hiddenWeights = data.getHiddenWeights();
        outWeights = data.getOutWeights();
    }


    public void training(String trainFile){

        Setting();
        ForwardPropagation forward = new ForwardPropagation();

        try {
            int M = input.length;
            int N = output.length;

            // opening the normalized file data
            FileInputStream fileInputStream = new FileInputStream(trainFile);
            Scanner scannerRead = new Scanner(fileInputStream);    //file to be scanned


            BackPropagation backPropagation = new BackPropagation();


            int i, j;
            double mse;
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

                // train the data to get the beast weights
                hidden = forward.forwardPropagation(input, hiddenWeights);
                predictedOutput = forward.forwardPropagation(hidden, outWeights);

                System.out.print("Training MSE ");
                // the mean square error
                System.out.println(backPropagation.MSE(output, predictedOutput));

                for (i = 0; i < 500; i++) {

                    if (backPropagation.MSE(output, predictedOutput) > 0.9) {
                        //System.out.print("MSE ");
                        //System.out.println(mse);

                        // back propagation
                        backPropagation.backPropagation(outWeights, hiddenWeights);

                        // forward propagation
                        hidden = forward.forwardPropagation(input, hiddenWeights);
                        predictedOutput = forward.forwardPropagation(hidden, outWeights);

                    } else {
                        break;
                    }
                }

            }
            scannerRead.close();     //closes the scanner
            /*
            for (i=0 ; i<hiddenWeights.length; i++){
                for (j=0 ; j<hiddenWeights[0].length ; j++){
                    System.out.print(hiddenWeights[i][j]);
                    System.out.print("   ");
                }
                System.out.println();
            }

            */

        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
}
