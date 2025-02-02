import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int randomWord = (int) (Math.random() * 496);
        Path wordPath = Paths.get("engWords.txt");
        Scanner engWord = new Scanner(wordPath);
        String hledane = "";
        for (int i = 0; i < randomWord; i++) {
            hledane = engWord.nextLine();
        }
        /// vyber nahodneho slovo
        boolean spravne = false;
        Scanner input = new Scanner(System.in);
        boolean znaky = false;
        String red = "\u001B[31m";
        String yellow = "\u001B[33m";
        int pokusy = 0;
        for (int neco = 0; neco < 5; neco++) {
            String inputWord = input.nextLine();
            if (inputWord.length() != 5) {
                System.out.println("Zadané slovo nemá přesně 5 znaků!");
            } else {
                znaky = true;

            }
            while (!spravne && pokusy < 5) {
                pokusy++;
                if (inputWord.length() != 5) {
                    System.out.println("Zadané slovo nemá přesně 5 znaků!");
                } else {
                    znaky = true;
                }
                if (znaky) {
                    String[] colors = new String[5];
                    for (int barvičky = 0; barvičky < 5; barvičky++) {
                        colors[barvičky] = "\u001B[30m";
                    }
                    if (inputWord.equals(hledane)) {
                        spravne = true;
                        for (int i = 0; i < colors.length; i++) {
                            colors[i] = red;
                        }
                        System.out.println("Našli jste hledané slovo.");
                        psani(colors, inputWord);
                        break;
                    }
                    for (int i = 0; i < inputWord.length(); i++) {
                        if (inputWord.charAt(i) == hledane.charAt(i)) {
                            colors[i] = red; /// setting color to red?
                        } else {
                            for (int j = 0; j < hledane.length(); j++) {
                                if (hledane.charAt(j) == inputWord.charAt(i)) {
                                    colors[i] = yellow; /// setting color to yellow?
                                }
                            }
                        }
                    }
                    psani(colors, inputWord);
                    System.out.println("");
                    inputWord = input.nextLine();
                }
            }
            if (!spravne) {
                System.out.println("Nenašli jste hledané solovo, bylo to: " + hledane);
            }
        }
    }

    public static void psani(String[] colors, String inputWord) {
        for (int i = 0; i < colors.length; i++) {
            System.out.print(colors[i] + inputWord.charAt(i) + "\u001B[0m");
        }
    }
}
