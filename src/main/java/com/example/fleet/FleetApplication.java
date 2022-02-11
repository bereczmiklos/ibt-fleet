package com.example.fleet;

import com.example.fleet.model.*;
import com.example.fleet.repository.BrandRepository;
import com.example.fleet.repository.CarRepository;
import com.example.fleet.repository.ClientRepository;
import com.example.fleet.repository.CustomerRepository;
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
	public CommandLineRunner demo(CustomerRepository repository, CarRepository carRepository,
								  BrandRepository brandRepository) {
		return (args) -> {
			log.info("---------------------------START---------------------------");
			List<Car> fordList = new ArrayList<>();
			List<Car> mercedesList = new ArrayList<>();
			List<Car> toyotaList = new ArrayList<>();
			List<Car> volkswagenList = new ArrayList<>();
			log.info("1.BLANK LIST CREATED");

			Brand ford = new Brand(fordList,BrandName.FORD);
			Brand mercedes = new Brand(mercedesList,BrandName.MERCEDES);
			Brand toyota = new Brand(toyotaList,BrandName.TOYOTA);
			Brand volkswagen = new Brand(volkswagenList,BrandName.VOLKSWAGEN);
			log.info("2.BRANDS CREATED");

			Car f1 = new Car(ford, CarCategory.CAR, "Fiesta", "AAA-123", CarFuelType.PETROL,
					149990);
			Car f2 = new Car(ford, CarCategory.CAR, "Puma", "AAA-234", CarFuelType.PETROL,
					174990);
			Car f3 = new Car(ford, CarCategory.CAR, "Mondeo", "AAA-567", CarFuelType.DIESEL,
					274990);
			Car f4 = new Car(ford, CarCategory.VAN, "Transit", "AAA-789", CarFuelType.DIESEL,
					219990);
			Car f5 = new Car(ford, CarCategory.MINIBUS, "Tourneo", "AAA-101", CarFuelType.DIESEL,
					259900);

			fordList.add(f1);
			fordList.add(f2);
			fordList.add(f3);
			fordList.add(f4);
			fordList.add(f5);

			Car m1 = new Car(mercedes, CarCategory.CAR, "C160", "BBB-123", CarFuelType.PETROL,
					319990);
			Car m2 = new Car(mercedes, CarCategory.CAR, "E200D", "BBB-456", CarFuelType.DIESEL,
					379990);

			mercedesList.add(m1);
			mercedesList.add(m2);

			Car t1 = new Car(toyota, CarCategory.CAR, "Yaris", "CCC-123", CarFuelType.ELECTRIC,
					129990);
			Car t2 = new Car(toyota, CarCategory.CAR, "Avensis", "CCC-456", CarFuelType.DIESEL,
					149990);
			Car t3 = new Car(toyota, CarCategory.MINIBUS, "HiAce", "CCC-789", CarFuelType.DIESEL,
					199900);

			toyotaList.add(t1);
			toyotaList.add(t2);
			toyotaList.add(t3);

			Car v1 = new Car(volkswagen, CarCategory.CAR, "Passat", "DDD-123", CarFuelType.DIESEL,
					199000);
			Car v2 = new Car(volkswagen, CarCategory.VAN, "Caddy", "DDD-456", CarFuelType.PETROL,
					239000);
			Car v3 = new Car(volkswagen, CarCategory.VAN, "Transporter-E", "DDD-789",
					CarFuelType.ELECTRIC,239000);

			volkswagenList.add(v1);
			volkswagenList.add(v2);
			volkswagenList.add(v3);

			brandRepository.save(ford);
			brandRepository.save(mercedes);
			brandRepository.save(toyota);
			brandRepository.save(volkswagen);
			log.info("3.BRANDS ADDED TO DB");

			for (Car c: fordList) { carRepository.save(c); ford.setCars(fordList); }
			for (Car c: mercedesList) { carRepository.save(c); mercedes.setCars(mercedesList);}
			for (Car c: toyotaList) { carRepository.save(c); toyota.setCars(toyotaList);}
			for (Car c: volkswagenList) { carRepository.save(c); volkswagen.setCars(volkswagenList);}
			log.info("4.CARS ADDED TO DB");

			log.info(">>>CARS");
			for (Car car : carRepository.findAll()) {
				log.info(car.toString());}

			log.info(">>>BRANDS");
			for (Brand brand : brandRepository.findAll()) {
				log.info(brand.toString());}

			log.info("-------------------------------END-------------------------------");
		};
	}

}
