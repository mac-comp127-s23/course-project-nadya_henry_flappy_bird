# Flappy Bird
## Created by Nadya, Kynan, and Henry
### Original Game by Dong Nguyen

<b>Project description:</b> The project is a simple implementation of the popular game "Flappy Bird," where the user controls a bird by clicking or pressing the space bar to avoid obstacles and score points. We set out to bring all functionality of the original game to our project, and we've achieved that.

<b>Technical guide:</b> The program requires Java 17 and the Kilt Graphics library. The main class is "FlappyBird" in the "flappybird" package. To run the program, simply compile and run the "FlappyBird.java" file, which will open a new window and start the game. The game will start when the user clicks the mouse or presses the space bar.

One of the more <b>challenging</b> parts of creating this project was refining the collisions between the bird and the pipes. Not only did we have to figure out a way to get information from a bird object to a PipesHandler object in a secure manner, we had to make sure that the player would feel responsible for losing, rather than getting frustrated and blaming shoddy programming. The functionality we settled on feels fair, but not too lenient.

<b>Acknowledgments:</b> The implementation is loosely inspired by Macalester's BubbleBlitz game. Images were taken from the original Flappy Bird game.

Regarding design limitations, there are no fundamental issues in the code, but there are some areas that could be improved:
One improvement could be adding more collision points, which would make it impossible for the bird image could clip into a pipe (which is,
at present, already an uncommon occurence).
In terms of specific bugs or glitches, the code is functional, but some potential issues could arise depending on the user's environment. For example, the game may not run properly if the image resources are not located in the correct directory. Another issue could be the responsiveness of the game, which may depend on the user's hardware and processing power.

<b>Societal Impact:</b>
When it comes to societal impact, there are concerns about the potential harm the game could cause to vulnerable populations, such as users with medical conditions or disabilities. The game's fast-paced movement and flashing colors could be distressing or triggering for some, while its control scheme may pose challenges for those with limited mobility or dexterity. To address these issues, the game could incorporate accessibility options, such as the ability to adjust the game's speed, use color palettes that are less likely to cause adverse reactions, or implement alternative control schemes to accommodate a wider range of abilities.

Lastly, the addictive nature of the game is a concern. Flappy Bird's simple mechanics can lead to excessive screen time and negative effects on mental and physical health. Although it's not the developer's responsibility to prevent users from playing too much, it's worth considering the potential impact on users and society. 

To gain valuable insights into the game's potential unintended consequences, feedback could be solicited from a diverse group of players, including individuals with disabilities. By being mindful of these issues and working towards inclusive and equitable software development practices, the project can be responsible and benefit society as a whole.

Press <kbd>Space</kbd> or <kbd>Click</kbd> to start!
