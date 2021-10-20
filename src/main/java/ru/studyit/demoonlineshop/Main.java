package ru.studyit.demoonlineshop;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xwpf.usermodel.*;
import ru.studyit.demoonlineshop.config.CConfigHibernate;
import ru.studyit.demoonlineshop.dao.CDAOGoods;
import ru.studyit.demoonlineshop.dao.CDAOOrders;
import ru.studyit.demoonlineshop.dao.CDAOUsers;
import ru.studyit.demoonlineshop.model.CGood;
import ru.studyit.demoonlineshop.model.COrder;
import ru.studyit.demoonlineshop.model.CUser;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {
    //Сделал форматтер "глобальной" переменной, т.к. используется в двух методах - во вводе и выводе.
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final ArrayList<CUser> users = new ArrayList<>();
    private static final ArrayList<CGood> goods = new ArrayList<>();
    private static final ArrayList<COrder> orders = new ArrayList<>();

    /****************************************************************************************************
     * Метод считывает список пользователей из файла.                                                   *
     ***************************************************************************************************/
//    private static void loadUsers()
//    {
//        File file = new File("users.txt");
//        //Изменил тип данных с массива на коллекцию, чтобы не требовать указания в файле количества записей.
//        boolean sex;
//        String sDateOfBirth;
//        LocalDate dateOfBirth;
//        String login;
//        UUID id;
//
//        try (Scanner sc =  new Scanner(file))
//        {
//            //Здесь используется "регулярное выражение" - шаблон для строк.
//            //В данном случае имеется в виду, что в качестве разделителей используются любое подряд идущее колиество (+)
//            //любых исмволов из списка \r или \n или ;
//            sc.useDelimiter(Pattern.compile("[\\r\\n;]+"));
//            //Пока есть какие-то записи в файле...
//            while(sc.hasNext())
//            {
//                //Проверка строки на равенство "1" вернёт true только в одном случае.
//                id = UUID.fromString(sc.next());
//                sex ="1".equals(sc.next());
//                sDateOfBirth = sc.next();
//                login = sc.next();
//                //Преобразование даты из строки в специальный объект LocalDate.
//                dateOfBirth = LocalDate.parse(sDateOfBirth, formatter);
//                //Создание и добавление нового объекта в писок.
//                users.add(new CUser(id, login, dateOfBirth, sex));
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//
//        }
//    }
    /****************************************************************************************************
     * Метод считывает список товаров из файла.                                                         *
     ***************************************************************************************************/
//    private static void loadGoods()
//    {
//        File file = new File("goods.txt");
//        String name;
//        UUID id;
//
//        try (Scanner sc =  new Scanner(file))
//        {
//            //Здесь используется "регулярное выражение" - шаблон для строк.
//            //В данном случае имеется в виду, что в качестве разделителей используются любое подряд идущее колиество (+)
//            //любых исмволов из списка \r или \n или ;
//            sc.useDelimiter(Pattern.compile("[\\r\\n;]+"));
//            //Пока есть какие-то записи в файле...
//            while(sc.hasNext())
//            {
//                id = UUID.fromString(sc.next());
//                name = sc.next();
//
//                //Создание и добавление нового объекта в писок.
//                goods.add(new CGood(id, name));
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//
//        }
//    }
    /****************************************************************************************************
     * Метод считывает список заказов из файла.                                                         *
     ***************************************************************************************************/
//    private static void loadOrders()
//    {
//        File file = new File("orders.txt");
//        UUID id;
//        UUID userId;
//        String temp;
//        Scanner sc1;
//        COrder order;
//        try (Scanner sc =  new Scanner(file))
//        {
//            //Здесь используется "регулярное выражение" - шаблон для строк.
//            //В данном случае имеется в виду, что в качестве разделителей используются любое подряд идущее колиество (+)
//            //любых исмволов из списка \r или \n или ;
//            sc.useDelimiter(Pattern.compile("[\\r\\n;]+"));
//            //Пока есть какие-то записи в файле...
//            while(sc.hasNext())
//            {
//                id = UUID.fromString(sc.next());
//                userId = UUID.fromString(sc.next());

//                order = new COrder(id, userId);
//
//                temp = sc.next();
//
//                sc1 = new Scanner(temp);
//                sc1.useDelimiter(Pattern.compile("[\\r\\n.]+"));
//
//                while(sc1.hasNext())
//                {
//                    order.getGoods().add(UUID.fromString(sc1.next()));
//                }
//
//                //Создание и добавление нового объекта в писок.
//                orders.add(order);
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//
//        }
//    }
//    private static void loadUsersExcel()
//    {
//        File file = new File("input_data.xlsx");
//        try (XSSFWorkbook wb = new XSSFWorkbook(file))
//        {
//            Sheet sheet = wb.getSheet("Пользователи");
//            int rows = sheet.getLastRowNum();
//            Row row;
//            Cell cell;
//            String temp, login;
//            UUID id;
//            boolean sex;
//            LocalDate dateOfBirth;
//            Double millis;
//
//
//            for (int i=1; i<=rows; i++) {
//                row = sheet.getRow(i);
//                if (row==null)
//                    continue;
//
//                cell = row.getCell(0);
//                temp = cell.getStringCellValue();
//                id = UUID.fromString(temp);
//
//                cell = row.getCell(3);
//                login = cell.getStringCellValue();
//
//                cell = row.getCell(1);
//                sex = cell.getNumericCellValue()==1;
//
//                cell = row.getCell(2);
//                //Преобразование даты из строки в специальный объект LocalDate.
//                dateOfBirth = cell.getDateCellValue().toInstant()
//                        .atZone(ZoneId.systemDefault())
//                        .toLocalDate();
//
//
//                users.add(new CUser(id, login, dateOfBirth, sex));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private static void load()
//    {
//        /*loadUsers();*/
//        loadGoods();
//        loadOrders();
//
//        loadUsersExcel();
//        //loadGoodsExcel();
//        //loadOrdersExcel();
//    }
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
    
//    private static TreeMap<UUID, Integer> getPurchasedGoods()
//    {
//        TreeMap<UUID, Integer> purchasedGoods = new TreeMap<>();
//        for (COrder order : orders)
//        {
//            for (UUID goodId : order.getGoods())
//            {
//                if (purchasedGoods.containsKey(goodId))
//                {
//                    purchasedGoods.put(goodId, purchasedGoods.get(goodId)+1);
//                }
//                else
//                {
//                    purchasedGoods.put(goodId, 1);
//                }
//            }
//
//        }
//        return purchasedGoods;
//    }
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
    private static void outUsersExcel(Workbook wb, ArrayList<CUser> users)
    {
        Sheet sheet = wb.createSheet("Пользователи");
        //Выводим шапку таблицы   Логин                     | Пол | Дата рождения | Возраст
        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("Логин");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("Пол");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("Дата рождения");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("Возраст");
        cell.setCellStyle(style);


        int nRow = 1;
        for (CUser user : users) {
            row = sheet.createRow(nRow);
            nRow++;

            cell = row.createCell(0);
            cell.setCellValue(user.getId().toString());
            cell = row.createCell(1);
            cell.setCellValue(user.getLogin());
            cell = row.createCell(2);
            cell.setCellValue(user.getSex() ? "М" : "Ж");
            cell = row.createCell(3);
            cell.setCellValue(user.getDateOfBirth().format(formatter));
            cell = row.createCell(4);
            cell.setCellValue(user.getAge());
        }

    }
    private static void createExcel(ArrayList<CUser> users)
    {
        Workbook wb = new XSSFWorkbook();
        outUsersExcel(wb, users);
        //outPurchasedGoodsExcel(wb);

        try (OutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
            wb.write(fileOut);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Нет прав на запись в эту папку");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private static void createWord(CUser user)
    {
        try (XWPFDocument doc = new XWPFDocument()) {
            XWPFParagraph p1 = doc.createParagraph();
            p1.setAlignment(ParagraphAlignment.CENTER);
            //p1.setVerticalAlignment(TextAlignment.TOP);

            XWPFRun r1 = p1.createRun();
            r1.setBold(true);
            r1.setText(user.getLogin());
            r1.setFontFamily("TimesNewRoman");
            r1.setUnderline(UnderlinePatterns.SINGLE);
            r1.setFontSize(16);
            //r1.setTextPosition(100);

            /*XWPFParagraph p2 = doc.createParagraph();
            p2.setAlignment(ParagraphAlignment.LEFT);

            XWPFRun r2 = p2.createRun();
            r2.setText("Возраст:");
            r2.setItalic(true);
            r2.setFontFamily("TimesNewRoman");
            r2.setFontSize(14);
            XWPFRun r3 = p2.createRun();
            r3.setText(" "+user.getAge());
            r3.setFontFamily("TimesNewRoman");
            r3.setFontSize(14);*/

            //create table
            XWPFTable table = doc.createTable();

            //create first row
            XWPFTableRow tableRowOne = table.getRow(0);
            tableRowOne.getCell(0).setText("Возраст:");
            tableRowOne.addNewTableCell().setText(""+user.getAge());

            XWPFTableRow row2 = table.createRow();
            row2.getCell(0).setText("Дата рождения:");
            row2.getCell(1).setText(""+user.getDateOfBirth().format(formatter));

            try (FileOutputStream out = new FileOutputStream("user_report.docx")) {
                doc.write(out);
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден: user_report.docx");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        load();
//        out(users);


//        createExcel(users);
//
//        TreeMap<UUID, Integer> purchasedGoods = getPurchasedGoods();
//        outPurchasedGoods(purchasedGoods);
//
//        createWord(users.get(0));

        CDAOUsers daoUsers = new CDAOUsers(CConfigHibernate.getSessionFactory());
        CDAOOrders daoOrders = new CDAOOrders(CConfigHibernate.getSessionFactory());
        CDAOGoods daoGoods = new CDAOGoods(CConfigHibernate.getSessionFactory());

        //Сохранение списка пользователей в БД.
        /*CUser user1 = new CUser();
        user1.setSex(true);
        user1.setLogin("This is login");
        user1.setDateOfBirth(LocalDate.now());
        daoUsers.save(user);
        */
//        for (CUser user:users)
//        {
//            daoUsers.save(user);
//        }
        //daoUsers.saveList( users);

        //Загрузка списка пользователей из БД.
        //List<CUser> users1 = daoUsers.getAll();

        //Получение пользователя по ID
        //CUser user = daoUsers.get(UUID.fromString("80c99edd-46bc-4669-9fb3-848755883d82"));
        //Удаление пользователя из БД.
        //daoUsers.delete(user);

        /*List<COrder> orders = daoOrders.getAllWithGoods();
        if (orders.size()>0)
        {
            //CUser owner = orders.get(0).getOwner();
            //List<COrder> ordersOfFirstOwner = owner.getOrders();

            List<CGood> goods1 = orders.get(0).getGoods();
            int x = 0;
        }*/
        /*CUser user = daoUsers.get("petrova");
        List<CGood> goods1 = daoGoods.getAll();


        COrder order = new COrder();
        order.setOwner(user);
        order.getGoods().add(goods1.get(0));
        order.getGoods().add(goods1.get(3));

        daoOrders.save(order);
        */
        CUser user = daoUsers.get("petrova");
        List<CGood> goodsOfPetrova = daoGoods.getAllByUser(user);


        return;

    }
}
