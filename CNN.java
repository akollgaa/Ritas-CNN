public class CNN {

    MathFunction math;
    InputImage image;

    // All variable weights
    float[][][][] f1;
    float[][][][] f2;
    float[][] w1;
    float[][] w2;
    float[][] w3;
    float[][] b1;
    float[][] b2;

    // Other parameters
    double learningRate = 0.1;
    int filterOneSize = 5;
    int filterTwoSize = 3;

    // FullyConnectedVariables
    float[][] a1;
    float[][] a2;
    float[][] a3;

    public CNN() {
        math = new MathFunction();
        image = new InputImage();
    }

    public void printArray(float[][] x) {
        for(int i = 0; i < x.length; i++) {
            for(int j = 0; j < x[0].length; j++) {
                System.out.print(x[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void assignRandomValues() {
        int L = ((((this.image.imageX - filterOneSize + 1) / 2) - filterTwoSize + 1) / 2);
        L = L * L * 24; // This calculates the fully connected layer length based on the input image.
        this.f1 = this.math.randomizeMatrix(12, 3, filterOneSize, filterOneSize);
        this.f2 = this.math.randomizeMatrix(24, 12, filterTwoSize, filterTwoSize);
        this.w1 = this.math.randomizeMatrix(L, 500);
        this.w2 = this.math.randomizeMatrix(500, 200);
        this.w3 = this.math.randomizeMatrix(200, 1);
        this.b1 = this.math.randomizeMatrix(1, 500);
        this.b2 = this.math.randomizeMatrix(1, 200);
    }

    public float[][] forwardPropogation(float[][][] input) {

        int layerOneSize = this.image.imageX - filterOneSize + 1;
        float[][][] layerOne = new float[12][layerOneSize][layerOneSize];
        // Convolutes through each of the twelve filters
        for(int filterCount = 0; filterCount < 12; filterCount++) {
            layerOne[filterCount] = this.math.convolute(input, f1[filterCount], 1);
            layerOne[filterCount] = this.math.ReLU(layerOne[filterCount]);
            layerOne[filterCount] = this.math.maxPool(layerOne[filterCount], 2, 2, false)[0];
        }

        int layerTwoSize = layerOne[0].length - filterTwoSize + 1;
        float[][][] layerTwo = new float[24][layerTwoSize][layerTwoSize];
        for(int filterCount = 0; filterCount < 24; filterCount++) {
            layerTwo[filterCount] = this.math.convolute(layerOne, f2[filterCount], 1);
            layerTwo[filterCount] = this.math.ReLU(layerTwo[filterCount]);
            layerTwo[filterCount] = this.math.maxPool(layerTwo[filterCount], 2, 2, false)[0];
        }

        float[][] fullyConnectedInput = this.math.convertToFullyConnected(layerTwo, this.image.imageX, this.image.imageY, filterOneSize, filterTwoSize);
        a1 = this.math.combine(this.math.matrixMultiply(fullyConnectedInput, w1), b1, true);
        a2 = this.math.combine(this.math.matrixMultiply(this.math.sigmoid(a1), w2), b2, true);
        a3 = this.math.matrixMultiply(this.math.sigmoid(a2), w3);
        return  this.math.sigmoid(a3);
    }

    public static void main(String[] args) {
        CNN network = new CNN();
        long startTime = System.currentTimeMillis();
        network.image.AssignImage("/home/pi/Documents/JavaProjects/BasicNeuralNetwork/images/symbol.png");
        network.assignRandomValues(); // Must be after image is loaded.
        float[][] y_hat = network.forwardPropogation(network.image.getPictureArray(true));
        network.printArray(y_hat);
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
