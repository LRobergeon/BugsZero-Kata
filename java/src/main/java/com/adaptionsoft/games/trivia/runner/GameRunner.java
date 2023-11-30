
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		playGame(rand);
	}

	public static void playGame() {
		// Random peu être instancé ici plutôt que passé en paramètre
		Random rand = new Random();
		Game aGame = new Game();

		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");


		do {

			aGame.roll(rand.nextInt(5) + 1);

			// Je préfère l'idée de passer le nombre directement à la fonction de Game, plutôt que d'appeler une fonction différente.
			// On pourrai avoir une seul fonction : checkAnwser(rand.nextInt(9)) ou checkAnwser(rand.nextInt(9) == 7) qui traite ensuite la valeur
			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}

		} while (notAWinner);
	}
}
