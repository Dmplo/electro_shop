package dev.plotnikov.rest.service;

import dev.plotnikov.rest.entity.*;
import dev.plotnikov.rest.repository.*;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class MigrationService {

    private final PurchaseRepository purchaseRepository;
    private final ElectroTypeEmployeeRepository electroTypeEmployeeRepository;
    private final ElectroShopRepository electroShopRepository;
    private final ElectroItemRepository electroItemRepository;
    private final EmployeeRepository employeeRepository;
    private final ElectroTypeRepository electroTypeRepository;
    private final PositionTypeRepository positionTypeRepository;
    private final PurchaseTypeRepository purchaseTypeRepository;
    private final ShopRepository shopRepository;

    @Transactional
    public void getRandomMigration() {

        List<String> lastNameListMale = List.of("Иванов", "Кузнецов", "Смирнов", "Попов", "Петров", "Васильев", "Новиков", "Соколов", "Каримов", "Волков", "Семенов", "Павлов", "Козлов");
        List<String> firstNameListMale = List.of("Александр", "Дмитрий", "Максим", "Сергей", "Андрей", "Алексей", "Артём", "Кирилл", "Никита", "Матвей", "Роман", "Егор", "Иван");
        List<String> patronymicListMale = List.of("Александрович", "Дмитриевич", "Максимович", "Сергеевич", "Андреевич", "Алексеевич", "Артёмович", "Кириллович", "Романович", "Егорович", "Иванович");

        List<String> lastNameListFemale = List.of("Иванова", "Кузнецова", "Смирнова", "Попова", "Петрова", "Васильева", "Новикова", "Соколова", "Каримова", "Волкова", "Семенова", "Павлова", "Козлова");
        List<String> firstNameListFemale = List.of("Александра", "Дарья", "Мария", "София", "Анна", "Анжелика", "Анастасия", "Екатерина", "Нина", "Мадина", "Маргарита", "Елена", "Ирина");
        List<String> patronymicListFemale = List.of("Александровна", "Дмитриевна", "Максимовна", "Сергеевна", "Андреевна", "Алексеевна", "Артёмовна", "Кирилловна", "Романовна", "Егоровна", "Ивановна");

        List<Shop> shopList = new ArrayList<>();
        List<ElectroType> electroTypeList = new ArrayList<>();
        List<PositionType> positionTypeList = new ArrayList<>();
        List<PurchaseType> purchaseTypeList = new ArrayList<>();
        List<Employee> employeeList = new ArrayList<>();
        List<ElectroItem> electroItemList = new ArrayList<>();

        Shop shop1 = shopRepository.save(new Shop(null, "Магазин-склад", "ул. Фабричная, д.2"));
        shopList.add(shop1);
        Shop shop2 = shopRepository.save(new Shop(null, "Филиал на Красном", "ул. Красный проспект, д.13"));
        shopList.add(shop2);
        Shop shop3 = shopRepository.save(new Shop(null, "Филиал на Ленина", "ул. Ленина, д.44"));
        shopList.add(shop3);
        Shop shop4 = shopRepository.save(new Shop(null, "Филиал на Кирова", "ул. Кирова, д.10"));
        shopList.add(shop4);
        Shop shop5 = shopRepository.save(new Shop(null, "Филиал на Мичурина", "ул. Мичурина, д.12"));
        shopList.add(shop5);
        Shop shop6 = shopRepository.save(new Shop(null, "Филиал на Маркса", "площадь Карла Маркса, д. 68"));
        shopList.add(shop6);

        ElectroType phoneType = electroTypeRepository.save(new ElectroType(null, "Смартфоны"));
        electroTypeList.add(phoneType);
        ElectroType noteType = electroTypeRepository.save(new ElectroType(null, "Ноутбуки"));
        electroTypeList.add(noteType);
        ElectroType smartWatchType = electroTypeRepository.save(new ElectroType(null, "Смарт-часы"));
        electroTypeList.add(smartWatchType);
        ElectroType freezeType = electroTypeRepository.save(new ElectroType(null, "Холодильники"));
        electroTypeList.add(freezeType);

        PositionType positionType1 = positionTypeRepository.save(new PositionType(null, "Стажер"));
        positionTypeList.add(positionType1);
        PositionType positionType2 = positionTypeRepository.save(new PositionType(null, "Младший продавец-консультант"));
        positionTypeList.add(positionType2);
        PositionType positionType3 = positionTypeRepository.save(new PositionType(null, "Продавец-консультант"));
        positionTypeList.add(positionType3);
        PositionType positionType4 = positionTypeRepository.save(new PositionType(null, "Старший продавец-консультант"));
        positionTypeList.add(positionType4);
        PositionType positionType5 = positionTypeRepository.save(new PositionType(null, "Ведущий продавец"));
        positionTypeList.add(positionType5);

        PurchaseType purchaseType1 = purchaseTypeRepository.save(new PurchaseType(null, "Наличный"));
        purchaseTypeList.add(purchaseType1);
        PurchaseType purchaseType2 = purchaseTypeRepository.save(new PurchaseType(null, "Безналичный"));
        purchaseTypeList.add(purchaseType2);
        PurchaseType purchaseType3 = purchaseTypeRepository.save(new PurchaseType(null, "Кредит"));
        purchaseTypeList.add(purchaseType3);
        PurchaseType purchaseType4 = purchaseTypeRepository.save(new PurchaseType(null, "Рассрочка"));
        purchaseTypeList.add(purchaseType4);

        for (int i = 0; i < 20; i++) {
            Boolean gender = ThreadLocalRandom.current().nextBoolean();
            String lastName, firstName, patronymic;
            if (gender) {
                lastName = getRandom(lastNameListMale);
                firstName = getRandom(firstNameListMale);
                patronymic = getRandom(patronymicListMale);
            } else {
                lastName = getRandom(lastNameListFemale);
                firstName = getRandom(firstNameListFemale);
                patronymic = getRandom(patronymicListFemale);
            }
            Employee employee = employeeRepository.save(new Employee(null, lastName, firstName, patronymic, generateRandomDate(), getRandom(positionTypeList).getId(), getRandom(shopList).getId(), gender));
            employeeList.add(employee);
        }
        int countPhone1 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem phone1 = electroItemRepository.save(new ElectroItem(null, "Смартфон Samsung Galaxy S23 256GB", phoneType.getId(), 65999L, countPhone1, countPhone1 <= 0, "хороший телефон"));
        electroItemList.add(phone1);
        int countPhone2 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem phone2 = electroItemRepository.save(new ElectroItem(null, "Смартфон Xiaomi Redmi 12 4/128GB", phoneType.getId(), 10499L, countPhone2, countPhone2 <= 0, "хороший телефон"));
        electroItemList.add(phone2);
        int countPhone3 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem phone3 = electroItemRepository.save(new ElectroItem(null, "Смартфон Apple iPhone 15 128GB", phoneType.getId(), 79999L, countPhone3, countPhone3 <= 0, "хороший телефон"));
        electroItemList.add(phone3);
        int countPhone4 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem phone4 = electroItemRepository.save(new ElectroItem(null, "Смартфон realme С61 6/128GB", phoneType.getId(), 9999L, countPhone4, countPhone4 <= 0, "хороший телефон"));
        electroItemList.add(phone4);
        int countPhone5 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem phone5 = electroItemRepository.save(new ElectroItem(null, "Смартфон Samsung Galaxy A15 LTE 4/128GB", phoneType.getId(), 12999L, countPhone5, countPhone5 <= 0, "хороший телефон"));
        electroItemList.add(phone5);
        int countPhone6 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem phone6 = electroItemRepository.save(new ElectroItem(null, "Смартфон HONOR H90 8/256GB Emerald", phoneType.getId(), 29999L, countPhone6, countPhone6 <= 0, "хороший телефон"));
        electroItemList.add(phone6);
        int countPhone7 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem phone7 = electroItemRepository.save(new ElectroItem(null, "Смартфон Apple iPhone 13 128GB", phoneType.getId(), 59999L, countPhone7, countPhone7 <= 0, "хороший телефон"));
        electroItemList.add(phone7);
        int countPhone8 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem phone8 = electroItemRepository.save(new ElectroItem(null, "Смартфон HUAWEI Pura 70 12/256GB", phoneType.getId(), 55999L, countPhone8, countPhone8 <= 0, "хороший телефон"));
        electroItemList.add(phone8);
        int countPhone9 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem phone9 = electroItemRepository.save(new ElectroItem(null, "Смартфон iQOO Z9 8/256GB", phoneType.getId(), 37499L, countPhone9, countPhone9 <= 0, "хороший телефон"));
        electroItemList.add(phone9);
        int countPhone10 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem phone10 = electroItemRepository.save(new ElectroItem(null, "Смартфон HONOR 200 8/256GB", phoneType.getId(), 39999L, countPhone10, countPhone10 <= 0, "хороший телефон"));
        electroItemList.add(phone10);

        ElectroItem note1 = electroItemRepository.save(new ElectroItem(null, "Ноутбук Infinix Inbook Y3 Max YL613", noteType.getId(), 39999L, 0, true, "хороший ноутбук"));
        electroItemList.add(note1);
        int countNote2 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem note2 = electroItemRepository.save(new ElectroItem(null, "Ноутбук HONOR MagicBook X16", noteType.getId(), 61999L, countNote2, countNote2 <= 0, "хороший ноутбук"));
        electroItemList.add(note2);
        int countNote3 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem note3 = electroItemRepository.save(new ElectroItem(null, "Ноутбук Machcreator Prime X16/16 Ryzen 5", noteType.getId(), 69999L, countNote3, countNote3 <= 0, "хороший ноутбук"));
        electroItemList.add(note3);
        int countNote4 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem note4 = electroItemRepository.save(new ElectroItem(null, "Ноутбук F+ FLAPTOP I-Series /15.6", noteType.getId(), 39999L, countNote4, countNote4 <= 0, "хороший ноутбук"));
        electroItemList.add(note4);
        int countNote5 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem note5 = electroItemRepository.save(new ElectroItem(null, "Ноутбук Machcreator One i3/15.6", noteType.getId(), 37999L, countNote5, countNote5 <= 0, "хороший ноутбук"));
        electroItemList.add(note5);
        int countNote6 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem note6 = electroItemRepository.save(new ElectroItem(null, "Ноутбук HONOR Magicbook 15/15.6", noteType.getId(), 49999L, countNote6, countNote6 <= 0, "хороший ноутбук"));
        electroItemList.add(note6);
        int countNote7 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem note7 = electroItemRepository.save(new ElectroItem(null, "Ноутбук Apple MacBook Air 13 M1", noteType.getId(), 84999L, countNote7, countNote7 <= 0, "хороший ноутбук"));
        electroItemList.add(note7);
        int countNote8 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem note8 = electroItemRepository.save(new ElectroItem(null, "Ноутбук HUAWEI MateBook D 16", noteType.getId(), 69999L, countNote8, countNote8 <= 0, "хороший ноутбук"));
        electroItemList.add(note8);
        int countNote9 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem note9 = electroItemRepository.save(new ElectroItem(null, "Ноутбук ASUS TUF A15", noteType.getId(), 113999L, countNote9, countNote9 <= 0, "хороший ноутбук"));
        electroItemList.add(note9);
        int countNote10 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem note10 = electroItemRepository.save(new ElectroItem(null, "Ноутбук ASUS TUF Gaming A17", noteType.getId(), 102999L, countNote10, countNote10 <= 0, "хороший ноутбук"));
        electroItemList.add(note10);

        int countWatch1 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem watch1 = electroItemRepository.save(new ElectroItem(null, "Смарт-часы Samsung Galaxy Watch7", smartWatchType.getId(), 24499L, countWatch1, countWatch1 <= 0, "хорошие смарт-часы"));
        electroItemList.add(watch1);
        int countWatch2 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem watch2 = electroItemRepository.save(new ElectroItem(null, "Смарт-часы Apple Watch Series 9", smartWatchType.getId(), 43999L, countWatch2, countWatch2 <= 0, "хорошие смарт-часы"));
        electroItemList.add(watch2);
        int countWatch3 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem watch3 = electroItemRepository.save(new ElectroItem(null, "Смарт-часы HUAWEI Watch GT 5 Pro", smartWatchType.getId(), 26999L, countWatch3, countWatch3 <= 0, "хорошие смарт-часы"));
        electroItemList.add(watch3);
        int countWatch4 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem watch4 = electroItemRepository.save(new ElectroItem(null, "Смарт-часы Samsung Galaxy Watch Ultra", smartWatchType.getId(), 18999L, countWatch4, countWatch4 <= 0, "хорошие смарт-часы"));
        electroItemList.add(watch4);
        int countWatch5 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem watch5 = electroItemRepository.save(new ElectroItem(null, "Смарт-часы HUAWEI Watch GT 5", smartWatchType.getId(), 37999L, countWatch5, countWatch5 <= 0, "хорошие смарт-часы"));
        electroItemList.add(watch5);
        int countWatch6 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem watch6 = electroItemRepository.save(new ElectroItem(null, "Смарт-часы Xiaomi Redmi Watch 5", smartWatchType.getId(), 3499L, countWatch6, countWatch6 <= 0, "хорошие смарт-часы"));
        electroItemList.add(watch6);
        int countWatch7 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem watch7 = electroItemRepository.save(new ElectroItem(null, "Смарт-часы HUAWEI FIT 2", smartWatchType.getId(), 9999L, countWatch7, countWatch7 <= 0, "хорошие смарт-часы"));
        electroItemList.add(watch7);
        int countWatch8 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem watch8 = electroItemRepository.save(new ElectroItem(null, "Смарт-часы Samsung Galaxy Watch6", smartWatchType.getId(), 18999L, countWatch8, countWatch8 <= 0, "хорошие смарт-часы"));
        electroItemList.add(watch8);
        int countWatch9 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem watch9 = electroItemRepository.save(new ElectroItem(null, "Смарт-часы Honor Choice Watch", smartWatchType.getId(), 6499L, countWatch9, countWatch9 <= 0, "хорошие смарт-часы"));
        electroItemList.add(watch9);
        int countWatch10 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem watch10 = electroItemRepository.save(new ElectroItem(null, "Смарт-часы Samsung Galaxy Watch7", smartWatchType.getId(), 21999L, countWatch10, countWatch10 <= 0, "хорошие смарт-часы"));
        electroItemList.add(watch10);

        int countFreeze1 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem freeze1 = electroItemRepository.save(new ElectroItem(null, "Холодильник Candy CCRN 6200", freezeType.getId(), 38999L, countFreeze1, countFreeze1 <= 0, "хороший холодильник"));
        electroItemList.add(freeze1);
        int countFreeze2 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem freeze2 = electroItemRepository.save(new ElectroItem(null, "Холодильник Candy CCRN 6180W", freezeType.getId(), 35999L, countFreeze2, countFreeze2 <= 0, "хороший холодильник"));
        electroItemList.add(freeze2);
        int countFreeze3 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem freeze3 = electroItemRepository.save(new ElectroItem(null, "Холодильник Gorenje NRK6202AW4", freezeType.getId(), 64999L, countFreeze3, countFreeze3 <= 0, "хороший холодильник"));
        electroItemList.add(freeze3);
        int countFreeze4 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem freeze4 = electroItemRepository.save(new ElectroItem(null, "Холодильник Haier C2F637CWMV", freezeType.getId(), 58999L, countFreeze4, countFreeze4 <= 0, "хороший холодильник"));
        electroItemList.add(freeze4);
        int countFreeze5 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem freeze5 = electroItemRepository.save(new ElectroItem(null, "Холодильник Gorenje NRK620EABXL4", freezeType.getId(), 65999L, countFreeze5, countFreeze5 <= 0, "хороший холодильник"));
        electroItemList.add(freeze5);
        int countFreeze6 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem freeze6 = electroItemRepository.save(new ElectroItem(null, "Холодильник Candy CCRN6200WD", freezeType.getId(), 42999L, countFreeze6, countFreeze6 <= 0, "хороший холодильник"));
        electroItemList.add(freeze6);
        int countFreeze7 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem freeze7 = electroItemRepository.save(new ElectroItem(null, "Холодильник Grundig GKPN669307FXD", freezeType.getId(), 71999L, countFreeze7, countFreeze7 <= 0, "хороший холодильник"));
        electroItemList.add(freeze7);
        int countFreeze8 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem freeze8 = electroItemRepository.save(new ElectroItem(null, "Холодильник Beko B3R0CNK312HW", freezeType.getId(), 36999L, countFreeze8, countFreeze8 <= 0, "хороший холодильник"));
        electroItemList.add(freeze8);
        int countFreeze9 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem freeze9 = electroItemRepository.save(new ElectroItem(null, "Холодильник Indesit ITS 4180 G", freezeType.getId(), 30999L, countFreeze9, countFreeze9 <= 0, "хороший холодильник"));
        electroItemList.add(freeze9);
        int countFreeze10 = ThreadLocalRandom.current().nextInt(0, 40);
        ElectroItem freeze10 = electroItemRepository.save(new ElectroItem(null, "Холодильник Hi HODN050047RW", freezeType.getId(), 7999L, countFreeze10, countFreeze10 <= 0, "хороший холодильник"));
        electroItemList.add(freeze10);


        Map<Long, Map<Long, ProductInfo>> shopMap = new HashMap<>();

        for (ElectroItem electroItem : electroItemList) {
            int count = electroItem.getCount();
            while (count > 0) {
                int randomCount = ThreadLocalRandom.current().nextInt(1, 10);
                if (count - randomCount < 0) {
                    randomCount = count;
                }
                Long shopId = getRandom(shopList).getId();
                if (shopMap.containsKey(shopId)) {
                    Map<Long, ProductInfo> map = shopMap.get(shopId);
                    if (map.containsKey(electroItem.getId())) {
                        ProductInfo info = map.get(electroItem.getId());
                        info.setCount(info.getCount() + randomCount);
                    } else {
                        map.put(electroItem.getId(), new ProductInfo(electroItem.getId(), randomCount));
                    }
                } else {
                    Map<Long, ProductInfo> itemMap = new HashMap<>();
                    itemMap.put(electroItem.getId(), new ProductInfo(electroItem.getId(), randomCount));
                    shopMap.put(shopId, itemMap);
                }
                count -= randomCount;
            }
        }

        for (Map.Entry<Long, Map<Long, ProductInfo>> entry : shopMap.entrySet()) {
            Long shopId = entry.getKey();
            Map<Long, ProductInfo> productInfoMap = entry.getValue();
            for (ProductInfo value : productInfoMap.values()) {
                electroShopRepository.save(new ElectroShop(shopId, value.getId(), value.getCount()));
            }
        }

        for (Employee employee : employeeList) {
            int rnd = ThreadLocalRandom.current().nextInt(1, 3);
            for (int i = 0; i <= rnd; i++) {
                electroTypeEmployeeRepository.save(new ElectroTypeEmployee(employee.getId(), getRandom(electroTypeList).getId()));
            }
        }

        for (int i = 1; i <= 60; i++) {
            Long shopId = getRandom(shopList).getId();
            ElectroShop electroShop = getRandom(electroShopRepository.findByShop(shopId));
            long count = ThreadLocalRandom.current().nextLong(1, 20);
            Long amount = count * ThreadLocalRandom.current().nextInt(10000, 99000);

            purchaseRepository.save(new Purchase(null, amount, count, electroShop.getElectroItemId(), getRandom(employeeList).getId(), generatePurchaseDate(), getRandom(purchaseTypeList).getId(), shopId));

        }

    }

    private <T> T getRandom(List<? extends T> items) {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, items.size());
        return items.get(randomIndex);
    }

    private LocalDateTime generatePurchaseDate() {
        long minDay = LocalDate.of(2023, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2024, 11, 29).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay).atStartOfDay();
    }


    private LocalDate generateRandomDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2010, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class ProductInfo {
    private Long id;
    private Integer count;
}
