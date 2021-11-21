package pl.edu.pw.mini.jrafalko.streams;

import pl.edu.pw.mini.jrafalko.figures.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImplementacjaInterfejsu implements MetodyStrumieniowe {

    List<Figura> figury;

    public ImplementacjaInterfejsu(List<Figura> fig) {this.figury = fig; }

    /**
     * 1
     * @return Największa figura względem właściwości(pole klasy) wysokosc
     * 0,5 pkt
     */
    @Override
    public Figura getNajwiekszaFigure() {
        return figury
                .stream()
                .max(Comparator.comparing(Figura::getWysokosc))
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * 2
     * @return Figura o najmniejszym polu powierzchni
     * 0,5 pkt
     */
    @Override
    public Figura getFigureONajmniejszymPolu() {
        return figury
                .stream()
                .min(Comparator.comparing(Figura::polePowierzchni))
                .orElseThrow(NoSuchElementException::new);
    }


    /**
     * 3
     * @return Najwyższa figura 3D
     * 0,5 pkt
     */
    @Override
    public Figura getNajwyzszaFigure3D() {
        return figury
                .stream()
                .filter(Figura3D.class::isInstance)
                .map (Figura3D.class::cast)
                .max(Comparator.comparing(Figura::polePowierzchni))
                .orElseThrow(NoSuchElementException::new);
            }


    /**
     * 4
     * @return Najmniejszy stożek względem objętości
     * 1 pkt
     */
    @Override
    public Figura getNajmniejszyStozek() {
        return figury
                .stream()
                .filter(Stozek.class::isInstance)
                .map (Stozek.class::cast)
                .min(Comparator.comparing(Stozek::objetosc))
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * 5
     * @return Lista figur posortowanych względem pola powierzchni
     * 0,5 pkt
     */
    @Override
    public List<Figura> getPosortowaneWzgledemPowiezchni() {
        return figury
                .stream()
                .sorted(Comparator.comparingDouble(Figura::polePowierzchni))
                .collect(Collectors.toList());
    }

    /**
     * 6
     * @return Druga figura z posortowanych malejaco względem obwodu
     * 1 pkt
     */
    @Override
    public Figura getDrugaZPosortowanychMalejacoWgObwodu() {
        return figury
                .stream()
                .sorted(Comparator.comparing(Figura::obwod).reversed())
                .skip(1)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * 7dd
     * @return Lista pięciu pierwszych figur posortowanych rosnąco względem pola powierzchni,
     * o wysokości nie większej niż 10 i polu powierzchni nie mniejszym niż 10
     * 1 pkt
     */
    @Override
    public List<Figura> getPierwszePiecPosortowaneRosnacoWgPowierzchni() {
        return figury
                .stream()
                .filter(fig -> fig.getWysokosc() <= 10 && fig.polePowierzchni() >= 10)
                .sorted(Comparator.comparing(Figura::polePowierzchni))
                .collect(Collectors.toList());
    }

    /**
     * 8
     * @return Lista wszystkich sześcianów o długości boku nie większej niż 10
     * 1 pkt
     */
    @Override
    public List<Figura> getSzesciany() {
        return figury
                .stream()
                .filter(Szescian.class::isInstance)
                .filter(e -> e.getWysokosc() <= 10)
                .collect(Collectors.toList());
    }

    /**
     * 9
     * @return Koło o najmniejszym polu powierzchni
     * 0,5 pkt
     */
    @Override
    public Figura getNajmniejszeKolo() {
        return figury
                .stream()
                .filter(Kolo.class::isInstance)
                .map (Kolo.class::cast)
                .min(Comparator.comparing(Kolo::polePowierzchni))
                .orElseThrow(NoSuchElementException::new);

    }

    /**
     * 10
     * @return Mapa figur względem ID
     * 1 pkt
     */
    @Override
    public Map<Integer, Figura> mapaWgId() {
        return figury
                .stream()
                .collect(Collectors.toMap(Figura::getId, p-> p));
    }

    /**
     * 11
     * @param pole Max pole powierzchni
     * @return Ilość figur o polu powierzchni nie większym niż pole
     * 0,5 pkt
     */
    @Override
    public int getiloscMalych(double pole) {
        return (int) figury
                .stream()
                .filter(fig -> fig.polePowierzchni() <= pole)
                .count();
    }

    /**
     * 12
     * @return Posortowany ciąg figur zaczynając od podanej
     * 0,5 pkt
     */
    @Override
    public List<Figura> posortowaneWgPolaZaczynajacOd(int nr) {
        return figury
                .stream()
                .sorted(Comparator.comparing(Figura::polePowierzchni))
                .skip(nr-1)
                .collect(Collectors.toList());
    }
}
