package pl.edu.pw.mini.jrafalko.train;


import pl.edu.pw.mini.jrafalko.TrolleyCompany;

public class Trolley {
    @TrolleyCompany(nazwaProducenta = "Zbyszek")
    public static String sticker = "Góra, dół, góra dół...";
    @TrolleyCompany(wielkoscDrezyny = "16")
    private int trolleySize = 4;

}
