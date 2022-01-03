package com.example.ann;

public class BackPropagation {
    public static double[] output;
    public static double [] predictedOutput;
    public static double [] hidden;
    public static double [] input;
    //public static double[][] outWeights;
    public static int m;
    public static int l;
    public static int n;
/*
    public BackPropagation(int m, int l, int n){
        this.m = m;
        this.l = l;
        this.n = n;

    }
 */
    public void setting(){
        Data data = new Data();
        output = data.getOutput();
        predictedOutput = data.getPredictedOutput();
        //System.out.println(predictedOutput[0]);
        hidden = data.getHidden();
        input = data.getInput();
    }

    // Mean Square Error
    public double MSE(){
        setting();
        double MSE = 0.0;
        for (int i=0 ; i<output.length ; i++) {
            MSE +=((output[i] - predictedOutput[i])*(output[i] - predictedOutput[i]));
        }
        return (MSE * 0.5);
    }

    public void backPropagation(double[][] outWeights, double[][] hiddenWeights){
        //1- calculate errors of the outputs neurons
        double []outError = new double[output.length]; // N=1
        for (int i=0 ; i<output.length ; i++) {
            outError[i] = predictedOutput[i]*(1-predictedOutput[i])*(output[i]-predictedOutput[i]);
        }

        /** initializing learning rate */
        double learningRate = 0.3;

        //2- change output layer weights
        for (int i=0 ; i<outWeights.length ; i++){  //1
            for (int j=0 ; j<outWeights[0].length ; j++){  //10
                outWeights[i][j] = outWeights[i][j] + learningRate * outError[i] * output[i];
            }
        }


        //3- calculate or (back-propagate) hidden layer Error
        double []hiddenError = new double[hidden.length]; // N=1
        double result = 0.0;
        for (int j=0 ; j<outWeights[0].length ; j++) {  // outWeights.length = 10 column
            for (int k=0 ; k<outWeights.length ; k++){  // k<outWeights[0].length = 1 row
                // first column value in outError * first row value in outWeights & so on..
                result += outError[k]*outWeights[k][j];
            }
            hiddenError[j] = hidden[j] * (1 - hidden[j]) * result;
            result = 0.0;
            }



        //4- change hidden layer weights
        for (int i=0 ; i<hiddenWeights.length ; i++){ //10
            for (int j=0 ; j<hiddenWeights[0].length ; j++){ //8
                hiddenWeights[i][j] = hiddenWeights[i][j] + learningRate * hiddenError[i] * input[j];
            }
        }


        // new outWeights  hiddenWeights
        System.out.println("hiddenWeights: ");
        for (int i=0 ; i<outWeights.length; i++){
            for (int j=0 ; j<outWeights[0].length ; j++){
                System.out.print(outWeights[i][j]);
                System.out.print("   ");
            }
            System.out.println();
        }

        System.out.println("hiddenWeights: ");
        for (int i=0 ; i<hiddenWeights.length; i++){
            for (int j=0 ; j<hiddenWeights[0].length ; j++){
                System.out.print(hiddenWeights[i][j]);
                System.out.print("   ");
            }
            System.out.println();
        }



    }
}
