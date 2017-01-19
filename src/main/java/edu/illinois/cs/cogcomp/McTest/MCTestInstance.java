package edu.illinois.cs.cogcomp.McTest;

public class MCTestInstance {
    public String instanceInfo;
    public String turkInfo;

    public String story;
    String[] questions = new String[4];
    String[][] options = new String[4][4];
    //int [] correctAnswer = new int[16];
    String[] correctAnswers = new String[4];
    int[] correctAnswersInt = new int[4];
    boolean[] NeedsMultipleSentences = new boolean[4];

    // curator annotation
    public byte[] storyAnt;
    public byte[][] questionAnt;

    @Override
    public String toString() {
        String str = instanceInfo + "\n" +
                turkInfo + "\n" +
                story + "\n";
        for (int i = 0; i < 4; i++) {
            str += questions[i] + "\n";
            for (int j = 0; j < 4; j++) {
                str += options[i][j] + "\n";
            }
        }
        return str;
    }
}
