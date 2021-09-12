import java.util.Random;

import java.lang.Math;

public class MathFunction {

    Random rand;

    public MathFunction() {
        rand = new Random();
    }

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

    public float[][] tanh(float[][] x) {
        float[][] output = new float[x.length][x[0].length];
        for(int i = 0; i < output.length; i++) {
            for(int j = 0; j < output[i].length; j++) {
                output[i][j] = (float) Math.tanh(x[i][j]);
            }
        }
        return output;
    }

    public float[][] tanhPrime(float[][] x) {
        float[][] output = new float[x.length][x[0].length];
        for(int i = 0; i < output.length; i++) {
            for(int j = 0; j < output[i].length; j++) {
                output[i][j] = (1 / (float)Math.cosh(x[i][j])) * (1 / (float)Math.cosh(x[i][j]));
            }
        }
        return output;
    }

    public float[][] sigmoid(float[][] x) {
        float[][] output = new float[x.length][x[0].length];
        for(int i = 0; i < output.length; i++) {
            for(int j = 0; j < output[i].length; j++) {
                output[i][j] = 1 / (1 + (float)Math.exp(-x[i][j]));
            }
        }
        return output;
    }

    public float[][] sigmoidPrime(float[][] x) {
        float[][] output = new float[x.length][x[0].length];
        for(int i = 0; i < output.length; i++) {
            for(int j = 0; j < output[i].length; j++) {
                output[i][j] = (float)Math.exp(-x[i][j]) / (float)Math.pow((1 + (float)Math.exp(-x[i][j])), 2);
            }
        }
        return output;
    }

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

    public float[][] scaler(float[][] input, float scale) {
        float[][] output = new float[input.length][input[0].length];
        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[i].length; j++) {
                output[i][j] = input[i][j] * scale;
            }
        }
        return output;
    }

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

    public float[][] elementWise(float[][] first, float[][] second) {
        float[][] output = new float[first.length][first[0].length];
        for(int i = 0; i < first.length; i++) {
            for(int j = 0; j < first[i].length; j++) {
                output[i][j] = first[i][j] * second[i][j];
            }
        }
        return output;
    }

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

    public float[][] transpose(float[][] input) {
        float[][] output = new float[input[0].length][input.length];
        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[i].length; j++) {
                output[j][i] = input[i][j];
            }
        }
        return output;
    }

    public float[][] combine(float[][] first, float[][] second, boolean addition) {
        float[][] output = new float[first.length][first[0].length];
        for(int i = 0; i < first.length; i++) {
            for(int j = 0; j < first[i].length; j++) {
                output[i][j] = (addition) ? first[i][j] + second[i][j] : first[i][j] - second[i][j];
            }
        }
        return output;
    }

    public float[][] randomizeMatrix(int height, int width) {
        float[][] output = new float[height][width];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                output[i][j] = rand.nextFloat() * ((rand.nextInt(2) == 0) ? -1 : 1);
            }
        }
        return output;
    }

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
        // Possibly make the output size calculation not as complicated
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

    // Assumes the stride and width/height is the same
    // Calculates the derivative and regular maxPool of the input
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
                    derivative[maxPosition[0]][maxPosition[1]] = max; // Maybe make this 1 instead
                }
            }
        }
        return new float[][][]{output, derivative};
    }

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
