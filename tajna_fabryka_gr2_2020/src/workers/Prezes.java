package pl.edu.pw.mini.jrafalko.workers;

import pl.edu.pw.mini.jrafalko.Pracownik;
import pl.edu.pw.mini.jrafalko.annotations.LiczbaLubProdukt;
import pl.edu.pw.mini.jrafalko.annotations.Podmianka;
import pl.edu.pw.mini.jrafalko.annotations.PodmiankaParametr;
import pl.edu.pw.mini.jrafalko.annotations.UstawLiczbyLubProdukty;

@UstawLiczbyLubProdukty(liczbalubProdukt = LiczbaLubProdukt.LICZBA)
public class Prezes extends Pracownik {

    private String ksywka;
    @PodmiankaParametr(tekstNaPodmiane = "Władywostok")
    private String miastoUrodzenia;
    @Podmianka
    private String charakterystykaOsobowosci;
    private int iloscPodwladnych;

    public Prezes(String imie, String nazwisko, int wiek, String ksywka,
                  String miastoUrodzenia, String charakterystykaOsobowosci,
                  int iloscPodwladnych) {
        super(imie, nazwisko, wiek);
        this.ksywka = ksywka;
        this.miastoUrodzenia = miastoUrodzenia;
        this.charakterystykaOsobowosci = charakterystykaOsobowosci;
        this.iloscPodwladnych = iloscPodwladnych;
    }

    @Override
    protected void zwiekszZysk() {
        wypracowanyZysk += 10;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", ksywka='" + ksywka + '\'' +
                ", miastoUrodzenia='" + miastoUrodzenia + '\'' +
                ", charakterystykaOsobowosci='" + charakterystykaOsobowosci + '\'' +
                ", iloscPodwladnych=" + iloscPodwladnych +
                ", prezes";
    }
}
