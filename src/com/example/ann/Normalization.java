package com.example.ann;
import java.lang.Math;


public class Normalization {
    private  double mean;
    private  double standardDev;

    private void Mean(double[] input){
        double sum = 0.0;
        for (int i=0 ; i<input.length ; i++){
            sum += input[i];
        }
        mean = sum/input.length;

    }

    private void standardDeviation(double[] input){
        double result = 0.0;
        for (int i=0 ; i<input.length ; i++){
            result += (input[i] - mean) * (input[i] - mean);
        }

        double blockResult = result/input.length;
        standardDev = Math.sqrt(blockResult);

    }


    public double[] inputNormalization(double[] input){
        Mean(input);
        standardDeviation(input);
        for (int i=0 ; i<input.length ; i++){
            input[i] = (input[i] - mean)/standardDev;
        }
        return input;
    }
}
