package pl.edu.pw.mini.jrafalko;

import com.sun.jdi.InterfaceType;
import pl.edu.pw.mini.jrafalko.cargo.Car;
import pl.edu.pw.mini.jrafalko.cargo.DiningCar;
import pl.edu.pw.mini.jrafalko.cargo.PassengerCar;
import pl.edu.pw.mini.jrafalko.cargo.TankWagon;
import pl.edu.pw.mini.jrafalko.train.Train;
import pl.edu.pw.mini.jrafalko.train.Trolley;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static List<Car> lista = new ArrayList<>();
    static Train pociag;

    public static void main(String[] args) {

        System.out.println("\n--------1---------");
        task1();
        System.out.println("\n--------2---------");
        task2();
        System.out.println("\n--------3---------");
        task3();
        System.out.println("\n--------4---------");
        task4();
        System.out.println("\n--------5---------");
        task5();
        System.out.println("\n--------6---------");
        task6();
        System.out.println("\n--------7---------");
        task7();
        System.out.println("\n--------8---------");
        task8();
        System.out.println("\n--------9---------");
        task9();
        System.out.println("\n--------10--------");
        task10();
        System.out.println("\n--------11--------");
        task11();
        System.out.println("\n--------12--------");
        task12();
        System.out.println("\n--------13--------");
        task13();
        System.out.println("\n--------14--------");
        task14();
        System.out.println("\n--------15--------");
        task15();
    }

    public static void task1() {
        Class class1 = TankWagon.class;
        Constructor[] constructors = class1.getDeclaredConstructors();
        System.out.println(constructors.length);
        for (Constructor constructor : constructors) {
            System.out.println(" " + constructor);
        }
    }

    public static void task2() {
        try {
            Class<?> class1 = Class.forName("pl.edu.pw.mini.jrafalko.cargo.PassengerCar");
            Constructor<?> constructor = class1.getDeclaredConstructor(boolean.class, String.class);
            System.out.println(constructor);
            System.out.println(Modifier.isProtected(constructor.getModifiers()));
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        //...
    }

    public static void task3() {
        Class<?> car = Car.class;
        System.out.println(car.getPackage());

    }

    public static void task4() {
        try {
            Class<?> diningCar = Class.forName("pl.edu.pw.mini.jrafalko.cargo.DiningCar");
            Method[] methods = diningCar.getDeclaredMethods();
            System.out.println("Metody zadeklarowane");
            System.out.println(Arrays.toString(methods));
            System.out.println("Argumenty w tych metodach");
            for (Method method : methods) {
                if (Modifier.isPrivate(method.getModifiers())) {
                    System.out.println(Arrays.toString(method.getParameterTypes()));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void task5() {
        try {
            Class<?> c = Trolley.class;
            Field field = c.getField("sticker");
            System.out.println(field.get(c));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void task6() {
        Class<?> c = TankWagon.class;
        System.out.println(c.getSuperclass().getName());
    }

    public static void task7() {
        Class<?> c = Car.class;
        Class<?>[] interfaces = c.getInterfaces();
        System.out.println(Arrays.toString(interfaces));
        System.out.println("Te interfejsy w pakiecie: ");
        for (Class<?> i : interfaces) {
            if (i.getPackage() == c.getPackage()) {
                System.out.println(i.getName());
            }
        }
    }

    public static void task8() {
        try {

            Class c = Class.forName("pl.edu.pw.mini.jrafalko.train.Trolley");
            Object trolley = c.newInstance();
            Field field = c.getDeclaredField("trolleySize");
            field.setAccessible(true);
            System.out.println(field.get(trolley));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void task9() {
        Constructor constructor;
        try {
            constructor = Car.class.getConstructor();
            Car car = (Car) constructor.newInstance();
            constructor = DiningCar.class.getConstructor();
            DiningCar diningCar = (DiningCar) constructor.newInstance();

            constructor = PassengerCar.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            PassengerCar passengerCar = (PassengerCar) constructor.newInstance();

            constructor = PassengerCar.class.getDeclaredConstructor(boolean.class, String.class);
            constructor.setAccessible(true);
            PassengerCar passengerCar2 = (PassengerCar) constructor.newInstance(true, "jakis label");

            constructor = TankWagon.class.getConstructor();
            TankWagon tankWagon = (TankWagon) constructor.newInstance();

            constructor = TankWagon.class.getDeclaredConstructor(boolean.class);
            constructor.setAccessible(true);
            TankWagon tankWagon2 = (TankWagon) constructor.newInstance(true);

            lista.add(car);
            lista.add(diningCar);
            lista.add(passengerCar);
            lista.add(tankWagon);
            lista.add(passengerCar2);
            lista.add(tankWagon2);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void task10() {
        try {
            Constructor constructor = Train.class.getConstructor();
            Train train = (Train) constructor.newInstance();
            pociag = (Train) constructor.newInstance();
            System.out.println(train);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void task11() {
        Class c = Train.class;
        try {
            Field field = c.getDeclaredField("wagons");
            field.setAccessible(true);
            Train.Wagons wagons = (Train.Wagons) field.get(pociag);
            Method method = field.getType().getDeclaredMethod("addCar", Car.class);
            method.setAccessible(true);
            for (Car car : lista) {
                method.invoke(wagons, car);
            }
            Method tmp = field.getType().getDeclaredMethod("printWagons");
            tmp.setAccessible(true);
            System.out.println(tmp.invoke(wagons));
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void task12() {
//        Class c = Train.class;

        try {
            Field trolley = pociag.getClass().getDeclaredField("trolley");
            Field fuelTank = pociag.getClass().getDeclaredField("fuelTank");

            trolley.setAccessible(true);
            fuelTank.setAccessible(true);

            Constructor constructor;
            constructor = trolley.getType().getDeclaredConstructor();
            constructor.setAccessible(true);
//            (trolley.getType())constructor.newInstance();
            trolley.set(pociag, constructor.newInstance()); // chyba pociag, nie c


            constructor = fuelTank.getType().getConstructor(pociag.getClass(), boolean.class);
            constructor.setAccessible(true);
            fuelTank.set(pociag, constructor.newInstance(pociag, true));


            System.out.println(fuelTank.get(pociag));
            System.out.println(trolley.get(pociag));

        } catch (NoSuchFieldException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    public static void task13() {
        try {
            Field locomotive = pociag.getClass().getDeclaredField("locomotive");
            locomotive.setAccessible(true);
/*
            if (locomotive.get(pociag) == null) {
                Constructor<?> constructor = locomotive.getType().getDeclaredConstructor();
                locomotive.set(pociag, constructor.newInstance());
            }
*/

            Train.Locomotive lokomotywaPociogowa = (Train.Locomotive) locomotive.get(pociag);

            Field driver = lokomotywaPociogowa.getClass().getDeclaredField("driver");
            Field ignitionSwitch = lokomotywaPociogowa.getClass().getDeclaredField("ignitionSwitch");

            driver.setAccessible(true);
            ignitionSwitch.setAccessible(true);

            if (driver.get(lokomotywaPociogowa) == null) {
                Constructor constructor = driver.getType().getConstructor();
                constructor.setAccessible(true);
                driver.set(lokomotywaPociogowa, constructor.newInstance());
                System.out.println("driver stworzony");
            }

            if (ignitionSwitch.get(lokomotywaPociogowa) == null) { //zadziala jak bedzie public IgnitionSwitch
                Constructor constructor = ignitionSwitch.getType().getDeclaredConstructor(lokomotywaPociogowa.getClass(), boolean.class);

//                Class[] klasy = lokomotywaPociogowa.getClass().getDeclaredClasses();

//                System.out.println(Arrays.toString(klasy));
//                constructor = klasy[1].getDeclaredConstructor(lokomotywaPociogowa.getClass(), boolean.class);
                constructor.setAccessible(true);
//                ignitionSwitch.set(lokomotywaPociogowa, constructor.newInstance(lokomotywaPociogowa, true));


                ignitionSwitch.set(lokomotywaPociogowa, constructor.newInstance(lokomotywaPociogowa, true));
                System.out.println("ignitionswitch stworzony");
            }


/*
            Field[] fields = locomotive.getType().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(lokomotywaPociogowa) == null) {
                    Constructor constructor = field.getType().getDeclaredConstructor(lokomotywaPociogowa.getClass());
                    field.set(lokomotywaPociogowa, constructor.newInstance(lokomotywaPociogowa));
                }
            }
*/
        } catch (NoSuchFieldException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void task14() {
        try {
            Field locomotive = pociag.getClass().getDeclaredField("locomotive");
            locomotive.setAccessible(true);

//            Train.Locomotive lokomotywaPociogowa = (Train.Locomotive)locomotive.get(pociag);

            Field driver = locomotive.getType().getDeclaredField("driver");
            driver.setAccessible(true);

            Method drive = driver.getType().getDeclaredMethod("drive");
            drive.setAccessible(true);

            drive.invoke(driver.get(locomotive.get(pociag)));


        } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public static void task15() {

        for (Field field : Trolley.class.getDeclaredFields()) {
            if(field.isAnnotationPresent(TrolleyCompany.class)) {
                System.out.println(field.getAnnotation(TrolleyCompany.class).nazwaProducenta()
                       +field.getAnnotation(TrolleyCompany.class).wielkoscDrezyny());
            }
        }


    }
}

