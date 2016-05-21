package com.nguyen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeFactory {
   static List<String> sJokes = new ArrayList<>();
   static {
      sJokes.add("How do I disable the autocorrect function on my wife?");
      sJokes.add("Never get on one knee for a girl who won't get on two for you.");
      sJokes.add("What's the difference between your wife and your job? After five years your job will still suck.");
      sJokes.add("Any married man should forget his mistakes, there's no use in two people remembering the same thing.");
      sJokes.add("It is much easier to apologize than to ask permission.");
      sJokes.add("A good wife always forgives her husband when she's wrong.");
      sJokes.add("What's the difference between a paycheck and a penis? You don't have to beg your wife to blow your paycheck.");
      sJokes.add("I haven't spoken to my wife for 18 months- I don't like to interrupt her.");
      sJokes.add("Childs experience: if a mother is laughing at the fathers jokes, it means they have guests.");
      sJokes.add("Top 3 situations that require witnesses: 1) Crimes 2) Accidents 3) Marriages Need I say more?");
   }

   public static String getJoke() {
      return sJokes.get(new Random().nextInt(sJokes.size()));
   }
}
