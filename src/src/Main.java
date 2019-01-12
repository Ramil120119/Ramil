// на складе завода имеется некоторое количество деталей.
// Детали различаются материалом(сталь железл медь дерево пластмас алмений и тд)
// назначением (комплектующие инструкменты) весом и стоимостью. ВЕс и стоимость задаются случайным образом.
// Создать коллекцю деталей. Если сстоимость детали привышает заданное значит,то выбрсить свое созданное исключение.
// обеспечить сортировку по весу по убыванию и по стоимости по возрастанию нажатием соответствующих кнопок.
// Кнопки расположить в верхней части экрана.

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

class Detal
{
    String material;
    String naznach;
    int ves;
    int stoim;
    Random rnd = new Random();
    public Detal(String mat, String naz)
    {
        material = mat;
        naznach = naz;
        ves = rnd.nextInt(100);
        stoim = rnd.nextInt(100);
    }
    public void Display()
    {
        System.out.print("Material: "+material);
        System.out.print("; Naznachenie: "+naznach);
        System.out.print("; Ves: "+ves);
        System.out.println("; Stoimost: "+stoim);
    }
}


class ComparatorVes implements Comparator<Detal> {

    public int compare(Detal a, Detal b){

        if (a.ves>b.ves)
            return -1;
        if (a.ves<b.ves)
            return 1;
        return 0;
    }
}


class ComporatorStoim implements Comparator<Detal> {

    public int compare(Detal a, Detal b){

        if (a.stoim>b.stoim)
            return 1;
        if (a.stoim<b.stoim)
            return -1;
        return 0;
    }
}

class SimpleFrame extends JFrame {

    public SimpleFrame() throws MyException {
        Detal d1 = new Detal("jelezo","naznachenie1");
        Detal d2 = new Detal("med","naznachenie2");
        Detal d3 = new Detal("stal","naznachenie3");
        Detal d4 = new Detal("derevo","naznachenie4");
        Detal d5 = new Detal("plastmas","naznachenie5");
        ArrayList<Detal> Detali = new ArrayList<>();
        Detali.add(d1);
        Detali.add(d2);
        Detali.add(d3);
        Detali.add(d4);
        Detali.add(d5);
        int granica = 70;// это можно менять, чем выше граница, тем больше шанс что исключение не вылетит
                            // стоимость деталей в диапазоне [0;100) задаются, то есть если граница >= 100 исключения не будет
        for (int i = 0; i<Detali.size(); i++)
        {
            if (Detali.get(i).stoim>granica)
                throw new MyException();
        }
        for (int i = 0; i<Detali.size(); i++)
        {
            Detali.get(i).Display();

        }
        SimpleFrame.MyComponent c = new SimpleFrame.MyComponent();
        add(c);
        JButton btn = new JButton("Сортировка по весу по убыванию");
        JButton btn2 = new JButton("Сортировка по стоимости по возрастанию");
        c.add(btn);
        c.add(btn2);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                ComparatorVes cmp = new ComparatorVes();
                Collections.sort(Detali,cmp);
                System.out.println("Сортировка по весу по убыванию");
                for (int i = 0; i<Detali.size(); i++)
                {
                    Detali.get(i).Display();
                }

            }
        });
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                ComporatorStoim cmp = new ComporatorStoim();
                Collections.sort(Detali,cmp);
                System.out.println("Сортировка по стоимости по возрастанию");
                for (int i = 0; i<Detali.size(); i++)
                {
                    Detali.get(i).Display();
                }
            }
        });
        setSize(1200,630);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    class MyComponent extends JPanel {


    }
}
class MyException extends Exception
{
    public MyException()
    {
        System.out.println("Стоимость детали превышает заданное");
    }
}
public class Main {

    public static void main(String[] args) throws MyException {
        new SimpleFrame();
    }
}
// остались комменты, по функциональности все
// напиши коммент и закоммить если у тебя все работает, ну или напиши что не работает
// если вылетает исключение, увеличь границу до 100. Внизу этой строки напиши коммент и закоммить
