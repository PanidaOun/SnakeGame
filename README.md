# PA4-Snake Game
# Snake Game
Snake game is the common name for a video game concept where the player maneuvers a line which grows in length, with the line itself being a primary obstacle.

![Imgur](https://i.imgur.com/n9w2jh0.png)
## How to play Snake Game
The player controls a snake on a bordered plane with the arrow keys on the keyboard. As it moves forward, it leaves a trail behind, resembling a moving snake. The end of the trail is in a fixed position, so the snake continually gets longer if they eat the food. For with every meal,the player will get a point but the snake also moves faster . The snake has a specific length, so the moving tail is a fixed number of units away from the head. The player will lose when the snake runs into the screen border, a trail , other obstacles, or itself. ** (player must input your name not number or leave it empty )**

![Snake Game Demo](src/gif/snake.gif)


## How to use the application.
1. Open terminal and cd to the file that collect SnakeGame.jar file.
2. Use this command java -jar SnakeGame.jar or if you use Java 11, you need to specify the module path for JavaFX. Enter: java --module-path (your javafx path)--add-modules javafx.controls -jar SnakeGame.jar to open the application.

