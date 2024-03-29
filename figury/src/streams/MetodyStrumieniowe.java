package pl.edu.pw.mini.jrafalko.streams;

import pl.edu.pw.mini.jrafalko.figures.Figura;

import java.util.List;
import java.util.Map;

public interface MetodyStrumieniowe {

    Figura getNajwiekszaFigure(); //Względem właściwości(pole klasy) wysokość
    Figura getFigureONajmniejszymPolu(); //Pole powierzchni
    Figura getNajwyzszaFigure3D();
    Figura getNajmniejszyStozek();
    List<Figura> getPosortowaneWzgledemPowiezchni();
    Figura getDrugaZPosortowanychMalejacoWgObwodu();
    List<Figura> getPierwszePiecPosortowaneRosnacoWgPowierzchni();
    List<Figura> getSzesciany();
    Figura getNajmniejszeKolo();
    Map<Integer, Figura> mapaWgId();
    int getiloscMalych(double pole); //5 małych figur względem pola
    List<Figura> posortowaneWgPolaZaczynajacOd(int nr);
}
