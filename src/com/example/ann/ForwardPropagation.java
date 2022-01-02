package com.example.ann;

public class ForwardPropagation {
    private double [][] hiddenWeights;
    private double [][] outWeights;


    //public void setHiddenWeights(double [][] hiddenWeights){this.hiddenWeights = hiddenWeights;}
    //public void setOutWeights(double[][]outWeights){this.outWeights = outWeights;}

    public double[] forwardPropa(double[]input, double[][] weights){
        int l = weights.length;
        int m = weights[0].length;
        System.out.println(m);
        System.out.println(l);
        double []hidden = new double[l];
        double result = 0.0;

        for (int i=0 ; i<l ; i++){
            for (int j=0 ; j<m ; j++){
                result += input[j]*weights[i][j];
            }
            // sigmoid function
            result = 1/( 1 + Math.pow(Math.E,(-1*result)));
            hidden[i] = result;
        }

        /**
        for (int i=0 ; i<l ; i++)
            System.out.println(hidden[i]);
         */

        return hidden;
    }

}
