import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        int wordNumber = 0;

        do
        {
            String currentWord = StdIn.readString();
            wordNumber++;

            if (StdRandom.bernoulli((double)1 / wordNumber))
            {
                champion = currentWord;
            }
        } while (!StdIn.isEmpty());

        StdOut.println(champion);
    }
}
