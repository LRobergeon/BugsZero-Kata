package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			// Il faut être conforme dans les developpements, utiliser des fonctions partout
			// On peu aussi factoriser les appels
			// On pourrai définir les strings dans un fichier de constante,
			// cela évite d'avoir à les chercher partout dans le code si on doit les modifiers
			popQuestions.addLast(createQuestions("Pop Question ", i));
			scienceQuestions.addLast(createQuestions("Science Question ", i));
			sportsQuestions.addLast(createQuestions("Sports Question ", i));
			rockQuestions.addLast(createQuestions("Rock Question ", i));
    	}
    }

	public String createQuestions(String typeQuestion, int index){
		return typeQuestion + " " + index;
	}

	public boolean isPlayable() {
		return players.size() >= 2;
	}

	// Pas besoin de return un boolean
	public void add(String playerName) {
		
		
	    players.add(playerName);
		// Utiliser directement players.size() plutôt que howManyPlayers(), qui est plus perturbant
	    places[players.size()] = 0;
	    purses[players.size()] = 0;
	    inPenaltyBox[players.size()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				movePlayerAndAskQuestion(roll);
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}
			
		} else {

			movePlayerAndAskQuestion(roll);
		}
		
	}

	private void movePlayerAndAskQuestion(int roll) {
		places[currentPlayer] = places[currentPlayer] + roll;
		// Utiliser un modulo
		if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

		System.out.println(players.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer]);
		System.out.println("The category is " + currentCategory());
		askQuestion();
	}

	private void askQuestion() {
		if (currentCategory() == "Pop")
			System.out.println(popQuestions.removeFirst());
		if (currentCategory() == "Science")
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory() == "Sports")
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory() == "Rock")
			System.out.println(rockQuestions.removeFirst());		
	}
	
	
	private String currentCategory() {
		int place = places[currentPlayer];
		// Ne pas mettre dans le désordre, mais surtout regroupé et factorisé
		// On peu utiliser un modulo aussi pour améliorer les conditions
		// if(place == 0 || place == 4 || place == 8){
		if(place % 4 == 0){
			return "Pop";
		} else if(place % 4 == 1){
			return "Science";
		} else if(place % 4 == 2){
			return "Sports";
		} else {
			return "Rock";
		}
	}

	public boolean wasCorrectlyAnswered() {
		// On peu factoriser certaines partie du code
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				// On peu factoriser cette partie ou la réponse est correcte, par exemple l'extraire en fonction (ou refacto les if)
				// et utiliser cette fonction ici et dans le else à la ligne 144, car les 2 font la même chose
				System.out.println("Answer was correct!!!!");
				currentPlayer++;
				// Faire un modulo (On pourrais extraire cette methode dans une fonction car on l'utilise à plusieurs endroits)
				if (currentPlayer == players.size()) currentPlayer = 0;
				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer)
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");

			} else {
				currentPlayer++;
				// Faire un modulo
				if (currentPlayer == players.size()) currentPlayer = 0;
			}

		} else {
		
			System.out.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");

			currentPlayer++;
			// Utiliser un modulo
			if (currentPlayer == players.size()) currentPlayer = 0;

		}
		return didPlayerWin();
	}
	
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		// Utiliser un modulo plutôt que ce If
		if (currentPlayer == players.size()) currentPlayer = 0;
		// On renvoie toujours True, on peu définir la variable utilisé plus haut à True directement
		return true;
	}


	private boolean didPlayerWin() {
		// Plus simple comme cela
		return purses[currentPlayer] < 6;
	}
}
