public class BasicNeuralNetworkWithMatrix {
	
	public static void main(String[] args) {
	    // Two inputs in layer 1
		final double[][] input = {{1,1}};
		
		// One output in layer 3
		double targetOutput = 0;
		
		// Input to Hidden (IH) weights
		double[][] IH = {{0.8,0.4,0.3},{0.5,0.9,0.2}};
		// second iteration of weights: Hidden to Output (HO) weights
		double[][] HO = {{0.3},{0.5},{0.9}};
		
		// initialization
		
		double [][] weightedSum;
		
		double [][] hiddenOutput = {{0,0,0}};
		
		double [][] secondWeightedSum;
		
		double calculatedOutput;

		double outputSumError;
		
		double deltaSum;
		
		double deltaHO1;
		double deltaHO2;
		double deltaHO3;
		
		double deltaIH1;
		double deltaIH2;
		double deltaIH3;
		double deltaIH4;
		double deltaIH5;
		double deltaIH6;
		
		for (int i=0;i<1000000;i++){
			// Three inputs in layer 2 (hidden layer)
			// calculating three weighted sums
			weightedSum = multiply(input,IH);
			
			// weighted sums converted into probabilities by sigmoid
			hiddenOutput[0][0] = sigmoid(weightedSum[0][0]);
			hiddenOutput[0][1] = sigmoid(weightedSum[0][1]);
			hiddenOutput[0][2] = sigmoid(weightedSum[0][2]);
			
			// second iteration of weighted sum
			secondWeightedSum = multiply(hiddenOutput,HO);
			
			// applying sigmoid on secondWeightedSum to get calculatedOutput
			calculatedOutput = sigmoid(secondWeightedSum[0][0]);
			System.out.println(calculatedOutput);
			// Back Propagation 
			
			//output sum error = target output - calculated output
			outputSumError = targetOutput - calculatedOutput;
			
			// delta sum = S'(sum)*outputSumError 
			deltaSum = sigmoidPrime(secondWeightedSum[0][0])*outputSumError;
				
		/* deltaIHWeights (1,2,3 are equal to 4,5,6 respectively 
		   because input1 and input2 are both 1) */
		deltaIH1 = deltaSum / HO[0][0] * sigmoidPrime(weightedSum[0][0]) / input[0][0];
		deltaIH2 = deltaSum / HO[1][0] * sigmoidPrime(weightedSum[0][1]) / input[0][0];
		deltaIH3 = deltaSum / HO[2][0] * sigmoidPrime(weightedSum[0][2]) / input[0][0];
		deltaIH4 = deltaSum / HO[0][0] * sigmoidPrime(weightedSum[0][0]) / input[0][1];
		deltaIH5 = deltaSum / HO[1][0] * sigmoidPrime(weightedSum[0][1]) / input[0][1];
		deltaIH6 = deltaSum / HO[2][0] * sigmoidPrime(weightedSum[0][2]) / input[0][1];
		
		// deltaHOWeights
		deltaHO1 = deltaSum / hiddenOutput[0][0];
		deltaHO2 = deltaSum / hiddenOutput[0][1];
		deltaHO3 = deltaSum / hiddenOutput[0][2];
		
		// Modifying Old Weights 
		
		// modifying IH weights
		IH[0][0] += deltaIH1;
		IH[0][1] += deltaIH2;
		IH[0][2] += deltaIH3;
		IH[1][0] += deltaIH4;
		IH[1][1] += deltaIH5;
		IH[1][2] += deltaIH6;
		
		// modifying HO weights
		HO[0][0] += deltaHO1;
		HO[1][0] += deltaHO2;
		HO[2][0] += deltaHO3;
		
		}
	}	
		// sigmoid function converting a number to a probability
		public static double sigmoid(double weightedSum) {
			double probability = 1/(1+Math.pow(Math.E, -weightedSum));
			return probability;
		}
		// derivative of sigmoid function gives instantaneous slope at sum
		public static double sigmoidPrime(double sum) {
			double sigmoid = 1/(1+Math.pow(Math.E, -sum));
			double slope = sigmoid*(1-sigmoid);
			return slope;
			
		}
		public static double[][] multiply(double[][] a, double[][] b) {
	        int m1 = a.length;
	        int n1 = a[0].length;
	        int m2 = b.length;
	        int n2 = b[0].length;
	        if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
	        double[][] c = new double[m1][n2];
	        for (int i = 0; i < m1; i++)
	            for (int j = 0; j < n2; j++)
	                for (int k = 0; k < n1; k++)
	                    c[i][j] += a[i][k] * b[k][j];
	        return c;
	    }

}