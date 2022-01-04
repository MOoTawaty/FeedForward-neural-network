package com.example.ann;

public class BackPropagation {
    public static double[] output;
    public static double [] predictedOutput;
    public static double [] hidden;
    public static double [] input;

    public void setting(){
        Training training = new Training();
        output = training.getOutput();
        predictedOutput = training.getPredictedOutput();
        hidden = training.getHidden();
        input = training.getInput();
    }

    // Mean Square Error
    public double MSE(double[]output, double[]predictedOutput){
        setting();
        double MSE = 0.0;
        for (int i=0 ; i<output.length ; i++) {
            MSE +=((output[i] - predictedOutput[i])*(output[i] - predictedOutput[i]));
        }
        // sigmoid function
        //MSE = 1/( 1 + Math.pow(Math.E,(-1*MSE)));
        return (MSE / output.length);
    }




    public void backPropagation(double[][] outWeights, double[][] hiddenWeights){


        //1- calculate errors of the outputs neurons
        double []outError = new double[output.length]; // N=1
        for (int i=0 ; i<output.length ; i++) {
            outError[i] = predictedOutput[i]*(1-predictedOutput[i])*(predictedOutput[i]-output[i]);
        }

        /** initializing learning rate */
        double learningRate = 0.3;

        //2- change output layer weights
        for (int i=0 ; i<outWeights.length ; i++){  //1
            for (int j=0 ; j<outWeights[0].length ; j++){  //10
                outWeights[i][j] = outWeights[i][j] + learningRate * outError[i] * hidden[j];
            }
        }


        //3- calculate or (back-propagate) hidden layer neurons Errors
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








/*

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
*/


    }
}
