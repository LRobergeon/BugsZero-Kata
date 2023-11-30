package com.adaptionsoft.games.trivia;

import static org.junit.Assert.*;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.io.*;
import java.util.Random;
import java.util.stream.IntStream;

public class GameTest {

	@Test
	public void itsLockedDown() throws Exception {

        Random randomizer = new Random(123455);
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));

        IntStream.range(1,15).forEach(i -> GameRunner.playGame(randomizer));

        Approvals.verify(resultStream.toString());

	}

    /**
     * Ajouter d'autres tests
     *
     * Par exemple tester le fonctionnement du programme
     * Verifier si après une bonne réponse, on a bien 1 morceau du trivial poursuite
     * Verifier 0 morceau si erreur
     *
     * Verifier les cas possible d'erreur (Il se passe quoi si on a tiré toute les questions pop ?)
     *
     *
     */


}
