package com.example.ann;

public class ForwardPropagation {
    private double [][] hiddenWeights;
    private double [][] outWeights;


    // forward Propagation method
    public double[] forwardPropagation(double[]input, double[][] weights){
        int l = weights.length;  //10
        int m = weights[0].length; // 8
        double []hidden = new double[l];
        double result = 0.0;

        for (int i=0 ; i<l ; i++){  // 2*  1
            for (int j=0 ; j<m ; j++){  // 2*  10
                result += input[j]*weights[i][j];
            }
            // sigmoid function
            result = 1/( 1 + Math.pow(Math.E,(-1*result)));
            hidden[i] = result;
            //System.out.println(hidden[i]);
            result = 0.0;
        }

        /**
        for (int i=0 ; i<l ; i++)
            System.out.println(hidden[i]);
         */

        return hidden;
    }

}
