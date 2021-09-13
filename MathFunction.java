import java.util.Random;
import java.lang.Math;

public class MathFunction {

    Random rand;

    public MathFunction() {
        rand = new Random();
    }

    // Used for 2-Dimenional arrays.
    public float[][] ReLU(float[][] x) {
        float[][] output = new float[x.length][x[0].length];
        for(int i = 0; i < output.length; i++) {
            for(int j = 0; j < output[i].length; j++) {
                if(x[i][j] > 0) {
                    output[i][j] = x[i][j];
                } else {
                    output[i][j] = 0;
                }
            }
        }
        return output;
    }

    // Used for 3-Dimensional arrays.
    public float[][][] ReLU(float[][][] x) {
        float[][][] output = new float[x.length][x[0].length][x[0][0].length];
        for(int c = 0; c < output.length; c++) {
            for(int i = 0; i < output[c].length; i++) {
                for(int j = 0; j < output[c][i].length; j++) {
                    if(x[c][i][j] > 0) {
                        output[c][i][j] = x[c][i][j];
                    } else {
                        output[c][i][j] = 0;
                    }
                }
            }
        }
        return output;
    }

    // Derivitive of the 2-Dimensional ReLU function
    public float[][] ReLUPrime(float[][] x) {
        float[][] output = new float[x.length][x[0].length];
        for(int i = 0; i < output.length; i++) {
            for(int j = 0; j < output[i].length; j++) {
                if(x[i][j] > 0) {
                    output[i][j] = 1;
                } else {
                    output[i][j] = 0;
                }
            }
        }
        return output;
    }

    // Derivitive of the 3-Dimensional ReLU function
    public float[][][] ReLUPrime(float[][][] x) {
        float[][][] output = new float[x.length][x[0].length][x[0][0].length];
        for(int c = 0; c < output.length; c++) {
            for(int i = 0; i < output[c].length; i++) {
                for(int j = 0; j < output[c][i].length; j++) {
                    if(x[c][i][j] > 0) {
                        output[c][i][j] = 1;
                    } else {
                        output[c][i][j] = 0;
                    }
                }
            }
        }
        return output;
    }

    // Hyperbolic tangent function.
    public float[][] tanh(float[][] x) {
        float[][] output = new float[x.length][x[0].length];
        for(int i = 0; i < output.length; i++) {
            for(int j = 0; j < output[i].length; j++) {
                output[i][j] = (float) Math.tanh(x[i][j]);
            }
        }
        return output;
    }

    // Derivitive of the tanh function.
    public float[][] tanhPrime(float[][] x) {
        float[][] output = new float[x.length][x[0].length];
        for(int i = 0; i < output.length; i++) {
            for(int j = 0; j < output[i].length; j++) {
                // Should be sech, but java.math does not have a sech function, so it is derived through cosh.
                output[i][j] = (1 / (float)Math.cosh(x[i][j])) * (1 / (float)Math.cosh(x[i][j]));
            }
        }
        return output;
    }

    // Sigmoid activation function for 2-dimensional inputs
    public float[][] sigmoid(float[][] x) {
        float[][] output = new float[x.length][x[0].length];
        for(int i = 0; i < output.length; i++) {
            for(int j = 0; j < output[i].length; j++) {
                output[i][j] = 1 / (1 + (float)Math.exp(-x[i][j]));
            }
        }
        return output;
    }

    // Derivative of the sigmoid activation function for 2-dimensional inputs.
    public float[][] sigmoidPrime(float[][] x) {
        float[][] output = new float[x.length][x[0].length];
        for(int i = 0; i < output.length; i++) {
            for(int j = 0; j < output[i].length; j++) {
                output[i][j] = (float)Math.exp(-x[i][j]) / (float)Math.pow((1 + (float)Math.exp(-x[i][j])), 2);
            }
        }
        return output;
    }

    // Derivative of the sigmoid activation function for 3-dimensional inputs.
    public float[][][] sigmoidPrime(float[][][] x) {
        float[][][] output = new float[x.length][x[0].length][x[0][0].length];
        for(int c = 0; c < output.length; c++) {
            for(int i = 0; i < output[c].length; i++) {
                for(int j = 0; j < output[c][i].length; j++) {
                    output[c][i][j] = (float)Math.exp(-x[c][i][j]) / (float)Math.pow((1 + (float)Math.exp(-x[c][i][j])), 2);
                }
            }
        }
        return output;
    }

    // Performs basic matrix multiplication for 2D inputs.
    // Assumes the input matrices can be calculated.
    // 1x2 * 2x4 will work, but 1x3 * 4x5 will through an error.
    public float[][] matrixMultiply(float[][] first, float[][] second) {
        float[][] output = new float[first.length][second[0].length];
        for(int i = 0; i < output.length; i++) {
            float[] row = first[i];
            for(int j = 0; j < output[i].length; j++) {
                float[] column = new float[second.length];
                for(int k = 0; k < second.length; k++) {
                column[k] = second[k][j];
                }
                for(int l = 0; l < row.length; l++) {
                output[i][j] += (row[l] * column[l]);
                }
            }
        }
        return output;
    }

    // Performs scalar multiplication against a 2D matrix. 
    public float[][] scaler(float[][] input, float scale) {
        float[][] output = new float[input.length][input[0].length];
        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[i].length; j++) {
                output[i][j] = input[i][j] * scale;
            }
        }
        return output;
    }

    // Performs scalar multiplication for a 3D matrix.
    public float[][][] scaler(float[][][] input, float scale) {
        float[][][] output = new float[input.length][input[0].length][input[0][0].length];
        for(int c = 0; c < input.length; c++) {
            for(int i = 0; i < input[c].length; i++) {
                for(int j = 0; j < input[c][i].length; j++) {
                    output[c][i][j] = input[c][i][j] * scale;
                }
            }
        }
        return output;
    }

    // Performs element-wise multiplication.
    // Assumes both matrices are the same size.
    // Combines two matrices of the same size, 1x2 * 1x2
    public float[][] elementWise(float[][] first, float[][] second) {
        float[][] output = new float[first.length][first[0].length];
        for(int i = 0; i < first.length; i++) {
            for(int j = 0; j < first[i].length; j++) {
                output[i][j] = first[i][j] * second[i][j];
            }
        }
        return output;
    }

    // Performs element-wize mutliplication for 3D inputs.
    public float[][][] elementWise(float[][][] first, float[][][] second) {
        float[][][] output = new float[first.length][first[0].length][first[0][0].length];
        for(int c = 0; c < first.length; c++) {
            for(int i = 0; i < first[c].length; i++) {
                for(int j = 0; j < first[c][i].length; j++) {
                    output[c][i][j] = first[c][i][j] * second[c][i][j];
                }
            }
        }
        return output;
    }

    // Transposes a 2D matrix.
    public float[][] transpose(float[][] input) {
        float[][] output = new float[input[0].length][input.length];
        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[i].length; j++) {
                output[j][i] = input[i][j];
            }
        }
        return output;
    }

    // Adds two matrices together, but if the addition boolean is false then it is subtracted.
    // This could be broken up into two seperate functions, one for adding and one for subtracting.
    public float[][] combine(float[][] first, float[][] second, boolean addition) {
        float[][] output = new float[first.length][first[0].length];
        for(int i = 0; i < first.length; i++) {
            for(int j = 0; j < first[i].length; j++) {
                output[i][j] = (addition) ? first[i][j] + second[i][j] : first[i][j] - second[i][j];
                // If the addition boolean is true then matrices are added if not then they are subtracted.
            }
        }
        return output;
    }

    // Returns a 2D matrix with random values from -1 and 1.
    public float[][] randomizeMatrix(int height, int width) {
        float[][] output = new float[height][width];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                output[i][j] = rand.nextFloat() * ((rand.nextInt(2) == 0) ? -1 : 1);
            }
        }
        return output;
    }

    // Returns a 3D matrix with random values from -1 and 1.
    public float[][][] randomizeMatrix(int channel, int height, int width) {
        float[][][] output = new float[channel][height][width];
        for(int c = 0; c < channel; c++) {
            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    output[c][i][j] = rand.nextFloat() * ((rand.nextInt(2) == 0) ? -1 : 1);
                }
            }
        }
        return output;
    }

    // Returns a 4D matrix with random values from -1 and 1.
    public float[][][][] randomizeMatrix(int count, int channel, int height, int width) {
        float[][][][] output = new float[count][channel][height][width];
        for(int i = 0; i < count; i++) {
            for(int c = 0; c < channel; c++) {
                for(int h = 0; h < height; h++) {
                    for(int w = 0; w < width; w++) {
                        output[i][c][h][w] = rand.nextFloat() * ((rand.nextInt(2) == 0) ? -1 : 1);
                    }
                }
            }
        }
        return output;
    }

    // Convolutes an input(image) and a filter. 
    // Assumes stride is 1 and has not been tested on strides greater than 1.
    public float[][] convolute(float[][] input, float[][] filter, int stride) {
        float[][] output = new float[(input.length - filter.length) + 1][(input[0].length - filter[0].length) + 1];
        for(int y = 0; y < output.length; y += stride) {
            for(int x = 0; x < output[0].length; x += stride) {
                float convolution = 0;
                for(int j = 0; j < filter.length; j++) {
                    for(int k = 0; k < filter[0].length; k++) {
                        convolution += input[j + y][k + x] * filter[j][k];
                    }
                }
                output[y][x] = convolution;
            }
        }
        return output;
    }

    // Allows for inputs and filters with multiple channels
    public float[][] convolute(float[][][] input, float[][][] filter, int stride) {
        // Possibly make the output size calculation not as complicated by using predetermined values.
        int height = ((input[0].length - filter[0].length) + 1) / stride;
        int width = ((input[0][0].length - filter[0][0].length) + 1) / stride;
        float[][] output = new float[height][width];
        for(int y = 0; y < output.length; y += stride) {
            for(int x = 0; x < output[0].length; x += stride) {
                float convolution = 0;
                for(int c = 0; c < filter.length; c++) {
                    for(int j = 0; j < filter[0].length; j++) {
                        for(int k = 0; k < filter[0][0].length; k++) {
                            convolution += input[c][j + y][k + x] * filter[c][j][k];
                        }
                    }
                }
                output[y][x] = convolution;
            }
        }
        return output;
    }

    /*
    Assumes the stride and width/height is the same
    This function is really weird.
    Because of how the derivative of the max pool function works, it is easier(computational faster) to calculate it now.
    It returns an array that contains the actual output and the derivative form. 
    The derivative form is only calculated if the boolean is true.
    */
    public float[][][] maxPool(float[][] input, int width, int height, boolean calculateDerivative) {
        float[][] derivative = null; 
        if(calculateDerivative) {
            derivative = new float[input.length][input[0].length];
        }
        float[][] output = new float[input.length / height][input[0].length / width];
        for(int y = 0; y < output.length; y++) {
            for(int x = 0; x < output[0].length; x++) {
                float max = 0;
                int[] maxPosition = null;
                // Loops through a 2x2 region in the input.
                for(int i = 0; i < height; i++) {
                    for(int j = 0; j < width; j++) {
                        float currentValue = input[i + (y * height)][j + (x * width)];
                        if(currentValue > max) {
                            max = currentValue;
                            maxPosition = new int[]{(i + (y * height)), (j + (x * width))};
                        }
                    }
                }
                output[y][x] = max;
                if(calculateDerivative && maxPosition != null) {
                    // I am not sure whether the derivative form is the actual max value or 1. Although I believe it is the max value.
                    derivative[maxPosition[0]][maxPosition[1]] = max;
                }
            }
        }
        return new float[][][]{output, derivative};
    }

    // Converts a 3D input to a 2D input, but it is effectivally a 1D input.
    // For a 256x256 image it is a 1x92256 matrix.
    public float[][] convertToFullyConnected(float[][][] input, int inputX, int inputY, int filterOneSize, int filterTwoSize) {
        int inputSizeX =  (((inputX - filterOneSize + 1) / 2) - filterTwoSize + 1) / 2; // 2 is the maxPool size
        int inputSizeY =  (((inputY - filterOneSize + 1) / 2) - filterTwoSize + 1) / 2; // 2 is the maxPool size
        int fullyConnectedHeight = inputSizeX * inputSizeY * 24; // You can do this by just taking the inputs height and width;

        float[][] output = new float[1][fullyConnectedHeight];
        int counter = 0;
        for(int c = 0; c < input.length; c++) {
            for(int y = 0; y < input[0].length; y++) {
                for(int x = 0; x < input[0][0].length; x++) {
                    output[0][counter] = input[c][y][x];
                    counter++;
                }
            }
        }
        return output;
    }
}
