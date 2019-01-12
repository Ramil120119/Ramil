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

class SimpleFrame2 extends JFrame {

    public SimpleFrame2() {
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
        for (int i = 0; i<Detali.size(); i++)
        {
            Detali.get(i).Display();
            System.out.println();
        }
        SimpleFrame2.MyComponent c = new SimpleFrame2.MyComponent();
        add(c);
        JButton btn = new JButton("Сортировка по весу по убыванию");
        JButton btn2 = new JButton("Сортировка по стоимости по возрастанию");
        c.add(btn);
        c.add(btn2);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {


            }
        });
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                repaint();
            }
        });
        setSize(1200,630);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    class MyComponent extends JPanel {


    }
}

public class Main {

    public static void main(String[] args) {
        new SimpleFrame2();
    }
}
