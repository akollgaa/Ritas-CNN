public class CNN {

    MathFunction math;
    InputImage image;

    // All variable weights
    float[][][][] f1; // Filter 1
    float[][][][] f2; // Filter 2
    float[][] w1; // Weight 1
    float[][] w2; // Weight 2
    float[][] w3; // Weight 3
    float[][] b1; // Bias 1
    float[][] b2; // Bias 2

    // Other parameters
    double learningRate = 0.1;
    int filterOneSize = 5; // 5x5 size
    int filterTwoSize = 3; // 3x3 size

    // FullyConnectedVariables
    // These are to help in the backpropogation process be more efficient.
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

    // These assign random values to each variable (Filters and weights);
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

        // First convolutional layer
        int layerOneSize = this.image.imageX - filterOneSize + 1; // 256 square image produces a 252 output feature map
        float[][][] layerOne = new float[12][layerOneSize][layerOneSize];
        // Convolutes through each of the 3 filters to produce 12 feature maps.
        for(int filterCount = 0; filterCount < 12; filterCount++) {
            layerOne[filterCount] = this.math.convolute(input, f1[filterCount], 1);
            layerOne[filterCount] = this.math.ReLU(layerOne[filterCount]);
            layerOne[filterCount] = this.math.maxPool(layerOne[filterCount], 2, 2, false)[0];
        }

        // Second convolutional layer
        int layerTwoSize = layerOne[0].length - filterTwoSize + 1;
        float[][][] layerTwo = new float[24][layerTwoSize][layerTwoSize];
        // Convolutes through each of the 12 filters to produce 24 feature maps.
        for(int filterCount = 0; filterCount < 24; filterCount++) {
            layerTwo[filterCount] = this.math.convolute(layerOne, f2[filterCount], 1);
            layerTwo[filterCount] = this.math.ReLU(layerTwo[filterCount]);
            layerTwo[filterCount] = this.math.maxPool(layerTwo[filterCount], 2, 2, false)[0];
        }

        // Fully Connected layers
        float[][] fullyConnectedInput = this.math.convertToFullyConnected(layerTwo, this.image.imageX, this.image.imageY, filterOneSize, filterTwoSize);
        a1 = this.math.combine(this.math.matrixMultiply(fullyConnectedInput, w1), b1, true); // First layer weights
        a2 = this.math.combine(this.math.matrixMultiply(this.math.sigmoid(a1), w2), b2, true); // Second Layer weights
        a3 = this.math.matrixMultiply(this.math.sigmoid(a2), w3); // Third layer weights

        return  this.math.sigmoid(a3); // Possibly change to a different activation instead.
    }

    public static void main(String[] args) {
        CNN network = new CNN();

        // Try using the fullpath if you can not get the image does not upload.
        network.image.assignImage("/Images/symbol.png");
        network.assignRandomValues(); // Must be after image is loaded.

        float[][] y_hat = network.forwardPropogation(network.image.getPictureArray(true));

        // Here you would do the backpropogation/learning step.

        network.printArray(y_hat); // Prints out answer
    }
