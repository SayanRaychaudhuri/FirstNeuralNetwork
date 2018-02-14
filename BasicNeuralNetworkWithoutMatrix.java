public class BasicNeuralNetworkWithoutMatrix {
	
	public static void main(String[] args) {
	    // Two inputs in layer 1
		final int input1 = 1;
		final int input2 = 1;
		
		// One output in layer 3
		double targetOutput = 0;
		
		// Input to Hidden (IH) weights
		double IH1 = 0.8;
		double IH2 = 0.4;
		double IH3 = 0.3;
		double IH4 = 0.2;
		double IH5 = 0.9;
		double IH6 = 0.5;
		
		// second iteration of weights: Hidden to Output (HO) weights
		double HO1 = 0.3;
		double HO2 = 0.5;
		double HO3 = 0.9;
		
		// initialization
		double weightedSum1;
		double weightedSum2;
		double weightedSum3;
		
		double hiddenOutput1;
		double hiddenOutput2;
		double hiddenOutput3;
		
		double calculatedOutput;
		
		double secondWeightedSum;
		
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
		
		for (int i=0;i<10000;i++){
			// Three inputs in layer 2 (hidden layer)
			weightedSum1 = input1*IH1+input2*IH4;
			weightedSum2 = input1*IH2+input2*IH5;
			weightedSum3 = input1*IH3+input2*IH6;
			
			// weighted sums converted into probabilities by sigmoid
			hiddenOutput1 = sigmoid(weightedSum1);
			hiddenOutput2 = sigmoid(weightedSum2);
			hiddenOutput3 = sigmoid(weightedSum3);
			// second iteration of weighted sum
			secondWeightedSum = hiddenOutput1*HO1+hiddenOutput2*HO2+hiddenOutput3*HO3;
			
			// applying sigmoid on secondWeightedSum to get calculatedOutput
			calculatedOutput = sigmoid(secondWeightedSum);
			System.out.println(calculatedOutput);
			// Back Propagation 
			
			//output sum error = target output - calculated output
			outputSumError = targetOutput - calculatedOutput;
			
			// delta sum = S'(sum)*outputSumError 
			deltaSum = sigmoidPrime(secondWeightedSum)*outputSumError;
				
		/* deltaIHWeights (1,2,3 are equal to 4,5,6 respectively 
		   because input1 and input2 are both 1) */
		deltaIH1 = deltaSum / HO1 * sigmoidPrime(weightedSum1);
		deltaIH2 = deltaSum / HO2 * sigmoidPrime(weightedSum2);
		deltaIH3 = deltaSum / HO3 * sigmoidPrime(weightedSum3);
		deltaIH4 = deltaSum / HO1 * sigmoidPrime(weightedSum1);
		deltaIH5 = deltaSum / HO2 * sigmoidPrime(weightedSum2);
		deltaIH6 = deltaSum / HO3 * sigmoidPrime(weightedSum3);
		
		// deltaHOWeights
		deltaHO1 = deltaSum / hiddenOutput1;
		deltaHO2 = deltaSum / hiddenOutput2;
		deltaHO3 = deltaSum / hiddenOutput3;
		
		// Modifying Old Weights 
		
		// modifying IH weights
		IH1 += deltaIH1;
		IH2 += deltaIH2;
		IH3 += deltaIH3;
		IH4 += deltaIH4;
		IH5 += deltaIH5;
		IH6 += deltaIH6;
		
		// modifying HO weights
		HO1 += deltaHO1;
		HO2 += deltaHO2;
		HO3 += deltaHO3;
		
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

}