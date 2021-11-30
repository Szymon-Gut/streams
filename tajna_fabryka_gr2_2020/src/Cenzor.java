package pl.edu.pw.mini.jrafalko;

import pl.edu.pw.mini.jrafalko.annotations.*;
import pl.edu.pw.mini.jrafalko.censor.Censorable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Cenzor implements Censorable {
    @Override
    public List<Pracownik> cenzuruj(List<Pracownik> list) {
        Iterator<Pracownik> iterator = list.iterator();
        while(iterator.hasNext()){
            Pracownik p = iterator.next();
            //Zad1
            if(p.getClass().getDeclaredAnnotation(SekretarkaAdnotacja.class) != null) {
                List<Field> fields = new ArrayList<>(List.of(p.getClass().getDeclaredFields()));
                Class<?> superclass = p.getClass().getSuperclass();
                while (superclass != null) {
                    fields.addAll(List.of(superclass.getDeclaredFields()));
                    superclass = superclass.getSuperclass();
                }
                for(Field field : fields) {
                    if(field.getType().equals(String.class)) {
                        field.setAccessible(true);
                        try {
                            field.set(p, "");
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            //Zad2
            List<Field> fields1 = Arrays.asList(p.getClass().getDeclaredFields());
            fields1.forEach(f -> {
                f.setAccessible(true);
                if (f.getDeclaredAnnotation(Podmianka.class) != null) {
                    try {
                        f.set(p, ((String)f.get(p)).substring(0,3) + "___");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
            //Zad3
            for (Field field : fields1) {
                PodmiankaParametr podmiankaParametrAnnotation = field.getDeclaredAnnotation(PodmiankaParametr.class);
                if (podmiankaParametrAnnotation != null) {
                    field.setAccessible(true);
                    try{
                        field.set(p, podmiankaParametrAnnotation.tekstNaPodmiane());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            //zad4
            ArrayList<Field> fields = new ArrayList<>(Arrays.asList(p.getClass().getDeclaredFields()));
            Class<?> superclass = p.getClass().getSuperclass();
            while(superclass != null) {
                fields.addAll(Arrays.asList(superclass.getDeclaredFields()));
                superclass = superclass.getSuperclass();
            }
            fields.forEach(f -> {
                if(f.getDeclaredAnnotation(NonIntTypeAnnotation.class) != null && f.getType().equals(int.class)){
                    f.setAccessible(true);
                    try {
                        f.set(p,0);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
            //zad5
            List<Method> methods = new ArrayList<>(Arrays.asList(p.getClass().getDeclaredMethods()));
            methods.forEach(f -> {
                Powtorz powtorz = f.getAnnotation(Powtorz.class);
                if (powtorz != null) {
                    f.setAccessible(true);
                    for (int i =0; i < powtorz.times(); i++) {
                        try {
                            f.invoke(p);
                        } catch (InvocationTargetException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
            }});
            //zad6
            fields = new ArrayList<>(Arrays.asList(p.getClass().getDeclaredFields()));
            UstawLiczbyLubProdukty adnotacja = p.getClass().getAnnotation(UstawLiczbyLubProdukty.class);
            if(adnotacja != null) {
                fields.forEach(f -> {
                    if (f.getType().equals(Produkty.class)) {
                        f.setAccessible(true);
                        try{
                            f.set(p, Produkty.BOMBKI);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    if (f.getType().equals(int.class)) {
                        f.setAccessible(true);
                        try{
                            f.set(p, -1);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            if(p.getClass().getDeclaredAnnotation(WiekMniejszyNiz30.class) != null) {
                if(p.wiek < 30) {
                    iterator.remove();
                }
            }

        }

        return list;
    }
}
