package com.example.ann;
import java.io.*;
import java.lang.Math;
import java.util.Scanner;


public class Normalization {
    private  double[] mean;
    private  double[] standardDev;
    private double[][] data;

    private void Mean(double[][] input){

        double sum = 0.0;
        for (int i=0 ; i<input[0].length ; i++){
            for (int j=0 ; j<input.length ; j++){
                sum += input[j][i];
            }
            mean[i] = sum/input.length;
            sum = 0.0;
            //System.out.println(mean[i]);
        }
    }

    private void standardDeviation(double[][] input){
        double result = 0.0;
        double blockResult;
        for (int i=0 ; i<input[0].length ; i++){  // 9
            for (int j=0 ; j<input.length ; j++) {  // 515
                result += (input[j][i] - mean[i]) * (input[j][i] - mean[i]);
            }
            blockResult = result/input.length;
            standardDev[i] = Math.sqrt(blockResult);
            //System.out.println(standardDev[i]);
            result = 0.0;
        }

    }


    public void inputNormalization(double[][] input){

        for (int i=0 ; i<input[0].length ; i++) {  // 9
            for (int j = 0; j < input.length; j++) {  // 515
                input[j][i] = (input[j][i] - mean[i])/standardDev[i];
            }
        }

    }

    public void fileData(String fileName){
        try
        {
            //the file to be opened for reading
            FileInputStream fis=new FileInputStream(fileName);
            Scanner scannerReader=new Scanner(fis);    //file to be scanned
            //returns true if there is another line to read
            int L = scannerReader.nextInt();
            scannerReader.nextInt();
            int N = scannerReader.nextInt();

            int size = scannerReader.nextInt();
            //System.out.println(size);  515
            data = new double[size][L+N];

            int i = 0;
            int j;
            while(scannerReader.hasNextLine())
            {
                for (j=0 ; j<(L+N) ; j++) data[i][j] = scannerReader.nextDouble();
                scannerReader.nextLine();
                i++;
            }
            scannerReader.close();     //closes the scanner

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void fileNormalization(String inFileName, String outFileNam){
        fileData(inFileName);
        mean = new double[data[0].length];
        Mean(data);
        standardDev = new double[data[0].length];
        standardDeviation(data);
        inputNormalization(data);

        try {
            // creating new file
            File file = new File(outFileNam);
            //create FileOutputStream object
            FileOutputStream fos = new FileOutputStream(file);
            // "US-ASCII" for converting to string
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos, "US-ASCII");

            for (int i=0 ; i<data.length ; i++) {  // 9
                for (int j = 0; j < data[0].length; j++) {  // 515
                    // converse double to string & store 6 digits from the double value
                    outputStreamWriter.append(String.valueOf(data[i][j]), 0, 6);
                    outputStreamWriter.write("   ");
                }
                outputStreamWriter.write('\n');
            }

            // Closing the file
            outputStreamWriter.close();

        } catch (IOException e) {
            System.out.println("IOException : " + e);

        }

    }

}
