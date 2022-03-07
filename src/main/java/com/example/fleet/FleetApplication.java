package com.example.fleet;

import com.example.fleet.model.*;
import com.example.fleet.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FleetApplication {
	private static final Logger log = LoggerFactory.getLogger(FleetApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FleetApplication.class);
	}

	@Bean
	public CommandLineRunner demo(CarRepository carRepository,
								  BrandRepository brandRepository,
								  ClientRepository clientRepository,
								  RentalRepository rentalRepository,
								  RentedCarRepository rentedCarRepository) {
		return (args) -> {

			// init only if DB not yet filled with test data:
			if (clientRepository.findAll().isEmpty()) {

				List<Car> fordList = new ArrayList<>();
				List<Car> mercedesList = new ArrayList<>();
				List<Car> toyotaList = new ArrayList<>();
				List<Car> volkswagenList = new ArrayList<>();

				//BRAND TABLE:
				Brand ford = new Brand(fordList,BrandName.FORD);
				Brand mercedes = new Brand(mercedesList,BrandName.MERCEDES);
				Brand toyota = new Brand(toyotaList,BrandName.TOYOTA);
				Brand volkswagen = new Brand(volkswagenList,BrandName.VOLKSWAGEN);

				//CAR TABLE:
				Car f1 = new Car(ford, CarCategory.CAR, "Fiesta", "AAA-123", CarFuelType.PETROL,
						149990 / 30);
				Car f2 = new Car(ford, CarCategory.CAR, "Puma", "AAA-234", CarFuelType.PETROL,
						174990 / 30);
				Car f3 = new Car(ford, CarCategory.CAR, "Mondeo", "AAA-567", CarFuelType.DIESEL,
						274990 / 30);
				Car f4 = new Car(ford, CarCategory.VAN, "Transit", "AAA-789", CarFuelType.DIESEL,
						219990 / 30);
				Car f5 = new Car(ford, CarCategory.MINIBUS, "Tourneo", "AAA-101", CarFuelType.DIESEL,
						259900 / 30);

				fordList.add(f1);
				fordList.add(f2);
				fordList.add(f3);
				fordList.add(f4);
				fordList.add(f5);

				Car m1 = new Car(mercedes, CarCategory.CAR, "C160", "BBB-123", CarFuelType.PETROL,
						319990 / 30);
				Car m2 = new Car(mercedes, CarCategory.CAR, "E200D", "BBB-456", CarFuelType.DIESEL,
						379990 / 30);

				mercedesList.add(m1);
				mercedesList.add(m2);

				Car t1 = new Car(toyota, CarCategory.CAR, "Yaris", "CCC-123", CarFuelType.ELECTRIC,
						129990 / 30);
				Car t2 = new Car(toyota, CarCategory.CAR, "Avensis", "CCC-456", CarFuelType.DIESEL,
						149990 / 30);
				Car t3 = new Car(toyota, CarCategory.MINIBUS, "HiAce", "CCC-789", CarFuelType.DIESEL,
						199900 / 30);

				toyotaList.add(t1);
				toyotaList.add(t2);
				toyotaList.add(t3);

				Car v1 = new Car(volkswagen, CarCategory.CAR, "Passat", "DDD-123", CarFuelType.DIESEL,
						199000 / 30);
				Car v2 = new Car(volkswagen, CarCategory.VAN, "Caddy", "DDD-456", CarFuelType.PETROL,
						239000 / 30);
				Car v3 = new Car(volkswagen, CarCategory.VAN, "Transporter-E", "DDD-789",
						CarFuelType.ELECTRIC,239000 / 30);

				volkswagenList.add(v1);
				volkswagenList.add(v2);
				volkswagenList.add(v3);

				//CLIENT DB
				Client c1 = new Client("Kovács és társa bt","kovacsbt@kovacsbt.hu");
				Client c2 = new Client("Pizza Hut", "pizzahut@ph.hu");
				Client c3 = new Client("Food Panda", "foodpanda@gmail.com");

				//SAVE TO DB
				brandRepository.save(ford);
				brandRepository.save(mercedes);
				brandRepository.save(toyota);
				brandRepository.save(volkswagen);

				for (Car c: fordList) { carRepository.save(c); }
				for (Car c: mercedesList) { carRepository.save(c); }
				for (Car c: toyotaList) { carRepository.save(c); }
				for (Car c: volkswagenList) { carRepository.save(c); }

				clientRepository.save(c1);
				clientRepository.save(c2);
				clientRepository.save(c3);
			}

			log.info("------------------------------START------------------------------");
			log.info("db created: " +
					"{brands(" + brandRepository.findAll().size() + ")," +
					"cars(" + carRepository.findAll().size() + ")," +
					"client(" + clientRepository.findAll().size() + ")," +
					"rental(" + rentalRepository.findAll().size() + ")," +
					"rentedcar(" + rentedCarRepository.findAll().size() + ")}");
		};
	}

}
