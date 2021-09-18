# Italian Ice Convolutional Neural Network
A Convolutional Neural Network that can detect different flavors of italian ice. Specifically, an input image *(256, 256)* gets processed and is able to detect what flavor of ice, and what size.

# Index
 - [Main Problem](#main-problem)
 - [Main Solution](#main-solution)
 - [Progress](#progress)
 - [Understanding the code/architecture](#understanding-the-code)
 - [File structure](#file-structure)
   - [Math Function](#math-function)
   - [Image Input](#image-input)
   - [CNN](#cnn) 
 - [Permission](#permission)
 - [Help](#help)

## Main Problem
As the policy states, *"After 2 days each italian ice must be thrown out"*, this creates lots of **waste**. The ice has to be made before the day starts, so what if you could make only the amount ice that would be sold in a day? Effectively eliminating **all** possible waste. You would essientally predict how much of each ice flavor and size would be sold before the store opens. This would be based on *weather, day of week, time of year, etc.* To be able to predict this you need **data**. Specifically how much ice is typically sold on *hot days, cold days, during May vs June, etc.* Theoretically you could track each order by placing a camera above each register which would take a picture of the order. Then the image would be processed through a Convolutional Neural Network that would detect the flavor of ice and size. This could track the order taken.

## Main Solution
This repository is not the prediction part of the problem, but gathering data part. It is a Convolutional Neural Network built in java from scratch. Meaning it is in **plain vanilla java**. No other libraries are neccesary! The code effectively can be used to process any image not just italian ice, so **feel free** to use it for any problem you see fit.

## Progress
 - The forward propogation is effectively complete. 
 - The backpropogation step has yet to be completed.
 - Various improvements regarding efficiency are still needed.

## Understanding the code
The main CNN.java file is what controls everything. As everything is written in Java the *main* function is a common starting point. First an image is taken from the image folder and is processed into memory as a matrix of color values. The each input value is then normalized and completes the forward propogation process. The model consists of **5 layers**. **2** convolutional layers *(Including activation and pooling)* followed by **3** fully connected layers *(Not including the output)*. The first convolutional layer uses a **3x5x5** filter *(3 is for each color channel, RGB)* with 1 stride and no padding. Then the *ReLU* activation function is used followed by max pooling in a 2x2 region. This is done **12** times to create 12 different feature maps. The next convolutional layer uses a **24x3x3** filter *(24 is for each feature map)* which is done **24** times to create 24 different feature maps. Which is followed by another *ReLU* activation function and 2x2 max pooling. The output is converted into a 1 dimensional array of numbers. Then the fully connected layers take the input and pass it into 2 hidden layers which have **500** and **200** nodes respectively. There is a *sigmoid* activation function applied after each layer as well. This results in 3 different weights for the fully connected layers. The output layer contains one node. Closer to 1 means it is a specific flavored ice, *such as cherry*, and 0 being it is not a specific flavored ice, *such as cherry*.

## File structure
#### Math Function
This contains all the math functions that are not found in vanilla java. Each function contains its own purpose although some are used for testing different scenerios such as the different activation functions *(Sigmoid, Tanh, ReLU)*.

#### Image Input
The image is processed and is converted into a 3-D array where the 3 RGB channels represent the 3rd dimension.(X and Y are the other dimenions). The file assumes all images are based on the same resolution and similiar features.

#### CNN
This file contains all the actual processing. It is the main file that everything stems from.

## Permission
Please feel free to download, copy, and reuse the code as necessary. This repository is under a *MIT liscence*, so do be mindful of that.

## Help
If you are not familiar with how Convolutional Neural Networks, *CNN*, work please check out the links below. They are mostly ranked from least to most complex. These are just articles/videos that I personally found helpful.

**General:**
- https://www.analyticsvidhya.com/blog/2021/05/convolutional-neural-networks-cnn/
- https://youtube.com/playlist?list=PLZHQObOWTQDNU6R1_67000Dx_ZCJB-3pi *(3Blue1Brown neural network youtube series. Approx. 1 hr)*
- https://towardsdatascience.com/understanding-neural-networks-what-how-and-why-18ec703ebd31 *(About neural networks in general)*

**More math based:**
- https://www.analyticsvidhya.com/blog/2020/02/mathematics-behind-convolutional-neural-network/
- https://www.jefkine.com/general/2016/09/05/backpropagation-in-convolutional-neural-networks/
- http://people.tamu.edu/~sji/classes/bp.pdf
