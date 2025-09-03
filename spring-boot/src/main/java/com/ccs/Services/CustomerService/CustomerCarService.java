package com.ccs.Services.CustomerService;

import com.ccs.Models.Car;
import com.ccs.Models.Customer;
import com.ccs.Repository.CarRepo;
import com.ccs.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
Ahmed
Epic 3: Customer APIs
•	GET /customers/{id}/cars
•	POST /customers/{id}/cars
•	PUT /customers/cars/{id}
•	DELETE /customers/cars/{id}
•	GET /customers/{id} (admin retrieve details)
*/
@Service
public class CustomerCarService {

    @Autowired
    private CarRepo carRepository;

    @Autowired
    private CustomerRepo customerRepository;

    // get customer car
    public List<Car> getCustomerCars(Long customerId) {
        customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        return carRepository.findByCustomerId(customerId);
    }

    // post customer car
    public Car addCustomerCar(Long customerId, Car car) {
        customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        car.setCustomerId(customerId);
        return carRepository.save(car);
    }

    // edit customer car details
    public Car updateCar(Long carId, Car carRequest) {
        Car existingCar = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + carId));

        if (carRequest.getLicensePlate() != null) {
            existingCar.setLicensePlate(carRequest.getLicensePlate());
        }
        if (carRequest.getModelYear() != null) {
            existingCar.setModelYear(carRequest.getModelYear());
        }
        if (carRequest.getColor() != null) {
            existingCar.setColor(carRequest.getColor());
        }
        if (carRequest.getCarTypeId() != null) {
            existingCar.setCarTypeId(carRequest.getCarTypeId());
        }

        return carRepository.save(existingCar);
    }

    // delete customer car
    public void deleteCar(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + carId));

        carRepository.delete(car);
    }

    // GET /customers/{id} (admin retrieve details)
    public Customer getCustomerDetails(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
    }
}