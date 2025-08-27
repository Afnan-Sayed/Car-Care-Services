package com.ccs.Controllers.CustomerControllers;

import com.ccs.Models.ApiResponse;
import com.ccs.Models.Car;
import com.ccs.Models.Customer;
import com.ccs.Models.User;
import com.ccs.Repository.UserRepo;
import com.ccs.Services.CustomerService.CustomerCarService;
import com.ccs.Services.UserAndPubAuthService.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Ahmed
 Epic 3: Customer APIs (Customer manages their own cars + Admin can view customer details)
•	GET /customers/cars - Customer gets their own cars
•	POST /customers/cars - Customer adds their own car
•	PUT /customers/cars/{id} - Customer updates their own car
•	DELETE /customers/cars/{id} - Customer deletes their own car
•	GET /customers/{id} (admin retrieve details)
 */
@RestController
@RequestMapping("/customers")
public class CustomerCarsController {

    @Autowired
    private CustomerCarService customerCarService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo userRepository;

    private Long getCustomerIdFromToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid Authorization header");
        }

        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getId();
    }

    // get customer cars
    @GetMapping("/cars")
    public ResponseEntity<ApiResponse<List<Car>>> getCustomerCars(@RequestHeader("Authorization") String authHeader) {
        try {
            Long customerId = getCustomerIdFromToken(authHeader);
            List<Car> cars = customerCarService.getCustomerCars(customerId);
            return ResponseEntity.ok(ApiResponse.success("Your cars retrieved successfully", cars));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    // post customer car
    @PostMapping("/cars")
    public ResponseEntity<ApiResponse<Car>> addCustomerCar(@RequestHeader("Authorization") String authHeader,
            @RequestBody Car car) {
        try {
            Long customerId = getCustomerIdFromToken(authHeader);
            Car savedCar = customerCarService.addCustomerCar(customerId, car);
            return ResponseEntity.ok(ApiResponse.success("Car added successfully", savedCar));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    // edit car details
    @PutMapping("/cars/{id}")
    public ResponseEntity<ApiResponse<Car>> updateCar(@RequestHeader("Authorization") String authHeader,
            @PathVariable Long id, @RequestBody Car car) {
        try {
            Long customerId = getCustomerIdFromToken(authHeader);

            List<Car> customerCars = customerCarService.getCustomerCars(customerId);
            boolean carBelongsToCustomer = customerCars.stream().anyMatch(c -> c.getId().equals(id));

            if (!carBelongsToCustomer) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Car not found or doesn't belong to you"));
            }

            Car updatedCar = customerCarService.updateCar(id, car);
            return ResponseEntity.ok(ApiResponse.success("Car updated successfully", updatedCar));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    // delete customer car
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCar(@RequestHeader("Authorization") String authHeader,
            @PathVariable Long id) {
        try {
            Long customerId = getCustomerIdFromToken(authHeader);

            List<Car> customerCars = customerCarService.getCustomerCars(customerId);
            boolean carBelongsToCustomer = customerCars.stream().anyMatch(c -> c.getId().equals(id));

            if (!carBelongsToCustomer) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Car not found or doesn't belong to you"));
            }

            customerCarService.deleteCar(id);
            return ResponseEntity.ok(ApiResponse.success("Car deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    // GET /customers/{id} (admin retrieve details)
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> getCustomerDetails(@PathVariable Long id) {
        try {
            Customer customer = customerCarService.getCustomerDetails(id);
            return ResponseEntity.ok(ApiResponse.success("Customer details retrieved successfully", customer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}
