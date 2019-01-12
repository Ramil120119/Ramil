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
import java.util.Comparator;
import java.util.Random;

class Detal
{
    String material;
    String naznach;
    int ves;
    int stoim;
    Random rnd = new Random();
    public Detal(String mat, String naz)// конструктор для создания детали
    {
        material = mat;
        naznach = naz;
        ves = rnd.nextInt(100); // случайным образом задаем вес в диапазоне [0;100)
        stoim = rnd.nextInt(100);   // случайным образом задаем стоимость в диапазоне [0;100)
    }
    public void Display()   // функция для вывода информации о детали
    {
        System.out.print("Material: "+material);
        System.out.print("; Naznachenie: "+naznach);
        System.out.print("; Ves: "+ves);
        System.out.println("; Stoimost: "+stoim);
    }
}


class ComparatorVes implements Comparator<Detal> {  // компаратор для того чтобы сортировать веса по убыванию

    public int compare(Detal a, Detal b){

        if (a.ves>b.ves)    // тут получается если вес детали а больше веса детали б, то при сортировки деталь а должна идти раньше детали б
            return -1;
        if (a.ves<b.ves)    // тут деталь а должна идти позже детали б
            return 1; 
        return 0;   // если веса одинаковые то без разницы кто раньше кто позже
    }
}


class ComparatorStoim implements Comparator<Detal> {// компаратор для того чтобы сортировать стоимость по возрастанию

    public int compare(Detal a, Detal b){

        if (a.stoim>b.stoim)    // тут аналогично, только знак поменялся
            return 1;
        if (a.stoim<b.stoim)
            return -1;
        return 0;
    }
}

class SimpleFrame extends JFrame {// создаем класс, который наследуется от jframe чтобы создать форму, на которой будут кнопки

    public SimpleFrame() throws MyException {
        // создаем 5 деталей
        Detal d1 = new Detal("jelezo","naznachenie1");
        Detal d2 = new Detal("med","naznachenie2");
        Detal d3 = new Detal("stal","naznachenie3");
        Detal d4 = new Detal("derevo","naznachenie4");
        Detal d5 = new Detal("plastmas","naznachenie5");
        ArrayList<Detal> Detali = new ArrayList<>();    // создаем коллекцию arraylist, в который положим все детали
        Detali.add(d1);
        Detali.add(d2);
        Detali.add(d3);
        Detali.add(d4);
        Detali.add(d5);
        int granica = 70;// это можно менять, чем выше граница, тем больше шанс что исключение не вылетит
                            // стоимость деталей в диапазоне [0;100) задаются, то есть если граница >= 100 исключения не будет
        for (int i = 0; i<Detali.size(); i++)   // проверяем если стоимость детали больше границы выкидываем свое исключение
        {
            if (Detali.get(i).stoim>granica)
                throw new MyException();
        }
        // если исключение не вылетело, выводим информацию о деталях
        for (int i = 0; i<Detali.size(); i++)
        {
            Detali.get(i).Display();

        }
        SimpleFrame.MyComponent c = new SimpleFrame.MyComponent();// создаем компоненту, чтобы поместить на нее кнопки
        add(c);
        JButton btn = new JButton("Сортировка по весу по убыванию");    // это кнопка для сортировки по весу
        JButton btn2 = new JButton("Сортировка по стоимости по возрастанию");// это кнопка для сортировки по стоимости
        c.add(btn); // добавляем кнопки в компоненту
        c.add(btn2);
        btn.addActionListener(new ActionListener() {    // обрабатываем нажатие кнопки
            public void actionPerformed(ActionEvent event) {

                ComparatorVes cmp = new ComparatorVes();    // создаем компоратор для сортировки по весу 
                Detali.sort(cmp);// сортируем, подав в качестве аргумента компоратор
                System.out.println("Сортировка по весу по убыванию");// выводим что получилось после сортировки
                for (int i = 0; i<Detali.size(); i++)
                {
                    Detali.get(i).Display();
                }

            }
        });
        btn2.addActionListener(new ActionListener() {   // здесь аналогично обрабатываем нажатие кнопки для сортировки по стоимости
            public void actionPerformed(ActionEvent event) {

                ComparatorStoim cmp = new ComparatorStoim();
                Detali.sort(cmp);
                System.out.println("Сортировка по стоимости по возрастанию");
                for (int i = 0; i<Detali.size(); i++)
                {
                    Detali.get(i).Display();
                }
            }
        });
        setSize(1200,630);  // задаем размер формы
        setVisible(true);   // делаем ее видимой
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // добавляем свое правило закрытия программы
                                                        // то есть если нажмем на крестик, окошко должно закрыться

    }

    class MyComponent extends JPanel {  // создаем свою компоненту для удобности, чтобы кнопки нормально распологались наверху


    }
}
class MyException extends Exception // класс со своим исключением
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
// все, напиши коммент или в вк, получил или нет
