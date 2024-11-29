package dev.plotnikov.page.service;

import dev.plotnikov.page.entity.payload.*;
import dev.plotnikov.page.exception.MigrationFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CreatorService {

    private final MigrationRestService service;

    public void createModels(Path path) throws MigrationFailedException {

        List<ShopPayload> shopList = new ArrayList<>();
        List<ElectroTypePayload> electroTypeList = new ArrayList<>();
        List<ElectroShopPayload> electroShopList = new ArrayList<>();
        List<PositionTypePayload> positionTypeList = new ArrayList<>();
        List<PurchaseTypePayload> purchaseTypeList = new ArrayList<>();
        List<EmployeePayload> employeeList = new ArrayList<>();
        List<ElectroItemPayload> electroItemList = new ArrayList<>();
        List<PurchasePayload> purchaseList = new ArrayList<>();
        List<ElectroTypeEmployeePayload> electroTypeEmployeeList = new ArrayList<>();

        List<File> all = new ArrayList<>();
        addTree(new File(path.toString()), all);


        for (File file : all) {

            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Cp1251"))) {
                boolean skipFirstLine = true;
                String line;
                while ((line = br.readLine()) != null) {
                    if (skipFirstLine) {
                        skipFirstLine = false;
                        continue;
                    }
                    String name = file.getName().replace(".csv", "");
                    String[] values = line.split(";");

                    if (name.equalsIgnoreCase("ElectroType")) {
                        electroTypeList.add(new ElectroTypePayload(Long.parseLong(values[0]), values[1]));
                    }

                    if (name.equalsIgnoreCase("Employee")) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        LocalDate birth = LocalDate.parse(values[4], formatter);

                        employeeList.add(new EmployeePayload(Long.parseLong(values[0]), values[1], values[2], values[3], birth, Long.parseLong(values[5]), Long.parseLong(values[6]), Boolean.valueOf(values[7].equals("1") ? "true" : "false")));
                    }

                    if (name.equalsIgnoreCase("PositionType")) {
                        positionTypeList.add(new PositionTypePayload(Long.parseLong(values[0]), values[1]));
                    }

                    if (name.equalsIgnoreCase("Purchase")) {
                        long count = ThreadLocalRandom.current().nextLong(1, 20);
                        Long amount = count * ThreadLocalRandom.current().nextInt(10000, 99000);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                        LocalDateTime dateTime = LocalDateTime.parse(values[3], formatter);

                        purchaseList.add(new PurchasePayload(Long.parseLong(values[0]), amount, count, Long.parseLong(values[1]), Long.parseLong(values[2]), dateTime, Long.parseLong(values[4]), Long.parseLong(values[5])));
                    }

                    if (name.equalsIgnoreCase("PurchaseType")) {
                        purchaseTypeList.add(new PurchaseTypePayload(Long.parseLong(values[0]), values[1]));
                    }

                    if (name.equalsIgnoreCase("PositionType")) {
                        positionTypeList.add(new PositionTypePayload(Long.parseLong(values[0]), values[1]));
                    }

                    if (name.equalsIgnoreCase("Shop")) {
                        shopList.add(new ShopPayload(Long.parseLong(values[0]), values[1], values[2]));
                    }

                    if (name.equalsIgnoreCase("ElectroEmployee")) {
                        electroTypeEmployeeList.add(new ElectroTypeEmployeePayload(Long.parseLong(values[0]), Long.parseLong(values[1])));
                    }

                    if (name.equalsIgnoreCase("ElectroItem")) {
                        electroItemList.add(new ElectroItemPayload(Long.parseLong(values[0]), values[1], Long.parseLong(values[2]), Long.parseLong(values[3]), Integer.parseInt(values[4]), Boolean.valueOf(values[5].equals("1") ? "true" : "false"), values[6]));
                    }

                    if (name.equalsIgnoreCase("ElectroShop")) {
                        electroShopList.add(new ElectroShopPayload(Long.parseLong(values[0]), Long.parseLong(values[1]), Integer.parseInt(values[2])));
                    }
                }
            } catch (IOException e) {
               throw new MigrationFailedException("Ошибка миграции");
            }
        }

          ResponseEntity<Void> responseShops = service.getMigration(shopList, "shops");
        if (responseShops.getStatusCode().is2xxSuccessful()) {
            ResponseEntity<Void> responseElectroType = service.getMigration(electroTypeList, "types");
            if (responseElectroType.getStatusCode().is2xxSuccessful()) {
                ResponseEntity<Void> responsePositionType = service.getMigration(positionTypeList, "positions");
                if (responsePositionType.getStatusCode().is2xxSuccessful()) {
                    ResponseEntity<Void> responsePurchaseType = service.getMigration(purchaseTypeList, "pays");
                    if (responsePurchaseType.getStatusCode().is2xxSuccessful()) {
                        ResponseEntity<Void> responseEmployees = service.getMigration(employeeList, "employees");
                        if (responseEmployees.getStatusCode().is2xxSuccessful()) {
                            ResponseEntity<Void> responseProducts = service.getMigration(electroItemList, "products");
                            if (responseProducts.getStatusCode().is2xxSuccessful()) {
                                ResponseEntity<Void> responseElectroShops = service.getMigration(electroShopList, "electro-shops");
                                if (responseElectroShops.getStatusCode().is2xxSuccessful()) {
                                    ResponseEntity<Void> responseTypeEmployee = service.getMigration(electroTypeEmployeeList, "electrotype-employees");
                                    if (responseTypeEmployee.getStatusCode().is2xxSuccessful()) {
                                        service.getMigration(purchaseList, "purchases");
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    private void addTree(File file, Collection<File> all) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                all.add(child);
                addTree(child, all);
            }
        }
    }

}


