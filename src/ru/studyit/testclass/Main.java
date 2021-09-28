package ru.studyit.testclass;

import ru.studyit.testclass.model.CGood;
import ru.studyit.testclass.model.COrder;
import ru.studyit.testclass.model.CUser;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class Main {
    //Сделал форматтер "глобальной" переменной, т.к. используется в двух методах - во вводе и выводе.
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final ArrayList<CUser> users = new ArrayList<>();
    private static final ArrayList<CGood> goods = new ArrayList<>();
    private static final ArrayList<COrder> orders = new ArrayList<>();

    /****************************************************************************************************
     * Метод считывает список пользователей из файла.                                                   *
     ***************************************************************************************************/
    private static void loadUsers()
    {
        File file = new File("users.txt");
        //Изменил тип данных с массива на коллекцию, чтобы не требовать указания в файле количества записей.
        boolean sex;
        String sDateOfBirth;
        LocalDate dateOfBirth;
        String login;
        UUID id;

        try (Scanner sc =  new Scanner(file))
        {
            //Здесь используется "регулярное выражение" - шаблон для строк.
            //В данном случае имеется в виду, что в качестве разделителей используются любое подряд идущее колиество (+)
            //любых исмволов из списка \r или \n или ;
            sc.useDelimiter(Pattern.compile("[\\r\\n;]+"));
            //Пока есть какие-то записи в файле...
            while(sc.hasNext())
            {
                //Проверка строки на равенство "1" вернёт true только в одном случае.
                id = UUID.fromString(sc.next());
                sex ="1".equals(sc.next());
                sDateOfBirth = sc.next();
                login = sc.next();
                //Преобразование даты из строки в специальный объект LocalDate.
                dateOfBirth = LocalDate.parse(sDateOfBirth, formatter);
                //Создание и добавление нового объекта в писок.
                users.add(new CUser(id, login, dateOfBirth, sex));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }
    /****************************************************************************************************
     * Метод считывает список товаров из файла.                                                         *
     ***************************************************************************************************/
    private static void loadGoods()
    {
        File file = new File("goods.txt");
        String name;
        UUID id;

        try (Scanner sc =  new Scanner(file))
        {
            //Здесь используется "регулярное выражение" - шаблон для строк.
            //В данном случае имеется в виду, что в качестве разделителей используются любое подряд идущее колиество (+)
            //любых исмволов из списка \r или \n или ;
            sc.useDelimiter(Pattern.compile("[\\r\\n;]+"));
            //Пока есть какие-то записи в файле...
            while(sc.hasNext())
            {
                id = UUID.fromString(sc.next());
                name = sc.next();

                //Создание и добавление нового объекта в писок.
                goods.add(new CGood(id, name));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }
    /****************************************************************************************************
     * Метод считывает список заказов из файла.                                                         *
     ***************************************************************************************************/
    private static void loadOrders()
    {
        File file = new File("orders.txt");
        UUID id;
        UUID userId;
        String temp;
        Scanner sc1;
        COrder order;
        try (Scanner sc =  new Scanner(file))
        {
            //Здесь используется "регулярное выражение" - шаблон для строк.
            //В данном случае имеется в виду, что в качестве разделителей используются любое подряд идущее колиество (+)
            //любых исмволов из списка \r или \n или ;
            sc.useDelimiter(Pattern.compile("[\\r\\n;]+"));
            //Пока есть какие-то записи в файле...
            while(sc.hasNext())
            {
                id = UUID.fromString(sc.next());
                userId = UUID.fromString(sc.next());
                order = new COrder(id, userId);

                temp = sc.next();

                sc1 = new Scanner(temp);
                sc1.useDelimiter(Pattern.compile("[\\r\\n.]+"));

                while(sc1.hasNext())
                {
                    order.getGoods().add(UUID.fromString(sc1.next()));
                }

                //Создание и добавление нового объекта в писок.
                orders.add(order);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }

    private static void load()
    {
        loadUsers();
        loadGoods();
        loadOrders();
    }
    /****************************************************************************************************
     * Вывод списка пользователей в консоль.                                                            *
     * @param users - список пользователей.                                                             *
     ***************************************************************************************************/
    private static void out(ArrayList<CUser> users)
    {
        System.out.println("|                 ID                   |                          Логин                     | Пол | Дата рождения | Возраст |");
        for (CUser user : users) {
            System.out.print("| ");
            System.out.print(user.getId());
            System.out.print(" | ");
            System.out.printf("%50s", user.getLogin());
            System.out.print(" | ");
            System.out.printf("%3s", user.getSex() ? "М" : "Ж");
            System.out.print(" | ");
            System.out.printf("%13s", user.getDateOfBirth().format(formatter));
            System.out.print(" | ");
            System.out.printf("%7s", user.getAge());
            System.out.print(" |");
            System.out.println();
        }
    }
    
    private static TreeMap<UUID, Integer> getPurchasedGoods()
    {
        TreeMap<UUID, Integer> purchasedGoods = new TreeMap<>();
        for (COrder order : orders)
        {
            for (UUID goodId : order.getGoods())
            {
                if (purchasedGoods.containsKey(goodId))
                {
                    purchasedGoods.put(goodId, purchasedGoods.get(goodId)+1);
                }
                else
                {
                    purchasedGoods.put(goodId, 1);
                }
            }

        }
        return purchasedGoods;
    }
    /****************************************************************************************************
     * Вывод списка пользователей в консоль.                                                            *
     * @param purchasedGoods - список купленных товаров.                                                *
     ***************************************************************************************************/
    private static void outPurchasedGoods(TreeMap<UUID, Integer> purchasedGoods)
    {
        UUID goodId;
        for (Map.Entry<UUID, Integer> entry : purchasedGoods.entrySet()) {
            goodId = entry.getKey();
            for (CGood good: goods)
            {
                if (good.getId().equals(goodId))
                {
                    System.out.print("| ");
                    System.out.printf("%50s", good.getName());
                    System.out.print(" | ");
                    System.out.printf("%7s", entry.getValue());
                    System.out.print(" |");
                    System.out.println();
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
        load();
        out(users);

        TreeMap<UUID, Integer> purchasedGoods = getPurchasedGoods();
        outPurchasedGoods(purchasedGoods);


    }
}
